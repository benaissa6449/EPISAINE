package edu.ezip.ing1.pds.IHM_BDD;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import edu.ezip.ing1.pds.business.dto.Client;
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

    public InsertPanel() {
        // panel's settings
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);

        // adding prompts panel
        add(promptPanel());

        // add buttons
        add(insertButton());
    }

    private JPanel promptPanel() {
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new GridBagLayout());
        jpanel.setBackground(Color.WHITE);

        // layout settings
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // adding jlabel and jtextfield to the layout (jlabel and jtextfield)
        gbc.gridx = 0; // first column (jlabel)

        gbc.gridy = 0; // line 1
        JLabel nomLabel = new JLabel("Nom :");
        jpanel.add(nomLabel, gbc);
        
        gbc.gridy = 1; // line 2
        JLabel prenomLabel = new JLabel("Prénom :");
        jpanel.add(prenomLabel, gbc);
        
        gbc.gridy = 2; // line 3
        JLabel dateLabel = new JLabel("Date de naissance :");
        jpanel.add(dateLabel, gbc);
        
        gbc.gridy = 3; // line 4
        JLabel poidsLabel = new JLabel("Poids :");
        jpanel.add(poidsLabel, gbc);

        gbc.gridy = 4; // line 5
        JLabel genreLabel = new JLabel("Genre :");
        jpanel.add(genreLabel, gbc);

        gbc.gridy = 5; // line 6
        JLabel tailleLabel = new JLabel("Taille :");
        jpanel.add(tailleLabel, gbc);
        
        gbc.gridy = 6; // line 7
        JLabel numLabel = new JLabel("Numéro de téléphone :");
        jpanel.add(numLabel, gbc);
        
        gbc.gridy = 7; // line 8
        JLabel mailLabel = new JLabel("Adresse mail :");
        jpanel.add(mailLabel, gbc);
        
        gbc.gridy = 8; // line 9
        JLabel villeLabel = new JLabel("Ville :");
        jpanel.add(villeLabel, gbc);
        
        gbc.gridy = 9; // line 10
        JLabel codePostalLabel = new JLabel("Code postal :");
        jpanel.add(codePostalLabel, gbc);
        
        gbc.gridy = 10; // line 11
        JLabel adressePostaleLabel = new JLabel("Adresse postale :");
        jpanel.add(adressePostaleLabel, gbc);

        gbc.gridx = 1; // second column (jtextfield)
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0; // line 1
        nomTextField = new JTextField(20);
        jpanel.add(nomTextField,gbc);

        gbc.gridy = 1; // line 2
        prenomTextField = new JTextField(20);
        jpanel.add(prenomTextField,gbc);
                
        gbc.gridy = 2; // line 3
        dateTextField = new JTextField(20);
        jpanel.add(dateTextField,gbc);

        gbc.gridy = 3; // line 4
        poidsTextField = new JTextField(20);
        jpanel.add(poidsTextField,gbc);

        gbc.gridy = 4; // line 5
        genreTextField = new JTextField(20);
        jpanel.add(genreTextField,gbc);
        
        gbc.gridy = 5; // line 6
        tailleTextField = new JTextField(20);
        jpanel.add(tailleTextField,gbc);
        
        gbc.gridy = 6; // line 7
        numTextField = new JTextField(20);
        jpanel.add(numTextField,gbc);

        gbc.gridy = 7; // line 8
        mailTextField = new JTextField(20);
        jpanel.add(mailTextField,gbc);
        
        gbc.gridy = 8; // line 9
        villeTextField= new JTextField(20);
        jpanel.add(villeTextField,gbc);
        
        gbc.gridy = 9; // line 10
        codePostalTextField = new JTextField(20);
        jpanel.add(codePostalTextField,gbc);
        
        gbc.gridy = 10; // line 11
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
        Client client = new Client();
        client.setnomClient(nomTextField.getText());
        client.setprenomClient(prenomTextField.getText());
        client.setdateDeNaissanceClient(dateTextField.getText());
        client.setPoids(poidsTextField.getText());
        client.setGenre(genreTextField.getText());
        client.setTaille(tailleTextField.getText());
        client.setNumero_de_telephone_Client(numTextField.getText());
        client.setMail_Client(mailTextField.getText());
        client.setVille(villeTextField.getText());
        client.setCode_Postal(codePostalTextField.getText());
        client.setAdresse(adressePostaleTextField.getText());

        try {
            MainInsertClient.setClient(client);
            MainInsertClient.main(new String[0]);
        }  
        catch(Exception ex){
            ex.printStackTrace();
        }
    }    
}
