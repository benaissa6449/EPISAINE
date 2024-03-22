package ing.sirius.episaine.client.Page_Accueil.Parts.listener;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ing.sirius.episaine.client.Fenetre_Accueil;

public class LightModeButtonListener implements ActionListener {
    private final String lightModLabel = "L i g h t - M o d e";
    private final Logger lightLog = LoggerFactory.getLogger(lightModLabel);

    private final JToggleButton homeButton;
    private final JToggleButton settingButton;
    private final JToggleButton sunButton;

    public LightModeButtonListener(JToggleButton homeButton, JToggleButton settingButton, JToggleButton sunButton) {
        this.homeButton = homeButton;
        this.settingButton = settingButton;
        this.sunButton = sunButton;
    }

    public void actionPerformed(ActionEvent av) {
                    try {
                        boolean switchValue = Fenetre_Accueil.getSwitchValue();
                        if (switchValue) {
                            lightLog.info("Activation mode nuit");

                            Image homeImg = ImageIO.read(getClass().getResource("/icons/dark_mode_home.png"));
                            homeButton.setIcon(new ImageIcon(homeImg));
                            Image settingImg = ImageIO.read(getClass().getResource("/icons/dark_mode_setting.png"));
                            settingButton.setIcon(new ImageIcon(settingImg));
                            Image sunImg = ImageIO.read(getClass().getResource("/icons/dark_mode_sun.png"));
                            sunButton.setIcon(new ImageIcon(sunImg));

                            Fenetre_Accueil.switchLight();
                            Fenetre_Accueil.changeSwitchValue(switchValue);
                        }
                        else {
                            lightLog.info("Desactivation mode nuit");
                            
                            Image homeImg = ImageIO.read(getClass().getResource("/icons/light_mode_home.png"));
                            homeButton.setIcon(new ImageIcon(homeImg));
                            Image settingImg = ImageIO.read(getClass().getResource("/icons/light_mode_setting.png"));
                            settingButton.setIcon(new ImageIcon(settingImg));
                            Image sunImg = ImageIO.read(getClass().getResource("/icons/light_mode_sun.png"));
                            sunButton.setIcon(new ImageIcon(sunImg));
                            
                            Fenetre_Accueil.switchLight();
                            Fenetre_Accueil.changeSwitchValue(switchValue);
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
}
