package edu.ezip.ing1.pds.client;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class InsertIntoYaml {
    public static void writeIntoYaml(String Nom_Client, String Prenom_Client, String Date_de_naissance_Client, String Poids, String Genre, String Taille, String Numero_de_telephone_Client, String Mail_Client, String Ville, String Adresse, String Code_Postal, String path) {
        Map<String, String> client = new LinkedHashMap<>();

        String nomString = "\"" + Nom_Client + "\"";
        client.put("nomClient", nomString);

        String prenomString = "\"" + Prenom_Client + "\"";
        client.put("    prenomClient", prenomString);

        String dateString = "\"" + Date_de_naissance_Client + "\"";
        client.put("    dateDeNaissanceClient", dateString);
        
        String poidsString = "\"" + Poids + "\"";
        client.put("    poids", poidsString);
        
        String genreString = "\"" + Genre + "\"";
        client.put("    genre", genreString);

        String tailleString = "\"" + Taille + "\"";
        client.put("    taille", tailleString);

        String numString = "\"" + Numero_de_telephone_Client + "\"";
        client.put("    numero_de_telephone_Client", numString);

        String mailString = "\"" + Mail_Client + "\"";
        client.put("    mail_Client", mailString);

        String villeString = "\"" + Ville + "\"";
        client.put("    ville", villeString);

        String adresseString = "\"" + Adresse + "\"";
        client.put("    adresse", adresseString);

        String codeString = "\"" + Code_Postal + "\"";
        client.put("    code_Postal", codeString);

        // ecriture dans le fichier yaml
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("clients :\n");               
            writer.write("  - ");
            for (Map.Entry<String, String> entry : client.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

