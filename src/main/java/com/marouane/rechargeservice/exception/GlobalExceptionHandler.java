package com.marouane.rechargeservice.exception;

import com.marouane.rechargeservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleAccountNotFoundException(AccountNotFoundException ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return ApiResponse.builder()
                .code("404")
                .status(HttpStatus.NOT_FOUND.value())
                .message("Compte non trouvé")
                .data(null)
                .errors(errors)
                .build();
    }

    @ExceptionHandler(InvalidRechargeOfferException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleInvalidRechargeOfferException(InvalidRechargeOfferException ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return ApiResponse.builder()
                .code("400")
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Offre de recharge invalide")
                .data(null)
                .errors(errors)
                .build();
    }

    @ExceptionHandler(SoldeInsuffisantException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleSoldeInsuffisantException(SoldeInsuffisantException ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return ApiResponse.builder()
                .code("400")
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Solde insuffisant")
                .data(null)
                .errors(errors)
                .build();
    }

    @ExceptionHandler(AccountBlockedOrSuspended.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse<Object> handleAccountBlockedOrSuspended(AccountBlockedOrSuspended ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return ApiResponse.builder()
                .code("403")
                .status(HttpStatus.FORBIDDEN.value())
                .message("Compte bloqué ou suspendu")
                .data(null)
                .errors(errors)
                .build();
    }

    @ExceptionHandler(AccountFeignException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ApiResponse<Object> handleAccountFeignException(AccountFeignException ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return ApiResponse.builder()
                .code("503")
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .message("Service de compte indisponible")
                .data(null)
                .errors(errors)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleGenericException(Exception ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("exception", ex.getMessage());

        return ApiResponse.builder()
                .code("500")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Erreur interne du serveur")
                .data(null)
                .errors(errors)
                .build();
    }
}
