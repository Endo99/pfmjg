package com.pfmjg.modulos.comum.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidacaoException extends RuntimeException {

    public ValidacaoException(String message) {
        super(message);
    }
}
