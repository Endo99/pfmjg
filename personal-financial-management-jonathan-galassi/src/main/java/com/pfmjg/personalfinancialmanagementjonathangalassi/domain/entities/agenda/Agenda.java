package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente", nullable = false),
    })
    private Paciente idPaciente;

    @JsonManagedReference
    @OneToMany(mappedBy = "agenda")
    private List<Consulta> consultasAgendas = new ArrayList<>();

    @NotNull
    private Date dataInicio;

    @NotNull
    private String descricao; // nome do evento

    @NotNull
    private Time horarioInicio;

    @NotNull
    private Time horaFinal;

    @NotNull
    private Integer lembrete; // Notificação do evento

    private String observacao;

}
