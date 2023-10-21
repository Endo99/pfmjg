package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.financa;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ControleCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idControleCaixa;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente idPaciente;

    @NotNull
    private Date data;

    @NotNull
    private String produtoOuCliente;

    @NotNull
    private Double preco;

    private String categoria;

    private  String status;

    @NotNull
    private String formaPagamento;

    private String lancamentoFuturo;

}
