package ing.sirius.episaine.client;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Fenetre_Accueil extends JFrame {
    private JToggleButton accueilButton;
    private JToggleButton settingButton;
    private JToggleButton sunButton;
    private static boolean switchValue = true;

    public Fenetre_Accueil() throws Exception {
        JPanel mainPanel = new JPanel();
        
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Header header = new Header("Page d'accueil", this);
        Footer footer = new Footer();

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(footer, BorderLayout.SOUTH);

        LeftMenu lm = new LeftMenu(this);

        add(mainPanel, BorderLayout.CENTER);
        add(lm, BorderLayout.WEST);

        accueilButton = lm.getAccueilButton();
        settingButton = lm.getSettingButton();
        sunButton = lm.getSunButton(); 

        // light or dark mode
        sunButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent av) {
                    try {
                        if (switchValue) {
                            Image homeImg = ImageIO.read(getClass().getResource("/icons/dark_mode_home.png"));
                            accueilButton.setIcon(new ImageIcon(homeImg));
                            Image settingImg = ImageIO.read(getClass().getResource("/icons/dark_mode_setting.png"));
                            settingButton.setIcon(new ImageIcon(settingImg));
                            Image sunImg = ImageIO.read(getClass().getResource("/icons/dark_mode_sun.png"));
                            sunButton.setIcon(new ImageIcon(sunImg));

                            header.switchLightFooter(switchValue);
                            footer.switchLightFooter(switchValue);
                            lm.switchLightLeftMenu(switchValue);

                            switchValue = false;
                        }
                        else {
                            Image homeImg = ImageIO.read(getClass().getResource("/icons/light_mode_home.png"));
                            accueilButton.setIcon(new ImageIcon(homeImg));
                            Image settingImg = ImageIO.read(getClass().getResource("/icons/light_mode_setting.png"));
                            settingButton.setIcon(new ImageIcon(settingImg));
                            Image sunImg = ImageIO.read(getClass().getResource("/icons/light_mode_sun.png"));
                            sunButton.setIcon(new ImageIcon(sunImg));
                            
                            header.switchLightFooter(switchValue);
                            footer.switchLightFooter(switchValue);
                            lm.switchLightLeftMenu(switchValue);

                            switchValue = true;
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }
}
