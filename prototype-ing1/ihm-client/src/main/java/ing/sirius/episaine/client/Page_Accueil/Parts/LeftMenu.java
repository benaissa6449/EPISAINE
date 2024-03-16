package ing.sirius.episaine.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class LeftMenu extends JPanel {
    private JToggleButton accueilButton;
    private JToggleButton settingButton;
    private JToggleButton sunButton;

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
        setPreferredSize(new Dimension(frame.getWidth()/13, frame.getHeight()));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        accueilButton = new JToggleButton();
        settingButton = new JToggleButton();
        sunButton = new JToggleButton();

        accueilButton.setBorder(BorderFactory.createEmptyBorder());
        settingButton.setBorder(BorderFactory.createEmptyBorder());
        sunButton.setBorder(BorderFactory.createEmptyBorder());

        accueilButton.setContentAreaFilled(false);
        settingButton.setContentAreaFilled(false);
        sunButton.setContentAreaFilled(false);

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
            this.setBackground(Color.BLACK);
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        }
        else {
            this.setBackground(Color.WHITE);
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }
    }
}
