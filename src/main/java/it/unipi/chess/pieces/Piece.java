package it.unipi.chess.pieces;

import it.unipi.chess.Move.Move;
import it.unipi.chess.board.Board;
import it.unipi.chess.*;
import java.util.*;

public abstract class Piece {
    
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
    public abstract Piece movePiece(Move move);
    
    @Override
    public boolean equals(final Object o) {
        if(this == o)
            return true;
        if(o == null || o.getClass() != getClass())
            return false;
        
        final Piece oPiece = (Piece) o;
        return color == oPiece.getColor() && type == oPiece.getType() && 
               position == oPiece.position && isFirstMove == oPiece.isFirstMove;
    }

    @Override
    public int hashCode() {
        int hash = type.hashCode();
        hash = 71 * hash + position;
        hash = 71 * hash + color.hashCode();
        hash = 71 * hash + (isFirstMove? 1 : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        return getType().toString();
    }

    
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
