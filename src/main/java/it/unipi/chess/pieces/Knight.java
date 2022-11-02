package it.unipi.chess.pieces;

import it.unipi.chess.board.Board;
import it.unipi.chess.Color;
import it.unipi.chess.Move;
import it.unipi.chess.board.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_COORD = {-17, -15, -10, -6, 6, 10, 15, 17};
    
    Knight(final int pos, final Color col) {
        super(pos, col);
    }

    @Override
    public List<Move> getPossibleMoves(Board board) {
        int candidateMove;
        List<Move> possibleMoves = new ArrayList<>();
        
        for (final int currentCandidate : CANDIDATE_MOVE_COORD) {
            candidateMove = position + currentCandidate;
            
            if (isValidTile(candidateMove)) {
                final Tile validMoveTile = board.getTile(candidateMove);
                
                if (!validMoveTile.isOccupied()) {
                    possibleMoves.add(new Move.NoCaptureMove(board, this, candidateMove));
                } else {
                    final Piece pieceAtDest = validMoveTile.getPiece();
                    final Color pieceAtDestColor = pieceAtDest.getPieceColor();
                    
                    if(this.color != pieceAtDestColor)
                        possibleMoves.add(new Move.CaptureMove(board, this, candidateMove, pieceAtDest));
                }
            }
        }
        return Collections.unmodifiableList(possibleMoves);
    }
    
    private boolean isValidTile (final int coord) {
        return coord >= 0 && coord < 64;
    }
    
    private static boolean isFirstColExclusion(final int currentPos, final int candidate) {
        
    }
    
}
