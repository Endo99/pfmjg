package com.pfmjg.modulos.nutricionista.dto;

import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.nutricionista.predicate.NutricionistaPredicate;

import java.util.List;

public record NutricionistaFiltros(
        String cpf,
        String nome,
        List<ESituacao> situacao
) {

    public NutricionistaPredicate toPredicate() {
        return new NutricionistaPredicate()
                .comCpf(cpf)
                .comNome(nome)
                .comSituacoes(situacao);
    }
}
