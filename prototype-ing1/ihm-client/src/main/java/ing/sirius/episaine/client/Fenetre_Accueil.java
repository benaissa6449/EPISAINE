package ing.sirius.episaine.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ing.sirius.episaine.client.Page_Accueil.Parts.Footer;
import ing.sirius.episaine.client.Page_Accueil.Parts.Header;
import ing.sirius.episaine.client.Page_Accueil.Parts.LeftMenu;
import ing.sirius.episaine.client.Page_Accueil.Parts.listener.HomeButtonListener;

public class Fenetre_Accueil extends JFrame {
    private JToggleButton homeButton;
    private JToggleButton settingButton;
    private JToggleButton sunButton;
    
    private static boolean switchValue = true;
    
    public static CardLayout cardLayout = new CardLayout();

    public Fenetre_Accueil() {
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

        homeButton = lm.getAccueilButton();
        settingButton = lm.getSettingButton();
        sunButton = lm.getSunButton(); 

        JPanel homePanel = new JPanel();
        homePanel.setLayout(cardLayout);
        homePanel.setBackground(Color.BLUE);
        mainPanel.add(homePanel, BorderLayout.CENTER);

        // light or dark mode
        sunButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent av) {
                    try {
                        if (switchValue) {
                            Image homeImg = ImageIO.read(getClass().getResource("/icons/dark_mode_home.png"));
                            homeButton.setIcon(new ImageIcon(homeImg));
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
                            homeButton.setIcon(new ImageIcon(homeImg));
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
        homeButton.addActionListener(new HomeButtonListener(homePanel, cardLayout));
    }
}
