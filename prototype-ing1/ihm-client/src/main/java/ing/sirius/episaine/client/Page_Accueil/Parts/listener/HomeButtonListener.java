package ing.sirius.episaine.client.Page_Accueil.Parts.listener;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class HomeButtonListener implements ActionListener {
    private JPanel panel;
    private CardLayout cardLayout;

    public HomeButtonListener(JPanel panel, CardLayout cardLayout) {
        this.panel = panel;
        this.cardLayout = cardLayout;
    }

    public void actionPerformed(ActionEvent e) {
        cardLayout.first(panel);
    }
}
