package it.unipi.chess.board;

public class BoardUtils {
    
    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_ROW = 8;
    
    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);
    
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
}
