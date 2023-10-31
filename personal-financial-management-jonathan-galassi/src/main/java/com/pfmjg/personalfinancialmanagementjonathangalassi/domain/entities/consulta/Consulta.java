package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.consulta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Integer idConsulta;
    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;

    @NotNull
    private Date dataConsultaAtual;
    @NotNull
    @Column(length = 10)
    private String tipoConsulta;

    @NotNull
    private String formaPagamento;


}
