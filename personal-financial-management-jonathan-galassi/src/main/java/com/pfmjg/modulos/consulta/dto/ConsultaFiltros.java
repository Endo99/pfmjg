package com.pfmjg.modulos.consulta.dto;

import com.pfmjg.modulos.consulta.enums.ESituacaoConsulta;
import com.pfmjg.modulos.consulta.predicate.ConsultaPredicate;

import java.time.LocalDate;
import java.util.List;

public record ConsultaFiltros(
        LocalDate data,
        List<ESituacaoConsulta> situacoes,
        List<Integer> pacientesIds,
        List<Integer> agendasIds
) {
    public ConsultaPredicate toPredicate() {
        return new ConsultaPredicate()
                .comData(data)
                .comSituacoes(situacoes)
                .comPacientesIds(pacientesIds)
                .comAgendasIds(agendasIds);
    }
}
