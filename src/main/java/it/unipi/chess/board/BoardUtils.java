package it.unipi.chess.board;

import static java.lang.Math.floor;

public class BoardUtils {
    
    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_ROW = 8;
    
    private BoardUtils() {
        throw new RuntimeException("Non puoi istanziare la classe BoardUtils");
    }
    
    private static boolean[] initColumn(int columnNumber) {
        final boolean[] column = new boolean[NUM_TILES];
        
        while (columnNumber < NUM_TILES) {
            column[columnNumber] = true;
        }
        return column;
    }
    
    public static boolean isValidTile (final int coord) {
        return coord >= 0 && coord < 64;
    }
    
    public static boolean isFirstColumn (final int coord) {
        return coord % NUM_TILES_ROW == 0;
    }
    
    public static boolean isSecondColumn (final int coord) {
        return coord % NUM_TILES_ROW == 1;
    }
    
    public static boolean isSeventhColumn (final int coord) {
        return coord % NUM_TILES_ROW == 6;
    }   
    
    public static boolean isEighthColumn (final int coord) {
        return coord % NUM_TILES_ROW == 7;
    }
    
    public static boolean isSecondRow (final int coord) {
        return floor(coord / NUM_TILES_ROW) == 1;
    }
}
