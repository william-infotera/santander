package br.com.infotera.santander.service;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSCliente;
import br.com.infotera.common.WSIntegrador;
import br.com.infotera.common.WSSessao;
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
import br.com.infotera.common.pagto.WSPagtoBoleto;
import br.com.infotera.common.pagto.WSPagtoCartaoDebito;
import br.com.infotera.common.pagto.WSPagtoForma;
import br.com.infotera.common.pagto.financiamento.WSFinanciamentoParcelas;
import br.com.infotera.common.pagto.financiamento.WSPagtoFinanciamento;
import br.com.infotera.common.pagto.financiamento.rqrs.WSPagtoFinanciamentoRQ;
import br.com.infotera.common.pagto.financiamento.rqrs.WSPagtoFinanciamentoRS;
import br.com.infotera.common.util.Utils;
import br.com.infotera.santander.client.SantanderClient;
import br.com.infotera.santander.model.*;
import br.com.infotera.santander.model.RQRS.*;
import br.com.infotera.santander.util.UtilsWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimularWS {

    @Autowired
    private SessaoWS sessaoWS;

    @Autowired
    private SantanderClient santanderClient;

    public WSPagtoFinanciamentoRS simular(WSPagtoFinanciamentoRQ pagtoFinanciamentoRQ) throws ErrorException {
        WSIntegrador integrador = null;
        if (pagtoFinanciamentoRQ.getIntegrador().getSessao() == null) {
            integrador = sessaoWS.abreSessao(pagtoFinanciamentoRQ.getIntegrador());
        } else {
            integrador = pagtoFinanciamentoRQ.getIntegrador();
        }

        WSPagtoFinanciamento pagtoFinanciamento = montaPagtoFinanciamento(integrador, pagtoFinanciamentoRQ);

        return null;
    }

    private WSPagtoFinanciamento montaPagtoFinanciamento(WSIntegrador integrador, WSPagtoFinanciamentoRQ pagtoFinanciamentoRQ) throws ErrorException {
        // Chamada para obter o Código da Loja (ID Loja) - Deve ser realizada nova chamada para obter novo código, caso seja feita nova análise
        IntegrationCodeRS integrationCode = santanderClient.identificarTab(integrador);

        // Obtem dados do cliente para requisição
        Customer customer = montaCustomer(integrador, pagtoFinanciamentoRQ);

        // Obtem dados da reserva e do conector
        Purchase purchase = montaPurchase(integrador, pagtoFinanciamentoRQ);

        // Retorna o objeto financiado referente a TAB (Cod Loja)
        List<FinancedObjectRS> financedObjectList = santanderClient.retornarFinancedObject(integrador, integrationCode.getTabId());

        // Retorna a quantidade máxima de parcelas para o estabelecimento (StoreId)
        InstallmentAmountRS installmentAmount = santanderClient.retornarParcelas(integrador, integrationCode.getTabId());

        // Retorna a quantidade máxima de parcelas para o estabelecimento (StoreId)
        List<ProductCodeRS> productCodeRSList = santanderClient.retornarProductCode(integrador);

        // Realiza a chamada a Pré-Analise com dados do cliente e insumo
        PreAnaliseRS preAnaliseRS = chamarPreAnalise(integrador, customer, purchase, integrationCode);

        // Realiza a chamada a Simulação com dados retornados pela Pré-Análise
        SimulacaoRS simulacaoRS = chamarSimulacao(integrador, integrationCode, customer, preAnaliseRS, pagtoFinanciamentoRQ);

        WSPagtoFinanciamento pagtoFinanciamento = null;
        if(preAnaliseRS != null) {
            try {
                List<WSFinanciamentoParcelas> parcelasList = new ArrayList<>();
                preAnaliseRS.getAtxResponse().getTerms().stream()
                        .map(t -> {
                            WSFinanciamentoParcelas parcela = new WSFinanciamentoParcelas();
                            parcela.setNrParcela(t.getTimes());
                            return parcela;
                        }).forEachOrdered((parcela) -> {
                            parcelasList.add(parcela);
                        });

                List<WSPagtoForma> formaPagtoList = new ArrayList<>();
                preAnaliseRS.getAtxResponse().getPaymentForms().stream()
                        .map(p -> {
                            WSPagtoForma formaPagto = null;
                            if(p.getIntegrationCode().equals("CA")){
                                WSPagtoBoleto boleto = new WSPagtoBoleto();
                                boleto.setCdBoleto(String.valueOf(p.getId()));
                                boleto.setCdEspecie(p.getDescription());
                                formaPagto = (WSPagtoForma) boleto;
                            } else if(p.getIntegrationCode().equals("DC")){
                                WSPagtoCartaoDebito debito = new WSPagtoCartaoDebito();
                                debito.setIdExterno(String.valueOf(p.getId()));
                                debito.setDsPagamento(p.getDescription());
                                formaPagto = (WSPagtoForma) debito;
                            }
                            return formaPagto;
                        }).forEachOrdered( forma -> {
                            formaPagtoList.add(forma);
                        });

                pagtoFinanciamento = new WSPagtoFinanciamento();
                pagtoFinanciamento.setCliente(pagtoFinanciamentoRQ.getPagtoFinanciamento().getCliente());
                pagtoFinanciamento.setDtPrimeiroVencimento(Utils.toDate(preAnaliseRS.getAtxResponse().getFirstInstallment().getRecommendedDate(), "yyyy-MM-dd"));
                pagtoFinanciamento.setVlEntrada(preAnaliseRS.getAtxResponse().getEntry().getRecommendedValue());
                pagtoFinanciamento.setParcelas(parcelasList);
                pagtoFinanciamento.setCdAutorizacao(preAnaliseRS.getId() + "#"
                                                    + preAnaliseRS.getAtxResponse().getPriceTable() + "#"
                                                    + preAnaliseRS.getAtxResponse().getFees().getTabFee() + "#"
                                                    + preAnaliseRS.getUuid().toString());

            } catch (Exception ex) {
                throw new ErrorException(integrador, SimularWS.class,
                        "montaPagtoFinanciamento", WSMensagemErroEnum.ADI, "Erro ao obter os valores para o PagtoFinanciamento.", WSIntegracaoStatusEnum.ATENCAO, ex);
            }
        }

        return pagtoFinanciamento;
    }

    private SimulacaoRS chamarSimulacao(WSIntegrador integrador, IntegrationCodeRS integrationCode, Customer customer, PreAnaliseRS preAnaliseRS, WSPagtoFinanciamentoRQ financiamentoRQ) throws ErrorException {
        SimulacaoRQ simulacaoRQ = null;

        try {
            Customer customerSimulation = new Customer();
            customerSimulation.setDocument(customer.getDocument());

            Store storeSimulation = new Store();
            storeSimulation.setTabId(integrationCode.getTabId());

            try {
                boolean tabfee = preAnaliseRS.getAtxResponse().getFees() != null; // Indicador de Pagamento TAB
                if(tabfee) {
                    Fee feeSimulation = new Fee();
                    feeSimulation.setTabFee(tabfee);
                    if(preAnaliseRS.getAtxResponse().getFees() != null && !preAnaliseRS.getAtxResponse().getFees().getTcExemption()) {
                        feeSimulation.setTcFee(false);
                    }
                    feeSimulation.setTcExemption(preAnaliseRS.getAtxResponse().getFees().getTcExemption()));
                    simulacaoRQ.setFees(feeSimulation);
                }

            } catch (Exception ex) {
                throw new ErrorException(integrador, SimularWS.class, "chamarSimulacao",
                        WSMensagemErroEnum.ADI, "Erro ao obter os parâmetros de Indicador de Pagamento TAB", WSIntegracaoStatusEnum.ATENCAO, ex);
            }

            try {
                Integer tpEntrada = 0;
                if (financiamentoRQ.getPagtoFinanciamento().getPagtoFormaEntrada() != null) {
                    if (financiamentoRQ.getPagtoFinanciamento().getPagtoFormaEntrada().isStCartaoDebito()) {
                        tpEntrada = 8; // cartao de debito
                    } else {
                        tpEntrada = 7; // outros meios de entrada(boleto,deposito etc)
                    }
                }

                Payment payment = new Payment();
//                payment.setPaymentFormId(preAnaliseRS.getAtxResponse().); PAGAMENTO SELECIONADO
            } catch (Exception ex) {
                throw new ErrorException(integrador, SimularWS.class, "chamarSimulacao",
                        WSMensagemErroEnum.ADI, "Erro ao obter os parâmetros para Identificação de Forma de Pagamento", WSIntegracaoStatusEnum.ATENCAO, ex);
            }

            simulacaoRQ.setTableNumber(preAnaliseRS.getAtxResponse().getPriceTable());
            simulacaoRQ.setUuid(preAnaliseRS.getUuid());
            simulacaoRQ.setCustomer(customerSimulation);
            //            simulacaoRQ.setPayment();
            simulacaoRQ.setStore(storeSimulation);
        } catch (Exception ex) {
            throw new ErrorException(integrador, SimularWS.class, "chamarSimulacao",
                    WSMensagemErroEnum.ADI, "Erro ao obter os parâmetros para realizar a Simulação.", WSIntegracaoStatusEnum.ATENCAO, ex);
        }

        return santanderClient.simulacao(integrador, simulacaoRQ);
    }

    private PreAnaliseRS chamarPreAnalise(WSIntegrador integrador, Customer customer, Purchase purchase, IntegrationCodeRS integrationCode) throws ErrorException {
        PreAnaliseRQ preAnaliseRQ = null;

        if(customer != null && purchase != null && integrationCode != null){
            preAnaliseRQ.setCustomer(customer);
            preAnaliseRQ.setPurchase(purchase);
            preAnaliseRQ.setUuid(integrationCode.getUuid().toString());
        }

        return santanderClient.preAnalise(integrador, preAnaliseRQ);
    }

    private Purchase montaPurchase(WSIntegrador integrador, WSPagtoFinanciamentoRQ pagtoFinanciamentoRQ) throws ErrorException {
        Purchase purchase = null;
        try {
            purchase.setFinancedObject("MO"); // PARA TESTES FOI SETADO VALOR FIXO AO INVÉS DE REALIZAR CHAMADA GET PARA OBTER O ID
            purchase.setEntryValue(pagtoFinanciamentoRQ.getPagtoFinanciamento().getVlEntrada()); // Valor de Entrada
            purchase.setInstallmentValue(pagtoFinanciamentoRQ.getPagtoFinanciamento().getVlDemaisParcelas()); // Valor das Parcelas
            purchase.setInstallmentQuantity(pagtoFinanciamentoRQ.getPagtoFinanciamento().getQtParcelas()); // EXISTE UMA CHAMADA GET A FIM DE OBTER O VALOR MÁXIMO DE PARCELAS
//            purchase.setTravelDate();  RETORNAR A DATA DE VIAGEM (OBRIGATÓRIO - TURISMO) - VERIFICAR COMO OBTER O WSRESERVA PARA COLETAR INFORMAÇÕES
            purchase.setSubsegment(0); // NÃO EXISTE NA DOC REFERENCIA PARA REALIZAR A CHAMADA GET PARA OBTER O CÓDIGO A SER INFORMADO - AVISAR SANTANDER
            purchase.setValue(pagtoFinanciamentoRQ.getPagtoFinanciamento().getVlFinanciado());
        } catch (Exception ex) {
            throw new ErrorException(integrador, SimularWS.class, "montaPurchase", WSMensagemErroEnum.ADI, "Erro ao obter os valores para realizar a Pré-Analise.", WSIntegracaoStatusEnum.ATENCAO, ex);
        }

        return purchase;
    }

    private Customer montaCustomer(WSIntegrador integrador, WSPagtoFinanciamentoRQ pagtoFinanciamentoRQ) throws ErrorException {
        WSCliente cliente = null;
        if(UtilsWS.verificarCliente(integrador, pagtoFinanciamentoRQ.getPagtoFinanciamento().getCliente())){
            cliente = pagtoFinanciamentoRQ.getPagtoFinanciamento().getCliente();
        }

        return new Customer(Utils.formatData(cliente.getDtNascimento(), "yyyy-MM-dd'T'HH:mm:ssz"),
                            cliente.getDocumento().getNrDocumento(),
                            new CellPhone(cliente.getTelefone().getNrDDD(), cliente.getTelefone().getNrTelefone()));
    }
}
