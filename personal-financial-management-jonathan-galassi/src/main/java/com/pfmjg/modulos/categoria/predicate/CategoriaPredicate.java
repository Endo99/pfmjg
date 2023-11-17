package com.pfmjg.modulos.categoria.predicate;

import com.pfmjg.infra.PredicateBase;

import java.util.List;

import static com.pfmjg.modulos.categoria.model.QCategoria.categoria;

public class CategoriaPredicate extends PredicateBase {

    public CategoriaPredicate comIds(List<Integer> ids) {
        if (!ids.isEmpty()) {
            builder.and(categoria.id.in(ids));
        }
        return this;
    }
}
