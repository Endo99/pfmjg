package com.pfmjg.modulos.paciente.dto;

import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.paciente.predicate.PacientePredicate;

public record PacienteFiltros(
        String cpf,
        String nome,
        ESituacao situacao
) {

    public PacientePredicate toPredicate() {
        return new PacientePredicate()
                .comCpf(cpf)
                .comNome(nome)
                .comSituacao(situacao);
    }
}
