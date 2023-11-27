package com.pfmjg.modulos.nutricionista.predicate;

import com.pfmjg.infra.PredicateBase;
import com.pfmjg.modulos.comum.enums.ESituacao;

import java.util.List;

import static com.pfmjg.modulos.nutricionista.model.QNutricionista.nutricionista;
import static com.pfmjg.modulos.paciente.model.QPaciente.paciente;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class NutricionistaPredicate extends PredicateBase {

    public NutricionistaPredicate comSituacoes(List<ESituacao> situacoes) {
        if (situacoes != null && !situacoes.isEmpty()) {
            builder.and(nutricionista.situacao.in(situacoes));
        }
        return this;
    }

    public NutricionistaPredicate comCpf(String cpf) {
        if (isNotBlank(cpf)) {
            builder.and(nutricionista.cpf.eq(cpf));
        }
        return this;
    }

    public NutricionistaPredicate comNome(String nome) {
        if (isNotBlank(nome)) {
            builder.and(nutricionista.nome.eq(nome));
        }
        return this;
    }
}
