package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.consulta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
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


    @JsonBackReference    // Resto dos campos e mapeamentos
    @ManyToOne
    @JoinColumn(name = "id_agenda", referencedColumnName = "id_agenda")
    private Agenda agenda;

    @NotNull
    private Date dataConsultaAtual;
    @NotNull
    @Column(length = 10)
    private String tipoConsulta;

    @NotNull
    @Column(length = 10)
    private String formaPagamento;

    private Integer mesesAcompanhado;

}
