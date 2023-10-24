package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pfmjg.personalfinancialmanagementjonathangalassi.config.CustomTimeDeserializer;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.consulta.Consulta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agenda")
    private Integer idAgenda;

    @ManyToOne //OneToMany
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;

    @NotNull
    private String descricao; // nome do evento

    @NotNull
    private String horarioInicio;

    @NotNull
    private String horaFinal;

    private String observacao;

}
