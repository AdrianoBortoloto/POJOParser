package br.com.involves.javachallenge;

import java.util.List;

/**
 *
 * @author adriano
 */
public interface Parsable {

    public <T> String parse(T object);

    public <T> String parse(List<T> arrayObject);

    public static <T> Boolean isNumeric(T value) {
        try {
            Double.parseDouble(value.toString());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
