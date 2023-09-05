package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConsulta;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;

    @NotNull
    private Date dataConsultaAtual;

    private LocalDateTime dataConsultaAlterada;

    private Date dataConsultaAntiga;

    @NotNull
    @Column(length = 10)
    private String tipoConsulta;

    @Column(length = 10)
    private String formaPagamento;

    private Integer mesesAcompanhado;
}
