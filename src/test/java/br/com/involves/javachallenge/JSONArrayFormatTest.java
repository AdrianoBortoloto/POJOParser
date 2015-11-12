package br.com.involves.javachallenge;

import static br.com.involves.javachallenge.JSONArrayFormat.getFormattedArray;
import static br.com.involves.javachallenge.JSONArrayFormat.toWrapperArray;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adriano
 */
public class JSONArrayFormatTest {

    public JSONArrayFormatTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of toWrapperArray method, of class JSONArrayFormat.
     */
    @Test
    public void testToWrapperArray() {

        int[] intArray = new int[]{1, 2, 3, 4, 5};
        Integer[] wrappedInt = toWrapperArray(intArray);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, wrappedInt);

        char[] charArray = new char[]{'1', 'a', '%'};
        Character[] wrappedChar = toWrapperArray(charArray);
        assertArrayEquals(new Character[]{'1', 'a', '%'}, wrappedChar);

        Double[] dblArray = toWrapperArray(null);
        assertArrayEquals(null, dblArray);
    }

    /**
     * Test of getFormattedArray method, of class JSONArrayFormat.
     */
    @Test
    public void testGetFormattedArray() {
        String formatted;

        Integer[] integerArray = new Integer[]{1, 2, null, 4};
        formatted = getFormattedArray(integerArray);
        assertEquals("[1, 2, null, 4]", formatted);

        Double[] doubleArray = new Double[]{5d, null, 7d, 8d};
        formatted = getFormattedArray(doubleArray);
        assertEquals("[5.0, null, 7.0, 8.0]", formatted);

        Character[] charArray = new Character[]{'a', 'b', null, '2'};
        formatted = getFormattedArray(charArray);
        assertEquals("[\"a\", \"b\", null, 2]", formatted);

        formatted = getFormattedArray(null);
        assertEquals(null, formatted);
    }

    /**
     * Test of parse method, of class JSONArrayFormat.
     */
    @Test
    public void testParse_GenericType() {
        String[] arrayObject = new String[]{"1", "a", "!"};
        JSONArrayFormat instance = new JSONArrayFormat();
        String expResult = "[1, \"a\", \"!\"]";
        String result = instance.parse(arrayObject);
        System.out.println("parseGeneric: " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of parse method, of class JSONArrayFormat.
     */
    @Test
    public void testParse_List() {

        List<String> object = new ArrayList<>();
        object.add("q");
        object.add("w");
        object.add("25");
        JSONArrayFormat instance = new JSONArrayFormat();
        String expResult = "[\"q\", \"w\", 25]";
        String result = instance.parse(object);
        System.out.println("parseList: " + result);
        assertEquals(expResult, result);
    }

}
