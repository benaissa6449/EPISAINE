package edu.ezip.ing1.pds.IHM_BDD_Recette;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import edu.ezip.ing1.pds.client.MainSelectRecette;
import edu.ezip.ing1.pds.client.StringAsciiTableRecette;


public class SelectPanelRecette extends JPanel implements ActionListener {
    private static DefaultTableModel defaultTableModel;
    private static JTable table;

    public SelectPanelRecette() {
        // parametre du panel
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);
        add(selectTextArea()); // ajout du jtextfield a la frame
        add(selectButton()); // ajout du bouton a la frame
    }

    private static JScrollPane selectTextArea() {
        defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };    
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(600,400));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // creation jscrollpane -- settings
        JScrollPane selectScrollPane = new JScrollPane(table);
        selectScrollPane.setPreferredSize(new Dimension(600,400));
        selectScrollPane.setBackground(Color.WHITE);
        selectScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        selectScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        return selectScrollPane;
    }

    // creer le bouton
    private JButton selectButton() {
        // creation jbutton
        JButton selectJButton = new JButton("SELECT_ALL_RECETTES");
        selectJButton.addActionListener(this);
        return selectJButton;
    }

    // ecouteur du bouton
    public void actionPerformed(ActionEvent e) {
        try {
            MainSelectRecette.main(new String[0]);
            String[][] recetteMatrix = StringAsciiTableRecette.getMatrix();
            defaultTableModel.setRowCount(0);
            
            // nom de chaque colonne
            String[] tableHeader = {"Nom de la recette", "Nombre de calories", "Ingrédients", "Instructions", "Régime alimentaire"}; 
            defaultTableModel.setColumnIdentifiers(tableHeader);
            
            // ajout du contenu de la table ligne par ligne
            for (String recette[] : recetteMatrix) {
                defaultTableModel.addRow(recette);
            }
            // ajustement de la largeur de chaque colonne
            for (int i = 0; i < table.getColumnCount(); i++) {
                int width = 0;
                for (int j = 0; j < table.getRowCount(); j++) {
                    int tempWidth = table.getValueAt(j, i).toString().length();
                    if (tempWidth > width) {
                        width = tempWidth;
                    }
                }
                int headerTempWidth = defaultTableModel.getColumnName(i).toString().length();
                if (headerTempWidth > width) {
                    width = headerTempWidth;
                }
                table.getColumnModel().getColumn(i).setPreferredWidth(width*10);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}