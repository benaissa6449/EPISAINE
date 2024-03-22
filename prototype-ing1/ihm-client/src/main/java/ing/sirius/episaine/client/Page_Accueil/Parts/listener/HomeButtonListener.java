package ing.sirius.episaine.client.Page_Accueil.Parts.listener;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeButtonListener implements ActionListener {
    private final String homeLabel = "H o m e - B u t t o n";
    private final Logger homeLog = LoggerFactory.getLogger(homeLabel);

    private JPanel panel;
    private CardLayout cardLayout;

    public HomeButtonListener(JPanel panel, CardLayout cardLayout) {
        this.panel = panel;
        this.cardLayout = cardLayout;
    }

    public void actionPerformed(ActionEvent e) {
        homeLog.info("Retour a la page d'accueil");
        cardLayout.first(panel);
    }
}
