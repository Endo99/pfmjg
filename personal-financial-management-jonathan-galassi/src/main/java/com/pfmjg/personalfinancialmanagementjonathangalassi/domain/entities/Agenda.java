package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Agenda {

    @Id
    @Column(name = "id_agenda")
    private Integer idAgenda;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;

    @NotNull
    private Date dataInicio;

    @NotNull
    private String descricao; // nome do evento

    @NotNull
    private Integer quantidadeConsulta;

    @NotNull
    private Date horarioInicio;

    @NotNull
    private Date horaFinal;

    @NotNull
    private Date lembrete; // Notificação do evento

    @NotNull
    private String observacao;

}
