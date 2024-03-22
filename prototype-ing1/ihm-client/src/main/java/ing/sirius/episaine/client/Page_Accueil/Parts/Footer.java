package ing.sirius.episaine.client.Page_Accueil.Parts;

import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ing.sirius.episaine.client.Page_Accueil.Parts.listener.ButtonChangeState;

import java.awt.*;
import java.util.ArrayList;

public class Footer extends JPanel implements FrameInterface, ButtonInterface {
    private final String footerLabel = "F o o t e r";
    private final Logger footerLog = LoggerFactory.getLogger(footerLabel);

    private ArrayList<JPanel> panelList = new ArrayList<JPanel>();
    private ArrayList<JButton> buttonList = new ArrayList<JButton>();

    public Footer() {
        JButton FAQ = new JButton("FAQ");
        JButton contactezNous = new JButton("Contactez nous");

        FAQ.setBackground(lightgray);
        FAQ.setOpaque(true);
        FAQ.setBorderPainted(false);

        contactezNous.setBackground(lightgray);
        contactezNous.setOpaque(true);
        contactezNous.setBorderPainted(false);

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
        setStateChanged();
    }

    public void switchLight(boolean switchVar) {
        if (switchVar) {
            footerLog.info("Mode nuit = " + switchVar);
            for (JPanel panel : panelList) {
                panel.setBackground(darkgray);
            }
            for (JButton button : buttonList) {
                button.setBackground(darkgray);
                button.setForeground(Color.WHITE);
            }
        }
        else {
            footerLog.info("Mode nuit = " + switchVar);
            for (JPanel panel : panelList) {
                panel.setBackground(lightgray);
            }
            for (JButton button : buttonList) {
                button.setBackground(lightgray);
                button.setForeground(Color.BLACK);
            }
        }
    }

    public void setStateChanged(){
        for (JButton button : buttonList) {
            button.addMouseListener(new ButtonChangeState());
        }
    }
}
