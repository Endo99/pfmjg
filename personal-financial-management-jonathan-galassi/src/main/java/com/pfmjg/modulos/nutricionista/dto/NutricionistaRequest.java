package com.pfmjg.modulos.nutricionista.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record NutricionistaRequest(
        @CPF
        @NotBlank
        String cpf,
        @NotBlank
        @Size(max = 255)
        String nome,
        @Size(max = 255)
        String descricao,
        @NotBlank
        @Size(max = 11, min = 10)
        String telefone,
        @NotEmpty
        List<Integer> categoriasIds
) {
}
