package ing.sirius.episaine.client.IHM_BDD;

import java.awt.*;
import javax.swing.*;

public class PanelBDD extends JPanel {
    public PanelBDD() {

        // adding panel to the frame
        setTransitionPanel();

        // TEMPORARY : adding buttons' panel to navigate between pages
        add(panelJButton(), BorderLayout.SOUTH);
    }

    private void setTransitionPanel(){
        // creation JPanel

        // creation CardLayout to navigate between panels

        // panels we add to the list
        add(new SelectPanel());
        add(new InsertPanel());
    }

    // TEMPORAIRE : buttons to navigate between panels
    private JPanel panelJButton(){
        // panel for every buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);

        return buttonPanel;
    }
}
