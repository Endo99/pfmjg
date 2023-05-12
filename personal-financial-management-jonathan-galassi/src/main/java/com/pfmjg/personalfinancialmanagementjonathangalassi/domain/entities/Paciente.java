package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.sql.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPaciente")
    //@Column(length = 40) // Colocar o tamanho da coluna;
    private Integer idPaciente;

    @NotNull
    @Column(length = 300)
    private String nomePaciente;

    @Column(length = 300)
    @NotNull
    private String sobrenomePaciente;

    @NotNull
    private Date dataNascimentoPaciente;

    private Integer idadePaciente;

    @NotNull
    private String cidade;

    @Column(length = 3)
    @NotNull
    private char[] estado;

    @Column(length = 20)
    @NotNull
    private String telefone;

    @NotNull
    private String statusPagamento;
    @Column(length = 10)
    private char[] formaPagamento;

    @NotNull
    @Column(length = 10)
    private char[] tipoConsulta;

    @NotNull
    private double valorConsulta;

    private Integer mesesAcompanhado;

    @NotNull
    private double quantiaPaga;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Paciente paciente = (Paciente) o;
        return getIdPaciente() != null && Objects.equals(getIdPaciente(), paciente.getIdPaciente());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

//    public Pacientes(long idPaciente, String nomePaciente, String sobrenomePaciente
//                    , Date dataDeNascimentoPaciente, String formaDePagamento, String tipoDeConsulta
//                    , double valorDaConsulta, Integer mesesAcompanhado, double quantiaPaga){
//        this.idPaciente = idPaciente;
//        this.nomePaciente = nomePaciente;
//        this.sobrenomePaciente = sobrenomePaciente;
//        this.dataDeNascimentoPaciente = dataDeNascimentoPaciente;
//        this.formaDePagamento = formaDePagamento;
//        this.tipoDeConsulta = tipoDeConsulta;
//        this.valorDaConsulta = valorDaConsulta;
//        this.mesesAcompanhado = mesesAcompanhado;
//        this.quantiaPaga = quantiaPaga;
}
