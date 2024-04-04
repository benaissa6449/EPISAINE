package ing.sirius.episaine.client.IHM_BDD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.business.dto.Information;
import edu.ezip.ing1.pds.business.dto.Informations;
import edu.ezip.ing1.pds.client.SelectByClient;
import ing.sirius.episaine.client.Page_Accueil.Parts.ButtonInterface;
import ing.sirius.episaine.client.Page_Accueil.Parts.FrameInterface;
import ing.sirius.episaine.client.Page_Accueil.Parts.listener.ButtonChangeState;

public class SelectPanel extends JPanel implements ActionListener, ButtonInterface, FrameInterface {
    private static DefaultTableModel defaultTableModel;

    private static JTable table;
    private static JComboBox<String> comboBox;
    private static JButton selectJButton;

    public SelectPanel() {
        // parametre du panel
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);
        add(comboBoxPanel(), BorderLayout.NORTH); // add combobox to frame
        add(selectTextArea(), BorderLayout.CENTER); // add jtextfield to frame
        add(selectButton(), BorderLayout.SOUTH); // add buttons to frame
        setStateChanged();
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
        //table.setFont(new Font("Arial", Font.PLAIN, 30));
        //table.setRowHeight(table.getRowHeight() + 30);
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
        selectJButton = new JButton("Lancer la selection");
        selectJButton.setBackground(mediumLightGray);
        selectJButton.setOpaque(true);
        selectJButton.setBorderPainted(false);
        selectJButton.addActionListener(this);
        return selectJButton;
    }

    private JComboBox<String> comboBoxPanel() {
        String[] requestOrders = {"SELECT_ALL_CLIENTS", "SELECT_ALL_INFORMATIONS"}; 
        comboBox = new JComboBox<>(requestOrders);
        return comboBox;
    }
    // ecouteur du bouton
    public void actionPerformed(ActionEvent e) {
        try {
            SelectByClient selectByClient = new SelectByClient();
            String requestOrder = comboBox.getItemAt(comboBox.getSelectedIndex());
            
            defaultTableModel.setRowCount(0);
            
            switch (requestOrder) {
                case "SELECT_ALL_CLIENTS":
                    Clients clients = (Clients) selectByClient.getValue(requestOrder);
                    // column name
                    String[] tableHeaderClient = {"Nom", "Prénom", "Date de naissance", "Poids (en kg)", "Genre", "Taille", "Numéro de téléphone", "Mail", "Ville", "Code postal", "Adresse"}; 
                    defaultTableModel.setColumnIdentifiers(tableHeaderClient);
                    
                    for (Client client : clients.getClients()) {
                        String[] row = client.getValue();
                        defaultTableModel.addRow(row);
                    }
                    break;
                case "SELECT_ALL_INFORMATIONS":
                    Informations informations = (Informations) selectByClient.getValue(requestOrder);
                    // column name
                    String[] tableHeaderInformation = {"ID_Client", "But", "Allergie", "Nombre de repas"}; 
                    defaultTableModel.setColumnIdentifiers(tableHeaderInformation);
                    
                    for (Information information : informations.getInformations()) {
                        String[] row = information.getValue();
                        defaultTableModel.addRow(row);
                    }
                    break;
                default:
                    break;
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
            ex.getMessage();
        }
    }

    public void setStateChanged(){
        selectJButton.addMouseListener(new ButtonChangeState());
    }

    public void switchLight(boolean switchValue) {
        if (switchValue) {
            selectJButton.setBackground(mediumDarkGray);
            selectJButton.setForeground(Color.WHITE);
        }
        else {
            selectJButton.setBackground(mediumLightGray);
            selectJButton.setForeground(Color.BLACK);
        }
    }
}
