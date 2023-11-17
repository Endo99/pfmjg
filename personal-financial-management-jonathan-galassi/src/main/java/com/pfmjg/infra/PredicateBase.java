package com.pfmjg.infra;

import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.SimpleExpression;

import java.util.Collection;
import java.util.List;

public class PredicateBase {

    protected BooleanBuilder builder;

    public PredicateBase() {
        this.builder = new BooleanBuilder();
    }

    public BooleanBuilder build() {
        return this.builder;
    }

    protected <T> void addIn(SimpleExpression<? super T> expression, Collection<? extends T> values, int batchSize) {
        builder.andAnyOf(Lists.partition(List.copyOf(values), batchSize).stream()
                .map(expression::in)
                .toArray(BooleanExpression[]::new));
    }
}
