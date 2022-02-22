package br.com.infotera.santander.client;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSIntegrador;
import br.com.infotera.common.WSIntegradorLog;
import br.com.infotera.common.enumerator.WSAmbienteEnum;
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSIntegradorLogTipoEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
import br.com.infotera.common.util.LogWS;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import br.com.infotera.santander.model.Error;

@Service
public class RESTClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    public <T> T sendReceive(WSIntegrador integrador, Object request, HttpMethod httpMethod, String method, Class<T> retorno) throws ErrorException {
        Object result = null;
        ResponseEntity<String> responseEntity = null;
        String endpoint = null;

        WSIntegradorLog log = new WSIntegradorLog(integrador.getDsAction(), WSIntegradorLogTipoEnum.JSON);
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout((integrador.getTimeoutSegundos() * 1000));

        try {
            HttpEntity<String> entity = new HttpEntity(gson.toJson(request), montarHeader(integrador, method));
            LogWS.convertRequest(integrador, log, gson, request);

            endpoint = retornarEnvironmentUri(integrador, method).get(0) + "/" + method;
            responseEntity = restTemplate.exchange(endpoint, httpMethod, entity, String.class);

            result = LogWS.convertResponse(integrador, log, gson, responseEntity, retorno);

            if(result == null) {
                Error error = gson.fromJson((String)responseEntity.getBody(), Error.class);
                verificaErro(integrador, error, method);
            }

        } catch (RestClientException ex) {
            throw LogWS.convertResponseException(integrador, log, ex);
        } catch (ErrorException ex) {
            LogWS.convertResponse(integrador, log, gson, responseEntity, retorno);
            throw ex;
        } catch (Exception ex) {
            integrador.setDsMensagem("Erro " + responseEntity.getStatusCode().toString());
            integrador.setIntegracaoStatus(WSIntegracaoStatusEnum.NEGADO);
            throw new ErrorException(integrador, RESTClient.class, "Erro ao realizar a chamada ao Fornecedor - Classe de Retorno: " + retorno.getSimpleName(), WSMensagemErroEnum.PGP, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        } finally {
            LogWS.montaLog(integrador, log);
        }
        return (T) result;
    }

    private HttpHeaders montarHeader(WSIntegrador integrador, String metodo) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        try {
            String tokenBasic = integrador.getDsCredencialList().get(2);
            String tokenBearer = null;

            if(integrador.getSessao() != null) {
                tokenBearer = integrador.getSessao().getDsSessao();
            }

            if (metodo.equals("Auth")) {
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.set("Content-Type", "application/json");
                headers.set("Accept", "application/json");
                headers.set("Authorization", "Basic " + tokenBasic);
            } else {
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.set("Content-Type", "application/json");
                headers.set("Accept", "application/json");
                headers.set("Authorization", "Bearer " + tokenBearer);
            }
        } catch (Exception ex) {
            throw new ErrorException(integrador, RESTClient.class, "Erro ao obter Token para Autenticação", WSMensagemErroEnum.PGP, ex.getMessage(), WSIntegracaoStatusEnum.NEGADO, ex, false);
        }
        return headers;
    }

    public List<String> retornarEnvironmentUri(WSIntegrador integrador, String method) throws ErrorException {
        List<String> result = new ArrayList<>();
        String endpointBoleto;

        try {
            if (integrador.getAmbiente().equals(WSAmbienteEnum.PRODUCAO)) {
                endpointBoleto = "https://core.usevirtus.com.br/api" + "/" + method;
            } else {
                endpointBoleto = "https://hml.usevirtus.com.br/api" + "/" + method;
            }
            result.add(0, endpointBoleto);

        } catch (Exception e) {
            throw new ErrorException(integrador, RESTClient.class, "Erro ao obter o Endpoint para realizar a chamada ao Fornecedor", WSMensagemErroEnum.PGP, e.getMessage(), WSIntegracaoStatusEnum.NEGADO, e, true);
        }

        return result;
    }

    private void verificaErro(WSIntegrador integrador, Error response, String metodo) throws ErrorException {
        String dsStatus = null;
        String dsMsg = null;

        if(response != null) {
            dsStatus = response.getStatus();
            dsMsg = response.getMessage();

            switch (response.getCode()) {
                case "400":
                case "500":
                    throw new ErrorException(integrador, RESTClient.class, "verificarErro", WSMensagemErroEnum.GENMETHOD, "Erro do conector: CODERROR: " + metodo + " " + dsStatus + " " + dsMsg, WSIntegracaoStatusEnum.NEGADO, null, false);
                case "401":
                    //Erro generico, mensagem especificada na documentação
                    throw new ErrorException(integrador, RESTClient.class, "verificarErro", WSMensagemErroEnum.GENMETHOD, "Erro do conector: O usuário e senha ou token de acesso são inválidos! CODERROR: " + metodo + " " + dsStatus + " " + dsMsg, WSIntegracaoStatusEnum.NEGADO, null, false);
                case "403":
                    //Erro generico, mensagem especificada na documentação
                    throw new ErrorException(integrador, RESTClient.class, "verificarErro", WSMensagemErroEnum.GENMETHOD, "Erro do conector: O acesso à API está bloqueado ou o usuário está bloqueado! CODERROR: " + metodo + " " + dsStatus + " " + dsMsg, WSIntegracaoStatusEnum.NEGADO, null, false);
                case "404":
                    //Erro generico, mensagem especificada na documentação
                    throw new ErrorException(integrador, RESTClient.class, "verificarErro", WSMensagemErroEnum.GENMETHOD, "Erro do conector: O endereço acessado não existe! CODERROR: " + metodo + " " + dsStatus + " " + dsMsg, WSIntegracaoStatusEnum.NEGADO, null, false);
                case "405":
                    //Erro generico, mensagem especificada na documentação
                    throw new ErrorException(integrador, RESTClient.class, "verificarErro", WSMensagemErroEnum.GENMETHOD, "Erro do conector: O acesso ao método não permitido! CODERROR: " + metodo + " " + dsStatus + " " + dsMsg, WSIntegracaoStatusEnum.NEGADO, null, false);
                case "406":
                    //Erro generico, mensagem especificada na documentação
                    throw new ErrorException(integrador, RESTClient.class, "verificarErro", WSMensagemErroEnum.GENMETHOD, "Erro do conector: A requisição está fora do formato (JSON) permitido! CODERROR: " + metodo + " " + dsStatus + " " + dsMsg, WSIntegracaoStatusEnum.NEGADO, null, false);
                case "422":
                    //Erro 422 não retorna lista de campos, apenas mensagem
                    throw new ErrorException(integrador, RESTClient.class, "verificarErro", WSMensagemErroEnum.GENMETHOD, "Erro do conector: CODERROR - " + metodo + " " + dsStatus + " " + dsMsg, WSIntegracaoStatusEnum.NEGADO, null, false);
                case "429":
                    //Erro generico, mensagem especificada na documentação
                    throw new ErrorException(integrador, RESTClient.class, "verificarErro", WSMensagemErroEnum.GENMETHOD, "Erro do conector: O usuário atingiu o limite de requisições! CODERROR: " + metodo + " " + dsStatus + " " + dsMsg, WSIntegracaoStatusEnum.NEGADO, null, false);
                case "503":
                    //Erro generico, mensagem especificada na documentação
                    throw new ErrorException(integrador, RESTClient.class, "verificarErro", WSMensagemErroEnum.GENMETHOD, "Erro do conector: Servidor temporariamente off-line! CODERROR: " + metodo + " " + dsStatus + " " + dsMsg, WSIntegracaoStatusEnum.NEGADO, null, false);
                default:
                    if(response.getCode().contains("@ER")){
                        throw new ErrorException(integrador, RESTClient.class, "verificarErro", WSMensagemErroEnum.GENMETHOD, "Erro do conector: O Sanatnder precisa validar os parâmetros envidados! CODERROR: " + metodo + " " + dsStatus + " " + dsMsg, WSIntegracaoStatusEnum.NEGADO, null, false);
                    }
            }
        }

    }
}
