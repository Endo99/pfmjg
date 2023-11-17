package com.pfmjg.modulos.categoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequest(
        @NotBlank
        @Size(max = 255)
        String descricao
) {
}
