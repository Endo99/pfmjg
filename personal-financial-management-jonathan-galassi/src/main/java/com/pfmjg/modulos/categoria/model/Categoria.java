package com.pfmjg.modulos.categoria.model;

import com.pfmjg.modulos.categoria.dto.CategoriaRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"DESCRICAO"})})
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRICAO", unique = true)
    private String descricao;

    public static Categoria of(CategoriaRequest request) {
        return Categoria.builder()
                .descricao(request.descricao())
                .build();
    }
}
