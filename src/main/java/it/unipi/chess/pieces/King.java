package it.unipi.chess.pieces;

import it.unipi.chess.Color;
import it.unipi.chess.Move;
import it.unipi.chess.board.Board;
import it.unipi.chess.board.BoardUtils;
import it.unipi.chess.board.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Utente
 */
public class King extends Piece {
    
    private final static PieceType type = PieceType.KING;      
    private final static int[] CANDIDATE_MOVE_COORD = {-9, -8, -7, -1, 1, 7, 8, 9};
    
    public King(final int pos, final Color col) {
        super(pos, col);
    }

    public PieceType getType() {
        return type;
    }
    
    @Override
    public List<Move> getPossibleMoves(final Board board) {
        int candidateMove;
        List<Move> possibleMoves = new ArrayList<>();
        
        for (final int coordOffset : CANDIDATE_MOVE_COORD) {
            candidateMove = position + coordOffset;
            
            if (BoardUtils.isValidTile(candidateMove) &&
                !isColumnExcluded(candidateMove, coordOffset)) { 
                                
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
