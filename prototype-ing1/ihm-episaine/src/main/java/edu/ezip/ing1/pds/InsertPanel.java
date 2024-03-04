package edu.ezip.ing1.pds;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import edu.ezip.ing1.pds.client.InsertIntoYaml;
import edu.ezip.ing1.pds.client.MainInsertClient;

public class InsertPanel extends JPanel implements ActionListener {
    private JTextField nomTextField;
    private JTextField prenomTextField;
    private JTextField dateTextField;
    private JTextField poidsTextField;
    private JTextField genreTextField;
    private JTextField tailleTextField;
    private JTextField numTextField;
    private JTextField mailTextField;
    private JTextField villeTextField;
    private JTextField codePostalTextField;
    private JTextField adressePostaleTextField;

    private String studentsToBeInserted = "../episaine-insert-client/src/main/resources/clients-to-be-inserted.yaml";

    public InsertPanel() {
        // parametre du panel
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);

        // ajout panel avec les prompt
        add(promptPanel());

        // ajout bouton
        add(insertButton());
    }

    private JPanel promptPanel() {
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new GridBagLayout());
        jpanel.setBackground(Color.WHITE);

        // mise en place layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // ajout des elements au panel (jlabel et jtextfield)
        gbc.gridx = 0; // place les elements sur la premiere colonne (ici pour les jlabel)

        gbc.gridy = 0; // ligne 1
        JLabel nomLabel = new JLabel("Nom :");
        jpanel.add(nomLabel, gbc);
        
        gbc.gridy = 1; // ligne 2
        JLabel prenomLabel = new JLabel("Prénom :");
        jpanel.add(prenomLabel, gbc);
        
        gbc.gridy = 2; // ligne 3
        JLabel dateLabel = new JLabel("Date de naissance :");
        jpanel.add(dateLabel, gbc);
        
        gbc.gridy = 3; // ligne 4
        JLabel poidsLabel = new JLabel("Poids :");
        jpanel.add(poidsLabel, gbc);

        gbc.gridy = 4; // ligne 5
        JLabel genreLabel = new JLabel("Genre :");
        jpanel.add(genreLabel, gbc);

        gbc.gridy = 5; // ligne 6
        JLabel tailleLabel = new JLabel("Taille :");
        jpanel.add(tailleLabel, gbc);
        
        gbc.gridy = 6; // ligne 7
        JLabel numLabel = new JLabel("Numéro de téléphone :");
        jpanel.add(numLabel, gbc);
        
        gbc.gridy = 7; // ligne 8
        JLabel mailLabel = new JLabel("Adresse mail :");
        jpanel.add(mailLabel, gbc);
        
        gbc.gridy = 8; // ligne 9
        JLabel villeLabel = new JLabel("Ville :");
        jpanel.add(villeLabel, gbc);
        
        gbc.gridy = 9; // ligne 10
        JLabel codePostalLabel = new JLabel("Code postal :");
        jpanel.add(codePostalLabel, gbc);
        
        gbc.gridy = 10; // ligne 11
        JLabel adressePostaleLabel = new JLabel("Adresse postale :");
        jpanel.add(adressePostaleLabel, gbc);

        gbc.gridx = 1; // place les elements sur la deuxieme colonne (ici pour les jtextfield)
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0; // ligne 1
        nomTextField = new JTextField(20);
        jpanel.add(nomTextField,gbc);

        gbc.gridy = 1; // ligne 2
        prenomTextField = new JTextField(20);
        jpanel.add(prenomTextField,gbc);
                
        gbc.gridy = 2; // ligne 3
        dateTextField = new JTextField(20);
        jpanel.add(dateTextField,gbc);

        gbc.gridy = 3; // ligne 4
        poidsTextField = new JTextField(20);
        jpanel.add(poidsTextField,gbc);

        gbc.gridy = 4; // ligne 5
        genreTextField = new JTextField(20);
        jpanel.add(genreTextField,gbc);
        
        gbc.gridy = 5; // ligne 6
        tailleTextField = new JTextField(20);
        jpanel.add(tailleTextField,gbc);
        
        gbc.gridy = 6; // ligne 7
        numTextField = new JTextField(20);
        jpanel.add(numTextField,gbc);

        gbc.gridy = 7; // ligne 8
        mailTextField = new JTextField(20);
        jpanel.add(mailTextField,gbc);
        
        gbc.gridy = 8; // ligne 9
        villeTextField= new JTextField(20);
        jpanel.add(villeTextField,gbc);
        
        gbc.gridy = 9; // ligne 10
        codePostalTextField = new JTextField(20);
        jpanel.add(codePostalTextField,gbc);
        
        gbc.gridy = 10; // ligne 11
        adressePostaleTextField = new JTextField(20);
        jpanel.add(adressePostaleTextField,gbc);

        return jpanel;
    }

    private JButton insertButton(){
        JButton insertButton = new JButton("INSERT_CLIENT");
        insertButton.addActionListener(this);

        return insertButton;
    }

    public void actionPerformed(ActionEvent e) {
        String nomString = nomTextField.getText();
        String prenomString = prenomTextField.getText();
        String dateString = dateTextField.getText();
        String poidsString = poidsTextField.getText();
        String genreString = genreTextField.getText();
        String tailleString = tailleTextField.getText();
        String numString = numTextField.getText();
        String mailString = mailTextField.getText();
        String villeString = villeTextField.getText();
        String codePostalString = codePostalTextField.getText();
        String adressePostaleString = adressePostaleTextField.getText();

        try {
            if ((!nomString.isEmpty()) && (!prenomString.isEmpty()) && (!dateString.isEmpty()) &&
            (!poidsString.isEmpty()) && (!genreString.isEmpty()) && (!tailleString.isEmpty()) &&
            (!numString.isEmpty()) && (!mailString.isEmpty()) && (!villeString.isEmpty()) &&
            (!codePostalString.isEmpty()) && (!adressePostaleString.isEmpty())) {
                InsertIntoYaml.writeIntoYaml(nomString, prenomString, dateString, poidsString,
                genreString, tailleString, numString, mailString, villeString, codePostalString, adressePostaleString, studentsToBeInserted);
                // Sous windows
                String currentDirectory = getCurrentDirectory();
                String batFilePath = currentDirectory + "\\src\\main\\resources\\compile.bat";
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "start", "/min", batFilePath);
                Process process = builder.start();
                process.waitFor();
                
                /*
                // Sous linux
                String currentDirectory = System.getProperty("user.dir");
                String shellScriptPath = currentDirectory + "/src/main/resources/compile.sh";
                ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", shellScriptPath);
                processBuilder.directory(new File(currentDirectory));
                Process process = processBuilder.start();
                process.waitFor();
                */
            }
        }  
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static String getCurrentDirectory() {
        return System.getProperty("user.dir");
    }
    
}
