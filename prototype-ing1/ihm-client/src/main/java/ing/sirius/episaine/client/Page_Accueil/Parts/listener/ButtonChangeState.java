package ing.sirius.episaine.client.Page_Accueil.Parts.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;

public class ButtonChangeState implements MouseListener {

    public void mouseEntered(MouseEvent e) {
        if (e.getSource().getClass() == JButton.class) {
            JButton button = (JButton) e.getSource();
            Color initialColor = button.getBackground();
            Color newColor = brighten(initialColor);
            button.setBackground(newColor);
        }
        if (e.getSource().getClass() == JToggleButton.class) {
            JToggleButton button = (JToggleButton) e.getSource();
            Color initialColor = button.getBackground();
            Color newColor = brighten(initialColor);
            button.setBackground(newColor);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource().getClass() == JButton.class) {
            JButton button = (JButton) e.getSource();
            Color initialColor = button.getBackground();
            Color newColor = darken(initialColor);
            button.setBackground(newColor);
        }
        if (e.getSource().getClass() == JToggleButton.class) {
            JToggleButton button = (JToggleButton) e.getSource();
            Color initialColor = button.getBackground();
            Color newColor = darken(initialColor);
            button.setBackground(newColor);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getSource().getClass() == JButton.class) {
            JButton button = (JButton) e.getSource();
            Color initialColor = button.getBackground();
            Color newColor = brighten(initialColor);
            button.setBackground(newColor);
        }
        if (e.getSource().getClass() == JToggleButton.class) {
            JToggleButton button = (JToggleButton) e.getSource();
            Color initialColor = button.getBackground();
            Color newColor = brighten(initialColor);
            button.setBackground(newColor);
        }
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource().getClass() == JButton.class) {
            JButton button = (JButton) e.getSource();
            Color initialColor = button.getBackground();
            Color newColor = darken(initialColor);
            button.setBackground(newColor);
        }
        if (e.getSource().getClass() == JToggleButton.class) {
            JToggleButton button = (JToggleButton) e.getSource();
            Color initialColor = button.getBackground();
            Color newColor = darken(initialColor);
            button.setBackground(newColor);
        }  
    }

    public void mouseClicked(MouseEvent e) {}

    public Color brighten(Color color) {
        int R = color.getRed();
        int G = color.getGreen();
        int B = color.getBlue();

        float i = 0.15f;

        Color newColor = new Color((int) Math.round(Math.min(255, R+(255*i))), (int) Math.round(Math.min(255,G+(255*i))), (int) Math.round(Math.min(255,B+(255*i))));
        return newColor;
    }

    public Color darken(Color color) {
        int R = color.getRed();
        int G = color.getGreen();
        int B = color.getBlue();

        float i = 0.15f;

        Color newColor = new Color((int) Math.round(Math.min(255, R-(255*i))), (int) Math.round(Math.min(255,G-(255*i))), (int) Math.round(Math.min(255,B-(255*i))));
        return newColor;
    }
}