package ing.sirius.episaine.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ing.sirius.episaine.client.Page_Accueil.Parts.CenterPanel;
import ing.sirius.episaine.client.Page_Accueil.Parts.Footer;
import ing.sirius.episaine.client.Page_Accueil.Parts.FrameInterface;
import ing.sirius.episaine.client.Page_Accueil.Parts.Header;
import ing.sirius.episaine.client.Page_Accueil.Parts.LeftMenu;
import ing.sirius.episaine.client.Page_Accueil.Parts.listener.HomeButtonListener;
import ing.sirius.episaine.client.Page_Accueil.Parts.listener.LightModeButtonListener;

public class Fenetre_Accueil extends JFrame {
    private final JToggleButton homeButton;
    private final JToggleButton settingButton;
    private final JToggleButton sunButton;
    private final String logLabel = "F e n e t r e - A c c u e i l";
    private final Logger fenetreLog = LoggerFactory.getLogger(logLabel);

    private static boolean switchValue = true;
    private static ArrayList<FrameInterface> panelList = new ArrayList<FrameInterface>();

    private final CardLayout cardLayout = new CardLayout();

    public Fenetre_Accueil() {
        fenetreLog.info("Lancement de la fenetre principale");
        JPanel mainPanel = new JPanel();
        
        // settings
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // panels
        Header header = new Header("Page d'accueil", this);
        Footer footer = new Footer();
        LeftMenu lm = new LeftMenu(this);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        add(lm, BorderLayout.WEST);

        homeButton = lm.getAccueilButton();
        settingButton = lm.getSettingButton();
        sunButton = lm.getSunButton(); 

        // TODO : first shown panel, cardlayout here
        JPanel transitionPanel = new JPanel();
        transitionPanel.setBackground(Color.BLUE);
        CenterPanel centerPanel = new CenterPanel(transitionPanel, cardLayout);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // light or dark mode
        sunButton.addActionListener(new LightModeButtonListener(homeButton, settingButton, sunButton));
        
        // home button
        homeButton.addActionListener(new HomeButtonListener(transitionPanel, cardLayout));

        // add every panel to list
        panelList.add(lm);
        panelList.add(header);
        panelList.add(footer);
        panelList.add(centerPanel);
    }

    // switch light mode in each panel
    public static void switchLight(){
        for (FrameInterface panel : panelList) {
            panel.switchLight(switchValue);
        }
    }

    public static void changeSwitchValue(boolean b) {
        if (b) {
            switchValue = false;
        }
        else switchValue = true;
    }

    public static boolean getSwitchValue(){return switchValue;}
}
