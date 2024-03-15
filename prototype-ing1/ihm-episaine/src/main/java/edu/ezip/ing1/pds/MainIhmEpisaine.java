package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.IHM_BDD.Frame;

public class MainIhmEpisaine {
    public static void main(String[] args){
        try {
            Frame frame = new Frame();
            frame.setVisible(true);
        }
        catch(Exception e) {
            e.getMessage();
        }
    }
}