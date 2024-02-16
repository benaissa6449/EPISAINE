package edu.ezip.ing1.pds;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.Flow;

import javax.swing.*;

import de.vandermeer.asciitable.AsciiTable;
import edu.ezip.ing1.pds.client.MainSelectClient;
import edu.ezip.ing1.pds.client.SelectAsciiTable;;

public class SelectPanel extends JPanel implements ActionListener {

    private static AsciiTable asciiTable;
    private static JTextArea selectJTextAreaVar;

    public void setAsciiTable(AsciiTable asciiTableVar) {asciiTable = asciiTableVar;}

    public SelectPanel() {
        // parametre du panel
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);
        add(selectTextArea()); // ajout du jtextfield a la frame
        add(selectButton()); // ajout du bouton a la frame
    }

    private static JScrollPane selectTextArea() {        
        // creation jtextarea
        JTextArea selectJTextArea = new JTextArea(20,70);
        selectJTextArea.setEditable(false);
        selectJTextArea.setFocusable(false);
        selectJTextArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        selectJTextAreaVar = selectJTextArea;

        // creation jscrollpane
        JScrollPane selectScrollPane = new JScrollPane(selectJTextArea);
        selectScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        selectScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        // jpanel pour le header du jscrollpane
        JPanel columnHeaderPanel = new JPanel();
        columnHeaderPanel.setBackground(Color.WHITE);
        columnHeaderPanel.setLayout(new GridLayout(1,3));

        columnHeaderPanel.add(new JLabel("First Name"));
        columnHeaderPanel.add(new JLabel("Name"));
        columnHeaderPanel.add(new JLabel("Group"));

        selectScrollPane.setColumnHeaderView(columnHeaderPanel);
        /*
        // Nom de chaque colonne pour la table cliente
        columnHeaderPanel.add(new JLabel("ID"));
        columnHeaderPanel.add(new JLabel("Nom"));
        columnHeaderPanel.add(new JLabel("Prénom"));
        columnHeaderPanel.add(new JLabel("Date de naissance"));
        columnHeaderPanel.add(new JLabel("Poids (en kg)"));
        columnHeaderPanel.add(new JLabel("Genre"));
        columnHeaderPanel.add(new JLabel("Taille"));
        columnHeaderPanel.add(new JLabel("Numéro de téléphone"));
        columnHeaderPanel.add(new JLabel("Mail"));
        columnHeaderPanel.add(new JLabel("Ville"));
        columnHeaderPanel.add(new JLabel("Code postal"));
        columnHeaderPanel.add(new JLabel("Adresse")); 
        
        // set column header
        selectScrollPane.setColumnHeaderView(columnHeaderPanel);
        */
        // dimension du select scroll pane
        selectScrollPane.setBounds(new Rectangle(500,400));

        return selectScrollPane;
    }

    private JButton selectButton() {
        // creation jbutton
        JButton selectJButton = new JButton("SELECT_ALL_CLIENT");
        selectJButton.addActionListener(this);
        return selectJButton;
    }

    // ecouteur du bouton
    public void actionPerformed(ActionEvent e) {
        try {
            MainSelectClient.main(new String[0]);
            asciiTable = SelectAsciiTable.getAsciiTable();
            selectJTextAreaVar.setText(asciiTable.render());
        }
        catch(Exception ex){
            ex.getMessage();
        }
    }
}
