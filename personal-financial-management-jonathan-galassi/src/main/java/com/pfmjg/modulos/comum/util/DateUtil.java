package com.pfmjg.modulos.comum.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class DateUtil {

    public static void validarDataEPeriodoAtual(LocalDate date) {
        LocalDate dataAtual = LocalDate.now();

        if (date.isBefore(dataAtual)) {
            throw new IllegalArgumentException("A data não pode ser menor que a data atual.");
        }
    }

    public static void validarPeriodoInicialMenorFinal(LocalDate dateInicial, LocalDate dateFinal) {
        if (dateInicial.isAfter(dateFinal)) {
            throw new IllegalArgumentException("Data inicial não pode ser maior que a data final.");
        }
    }

    public static int calcularIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        return Period.between(dataNascimento, hoje).getYears();
    }

    public static void validarDataNoFuturo(LocalDate date) {
        LocalDate dataAtual = LocalDate.now();

        if (date.isAfter(dataAtual)) {
            throw new IllegalArgumentException("A data não pode ser no futuro.");
        }
    }

    public static List<LocalDate> diasAgenda(LocalDate dataInicial, LocalDate dataFinal) {
        return Stream.iterate(dataInicial, date -> date.plusDays(1))
                .limit(dataFinal.toEpochDay() - dataInicial.toEpochDay() + 1)
                .collect(Collectors.toList());
    }
}
