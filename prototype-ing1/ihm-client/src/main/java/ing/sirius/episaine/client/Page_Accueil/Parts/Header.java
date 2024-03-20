package ing.sirius.episaine.client.Page_Accueil.Parts;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    private JLabel titre;
    
    private Color darkgray = new Color(55,55,55);
    private Color lightgray = new Color(210,210,210);

    public Header(String title, JFrame frame) {
        setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/20));

        titre = new JLabel(title, SwingConstants.CENTER);
        titre.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        add(titre, BorderLayout.CENTER);
        setBackground(lightgray);
    }

    public void switchLightFooter(boolean switchVar) {
        if (switchVar) {
            this.setBackground(darkgray);
            titre.setForeground(Color.WHITE);
        }
        else {
            this.setBackground(lightgray);
            titre.setForeground(Color.BLACK);
        }
    }
}
