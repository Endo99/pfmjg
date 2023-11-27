package com.pfmjg.modulos.consulta.predicate;

import com.pfmjg.infra.PredicateBase;
import com.pfmjg.modulos.consulta.enums.ESituacaoConsulta;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.pfmjg.modulos.consulta.model.QConsulta.consulta;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class ConsultaPredicate extends PredicateBase {

    public ConsultaPredicate comData(LocalDate data) {
        if (Objects.nonNull(data)) {
            builder.and(consulta.data.eq(data));
        }
        return this;
    }

    public ConsultaPredicate comDataAnterior(LocalDate data) {
        if (Objects.nonNull(data)) {
            builder.and(consulta.data.before(data));
        }
        return this;
    }

    public ConsultaPredicate comSituacoes(List<ESituacaoConsulta> situacoes) {
        if (situacoes != null && !situacoes.isEmpty()) {
            builder.and(consulta.situacao.in(situacoes));
        }
        return this;
    }

    public ConsultaPredicate comPacientesIds(List<Integer> pacientesIds) {
        if (Objects.nonNull(pacientesIds) && !pacientesIds.isEmpty()) {
            builder.and(consulta.paciente.id.in(pacientesIds));
        }
        return this;
    }

    public ConsultaPredicate comAgendasIds(List<Integer> agendasIds) {
        if (Objects.nonNull(agendasIds) && !agendasIds.isEmpty()) {
            builder.and(consulta.agenda.id.in(agendasIds));
        }
        return this;
    }

    public ConsultaPredicate comNutricionistaNome(String nutricionista) {
        if (isNotBlank(nutricionista)) {
            builder.and(consulta.agenda.nutricionista.nome.eq(nutricionista));
        }
        return this;
    }

    public ConsultaPredicate comPacienteNome(String paciente) {
        if (isNotBlank(paciente)) {
            builder.and(consulta.paciente.nome.eq(paciente));
        }
        return this;
    }
}
