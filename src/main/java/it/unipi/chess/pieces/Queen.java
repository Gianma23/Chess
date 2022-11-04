package it.unipi.chess.pieces;

import it.unipi.chess.Color;
import it.unipi.chess.Move;
import it.unipi.chess.board.Board;
import it.unipi.chess.board.BoardUtils;
import it.unipi.chess.board.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Queen extends Piece {
    
    private final static PieceType type = PieceType.QUEEN;  
    private final static int[] CANDIDATE_MOVE_VECTOR = {-9, -8, -7, -1, 1, 8, 7, 9};
    
    public Queen(final int pos, final Color col) {
        super(pos, col);
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
                
                final Tile validMoveTile = board.getTile(candidateMove);                
                if (!validMoveTile.isOccupied()) {
                    possibleMoves.add(new Move.NoCaptureMove(board, this, candidateMove));
                } else {
                    final Piece pieceAtDest = validMoveTile.getPiece();
                    final Color pieceAtDestColor = pieceAtDest.getPieceColor();
                    
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
        return BoardUtils.isFirstColumn(candidateMove) && (coordOffset == -9 || coordOffset == -1 || coordOffset == 7);
    }
    
    private static boolean isEighthColumnExcluded(final int candidateMove, final int coordOffset) {
        return BoardUtils.isEighthColumn(candidateMove) && (coordOffset == -7 || coordOffset == 1 || coordOffset == 9);
    }

}
