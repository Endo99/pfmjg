package com.pfmjg.modulos.agenda.dto;

import com.pfmjg.modulos.agenda.model.Agenda;
import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.comum.util.DatePattern;
import com.pfmjg.modulos.comum.util.TimePattern;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendaResponse(
        Integer id,
        @DatePattern
        LocalDate dataInicial,
        @DatePattern
        LocalDate dataFinal,
        @TimePattern
        LocalTime horaDiaInicial,
        @TimePattern
        LocalTime horaDiaFinal,
        @TimePattern
        LocalTime tempoPadrao,
        ESituacao situacao,
        String nutricionistaNome,
        Integer nutricionistaId
) {
    public static AgendaResponse of(Agenda agenda) {
        return new AgendaResponse(agenda.getId(), agenda.getDataInicial(), agenda.getDataFinal(), agenda.getHoraDiaInicial(),
                agenda.getHoraDiaFinal(), agenda.getTempoPadrao(), agenda.getSituacao(), agenda.getNutricionista().getNome(),
                agenda.getNutricionista().getId());
    }
}
