package com.pfmjg.modulos.paciente.predicate;

import com.pfmjg.infra.PredicateBase;
import com.pfmjg.modulos.comum.enums.ESituacao;

import static com.pfmjg.modulos.paciente.model.QPaciente.paciente;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class PacientePredicate extends PredicateBase {

    public PacientePredicate comSituacao(ESituacao situacao) {
        if (situacao != null) {
            builder.and(paciente.situacao.eq(situacao));
        }
        return this;
    }

    public PacientePredicate comCpf(String cpf) {
        if (isNotBlank(cpf)) {
            builder.and(paciente.cpf.eq(cpf));
        }
        return this;
    }

    public PacientePredicate comNome(String nome) {
        if (isNotBlank(nome)) {
            builder.and(paciente.nome.eq(nome));
        }
        return this;
    }
}
