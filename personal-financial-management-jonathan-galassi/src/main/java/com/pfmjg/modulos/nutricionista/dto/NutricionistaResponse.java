package com.pfmjg.modulos.nutricionista.dto;

import com.pfmjg.modulos.categoria.model.Categoria;
import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.nutricionista.model.Nutricionista;

import java.util.List;

import static com.pfmjg.modulos.comum.util.StringUtil.adicionarMascaraCpf;
import static com.pfmjg.modulos.comum.util.StringUtil.adicionarMascaraTelefone;

public record NutricionistaResponse(
        Integer id,
        String cpf,
        String nome,
        String descricao,
        String telefone,
        ESituacao situacao,
        List<String> categoriasDescricao,
        List<Categoria> categorias
) {

    public static NutricionistaResponse of(Nutricionista nutricionista) {
        List<String> categoriaDescricao = nutricionista.getCategorias()
                .stream()
                .map(Categoria::getDescricao)
                .toList();

        return new NutricionistaResponse(
                nutricionista.getId(),
                adicionarMascaraCpf(nutricionista.getCpf()),
                nutricionista.getNome(),
                nutricionista.getDescricao(),
                adicionarMascaraTelefone(nutricionista.getTelefone()),
                nutricionista.getSituacao(),
                categoriaDescricao,
                nutricionista.getCategorias()
        );
    }
}
