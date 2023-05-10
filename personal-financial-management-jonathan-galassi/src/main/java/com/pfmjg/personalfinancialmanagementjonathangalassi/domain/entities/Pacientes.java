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
    private Long idPaciente;

    @Column(length = 300)
    private String nomePaciente;

    @Column(length = 300)
    private String sobrenomePaciente;

    private Date dataDeNascimentoPaciente;

    @Column(length = 300)
    private String formaDePagamento;

    @Column(length = 300)
    private String tipoDeConsulta;

    private double valorDaConsulta;

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
