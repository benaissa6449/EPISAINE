package ing.sirius.episaine.client.Page_Accueil.Parts;

import javax.swing.*;

import ing.sirius.episaine.client.Page_Accueil.Parts.listener.ButtonChangeState;

import java.awt.*;
import java.util.ArrayList;

public class Footer extends JPanel {
    private ArrayList<JPanel> panelList = new ArrayList<JPanel>();
    private ArrayList<JButton> buttonList = new ArrayList<JButton>();

    private Color darkgray = new Color(55,55,55);
    private Color lightgray = new Color(210,210,210);

    public Footer() {
        JButton FAQ = new JButton("FAQ");
        JButton contactezNous = new JButton("Contactez nous");

        FAQ.setBackground(lightgray);
        FAQ.setOpaque(true);
        FAQ.setBorderPainted(false);
        FAQ.addMouseListener(new ButtonChangeState());

        contactezNous.setBackground(lightgray);
        contactezNous.setOpaque(true);
        contactezNous.setBorderPainted(false);
        contactezNous.addMouseListener(new ButtonChangeState());

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        footerPanel.add(FAQ, BorderLayout.WEST);
        footerPanel.add(contactezNous, BorderLayout.EAST);
        footerPanel.setBackground(lightgray);

        setLayout(new BorderLayout());
        add(footerPanel);

        panelList.add(footerPanel);

        buttonList.add(FAQ);
        buttonList.add(contactezNous);
    }

    public void switchLightFooter(boolean switchVar) {
        if (switchVar) {
            for (JPanel panel : panelList) {
                panel.setBackground(darkgray);
            }
            for (JButton button : buttonList) {
                button.setBackground(darkgray);
                button.setForeground(Color.WHITE);
            }
        }
        else {
            for (JPanel panel : panelList) {
                panel.setBackground(lightgray);
            }
            for (JButton button : buttonList) {
                button.setBackground(lightgray);
                button.setForeground(Color.BLACK);
            }
        }
    }
}
