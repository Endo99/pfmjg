package com.pfmjg.modulos.agenda.model;

import com.pfmjg.modulos.agenda.dto.AgendaRequest;
import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.comum.util.DatePattern;
import com.pfmjg.modulos.nutricionista.model.Nutricionista;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DATA_INICIAL")
    private LocalDate dataInicial;

    @Column(name = "DATA_FINAL")
    private LocalDate dataFinal;

    @Column(name = "HORA_DIA_INICIAL")
    private LocalTime horaDiaInicial;

    @Column(name = "HORA_DIA_FINAL")
    private LocalTime horaDiaFinal;

    @Column(name = "TEMPO_PADRAO")
    private LocalTime tempoPadrao;

    @Column(name = "SITUACAO")
    @Enumerated(EnumType.STRING)
    private ESituacao situacao;

    @ManyToOne
    private Nutricionista nutricionista;

    public static Agenda of(AgendaRequest request, Nutricionista nutricionista) {
        return Agenda.builder()
                .dataInicial(request.dataInicial())
                .dataFinal(request.dataFinal())
                .horaDiaInicial(request.horaDiaInicial())
                .horaDiaFinal(request.horaDiaFinal())
                .tempoPadrao(request.tempoPadrao())
                .situacao(ESituacao.ATIVO)
                .nutricionista(nutricionista)
                .build();
    }
}
