package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsultaDTO {

    private Integer idConsulta;
    private Agenda agenda;

    private Date dataConsultaAtual;

    private String tipoConsulta;

    private String formaPagamento;

    private Integer mesesAcompanhado;
}
