package com.pfmjg.modulos.comum.util;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class TimeUtil {

    public static void validarHorarioEPeriodoAtual(LocalTime horario, LocalDate data) {
        LocalTime horarioAtual = LocalTime.now();
        LocalDate dataAtual = LocalDate.now();

        if (dataAtual.isEqual(data) && horario.isBefore(horarioAtual)) {
            throw new IllegalArgumentException("O horário não pode ser menor que o horário atual.");
        }
    }

    public static void validarHoraPeriodoInicialMenorFinal(LocalTime horarioInicial, LocalTime horarioFinal) {
        if (horarioInicial.isAfter(horarioFinal)) {
            throw new IllegalArgumentException("Horário inicial não pode ser maior que o horário final.");
        }
    }

    public static LocalTime calcularDiferenca(LocalTime horaInicial, LocalTime horaFinal) {
        var duracao = Duration.between(horaInicial, horaFinal);
        return LocalTime.MIDNIGHT.plus(duracao);
    }

    public static LocalTime calcularHoraFinal(LocalTime horaInicial, LocalTime periodo) {
        return horaInicial.plusHours(periodo.getHour()).plusMinutes(periodo.getMinute());
    }

    public static List<LocalTime> horariosAgenda(LocalTime horaInicial, LocalTime horaFinal, LocalTime tempo) {
        var intervalo = Duration.between(horaInicial, horaFinal);
        var intervaloEmSegundos = intervalo.getSeconds();

        return IntStream.iterate(0, i -> i + tempo.toSecondOfDay())
                .limit((intervaloEmSegundos / tempo.toSecondOfDay()) + 1)
                .mapToObj(horaInicial::plusSeconds)
                .collect(Collectors.toList());
    }
}
