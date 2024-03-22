package ing.sirius.episaine.client;

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Fenetre_Accueil page = new Fenetre_Accueil();
        page.setVisible(true);
    }
}