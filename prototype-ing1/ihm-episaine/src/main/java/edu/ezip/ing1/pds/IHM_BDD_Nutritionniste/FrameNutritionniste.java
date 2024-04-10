package edu.ezip.ing1.pds.IHM_BDD_Nutritionniste;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class FrameNutritionniste extends JFrame implements ActionListener {
    private CardLayout cardLayout;

    private JPanel transitionPanel;

    private JButton next;
    private JButton previous;
    private JButton first;
    private JButton last;

    public FrameNutritionniste() throws IOException, SQLException, InterruptedException {
        // Parametre de la frame
        setTitle("Episaine");
        setSize(800,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // adding panel to the frame
        setTransitionPanel();
        add(transitionPanel);

        // TEMPORARY : adding buttons' panel to navigate between pages
        add(panelJButton(), BorderLayout.SOUTH);
    }

    private void setTransitionPanel(){
        // creation JPanel
        transitionPanel = new JPanel();

        // creation CardLayout to navigate between panels
        cardLayout = new CardLayout();
        transitionPanel.setLayout(cardLayout);

        // panels list
        ArrayList<JPanel> panelArray = new ArrayList<JPanel>();

        // panels we add to the list
        panelArray.add(new SelectPanelNutritionniste());
        panelArray.add(new InsertPanelNutritionniste());

        // insert list's componnent to the cardlayout
        for (JPanel jpanel : panelArray) {
            transitionPanel.add(jpanel);
        }
    }

    // TEMPORAIRE : button's to navigate between panels
    private JPanel panelJButton(){
        // panel for every buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);

        // buttons creation
        next = new JButton("<|");
        previous = new JButton("|>");
        first = new JButton("first");
        last = new JButton("last");

        // listener for buttons
        next.addActionListener(this);
        previous.addActionListener(this);
        first.addActionListener(this);
        last.addActionListener(this);

        // buttons' panel
        buttonPanel.add(next);
        buttonPanel.add(previous);
        buttonPanel.add(first);
        buttonPanel.add(last);

        return buttonPanel;
    }

    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object == next) {
            cardLayout.next(transitionPanel);
        }
        if (object == previous) {
            cardLayout.previous(transitionPanel);
        }
        if (object == first) {
            cardLayout.first(transitionPanel);
        }
        if (object == last) {
            cardLayout.last(transitionPanel);
        }
    }
}
