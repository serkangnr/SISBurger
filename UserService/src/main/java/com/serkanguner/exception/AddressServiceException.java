package com.serkanguner.exception;

import lombok.Getter;

@Getter
public class AddressServiceException extends RuntimeException {
    private ErrorType errorType;

    public AddressServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AddressServiceException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }


}
