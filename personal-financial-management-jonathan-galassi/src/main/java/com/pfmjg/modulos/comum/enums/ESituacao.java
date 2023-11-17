package com.pfmjg.modulos.comum.enums;

import org.springframework.util.ObjectUtils;

public enum ESituacao {
    ATIVO,
    INATIVO;

    public static ESituacao valueOf(boolean arg) {
        return !ObjectUtils.isEmpty(arg) && arg ? ESituacao.ATIVO : ESituacao.INATIVO;
    }

    public static boolean valueOf(ESituacao arg) {
        return !ObjectUtils.isEmpty(arg) && arg == ESituacao.ATIVO;
    }
}
