package br.gov.cesarschool.projeto.geral.util;

public class ValidadorCNPJ {

    public static boolean validarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14) {
            return false;
        }


        if (cnpj.matches("(\\d)\\1*")) {
            return false;
        }

        int sum = 0;
        int weight = 5;
        for (int i = 0; i < 12; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weight;
            weight = (weight == 2) ? 9 : weight - 1;
        }
        int digit1 = 11 - (sum % 11);
        if (digit1 >= 10) {
            digit1 = 0;
        }
        if (digit1 != Character.getNumericValue(cnpj.charAt(12))) {
            return false;
        }

        // Verifique o segundo dígito verificador
        sum = 0;
        weight = 6;
        for (int i = 0; i < 13; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weight;
            weight = (weight == 2) ? 9 : weight - 1;
        }
        int digit2 = 11 - (sum % 11);
        if (digit2 >= 10) {
            digit2 = 0;
        }
        if (digit2 != Character.getNumericValue(cnpj.charAt(13))) {
            return false;
        }

        return true;
    }
}

