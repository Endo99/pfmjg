package com.pfmjg.modulos.agenda.dto;

import com.pfmjg.modulos.agenda.predicate.AgendaPredicate;
import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.comum.util.DatePattern;

import java.time.LocalDate;

public record AgendaFiltros(

        Integer id,
        @DatePattern
        LocalDate data,
        ESituacao situacao,
        Integer nutricionistaId
) {
    public AgendaPredicate toPredicate() {
        return new AgendaPredicate()
                .comAgendaId(id)
                .comSituacao(situacao)
                .comNutricionistaId(nutricionistaId);
    }
}
