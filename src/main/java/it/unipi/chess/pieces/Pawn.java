package it.unipi.chess.pieces;

import it.unipi.chess.board.Board;
import it.unipi.chess.Color;
import it.unipi.chess.Move;
import it.unipi.chess.board.BoardUtils;
import it.unipi.chess.board.Tile;
import it.unipi.chess.board.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {
    
    private static final int[] CANDIDATE_MOVE_COORD = {7, 8, 9};
    private final int direction;
    
    public Pawn(final int pos, final Color col) {
        super(PieceType.PAWN, pos, col);
        direction = setDirection(pos);
    }
    
    private int setDirection(int pos) {
        if(BoardUtils.isSecondRow(pos))
            return 1;
        return -1;
    }
    
    @Override
    public List<Move> getPossibleMoves(final Board board) {
        int candidateMove;
        List<Move> possibleMoves = new ArrayList<>();
        
        for (final int coordOffset : CANDIDATE_MOVE_COORD) {
            candidateMove = position + coordOffset * direction;
            
            if (BoardUtils.isValidTile(candidateMove)) {     
                
                final Tile validMoveTile = board.getTile(candidateMove);                
                
                // no capture move
                if (coordOffset == 8 && !validMoveTile.isOccupied()) {
                    //TODO: promozione pezzo
                    possibleMoves.add(new Move.NoCaptureMove(board, this, candidateMove));
                    
                    // jump move
                    if (coordOffset + 8 * direction == 16 && !validMoveTile.isOccupied() && isFirstMove())
                        possibleMoves.add(new Move.NoCaptureMove(board, this, candidateMove));
                } 
                // diagonal capture move
                else if (validMoveTile.isOccupied() && 
                         !isColumnExcluded(candidateMove, coordOffset)) {
                    
                    final Piece pieceAtDest = validMoveTile.getPiece();
                    final Color pieceAtDestColor = pieceAtDest.getColor();

                    if(this.color != pieceAtDestColor) {
                        //TODO: promozione pezzo
                        possibleMoves.add(new Move.CaptureMove(board, this, candidateMove, pieceAtDest)); 
                    }   
                }
            }
        }
        return Collections.unmodifiableList(possibleMoves);
    }
    
    private static boolean isColumnExcluded(final int candidateMove, final int coordOffset) {
        return isFirstColumnExcluded(candidateMove, coordOffset) || isEighthColumnExcluded(candidateMove, coordOffset);
    }
        
    private static boolean isFirstColumnExcluded(final int candidateMove, final int coordOffset) {
        return BoardUtils.isFirstColumn(candidateMove) && (coordOffset == -9 || coordOffset == 7);
    }
    
    private static boolean isEighthColumnExcluded(final int candidateMove, final int coordOffset) {
        return BoardUtils.isEighthColumn(candidateMove) && (coordOffset == -7 || coordOffset == 9);
    }
}
