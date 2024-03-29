package com.pfmjg.modulos.comum.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private String message;
    private String field;

    public ErrorMessage(String message) {
        this.message = message;
    }
}
