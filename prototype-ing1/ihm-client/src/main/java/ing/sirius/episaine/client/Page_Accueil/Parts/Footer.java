package ing.sirius.episaine.client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Footer extends JPanel {
    private ArrayList<JPanel> panelList = new ArrayList<JPanel>();
    private ArrayList<JButton> buttonList = new ArrayList<JButton>();

    public Footer() {
        JButton FAQ = new JButton("FAQ");
        JButton contactezNous = new JButton("Contactez nous");

        FAQ.setBackground(Color.WHITE);
        FAQ.setOpaque(true);
        //FAQ.setBorderPainted(false);

        contactezNous.setBackground(Color.WHITE);
        contactezNous.setOpaque(true);
        //contactezNous.setBorderPainted(false);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        footerPanel.add(FAQ, BorderLayout.WEST);
        footerPanel.add(contactezNous, BorderLayout.EAST);
        footerPanel.setBackground(Color.WHITE);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(footerPanel);

        panelList.add(footerPanel);

        buttonList.add(FAQ);
        buttonList.add(contactezNous);
    }

    public void switchLightFooter(boolean switchVar) {
        if (switchVar) {
            for (JPanel panel : panelList) {
                panel.setBackground(Color.BLACK);
            }
            for (JButton button : buttonList) {
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
            }
        }
        else {
            for (JPanel panel : panelList) {
                panel.setBackground(Color.WHITE);
            }
            for (JButton button : buttonList) {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }
        }
    }
}
