package com.pfmjg.modulos.agenda.dto;

import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.comum.util.DatePattern;
import com.pfmjg.modulos.comum.util.TimePattern;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendaRequest(
        @NotNull
        @DatePattern
        LocalDate dataInicial,
        @NotNull
        @DatePattern
        LocalDate dataFinal,
        @NotNull
        @TimePattern
        LocalTime horaDiaInicial,
        @NotNull
        @TimePattern
        LocalTime horaDiaFinal,
        @NotNull
        @TimePattern
        LocalTime tempoPadrao,
        ESituacao situacao,
        @NotNull
        Integer nutricionistaId
) {
}
