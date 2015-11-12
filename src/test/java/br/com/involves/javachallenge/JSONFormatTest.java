package br.com.involves.javachallenge;

import br.com.involves.javachallenge.pojos.Person;
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
public class JSONFormatTest {
    
    public JSONFormatTest() {
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
     * Test of parseToJSON method, of class JSONFormat.
     */
    @Test
    public void testParseToJSON() {
        System.out.println("parseToJSON");
        Person pojo = new Person();
        pojo.setFullName("Adriano Bortoloto");
        pojo.setAge(35);
        String[] skills = {"Java", "SQL", "Excel"};
        pojo.setSkills(skills);
        
        String expResult = "";
        String result = JSONFormat.parseToJSON(pojo);
        System.out.println("result: " + result);
        assertEquals(expResult, result);   
    }
}
