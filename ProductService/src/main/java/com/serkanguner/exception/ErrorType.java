package com.serkanguner.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    PRODUCT_NOT_CREATED(1001, "Product Olusturulamadi", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(1002,"Urun Bulunamadi" ,HttpStatus.BAD_REQUEST ),

    TOKEN_CREATION_FAILED(2001,"Token Olusturulurken Hata Meydana geldi." ,HttpStatus.INTERNAL_SERVER_ERROR ),
    TOKEN_ARGUMENT_NOTVALID(2002,"Token Argumanı Yanlıs" ,HttpStatus.BAD_REQUEST),
    TOKEN_VERIFY_FAILED(2003,"Token verify etmede bir hata meydana geldi." ,HttpStatus.BAD_REQUEST ),
    INVALID_TOKEN(2004,"Gecersiz Token",HttpStatus.UNAUTHORIZED ),
    INVALID_UNAUTHORIZED(2005,"Token ile yetkiniz yok",HttpStatus.UNAUTHORIZED );






    private Integer code;
    private String message;
    private HttpStatus httpStatus;
}
