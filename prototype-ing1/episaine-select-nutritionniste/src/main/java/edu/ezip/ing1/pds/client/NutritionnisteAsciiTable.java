package edu.ezip.ing1.pds.client;

import de.vandermeer.asciitable.AsciiTable;

public class NutritionnisteAsciiTable {
    private static AsciiTable asciiTable;

    public static void setAsciiTable(AsciiTable asciiTableVar) {
        asciiTable = asciiTableVar;
    }

    public static AsciiTable getAsciiTable() {
        return asciiTable;
    }
}
