package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PacienteDTO {

    private Integer idPaciente;
    private String cpf;
    private String nomePaciente;
    private Date dataNascimentoPaciente;
    private Integer idadePaciente;
    private String cidade;
    private String estado;
    private String telefone;

}
