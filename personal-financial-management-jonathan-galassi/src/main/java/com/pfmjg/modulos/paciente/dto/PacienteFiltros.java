package com.pfmjg.modulos.paciente.dto;

import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.paciente.predicate.PacientePredicate;

import java.util.List;

public record PacienteFiltros(
        String cpf,
        String nome,
        List<ESituacao> situacao
) {

    public PacientePredicate toPredicate() {
        return new PacientePredicate()
                .comCpf(cpf)
                .comNome(nome)
                .comSituacoes(situacao);
    }
}
