package it.unipi.chess.pieces;

import it.unipi.chess.board.Board;
import it.unipi.chess.*;
import java.util.*;

public abstract class Piece {
         
    public enum PieceType {
        
        PAWN("P"),
        KNIGHT("N"),
        BISHOP("B"),
        ROOK("R"),
        QUEEN("Q"),
        KING("K");
        
        private String pieceName;
        
        PieceType(final String pn) {
            pieceName = pn;
        }
        
        @Override
        public String toString() {
            return pieceName;
        }
    }
    
    protected final PieceType type;
    protected final int position;
    protected final Color color;
    protected final boolean isFirstMove;
    
    Piece(final PieceType type, final int pos, final Color col) {
        this.type = type;
        position = pos;
        color = col;
        isFirstMove = false;
    }
    
    public abstract List<Move> getPossibleMoves(final Board board);
    
    public PieceType getType() {
        return type;
    }
    
    public Color getColor() {
        return color;
    }

    public int getPosition() {
        return position;
    }
    
    public boolean isFirstMove() {
        return isFirstMove;
    }
}
