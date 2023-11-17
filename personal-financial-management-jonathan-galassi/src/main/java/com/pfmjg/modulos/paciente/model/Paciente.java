package com.pfmjg.modulos.paciente.model;

import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.paciente.dto.PacienteRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.pfmjg.modulos.comum.util.StringUtil.removerCaracteresNaoNumericos;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"CPF"})})
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CPF", unique = true)
    private String cpf;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(length = 3, name = "ESTADO")
    private String estado;

    @Column(length = 20, name = "TELEFONE")
    private String telefone;

    @Column(name = "SITUACAO")
    @Enumerated(EnumType.STRING)
    private ESituacao situacao;

    public static Paciente of(PacienteRequest request) {
        return Paciente.builder()
                .cpf(removerCaracteresNaoNumericos(request.cpf()))
                .nome(request.nome())
                .dataNascimento(request.dataNascimento())
                .cidade(request.cidade())
                .estado(request.estado())
                .telefone(removerCaracteresNaoNumericos(request.telefone()))
                .situacao(ESituacao.ATIVO)
                .build();
    }
}

