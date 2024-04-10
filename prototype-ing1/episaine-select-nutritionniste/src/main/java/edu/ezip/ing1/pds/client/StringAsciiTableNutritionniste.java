package edu.ezip.ing1.pds.client;

import java.util.ArrayList;

public class StringAsciiTableNutritionniste {
    private static ArrayList<String[]> nutritionisteList;

    public static void setNutritionnisteList(ArrayList<String[]> list) {
        nutritionisteList = list;
    }

    public static ArrayList<String[]> getNutritionnisteList() {
        return nutritionisteList;
    }

    public static String[][] getMatrix() {
        String[][] nutritionisteMatrix = new String[nutritionisteList.size()][7];
        for (int i = 0; i < nutritionisteList.size(); i++) {
            nutritionisteMatrix[i] = nutritionisteList.get(i);
        }
        return nutritionisteMatrix;
    }
}
