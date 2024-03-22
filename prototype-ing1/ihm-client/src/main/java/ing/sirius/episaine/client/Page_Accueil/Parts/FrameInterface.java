package ing.sirius.episaine.client.Page_Accueil.Parts;

import java.awt.Color;

public interface FrameInterface {
    
    public final Color darkgray = new Color(55,55,55);
    public final Color lightgray = new Color(210,210,210);

    public final Color mediumDarkGray = new Color(80,80,80);
    public final Color mediumLightGray = new Color(170,170,170);

    public void switchLight(boolean switchVar);
}
