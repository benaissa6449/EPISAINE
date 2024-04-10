package edu.ezip.ing1.pds;



import edu.ezip.ing1.pds.IHM_BDD_Recette.FrameRecette;



public class MainIhmEpisaine {
    public static void main(String[] args){
        try {
            FrameRecette frameRecette = new FrameRecette();
            frameRecette.setVisible(true);
        }
        catch(Exception e) {
            e.getMessage();
        }
    }
}