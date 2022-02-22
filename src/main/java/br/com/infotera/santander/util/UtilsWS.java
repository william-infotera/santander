package br.com.infotera.santander.util;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSCliente;
import br.com.infotera.common.WSIntegrador;
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
import br.com.infotera.common.enumerator.WSTelefoneTipoEnum;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UtilsWS {

    public String geraArquivo(InputStream content) {
        int read = 0;
        byte[] bytes = new byte[4096];
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            while ((read = content.read(bytes)) != -1) {
                baos.write(bytes, 0, read);
            }
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(baos.toByteArray()));
        } catch (IOException e) {
            throw new RuntimeException("Unable to convert content stream to base 64 encoded string", e);
        }
    }

    public static boolean verificarCliente(WSIntegrador integrador, WSCliente cliente) throws ErrorException{
        boolean statusCadastro = false;

        if (cliente != null) {
            if (cliente.getDocumento() != null) {
                if (cliente.getDocumento().getDocumentoTipo() != null && cliente.getDocumento().getDocumentoTipo() == cliente.getDocumento().getDocumentoTipo().CPF) {
                    statusCadastro = true;
                } else {
                    throw new ErrorException(integrador, UtilsWS.class, "verificarCliente", WSMensagemErroEnum.ADI, "Documento necessario: CPF", WSIntegracaoStatusEnum.NEGADO, null, false);
                }
            } else {
                throw new ErrorException(integrador, UtilsWS.class, "verificarCliente", WSMensagemErroEnum.ADI, "Necessario informar documento", WSIntegracaoStatusEnum.NEGADO, null, false);
            }
            if (cliente.getTelefone() != null){
                if (!cliente.getTelefone().getTelefoneTipo().equals(WSTelefoneTipoEnum.CELULAR)){
                    statusCadastro = true;
                } else {
                    throw new ErrorException(integrador, UtilsWS.class, "verificarCliente", WSMensagemErroEnum.ADI, "Necessario informar o telefone padrão (CELULAR)", WSIntegracaoStatusEnum.NEGADO, null, false);
                }
            } else {
                throw new ErrorException(integrador, UtilsWS.class, "verificarCliente", WSMensagemErroEnum.ADI, "Necessario informar o telefone padrão (CELULAR)", WSIntegracaoStatusEnum.NEGADO, null, false);
            }
        } else {
            throw new ErrorException(integrador, UtilsWS.class, "verificarCliente", WSMensagemErroEnum.ADI, "Não foi localizado o Cliente na base - Entre em contato com suporte", WSIntegracaoStatusEnum.NEGADO, null, false);
        }

        return statusCadastro;
    }
}
