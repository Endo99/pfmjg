package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Pacientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPaciente")
    //@Column(length = 40) // Colocar o tamanho da coluna;
    private Integer idPaciente;

    @Column(length = 300)
    private String nomePaciente;

    @Column(length = 300)
    private String sobrenomePaciente;

    private Date dataNascimentoPaciente;

    private String cidade;

    @Column(length = 2)
    private char estado;

    private int telefone;
    private String statusPagamento;
    @Column(length = 7)
    private char formaPagamento;

    @Column(length = 10)
    private char tipoConsulta;

    private double valorConsulta;

    private Integer mesesAcompanhado;

    private double quantiaPaga;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pacientes pacientes = (Pacientes) o;
        return getIdPaciente() != null && Objects.equals(getIdPaciente(), pacientes.getIdPaciente());
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
