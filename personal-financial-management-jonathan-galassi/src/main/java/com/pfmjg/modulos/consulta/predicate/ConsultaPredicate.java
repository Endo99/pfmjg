package com.pfmjg.modulos.consulta.predicate;

import com.pfmjg.infra.PredicateBase;
import com.pfmjg.modulos.consulta.enums.ESituacaoConsulta;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.pfmjg.modulos.consulta.model.QConsulta.consulta;

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
        if (situacoes.isEmpty()) {
            builder.and(consulta.situacao.in(situacoes));
        }
        return this;
    }

    public ConsultaPredicate comPacientesIds(List<Integer> pacientesIds) {
        if (pacientesIds.isEmpty()) {
            builder.and(consulta.paciente.id.in(pacientesIds));
        }
        return this;
    }

    public ConsultaPredicate comAgendasIds(List<Integer> agendasIds) {
        if (agendasIds.isEmpty()) {
            builder.and(consulta.agenda.id.in(agendasIds));
        }
        return this;
    }
}
