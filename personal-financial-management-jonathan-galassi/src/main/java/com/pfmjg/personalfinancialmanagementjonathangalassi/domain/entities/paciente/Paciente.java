package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente;

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
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "cpf" }) })
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer idPaciente;

    @Column(unique=true)
    @NotNull
    private String cpf;

    @NotNull
    private String nomePaciente;

    @NotNull
    private Date dataNascimentoPaciente;

    @NotNull
    private Integer idadePaciente;

    @NotNull
    private String cidade;

    @Column(length = 3)
    @NotNull
    private String estado;

    @Column(length = 20)
    @NotNull
    private String telefone;

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
