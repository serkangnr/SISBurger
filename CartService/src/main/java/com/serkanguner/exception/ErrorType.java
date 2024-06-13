package com.serkanguner.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    USER_NOT_CREATED(1001, "User Olusturulamadi", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002,"User Bulunamadi" ,HttpStatus.BAD_REQUEST ),
    USER_ALREADY_EXISTS(1003,"User zaten kayitli" ,HttpStatus.BAD_REQUEST),
    USER_PASSWORD_NOT_MATCH(1004,"Sifreler uyusmamaktadir. Tekrar deneyiniz." ,HttpStatus.BAD_REQUEST ),
    USER_NOT_ACTIVE(1005,"User Aktif Degil" ,HttpStatus.BAD_REQUEST),
    TOKEN_CREATION_FAILED(2001,"Token Olusturulurken Hata Meydana geldi." ,HttpStatus.INTERNAL_SERVER_ERROR ),
    TOKEN_ARGUMENT_NOTVALID(2002,"Token Argumanı Yanlıs" ,HttpStatus.BAD_REQUEST),
    TOKEN_VERIFY_FAILED(2003,"Token verify etmede bir hata meydana geldi." ,HttpStatus.BAD_REQUEST ),
    INVALID_TOKEN(2004,"Token ile yetkiniz yok" , HttpStatus.BAD_REQUEST),
    INVALID_UNAUTHORIZED(2005,"Token ile yetkiniz yok" ,HttpStatus.BAD_REQUEST);









    private Integer code;
    private String message;
    private HttpStatus httpStatus;
}
