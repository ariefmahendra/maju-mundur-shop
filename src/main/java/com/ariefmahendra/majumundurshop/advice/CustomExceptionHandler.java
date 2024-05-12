package com.ariefmahendra.majumundurshop.advice;

import com.ariefmahendra.majumundurshop.dto.WebResponse;
import com.ariefmahendra.majumundurshop.exception.DuplicateException;
import com.ariefmahendra.majumundurshop.exception.NotFoundException;
import com.ariefmahendra.majumundurshop.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public WebResponse<String> handleUserNotFoundException(NotFoundException e){
        log.error(e.getMessage());
        return WebResponse.<String>builder()
                .code(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .data(null)
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DuplicateException.class)
    public WebResponse<String> handleUserDuplicateException(DuplicateException e){
        log.error(e.getMessage());
        return WebResponse.<String>builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .data(null)
                .build();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public WebResponse<String> handleUnauthorizedException(UnauthorizedException e){
        log.error(e.getMessage());
        return WebResponse.<String>builder()
                .code(HttpStatus.UNAUTHORIZED)
                .message(e.getMessage())
                .data(null)
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public WebResponse<String> handleUnknownException(Exception e){
        log.error(e.getMessage());
        return WebResponse.<String>builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Something went wrong")
                .data(null)
                .build();
    }
}
