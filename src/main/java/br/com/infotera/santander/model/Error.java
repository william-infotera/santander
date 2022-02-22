package br.com.infotera.santander.model;

public class Error {

    private String status; // ": "BAD_REQUEST",
    private String timestamp; // "timestamp": "14-05-2021 01:17:00",
    private String debugMessage; //"debugMessage": "Unexpected error",
    private String message; //"message": "Verifique o contrato de envio, não foi possível fazer a conversão do valor null",
    private String code; //"code": 400

    public Error() {
    }

    public Error(String status, String timestamp, String debugMessage, String message, String code) {
        this.status = status;
        this.timestamp = timestamp;
        this.debugMessage = debugMessage;
        this.message = message;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
