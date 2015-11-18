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
        
        Object[] wrapped;
        wrapped = toWrapperArray(new int[]{1, 2, 3, 4, 5});
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5}, wrapped);

        wrapped = toWrapperArray(new char[]{'1', 'a', '%'});
        assertArrayEquals(new Object[]{'1', 'a', '%'}, wrapped);
        
        wrapped = toWrapperArray(new Object[]{1, 'a', "obj"});
        assertArrayEquals(new Object[]{1, 'a', "obj"}, wrapped);

        wrapped = toWrapperArray(null);
        assertArrayEquals(null, wrapped);
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
        
        JSONArrayFormat instance = new JSONArrayFormat();
        String expResult, result;
        String[] arrayString = new String[]{"1", "a", "!", null};
        
        expResult = "[1, \"a\", \"!\", null]";
        result = instance.parse(arrayString);
        System.out.println("parseGenericString: " + result);
        assertEquals(expResult, result);
        
        float[] arrayFloat = new float[]{2.34f, 3.1415f, 500f};
        expResult = "[2.34, 3.1415, 500.0]";
        result = instance.parse(arrayFloat);
        System.out.println("parseGenericFloat: " + result);
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
        object.add(null);
        JSONArrayFormat instance = new JSONArrayFormat();
        String expResult = "[\"q\", \"w\", 25, null]";
        String result = instance.parse(object);
        System.out.println("parseList: " + result);
        assertEquals(expResult, result);
    }

}
