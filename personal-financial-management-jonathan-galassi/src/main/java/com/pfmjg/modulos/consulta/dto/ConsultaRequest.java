package com.pfmjg.modulos.consulta.dto;

import com.pfmjg.modulos.comum.util.DatePattern;
import com.pfmjg.modulos.comum.util.TimePattern;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultaRequest(
        @NotNull
        @DatePattern
        LocalDate data,
        @NotNull
        @TimePattern
        LocalTime horaInicial,
        @NotNull
        Integer pacienteId,
        @NotNull
        Integer agendaId
) {
}
