package it.unipi.chess.pieces;

import it.unipi.chess.board.Board;
import it.unipi.chess.Color;
import it.unipi.chess.Move.Move;
import it.unipi.chess.board.BoardUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece {
    
    private final static int[] CANDIDATE_MOVE_VECTOR = {-8, -1, 1, 8};
    
    public Rook(final int pos, final Color col) {
        super(PieceType.ROOK, pos, col);
    }
    
    public PieceType getType() {
        return type;
    }
    
    @Override
    public List<Move> getPossibleMoves(final Board board) {
        int candidateMove;
        List<Move> possibleMoves = new ArrayList<>();
        
        for (final int coordOffset : CANDIDATE_MOVE_VECTOR) {
            candidateMove = position + coordOffset;
            
            while (BoardUtils.isValidTile(candidateMove)) {      
                if(isColumnExcluded(candidateMove, coordOffset))
                    break;
                
                final Piece pieceAtDest = board.getPiece(candidateMove);            
                if (pieceAtDest == null) {
                    possibleMoves.add(new Move.NoCaptureMove(board, this, candidateMove));
                } else {
                    final Color pieceAtDestColor = pieceAtDest.getColor();
                    
                    if(this.color != pieceAtDestColor)
                        possibleMoves.add(new Move.CaptureMove(board, this, candidateMove, pieceAtDest));
                    break;  // cannot go over occupied tile
                }
                
                candidateMove += coordOffset;  // continue on vector
            }
        }
        return Collections.unmodifiableList(possibleMoves);
    }
    
    private static boolean isColumnExcluded(final int candidateMove, final int coordOffset) {
        return isFirstColumnExcluded(candidateMove, coordOffset) || isEighthColumnExcluded(candidateMove, coordOffset);
    }
    
    private static boolean isFirstColumnExcluded(final int candidateMove, final int coordOffset) {
        return BoardUtils.isFirstColumn(candidateMove) && coordOffset == -1;
    }
    
    private static boolean isEighthColumnExcluded(final int candidateMove, final int coordOffset) {
        return BoardUtils.isFirstColumn(candidateMove) && coordOffset == 1;
    }

    @Override
    public Piece movePiece(Move move) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
