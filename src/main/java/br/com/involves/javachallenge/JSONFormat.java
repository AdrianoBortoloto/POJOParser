package br.com.involves.javachallenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author adriano
 */
public final class JSONFormat implements Parsable, Exportable {

    @Override
    public String parse(Object pojo) {
        String parsed;
        List<String> members = getProperties(pojo);
        StringBuilder buildBlock = new StringBuilder();
        buildBlock.append("{\n");
        for (String member : members) {
            buildBlock.append(member);
        }
        buildBlock.append("}");
        parsed = buildBlock.toString()
                .replace(",}", "}")
                .replace(",\n}", "\n}");

        return parsed;

    }

    public static String formatMembers(String name, Object value) {
        name = "\"" + name + "\": ";
        String member;
        if (value.getClass().isArray() || Parsable.isNumeric(value)) {
            member = name + value;
        } else {
            member = name + "\"" + value + "\"";
        }

        member = member
                .replace("\"[", "[")
                .replace("]\"", "]");

        return member;
    }

    @Override
    public <T extends Collection> String parse(T pojoList) {
        StringBuilder sb = new StringBuilder();
        for (Object element : pojoList) {
            sb.append(parse(element));
        }
        return sb.toString();
    }

    public static String parseToJSON(Object pojo) {
        JSONFormat json = new JSONFormat();
        String parsed = json.parse(pojo);
        return parsed;
    }

    @Override
    public OutputStream export(Object pojo) {
        Charset charset = Charset.forName("UTF-8");
        OutputStream output = null;
        try {
            //encode
            String fileName = pojo.getClass().getSimpleName().concat(".json");
            output = new FileOutputStream(choosePathToExport() + "\\" + fileName);
            String parsed = parseToJSON(pojo);
            byte[] encoded = parsed.getBytes(charset);
            output.write(encoded);
            output.flush();
            return output;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSONFormat.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(JSONFormat.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(JSONFormat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static File choosePathToExport() {
        JFileChooser pathChooser;
        String curDir;
        try {
            pathChooser = new JFileChooser();
            curDir = new File(".").getCanonicalPath();
            pathChooser.setCurrentDirectory(new File(curDir));
            pathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            pathChooser.setDialogTitle("Select path to JSON file!");
            int option = pathChooser.showOpenDialog(pathChooser);
            if (option == JFileChooser.APPROVE_OPTION) {
                return pathChooser.getSelectedFile();
            } else {
                return null;
            }
        } catch (IOException ex) {
            Logger.getLogger(JSONFormat.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private List<String> getProperties(Object pojo) {

        List<String> properties = new ArrayList<>();
        try {
            Class<?> pojoClass = pojo.getClass();
            Field[] pojoFields = pojoClass.getDeclaredFields();
            AccessibleObject.setAccessible(pojoFields, true);
            StringBuilder buildPairs = new StringBuilder();

            for (Field field : pojoFields) {
                String name = field.getName();
                Object value = field.get(pojo);
                if (value.getClass().isArray()) {
                    value = JSONArrayFormat.getFormattedArray(value);
                }
                String jsonMembers = JSONFormat.formatMembers(name, value);
                buildPairs.append(jsonMembers).append(",\n");
            }
            properties.add(buildPairs.toString());

        } catch (IllegalAccessException ex) {
        } catch (IllegalArgumentException ex) {
        } catch (SecurityException ex) {
        }

        return properties;
    }

}
