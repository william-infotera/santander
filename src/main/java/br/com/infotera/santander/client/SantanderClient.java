package br.com.infotera.santander.client;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSIntegrador;
import br.com.infotera.common.WSSessao;
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
import br.com.infotera.santander.model.DocumentGet;
import br.com.infotera.santander.model.RQRS.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SantanderClient {

    @Autowired
    private RESTClient restClient;

    public WSSessao abrirSessao(WSIntegrador integrador, AuthTokenRQ authToken) throws ErrorException {
        AuthTokenRS result = null;
        WSSessao sessao = null;

        try {
            result = restClient.sendReceive(integrador, authToken, HttpMethod.POST, "Auth", AuthTokenRS.class);
            sessao = new WSSessao(result.getTokenType(), integrador.getIdEmpresa(), result.getAccessToken(), new Date(), null);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex) {
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao realizar Autenticação", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return sessao;
    }

    public IntegrationCodeRS identificarTab(WSIntegrador integrador) throws ErrorException {
        String codigoIntegra = null;
        IntegrationCodeRS result = null;

        try {
            codigoIntegra = integrador.getDsCredencialList().get(3);
            result = restClient.sendReceive(integrador, null, HttpMethod.GET, codigoIntegra, IntegrationCodeRS.class);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex) {
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao retornar o Código TAB (IntegrationCode)", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return result;
    }

    public List<FinancedObjectRS> retornarFinancedObject(WSIntegrador integrador, Integer storeId) throws ErrorException {
        List<FinancedObjectRS> result = null;

        try {
            FinancedObjectRQ financedObjectRQ = new FinancedObjectRQ(storeId);
            result = restClient.sendReceive(integrador, financedObjectRQ, HttpMethod.GET, String.valueOf(financedObjectRQ.getId()), List.class);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex) {
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao retornar o Código TAB (IntegrationCode)", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return result;
    }

    public SubSegmentRS retornarSubSegment(WSIntegrador integrador, Integer storeId) throws ErrorException {
        SubSegmentRS result = null;

        try {
            SubSegmentRQ subSegmentRQ = new SubSegmentRQ(storeId);
            result = restClient.sendReceive(integrador, subSegmentRQ, HttpMethod.GET, String.valueOf(subSegmentRQ.getStoreId()), SubSegmentRS.class);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex) {
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao retornar o Código de SubSegmento (SubSegment)", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return result;
    }

    public InstallmentAmountRS retornarParcelas(WSIntegrador integrador, Integer storeID) throws ErrorException {
        InstallmentAmountRS result = null;

        try {
            InstallmentAmountRQ installmentAmountRQ = new InstallmentAmountRQ(storeID);
            result = restClient.sendReceive(integrador, installmentAmountRQ, HttpMethod.GET, String.valueOf(installmentAmountRQ.getStoreId()), InstallmentAmountRS.class);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex) {
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao obter a quantidade máxima de parcelas (StoreID)", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return result;
    }

    public List<ProductCodeRS> retornarProductCode(WSIntegrador integrador) throws ErrorException {
        List<ProductCodeRS> result = null;

        try {
            result = restClient.sendReceive(integrador, null, HttpMethod.GET, "AUT", List.class);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex) {
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao retornar o Código TAB (IntegrationCode)", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return result;
    }

    public List<DocumentGet> retornarTipoDocumento(WSIntegrador integrador) throws ErrorException {
        List<DocumentGet> result = null;

        try {
//            result = new ArrayList<>();
            result = restClient.sendReceive(integrador, null, HttpMethod.GET, "financed-objects", List.class);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex) {
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao obter a lista de Tipos de Documentos", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return result;
    }

    public List<DocumentGet> retornarCodOcupacao(WSIntegrador integrador) throws ErrorException {
        List<DocumentGet> result = null;

        try {
//            result = new ArrayList<>();
            result = restClient.sendReceive(integrador, null, HttpMethod.GET, "role", List.class);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex) {
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao obter a lista das Prafissões", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return result;
    }

    public List<DocumentGet> retornarCodEstadoCivil(WSIntegrador integrador) throws ErrorException {
        List<DocumentGet> result = null;

        try {
//            result = new ArrayList<>();
            result = restClient.sendReceive(integrador, null, HttpMethod.GET, "maritalStatus", List.class);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex) {
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao obter a lista de Estados Cívil", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return result;
    }

    public List<DocumentGet> retornarCodCidade(WSIntegrador integrador, String codEstado) throws ErrorException {
        List<DocumentGet> result = null;

        try {
//            result = new ArrayList<>();
            result = restClient.sendReceive(integrador, null, HttpMethod.GET, "naturalness" + "/" + codEstado, List.class);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex) {
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao obter a lista de Código de Municipios", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return result;
    }

    public PreAnaliseRS preAnalise(WSIntegrador integrador, PreAnaliseRQ preAnaliseRQ) throws ErrorException {
        PreAnaliseRS result = null;
        try {
            result = restClient.sendReceive(integrador, preAnaliseRQ, HttpMethod.POST, "preanalise", PreAnaliseRS.class);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex) {
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao chamar Pre-Análise", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return result;
    }

    public SimulacaoRS simulacao(WSIntegrador integrador, SimulacaoRQ simulacaoRQ) throws ErrorException {
        SimulacaoRS result = null;
        try {
            result = restClient.sendReceive(integrador, simulacaoRQ, HttpMethod.POST, "simulacao", SimulacaoRS.class);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex) {
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao chamar Simulação", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return result;
    }

    public PropostaRS proposta(WSIntegrador integrador, PropostaRQ propostaRQ) throws ErrorException {
        PropostaRS result = null;
        try {
            result = restClient.sendReceive(integrador, propostaRQ, HttpMethod.POST, "order" + "/",  PropostaRS.class);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex){
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao chamar o Capturar", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return result;
    }

    public SimulacaoRS order(WSIntegrador integrador, SimulacaoRQ simulacaoRQ) throws ErrorException {
        SimulacaoRS result = null;
        try {
            result = restClient.sendReceive(integrador, simulacaoRQ, HttpMethod.POST, "order", SimulacaoRS.class);
        } catch(ErrorException e) {
            throw e;
        } catch (Exception ex) {
            integrador.setDsMensagem(ex.getMessage());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao chamar a Simulação", WSMensagemErroEnum.ADI, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return result;
    }
}
