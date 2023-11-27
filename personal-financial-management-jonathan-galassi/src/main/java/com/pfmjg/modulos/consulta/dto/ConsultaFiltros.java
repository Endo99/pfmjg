package com.pfmjg.modulos.consulta.dto;

import com.pfmjg.modulos.comum.util.DatePattern;
import com.pfmjg.modulos.consulta.enums.ESituacaoConsulta;
import com.pfmjg.modulos.consulta.predicate.ConsultaPredicate;

import java.time.LocalDate;
import java.util.List;

public record ConsultaFiltros(
        @DatePattern
        LocalDate data,
        String nutricionista,
        String paciente,
        List<ESituacaoConsulta> situacoes,
        List<Integer> pacientesIds,
        List<Integer> agendasIds
) {
    public ConsultaPredicate toPredicate() {
        return new ConsultaPredicate()
                .comData(data)
                .comSituacoes(situacoes)
                .comPacientesIds(pacientesIds)
                .comAgendasIds(agendasIds)
                .comNutricionistaNome(nutricionista)
                .comPacienteNome(paciente);
    }
}
