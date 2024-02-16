package edu.ezip.ing1.pds.client;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class InsertIntoYaml {
    public static void writeIntoYaml(String firstname, String name, String group, String path) {
        Map<String, String> student = new LinkedHashMap<>();

        String nameString = "\"" + name + "\"";
        student.put("name", nameString);

        String firstNameString = "\""+ firstname + "\"";
        student.put("    firstname", firstNameString);

        String groupString = group;
        student.put("    group", groupString);        

        // ecriture dans le fichier yaml
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("students :\n");               
            writer.write("  - ");
            for (Map.Entry<String, String> entry : student.entrySet()) {
                writer.write(entry.getKey() + " : " + entry.getValue() + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

