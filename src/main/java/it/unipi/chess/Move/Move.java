package it.unipi.chess.Move;

import it.unipi.chess.board.Board;
import it.unipi.chess.board.Board.BoardBuilder;
import it.unipi.chess.pieces.Piece;
import java.util.Objects;

public abstract class Move {
    
    final Board board;
    final Piece movedPiece;
    final int destCoordinate;
    
    Move(final Board b, final Piece mp, final int dc) {
        board = b;
        movedPiece = mp;
        destCoordinate = dc;
    }
    
    public Board getBoard() {
        return board;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }
    
    public int getDestCoordinate() {
        return destCoordinate;
    }

    public Board execute() {
        // not moving pieces
        final BoardBuilder builder = new BoardBuilder();
        for (final Piece piece : board.getCurrentPlayer().getPieces()) {
            if(!movedPiece.equals(piece))
                builder.setPiece(piece);
        }
        for (final Piece piece : board.getCurrentPlayer().getOpponent().getPieces()) {
            builder.setPiece(piece);
        }
        // moving piece
        builder.setPiece(movedPiece.movePiece(this));
        builder.setNextMoveColor(board.getCurrentPlayer().getOpponent().getColor());
        return builder.build();
    }
    
    @Override
    public boolean equals(final Object o) {
        if(this == o)
            return true;
        if(o == null || o.getClass() != getClass())
            return false;
        
        final Move oMove = (Move) o;
        return movedPiece.equals(oMove.getMovedPiece())&& 
               destCoordinate == oMove.destCoordinate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + movedPiece.hashCode();
        hash = 67 * hash + destCoordinate;
        return hash;
    }
    
    public static final class NoCaptureMove extends Move {

        public NoCaptureMove(Board b, Piece mp, int dc) {
            super(b, mp, dc);
        }  
        
    }
    
    public static final class CaptureMove extends Move {
        
        final Piece capturedPiece;
        
        public CaptureMove(final Board board, final Piece mp, final int dc, final Piece cp) {
            super(board, mp, dc);
            capturedPiece = cp;
        }

        public Piece getCapturedPiece() {
            return capturedPiece;
        }
        //TODO: equals hashcode
        
        @Override
        public Board execute() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }    
}
