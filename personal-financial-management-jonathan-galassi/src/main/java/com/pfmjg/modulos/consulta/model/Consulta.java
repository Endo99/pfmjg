package com.pfmjg.modulos.consulta.model;

import com.pfmjg.modulos.agenda.model.Agenda;
import com.pfmjg.modulos.consulta.dto.ConsultaRequest;
import com.pfmjg.modulos.consulta.enums.ESituacaoConsulta;
import com.pfmjg.modulos.paciente.model.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.pfmjg.modulos.comum.util.TimeUtil.calcularHoraFinal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DATA")
    private LocalDate data;

    @Column(name = "HORA_INICIAL")
    private LocalTime horaInicial;

    @Column(name = "HORA_FINAL")
    private LocalTime horaFinal;

    @Column(name = "SITUACAO")
    @Enumerated(EnumType.STRING)
    private ESituacaoConsulta situacao;

    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Agenda agenda;

    public static Consulta of(ConsultaRequest request, Paciente paciente, Agenda agenda) {
        return Consulta.builder()
                .data(request.data())
                .horaInicial(request.horaInicial())
                .horaFinal(calcularHoraFinal(request.horaInicial(), agenda.getTempoPadrao()))
                .situacao(ESituacaoConsulta.AGENDADO)
                .paciente(paciente)
                .agenda(agenda)
                .build();
    }
}
