package br.com.involves.javachallenge;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adriano
 */
public class JSONArrayFormat implements Parsable {

    @Override
    public String parse(Object arrayObject) {
        if (arrayObject == null) {
            return null;
        } else {
            Object[] formattedArray = toWrapperArray(arrayObject);
            if (formattedArray == null) {
                return null;
            } else {
                String parsed;
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                for (Object element : formattedArray) {
                    sb.append(getFormattedValueFromArray(element)).append(", ");
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
    public <T extends Collection> String parse(T arrayObject) {
        return parse(arrayObject.toArray());
    }

    public static Object[] toWrapperArray(Object valueObject) { //public to @Test
        if (valueObject == null) {
            return null;
        } else {
            Class<?> objClass = valueObject.getClass();
            if (!objClass.isArray()) {
                String errMsg = "Object is not an array. Can't be wrapped!";
                Logger.getLogger(JSONArrayFormat.class.getName()).log(Level.SEVERE, errMsg);
                throw new IllegalArgumentException(errMsg);
            } else if (!objClass.getComponentType().isPrimitive()) {
                return (Object[]) valueObject; //if not a primitive array, only a cast is needed.
            } else {
                int length = Array.getLength(valueObject);
                if (length == 0) {
                    String errMsg = "Primitive array without elements!";
                    Logger.getLogger(JSONArrayFormat.class.getName()).log(Level.SEVERE, errMsg);
                    throw new IllegalArgumentException(errMsg);
                }
                Object element;
                Object[] wrappedArray = null;
                for (int i = 0; i < length; i++) {
                    element = Array.get(valueObject, i);
                    if (wrappedArray == null) {
                        wrappedArray = (Object[]) Array.newInstance(element.getClass(), length);
                    }
                    wrappedArray[i] = element;
                }
                return wrappedArray;
            }
        }
    }

    private static String getFormattedValueFromArray(Object value) {
        try {
            if (!value.getClass().isArray() && !Parsable.isNumeric(value)) {
                return "\"" + value + "\"";
            } else {
                return value.toString();
            }
        } catch (NullPointerException npe) {
            return "null";
        }
    }

    public static String getFormattedArray(Object arrayValue) {//public to test
        JSONArrayFormat j = new JSONArrayFormat();
        return j.parse(arrayValue);
    }

}
