package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities;

import jakarta.persistence.*;
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
@Data
public class Pacientes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ", allocationSize = 1)
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
}
