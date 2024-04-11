package edu.ezip.ing1.pds.IHM_BDD_Nutritionniste;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import edu.ezip.ing1.pds.business.dto.Nutritionniste;
import edu.ezip.ing1.pds.client.MainInsertNutritionniste;

public class InsertPanelNutritionniste extends JPanel implements ActionListener {

    private JTextField nomNutritionnisteTextField;
    private JTextField prenomNutritionnisteTextField;
    private JTextField numerotelephoneNutritionnisteTextField;
    private JTextField mailNutritionnisteTextField;


    public InsertPanelNutritionniste() {
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
        JLabel nomNutritionnisteLabel = new JLabel("Nom Nutritionniste:");
        jpanel.add(nomNutritionnisteLabel, gbc);
        
        gbc.gridy = 1; // line 2
        JLabel prenomNutritionnisteLabel = new JLabel("Prenom Nutritionniste:");
        jpanel.add(prenomNutritionnisteLabel, gbc);
        
        gbc.gridy = 2; // line 3
        JLabel numerotelephoneNutritionnisteLabel = new JLabel("Numéro de Téléphone Nutritionniste:");
        jpanel.add(numerotelephoneNutritionnisteLabel, gbc);

        gbc.gridy = 3; // line 4
        JLabel mailNutritionnisteLabel = new JLabel("Adresse mail Nutritionniste:");
        jpanel.add(mailNutritionnisteLabel, gbc);

        
        gbc.gridx = 1; // second column (jtextfield)
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0; // line 1
        nomNutritionnisteTextField = new JTextField(20);
        jpanel.add(nomNutritionnisteTextField,gbc);

        gbc.gridy = 1; // line 2
        prenomNutritionnisteTextField = new JTextField(20);
        jpanel.add(prenomNutritionnisteTextField,gbc);
                
        gbc.gridy = 2; // line 3
        numerotelephoneNutritionnisteTextField = new JTextField(20);
        jpanel.add(numerotelephoneNutritionnisteTextField,gbc);


        gbc.gridy = 3; // line 5
        mailNutritionnisteTextField = new JTextField(20);
        jpanel.add(mailNutritionnisteTextField,gbc);
        

        return jpanel;
    }

    private JButton insertButton(){
        JButton insertButton = new JButton("INSERT_NUTRITIONNISTE");
        insertButton.addActionListener(this);

        return insertButton;
    }

    public void actionPerformed(ActionEvent e) {
        Nutritionniste nutritionniste = new Nutritionniste();
        nutritionniste.setnom_N(nomNutritionnisteTextField.getText());
        nutritionniste.setprenom_N(prenomNutritionnisteTextField.getText());
        nutritionniste.setnumero_de_telephone_N(numerotelephoneNutritionnisteTextField.getText());
        nutritionniste.setmail_N(mailNutritionnisteTextField.getText());


        try {
            MainInsertNutritionniste.setNutritionniste(nutritionniste);
            MainInsertNutritionniste.main(new String[0]);
        }  
        catch(Exception ex){
            ex.printStackTrace();
        }
    }    
}
