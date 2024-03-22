package edu.ezip.ing1.pds.client;

import java.util.ArrayList;

public class StringAsciiTableRecette {
    private static ArrayList<String[]> recetteList;

    public static void setRecetteList(ArrayList<String[]> list) {
        recetteList = list;
    }

    public static ArrayList<String[]> getRecetteList() {
        return recetteList;
    }

    public static String[][] getMatrix() {
        String[][] recetteMatrix = new String[recetteList.size()][7];
        for (int i = 0; i < recetteList.size(); i++) {
            recetteMatrix[i] = recetteList.get(i);
        }
        return recetteMatrix;
    }
}
