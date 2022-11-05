package it.unipi.chess;

import it.unipi.chess.board.Board;
import it.unipi.chess.pieces.Piece;

public abstract class Move {
    
    final Board board;
    final Piece movedPiece;
    final int destCoordinate;
    
    Move(final Board b, final Piece mp, final int dc) {
        board = b;
        movedPiece = mp;
        destCoordinate = dc;
    }
    
    public abstract Board execute();
    
    public static final class NoCaptureMove extends Move {

        public NoCaptureMove(Board b, Piece mp, int dc) {
            super(b, mp, dc);
        }  

        @Override
        public Board execute() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    
    public static final class CaptureMove extends Move {
        
        final Piece capturedPiece;
        
        public CaptureMove(final Board board, final Piece mp, final int dc, final Piece cp) {
            super(board, mp, dc);
            capturedPiece = cp;
        }

        @Override
        public Board execute() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    
    public int getDestCoordinate() {
        return destCoordinate;
    }
}
