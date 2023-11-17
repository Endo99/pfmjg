package com.pfmjg.modulos.categoria.dto;

import com.pfmjg.modulos.categoria.model.Categoria;

public record CategoriaResponse(
        Integer id,
        String descricao
) {
    public static CategoriaResponse of(Categoria categoria) {
        return new CategoriaResponse(categoria.getId(), categoria.getDescricao());
    }
}
