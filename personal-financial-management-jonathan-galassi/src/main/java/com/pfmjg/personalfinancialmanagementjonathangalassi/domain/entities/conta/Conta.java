package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.conta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.financa.ControleCaixa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConta;

    @JsonIgnore
    @ManyToMany(mappedBy = "contas", fetch = FetchType.LAZY)
    private Set<ControleCaixa> controleCaixa = new HashSet<>();

    @NotNull
    private Double saldoAtual;
}
