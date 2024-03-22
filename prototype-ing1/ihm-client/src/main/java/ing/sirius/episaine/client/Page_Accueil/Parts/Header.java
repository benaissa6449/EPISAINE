package ing.sirius.episaine.client.Page_Accueil.Parts;

import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class Header extends JPanel implements FrameInterface {
    private final String headerLabel = "H e a d e r";
    private final Logger headerLog = LoggerFactory.getLogger(headerLabel);
    
    private JLabel titre;

    public Header(String title, JFrame frame) {
        setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/20));

        titre = new JLabel(title, SwingConstants.CENTER);
        titre.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        add(titre, BorderLayout.CENTER);
        setBackground(lightgray);
    }

    public void switchLight(boolean switchVar) {
        if (switchVar) {
            headerLog.info("Mode nuit = " + switchVar);
            this.setBackground(darkgray);
            titre.setForeground(Color.WHITE);
        }
        else {
            headerLog.info("Mode nuit = " + switchVar);
            this.setBackground(lightgray);
            titre.setForeground(Color.BLACK);
        }
    }
}
