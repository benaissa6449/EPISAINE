package edu.ezip.ing1.pds.client;

import java.util.ArrayList;

public class StringAsciiTable {
    private static ArrayList<String[]> clientList;

    public static void setClientList(ArrayList<String[]> list) {
        clientList = list;
    }

    public static ArrayList<String[]> getClientList() {
        return clientList;
    }

    public static String[][] getMatrix () {
        String[][] clientMatrix = new String[clientList.size()][12];
        int size_i = clientList.size();
        int size_j = clientList.get(0).length;
        for (int i = 0; i < size_i; i++) {
            for (int j = 0; j < size_j; j++) {
                clientMatrix[i][j] = clientList.get(i)[j];
            }
        }
        return clientMatrix;
    }
}
