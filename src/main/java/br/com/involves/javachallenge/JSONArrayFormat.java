package br.com.involves.javachallenge;

import java.lang.reflect.Array;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adriano
 */
public class JSONArrayFormat implements Parsable {

    @Override
    public <T> String parse(T arrayObject) {
        if (arrayObject == null) {
            return null;
        } else {
            T[] formattedArray = toWrapperArray(arrayObject);
            if (formattedArray == null) {
                return null;
            } else {
                String parsed;
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                for (T element : formattedArray) {
                    sb.append(getFormattedValueFromArray((T) element));
                }
                sb.append("]");
                parsed = sb.toString()
                        .replace(", ]", "]")
                        .replace("\"[", "[")
                        .replace("]\"", "]");

                return parsed;
            }
        }
    }

    @Override
    public <T> String parse(List<T> arrayObject) {
        return parse(arrayObject.toArray());
    }

    public static <T> T[] toWrapperArray(Object valueObject) { //public to @Test
        if (valueObject == null) {
            return null;
        } else {
            Class<?> objClass = valueObject.getClass();
            if (!objClass.isArray()) {
                String errMsg = "Object is not an array. Can't be wrapped!";
                Logger.getLogger(JSONArrayFormat.class.getName()).log(Level.SEVERE, errMsg);
                throw new IllegalArgumentException(errMsg);
            } else if (!objClass.getComponentType().isPrimitive()) {
                return (T[]) valueObject; //if not a primitive array, only a cast is needed.
            } else {
                int length = Array.getLength(valueObject);
                if (length == 0) {
                    String errMsg = "Primitive array without elements!";
                    Logger.getLogger(JSONArrayFormat.class.getName()).log(Level.SEVERE, errMsg);
                    throw new IllegalArgumentException(errMsg);
                }
                T element;
                T[] wrappedArray = null;
                for (int i = 0; i < length; i++) {
                    element = (T) Array.get(valueObject, i);
                    if (wrappedArray == null) {
                        wrappedArray = (T[]) Array.newInstance(element.getClass(), length);
                    }
                    wrappedArray[i] = element;
                }
                return wrappedArray;
            }
        }
    }

    private static <T> String getFormattedValueFromArray(T value) {
        try {
            if (!value.getClass().isArray() && !Parsable.isNumeric(value)) {
                return "\"" + value + "\"" + ", ";
            } else {
                return value + ", ";
            }
        } catch (NullPointerException npe) {
            return "null, ";
        }
    }

    public static <T> String getFormattedArray(T arrayValue) {//public to test
        JSONArrayFormat j = new JSONArrayFormat();
        return j.parse(arrayValue);
    }

}
