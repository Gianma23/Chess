package it.unipi.chess.Move;

import it.unipi.chess.board.Board;
import it.unipi.chess.board.Board.BoardBuilder;
import it.unipi.chess.pieces.Piece;


public class KingSideCastleMove extends Move {
    
    public KingSideCastleMove(Board b, Piece mp, int dc) {
        super(b, mp, dc);
    }  
    
    @Override
    public Board execute() {
        final BoardBuilder builder = new BoardBuilder();
        
        for(final Piece piece : board.getCurrentPlayer().getPieces())
            if(!movedPiece.equals(piece))
    } 
}
