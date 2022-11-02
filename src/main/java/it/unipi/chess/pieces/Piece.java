package it.unipi.chess.pieces;

import it.unipi.chess.board.Board;
import it.unipi.chess.*;
import java.util.*;

public abstract class Piece {
    protected final int position;
    protected final Color color;
    
    Piece(final int pos, final Color col) {
        position = pos;
        color = col;
    }
    
    public abstract List<Move> getPossibleMoves(final Board board);
    public Color getPieceColor() {
        return color;
    }
}
