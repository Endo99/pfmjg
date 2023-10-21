package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AgendaDTO {

    private Integer idAgenda;
    private Date dataInicio;
    private String descricao;
    private Time horarioInicio;
    private Time horaFinal;
    private Integer lembrete;
    private String observacao;

}
