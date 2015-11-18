package br.com.involves.javachallenge;

import static br.com.involves.javachallenge.JSONFormat.choosePathToExport;
import br.com.involves.javachallenge.pojos.Person;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    Person pojo = new Person();

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
        pojo.setFullName("Adriano Bortoloto");
        pojo.setAge(35);
        String[] skills = {"Java", "SQL", "Excel"};
        pojo.setSkills(skills);
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

        String expResult = "{\n"
                + "\"fullName\": \"Adriano Bortoloto\",\n"
                + "\"age\": 35,\n"
                + "\"skills\": [\"Java\", \"SQL\", \"Excel\"]\n"
                + "}";
        String result = JSONFormat.parseToJSON(pojo);
        System.out.println("result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testExport() {
        System.out.println("testExport");
        JSONFormat json = new JSONFormat();
        json.export(pojo);
        FileInputStream input = null;
        String fileName = pojo.getClass().getSimpleName().concat(".json");
        StringBuilder sb = new StringBuilder();

        try {
            input = new FileInputStream(choosePathToExport() + "\\" + fileName);
            int content;
            while ((content = input.read()) != -1) {
                sb.append((char) content);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException ex) {
            System.out.println("Can't read the file!");
            Logger.getLogger(JSONFormatTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        String expResult = "{\n"
                + "\"fullName\": \"Adriano Bortoloto\",\n"
                + "\"age\": 35,\n"
                + "\"skills\": [\"Java\", \"SQL\", \"Excel\"]\n"
                + "}";
        String result = sb.toString();
        System.out.println("result: " + result);
        assertEquals(expResult, result);
    }
}
