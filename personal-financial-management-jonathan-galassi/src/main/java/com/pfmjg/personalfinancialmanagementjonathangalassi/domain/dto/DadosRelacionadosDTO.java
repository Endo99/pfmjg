package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DadosRelacionadosDTO {

    private String nomePaciente;
    private String tipoConsulta;
    private String dataConsulta;
    private String horaAgendamento;

}
