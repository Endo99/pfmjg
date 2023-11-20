package com.pfmjg.modulos.consulta.dto;

import com.pfmjg.modulos.comum.util.DatePattern;
import com.pfmjg.modulos.comum.util.TimePattern;
import com.pfmjg.modulos.consulta.enums.ESituacaoConsulta;
import com.pfmjg.modulos.consulta.model.Consulta;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.pfmjg.modulos.comum.util.TimeUtil.calcularDiferenca;

public record ConsultaResponse(
        Integer id,
        @DatePattern
        LocalDate data,
        @TimePattern
        LocalTime horaInicial,
        @TimePattern
        LocalTime horaFinal,
        @TimePattern
        LocalTime periodo,
        ESituacaoConsulta situacao,
        String pacienteNome,
        String agendaNutricionista,
        Integer pacienteId,
        Integer agendaNutricionistaId
) {
    public static ConsultaResponse of(Consulta consulta) {
        return new ConsultaResponse(consulta.getId(), consulta.getData(), consulta.getHoraInicial(), consulta.getHoraFinal(),
                calcularDiferenca(consulta.getHoraInicial(), consulta.getHoraFinal()), consulta.getSituacao(),
                consulta.getPaciente().getNome(), consulta.getAgenda().getNutricionista().getNome(),
                consulta.getPaciente().getId(), consulta.getAgenda().getNutricionista().getId());
    }
}
