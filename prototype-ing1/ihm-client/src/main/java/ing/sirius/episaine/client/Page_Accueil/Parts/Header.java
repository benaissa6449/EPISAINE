package ing.sirius.episaine.client;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    private JLabel titre;

    public Header(String title, JFrame frame) {
        setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/20));

        titre = new JLabel(title, SwingConstants.CENTER);
        titre.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        add(titre, BorderLayout.CENTER);
        setBackground(Color.WHITE);
    }

    public void switchLightFooter(boolean switchVar) {
        if (switchVar) {
            this.setBackground(Color.BLACK);
            titre.setForeground(Color.WHITE);
        }
        else {
            this.setBackground(Color.WHITE);
            titre.setForeground(Color.BLACK);
        }
    }
}
