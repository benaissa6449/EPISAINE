package ing.sirius.episaine.client.Page_Accueil.Parts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ing.sirius.episaine.client.Page_Accueil.Parts.listener.ButtonChangeState;

public class LeftMenu extends JPanel {
    private JToggleButton accueilButton;
    private JToggleButton settingButton;
    private JToggleButton sunButton;

    private Color darkgray = new Color(55,55,55);
    private Color lightgray = new Color(210,210,210);

    private ArrayList<JToggleButton> buttonList = new ArrayList<JToggleButton>();

    public JToggleButton getAccueilButton() {
        return accueilButton;
    }
    public JToggleButton getSettingButton() {
        return settingButton;
    }
    public JToggleButton getSunButton() {
        return sunButton;
    }

    public LeftMenu(JFrame frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(frame.getWidth()/15, frame.getHeight()));
        setBackground(lightgray);

        accueilButton = new JToggleButton();
        settingButton = new JToggleButton();
        sunButton = new JToggleButton();

        buttonList.add(accueilButton);
        buttonList.add(settingButton);
        buttonList.add(sunButton);

        accueilButton.setBorderPainted(false);
        settingButton.setBorderPainted(false);
        sunButton.setBorderPainted(false);

        accueilButton.setContentAreaFilled(false);
        settingButton.setContentAreaFilled(false);
        sunButton.setContentAreaFilled(false);

        accueilButton.setOpaque(true);
        settingButton.setOpaque(true);
        sunButton.setOpaque(true);

        accueilButton.setBackground(lightgray);
        settingButton.setBackground(lightgray);
        sunButton.setBackground(lightgray);

        accueilButton.addMouseListener(new ButtonChangeState());
        settingButton.addMouseListener(new ButtonChangeState());
        sunButton.addMouseListener(new ButtonChangeState());

        try {
            Image homeImg = ImageIO.read(getClass().getResource("/icons/light_mode_home.png"));
            accueilButton.setIcon(new ImageIcon(homeImg));

            Image settingImg = ImageIO.read(getClass().getResource("/icons/light_mode_setting.png"));
            settingButton.setIcon(new ImageIcon(settingImg));

            final Image sunImg = ImageIO.read(getClass().getResource("/icons/light_mode_sun.png"));
            sunButton.setIcon(new ImageIcon(sunImg));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        add(accueilButton);    
        add(settingButton);
        add(sunButton);
    }

    public void switchLightLeftMenu(boolean switchVar) {
        if (switchVar) {
            for (JToggleButton button : buttonList) {
                button.setBackground(darkgray);
            }
            this.setBackground(darkgray);
        }
        else {
            for (JToggleButton button : buttonList) {
                button.setBackground(lightgray);
            }
            this.setBackground(lightgray);
        }
    }
}
