package com.pfmjg.modulos.comum.controller;

import com.pfmjg.modulos.comum.exception.ErrorMessage;
import com.pfmjg.modulos.comum.exception.NotFoundException;
import com.pfmjg.modulos.comum.exception.ValidacaoException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public List<ErrorMessage> handleBeanValidationException(Exception ex) {
        log.error(ex.getMessage());
        BindingResult result;
        if (ex instanceof MethodArgumentNotValidException res) {
            result = res.getBindingResult();
        } else {
            result = ((BindException) ex).getBindingResult();
        }

        return result.getFieldErrors()
                .stream()
                .map(err ->
                        new ErrorMessage(
                                String.format("O campo %s %s", err.getField(), err.getDefaultMessage()),
                                err.getField())
                )
                .toList();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidContentTypeException.class, MissingServletRequestPartException.class})
    public ErrorMessage handleInvalidContentTypeException(Exception ex) {
        log.error(ex.getMessage());
        return new ErrorMessage("Requisição não pode estar incompleta.");
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundException(NotFoundException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(ValidacaoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleValidacaoException(ValidacaoException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleValidacaoException(IllegalArgumentException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage());
    }
}
