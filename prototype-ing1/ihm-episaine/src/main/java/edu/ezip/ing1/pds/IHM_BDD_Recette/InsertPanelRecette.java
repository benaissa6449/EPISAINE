package edu.ezip.ing1.pds.IHM_BDD_Recette;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.client.MainInsertRecette;

public class InsertPanelRecette extends JPanel implements ActionListener {
    private JTextField nomRecetteTextField;
    private JTextField nombreCaloriesTextField;
    private JTextField ingredientsTextField;
    private JTextField instructionsTextField;
    private JTextField regimeAlimentaireTextField;

    public InsertPanelRecette() {
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
        JLabel nomRecetteLabel = new JLabel("Nom recette:");
        jpanel.add(nomRecetteLabel, gbc);
        
        gbc.gridy = 1; // line 2
        JLabel nombreCaloriesLabel = new JLabel("Nombre de calories:");
        jpanel.add(nombreCaloriesLabel, gbc);
        
        gbc.gridy = 2; // line 3
        JLabel ingredientsLabel = new JLabel("Ingrédients:");
        jpanel.add(ingredientsLabel, gbc);
        
        gbc.gridy = 3; // line 4
        JLabel instructionsLabel = new JLabel("Instructions:");
        jpanel.add(instructionsLabel, gbc);

        gbc.gridy = 4; // line 5
        JLabel regimeAlimentaireLabel = new JLabel("Régime alimentaire:");
        jpanel.add(regimeAlimentaireLabel, gbc);

        
        gbc.gridx = 1; // second column (jtextfield)
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0; // line 1
        nomRecetteTextField = new JTextField(20);
        jpanel.add(nomRecetteTextField,gbc);

        gbc.gridy = 1; // line 2
        nombreCaloriesTextField = new JTextField(20);
        jpanel.add(nombreCaloriesTextField,gbc);
                
        gbc.gridy = 2; // line 3
        ingredientsTextField = new JTextField(20);
        jpanel.add(ingredientsTextField,gbc);

        gbc.gridy = 3; // line 4
        instructionsTextField = new JTextField(20);
        jpanel.add(instructionsTextField,gbc);

        gbc.gridy = 4; // line 5
        regimeAlimentaireTextField = new JTextField(20);
        jpanel.add(regimeAlimentaireTextField,gbc);
        

        return jpanel;
    }

    private JButton insertButton(){
        JButton insertButton = new JButton("INSERT_RECETTE");
        insertButton.addActionListener(this);

        return insertButton;
    }

    public void actionPerformed(ActionEvent e) {
        Recette recette = new Recette();
        recette.setNom_Recette(nomRecetteTextField.getText());
        recette.setNombre_de_Calories(Integer.parseInt(nombreCaloriesTextField.getText()));
        recette.setIngredients(ingredientsTextField.getText());
        recette.setInstructions(instructionsTextField.getText());
        recette.setRegimeAlimentaire(regimeAlimentaireTextField.getText());
        try {
            MainInsertRecette.setRecette(recette);
            MainInsertRecette.main(new String[0]);
        }  
        catch(Exception ex){
            ex.printStackTrace();
        }
    }    
}
