package edu.ezip.ing1.pds;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
    private CardLayout cardLayout;

    private JPanel transitionPanel;

    private JButton next;
    private JButton previous;
    private JButton first;
    private JButton last;

    public Frame() throws IOException, SQLException, InterruptedException {
        // Parametre de la frame
        setTitle("Episaine");
        setSize(800,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ajout du panel qui change
        setTransitionPanel();
        add(transitionPanel);

        // TEMPORAIRE : ajout du panel des boutons pour les transitions
        add(panelJButton(), BorderLayout.SOUTH);
    }

    private void setTransitionPanel(){
        // Creation JPanel
        transitionPanel = new JPanel();

        // Creation CardLayout pour transition entre les jpanels
        cardLayout = new CardLayout();
        transitionPanel.setLayout(cardLayout);

        // Liste de panels
        ArrayList<JPanel> panelArray = new ArrayList<JPanel>();

        // JPanel qu'on met dans la liste
        panelArray.add(new SelectPanel());
        panelArray.add(new InsertPanel());

        // On insere les elements de la liste dans le CardLayout
        for (JPanel jpanel : panelArray) {
            transitionPanel.add(jpanel);
        }
    }

    // TEMPORAIRE : boutons pour naviguer entre les panels
    private JPanel panelJButton(){
        // panel qui regroupe les boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);

        // creation des boutons
        next = new JButton("next");
        previous = new JButton("previous");
        first = new JButton("first");
        last = new JButton("last");

        // associe l'ecouteur aux boutons
        next.addActionListener(this);
        previous.addActionListener(this);
        first.addActionListener(this);
        last.addActionListener(this);

        // panel de boutons
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
