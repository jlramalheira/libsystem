package util;

/**
 * Classe utilitaria para converter numeros decimais com ',' para double
 *
 * @author Max
 */
public class FormatNumber {

    public static String toDot(String s) {
        return s.replace(',', '.');
    }

    public static String toComma(String s) {
        return s.replace('.', ',');
    }

    public static String toComma(double d) {
        return String.valueOf(d).replace('.', ',');
    }
}
