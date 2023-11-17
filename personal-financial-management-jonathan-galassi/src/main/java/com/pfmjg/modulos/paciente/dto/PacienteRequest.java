package com.pfmjg.modulos.paciente.dto;

import com.pfmjg.modulos.comum.util.DatePattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PacienteRequest(

        @CPF
        @NotBlank
        String cpf,
        @NotBlank
        @Size(max = 255)
        String nome,
        @NotNull
        @DatePattern
        LocalDate dataNascimento,
        @NotNull
        @Size(max = 255)
        String cidade,
        @NotBlank
        @Size(max = 2)
        String estado,
        @NotBlank
        @Size(max = 11, min = 10)
        String telefone
) {
}
