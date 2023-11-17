package com.pfmjg.modulos.paciente.dto;

import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.comum.util.DatePattern;
import com.pfmjg.modulos.paciente.model.Paciente;

import java.time.LocalDate;

import static com.pfmjg.modulos.comum.util.DateUtil.calcularIdade;
import static com.pfmjg.modulos.comum.util.StringUtil.adicionarMascaraCpf;
import static com.pfmjg.modulos.comum.util.StringUtil.adicionarMascaraTelefone;

public record PacienteResponse(
        Integer id,
        String cpf,
        String nome,
        @DatePattern
        LocalDate dataNascimento,
        int idade,
        String cidade,
        String estado,
        String telefone,
        ESituacao situacao
) {

    public static PacienteResponse of(Paciente paciente) {
        return new PacienteResponse(paciente.getId(), adicionarMascaraCpf(paciente.getCpf()),
                paciente.getNome(), paciente.getDataNascimento(), calcularIdade(paciente.getDataNascimento()),
                paciente.getCidade(), paciente.getEstado(), adicionarMascaraTelefone(paciente.getTelefone()),
                paciente.getSituacao());
    }
}
