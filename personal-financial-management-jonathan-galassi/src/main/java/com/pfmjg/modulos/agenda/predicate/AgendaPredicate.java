package com.pfmjg.modulos.agenda.predicate;

import com.pfmjg.infra.PredicateBase;
import com.pfmjg.modulos.comum.enums.ESituacao;

import java.time.LocalDate;
import java.util.Objects;

import static com.pfmjg.modulos.agenda.model.QAgenda.agenda;

public class AgendaPredicate extends PredicateBase {

    public AgendaPredicate comAgendaId(Integer agendaId) {
        if (Objects.nonNull(agendaId)) {
            builder.and(agenda.id.eq(agendaId));
        }
        return this;
    }

    public AgendaPredicate comSituacao(ESituacao situacao) {
        if (situacao != null) {
            builder.and(agenda.situacao.eq(situacao));
        }
        return this;
    }

    public AgendaPredicate comNutricionistaId(Integer nutricionistaId) {
        if (Objects.nonNull(nutricionistaId)) {
            builder.and(agenda.nutricionista.id.eq(nutricionistaId));
        }
        return this;
    }

    public AgendaPredicate comData() {
        builder.and(agenda.dataInicial.after(LocalDate.now().plusDays(-1)));
        return this;
    }
}
