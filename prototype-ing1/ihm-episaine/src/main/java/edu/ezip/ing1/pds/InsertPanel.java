package edu.ezip.ing1.pds;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import edu.ezip.ing1.pds.client.InsertIntoYaml;
import edu.ezip.ing1.pds.client.MainInsertClient;

public class InsertPanel extends JPanel implements ActionListener {
    private JTextField firstNameTextField;
    private JTextField nameTextField;
    private JTextField groupTextField;
    private String studentsToBeInserted = "../xmart-insert-client/src/main/resources/students-to-be-inserted.yaml";

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
        JLabel firstNameLabel = new JLabel("First Name :");
        jpanel.add(firstNameLabel, gbc);
        
        gbc.gridy = 1; // ligne 2
        JLabel nameLabel = new JLabel("Name :");
        jpanel.add(nameLabel, gbc);
        
        gbc.gridy = 2; // ligne 3
        JLabel groupLabel = new JLabel("Group :");
        jpanel.add(groupLabel, gbc);
        

        gbc.gridx = 1; // place les elements sur la deuxieme colonne (ici pour les jtextfield)
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0; // ligne 1
        firstNameTextField = new JTextField(20);
        jpanel.add(firstNameTextField,gbc);

        gbc.gridy = 1; // ligne 2
        nameTextField = new JTextField(20);
        jpanel.add(nameTextField,gbc);
                
        gbc.gridy = 2; // ligne 3
        groupTextField = new JTextField(20);
        jpanel.add(groupTextField,gbc);

        return jpanel;
    }

    private JButton insertButton(){
        JButton insertButton = new JButton("INSERT_CLIENT");
        insertButton.addActionListener(this);

        return insertButton;
    }

    public void actionPerformed(ActionEvent e) {
        String nameText = nameTextField.getText();
        String firstNameText = firstNameTextField.getText();
        String groupText = groupTextField.getText();
        try {
            if ((!nameText.isEmpty()) && (!firstNameText.isEmpty()) && (!groupText.isEmpty())){
                InsertIntoYaml.writeIntoYaml(firstNameTextField.getText(), nameTextField.getText(), groupTextField.getText(), studentsToBeInserted);
                // Sous windows
                String currentDirectory = getCurrentDirectory();
                String batFilePath = currentDirectory + "\\src\\main\\resources\\compile.bat";
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "start", batFilePath);
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
