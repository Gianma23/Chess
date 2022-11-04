package it.unipi.chess.board;

import it.unipi.chess.pieces.*;
public abstract class Tile {
    
    protected final int coordinate;
    
    public static Tile createTile(final int coord, final Piece piece) {
        return piece != null ? new OccupiedTile(coord, piece) : new EmptyTile(coord);
    }
    
    private Tile(int coord) {
        coordinate = coord;
    }
    
    public abstract boolean isOccupied();    
    public abstract Piece getPiece();
    
    public static final class EmptyTile extends Tile {
        
        private EmptyTile(final int coord) {
            super(coord);
        }
        
        @Override
        public String toString() {
            return "-";
        }
        
        @Override
        public boolean isOccupied() {
            return false;
        }
        
        @Override
        public Piece getPiece() {
            return null;
        }
    }
    
    public static final class OccupiedTile extends Tile {
        
        private final Piece piece;
        
        private OccupiedTile(final int coord, final Piece p) {
            super(coord);
            piece = p;
        }
        
        @Override
        public String toString() {
            return this.getPiece().getType().toString();
        }
        
        @Override
        public boolean isOccupied() {
            return true;
        }
        
        @Override
        public Piece getPiece() {
            return piece;
        }
    }
}
