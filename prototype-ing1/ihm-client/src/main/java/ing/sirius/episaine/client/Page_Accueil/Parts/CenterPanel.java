package ing.sirius.episaine.client.Page_Accueil.Parts;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import ing.sirius.episaine.client.Page_Accueil.Parts.IHM_BDD.InsertClientPanel;
import ing.sirius.episaine.client.Page_Accueil.Parts.IHM_BDD.SelectPanel;
import ing.sirius.episaine.client.Page_Accueil.Parts.listener.ButtonChangeState;

public class CenterPanel extends JPanel implements FrameInterface, ActionListener, ButtonInterface {
    private final JPanel transitionPanel;
    private final CardLayout cardLayout;

    private final ArrayList<JPanel> panelList = new ArrayList<JPanel>();
    private final ArrayList<JButton> buttonList = new ArrayList<JButton>();

    private SelectPanel selectPanel;
    private InsertClientPanel insertPanel;

    // temp
    private final JButton next;
    private final JButton previous; 

    public CenterPanel(JPanel transitionPanel, CardLayout cardLayout) {
        this.transitionPanel = transitionPanel;
        this.cardLayout = cardLayout;

        setLayout(new BorderLayout());
        transitionPanel.setLayout(cardLayout);

        selectPanel = new SelectPanel();
        insertPanel = new InsertClientPanel();

        transitionPanel.add(selectPanel);
        transitionPanel.add(insertPanel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        next = new JButton("Next");
        next.setOpaque(true);
        next.setBorderPainted(false);
        next.addActionListener(this);
        next.setBackground(mediumLightGray);
        previous = new JButton("Previous");
        previous.setOpaque(true);
        previous.setBorderPainted(false);
        previous.addActionListener(this);
        previous.setBackground(mediumLightGray);
        buttonPanel.add(previous);
        buttonPanel.add(next);

        add(transitionPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // add panel to list
        panelList.add(transitionPanel);
        panelList.add(buttonPanel);
        panelList.add(selectPanel);
        panelList.add(insertPanel);

        // add button to list
        buttonList.add(next);
        buttonList.add(previous);

        setStateChanged();
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == previous) {
            cardLayout.previous(transitionPanel);
        }
        if (source == next) {
            cardLayout.next(transitionPanel);
        }
    }

    public void switchLight(boolean switchValue) {
        if (switchValue) {
            for (JPanel panel : panelList) {
                panel.setBackground(mediumLightGray);
            }
            for (JButton button : buttonList) {
                button.setBackground(mediumDarkGray);
                button.setForeground(Color.WHITE);
            }
            selectPanel.switchLight(switchValue);
        }
        else {
            for (JPanel panel : panelList) {
                panel.setBackground(Color.WHITE);
            }
            for (JButton button : buttonList) {
                button.setBackground(mediumLightGray);
                button.setForeground(Color.BLACK);
            }
            selectPanel.switchLight(switchValue);
        }
    }

    public void setStateChanged(){
        for (JButton button : buttonList) {
            button.addMouseListener(new ButtonChangeState());
        }
    }
}
