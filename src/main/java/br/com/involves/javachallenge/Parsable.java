package br.com.involves.javachallenge;

import java.util.Collection;

/**
 *
 * @author adriano
 */
public interface Parsable {

    public String parse(Object object);

    public <T extends Collection> String parse(T collection);

    public static Boolean isNumeric(Object value) {
        try {
            Double.parseDouble(value.toString());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
