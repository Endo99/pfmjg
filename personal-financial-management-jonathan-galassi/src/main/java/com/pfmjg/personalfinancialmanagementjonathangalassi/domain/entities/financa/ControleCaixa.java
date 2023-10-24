package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.financa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.conta.Conta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ControleCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idControleCaixa;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente idPaciente;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "controle_caixa_id_gastos",
            joinColumns = @JoinColumn(name = "idControleCaixa"),
            inverseJoinColumns = @JoinColumn(name = "idConta"))
    private Set<Conta> contas = new HashSet<>();

    @NotNull
    private Date data;

    @NotNull
    private String produtoOuCliente;

    @NotNull
    private Double preco;

    @NotNull
    private String formaPagamento;

    private String lancamentoFuturo;

}
