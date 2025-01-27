package com.yerinden.yerinden.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException{

    private HttpStatus status;
    private String errorMessage;

    public static BusinessException jwtValidationException(){return new BusinessException(HttpStatus.FORBIDDEN, "jwt_validation_failed");}
    public static BusinessException userNotFound(){return new BusinessException(HttpStatus.BAD_REQUEST, "user_not_found");}
    public static BusinessException productNotFound(){return new BusinessException(HttpStatus.BAD_REQUEST, "product_not_found");}
    public static BusinessException basketItemNotFound(){return new BusinessException(HttpStatus.BAD_REQUEST, "basket_item_not_found");}
    public static BusinessException favItemNotFound(){return new BusinessException(HttpStatus.BAD_REQUEST, "fav_item_not_found");}
    public static BusinessException passwordDoesntMatch(){return new BusinessException(HttpStatus.BAD_REQUEST, "password_doesnt_match");}
    public static BusinessException userAlreadyExist(){return new BusinessException(HttpStatus.BAD_REQUEST, "user_already_exist");}
    public static BusinessException incorrectRole(){return new BusinessException(HttpStatus.FORBIDDEN, "incorrect_role");}
}
