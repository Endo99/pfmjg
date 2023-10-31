package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
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
    private Paciente paciente;
    private String descricao;
    private String horarioInicio;
    private String horaFinal;
    private String observacao;

}
