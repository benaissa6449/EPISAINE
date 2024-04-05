package ing.sirius.episaine.client.Page_Accueil.Parts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ing.sirius.episaine.client.Page_Accueil.Parts.listener.ButtonChangeState;

public class FirstPage extends JPanel implements FrameInterface, ButtonInterface{
    private final String fpLabel = "F i r s t - P a g e";
    private final Logger fpLog = LoggerFactory.getLogger(fpLabel); 

    private ArrayList<JPanel> panelList = new ArrayList<JPanel>();
    private ArrayList<JButton> buttonList = new ArrayList<JButton>();

    private JTextPane presentationTextPane;

    private Header header;
    private JComboBox<String> comboBox;

    public FirstPage() throws FileNotFoundException, IOException {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel mainPanel = new JPanel();
        header = new Header("Page d'accueil", mainPanel);
        mainPanel.setLayout(new GridLayout(1,2));
        JPanel leftPanel = new JPanel(); 
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new GridLayout());
        leftPanel.setPreferredSize(getMaximumSize());
        File file = new File("src/main/resources/presentation.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);        

        JPanel presentationPanel = new JPanel();

        presentationPanel.setLayout(new GridLayout(3, 1));
        presentationPanel.setBackground(Color.WHITE);
        presentationTextPane = new JTextPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(15,15);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                //Draws the rounded opaque panel with borders.
                graphics.setColor(getForeground());
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
            }
        };
        presentationTextPane.read(bufferedReader, String.class);
        while (bufferedReader.readLine()!= null) {}
        bufferedReader.close();
        presentationTextPane.setEditable(false);
        presentationTextPane.setOpaque(true);
        presentationPanel.add(new JLabel());
        presentationPanel.add(presentationTextPane);
        
        StyledDocument doc = presentationTextPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        leftPanel.add(presentationPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1));

        Vector<String> storeVector = new Vector<String>();

        // TODO : Initialize list with grandeSurface table
        storeVector.add("Test1");
        storeVector.add("Test2");

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout());
        comboBoxPanel.setBackground(Color.WHITE);
        comboBox = new JComboBox<>(storeVector);
        comboBox.setOpaque(false);
        comboBox.setBackground(lightgray);
        comboBox.setPreferredSize(new Dimension(300,50));
        comboBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        comboBoxPanel.add(comboBox, BorderLayout.CENTER);
        DefaultListCellRenderer defaultListCellRenderer = new DefaultListCellRenderer();
        defaultListCellRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        comboBox.setRenderer(defaultListCellRenderer);

        JPanel groceryPanel = new JPanel();
        groceryPanel.setLayout(new FlowLayout());
        groceryPanel.setBackground(Color.WHITE);
        JButton groceryButton = new JButton("Obtenir ma liste de course");
        groceryButton.setPreferredSize(new Dimension(300,50));
        groceryPanel.add(groceryButton, BorderLayout.CENTER);
        groceryButton.setBackground(lightgray);

        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new FlowLayout());
        reviewPanel.setBackground(Color.WHITE);
        JButton reviewButton = new JButton("Consulter les commentaires");
        reviewButton.setPreferredSize(new Dimension(300,50));
        reviewPanel.add(reviewButton, BorderLayout.CENTER);
        reviewButton.setBackground(lightgray);

        rightPanel.add(comboBoxPanel);
        rightPanel.add(groceryPanel);
        rightPanel.add(reviewPanel);
        rightPanel.setBackground(Color.WHITE);

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        

        panelList.add(this);
        panelList.add(leftPanel);
        panelList.add(rightPanel);
        panelList.add(presentationPanel);
        panelList.add(comboBoxPanel);
        panelList.add(groceryPanel);
        panelList.add(reviewPanel);

        buttonList.add(groceryButton);
        buttonList.add(reviewButton);
        setStateChanged();
    }

    public void setStateChanged() {
        for (JButton button : buttonList) {
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.addMouseListener(new ButtonChangeState());
        }
        comboBox.addMouseListener(new ButtonChangeState());
    }

    public void switchLight(boolean switchVar) {
        if (switchVar) {
            fpLog.info("Mode nuit = " + switchVar);
            for (JButton button : buttonList) {
                button.setBackground(darkgray);
                button.setForeground(Color.WHITE);
            }
            for (JPanel panel : panelList) {
                panel.setBackground(Color.GRAY);
            }
            presentationTextPane.setBackground(Color.BLACK);
            presentationTextPane.setForeground(Color.WHITE);
            comboBox.setBackground(darkgray);
            comboBox.setForeground(Color.WHITE);
            header.switchLight(switchVar);
        }
        else {
            fpLog.info("Mode nuit = " + switchVar);
            for (JButton button : buttonList) {
                button.setBackground(lightgray);
                button.setForeground(Color.BLACK);
            }
            for (JPanel panel : panelList) {
                panel.setBackground(Color.WHITE);
            }
            presentationTextPane.setBackground(Color.WHITE);
            presentationTextPane.setForeground(Color.BLACK);
            comboBox.setBackground(lightgray);
            comboBox.setForeground(Color.BLACK);
            header.switchLight(switchVar);
        }
    }
}
