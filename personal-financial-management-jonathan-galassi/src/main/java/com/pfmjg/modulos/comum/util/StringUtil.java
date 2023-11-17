package com.pfmjg.modulos.comum.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {

    public static String removerCaracteresNaoNumericos(String arg) {
        return arg.replaceAll("[^0-9]", "");
    }

    public static String adicionarMascaraCpf(String cpf) {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static String adicionarMascaraTelefone(String telefone) {
        if (telefone.length() == 10) {
            return telefone.replaceFirst("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
        }
        return telefone.replaceFirst("(\\d{2})(\\d{1})(\\d{4})(\\d{4})", "($1) $2.$3-$4");
    }
}
