package com.fortunetree.basic.support.commons.business.encrypt;

public class CipherAesException extends RuntimeException {
    private static final long serialVersionUID = 5427957736208284422L;

    private String errorMessage;
    private String errorCode;

    public CipherAesException(){
    }

    public CipherAesException(String errorMessage){
        super((String)null);
        this.errorMessage = errorMessage;
    }

    public CipherAesException(String errorCode, String errorMessage) {
        super((String)null);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public CipherAesException(String errorMessage, Throwable cause) {
        super((String)null, cause);
        this.errorMessage = errorMessage;
    }

    public CipherAesException(Throwable cause) {
        super((String)null, cause);
    }

    public CipherAesException(String errorCode, String errorMessage, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public CipherAesException setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public CipherAesException setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "errorMessage='" + errorMessage + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
