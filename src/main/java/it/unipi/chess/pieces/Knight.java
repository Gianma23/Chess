package it.unipi.chess.pieces;

import it.unipi.chess.board.Board;
import it.unipi.chess.Color;
import it.unipi.chess.Move.Move;
import it.unipi.chess.board.BoardUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_COORD = {-17, -15, -10, -6, 6, 10, 15, 17};
    
    public Knight(final int pos, final Color col) {
        super(PieceType.KNIGHT, pos, col);
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
                
                final Piece pieceAtDest = board.getPiece(candidateMove); 
                
                if (pieceAtDest == null) {
                    possibleMoves.add(new Move.NoCaptureMove(board, this, candidateMove));
                } else {
                    final Color pieceAtDestColor = pieceAtDest.getColor();
                    
                    if(this.color != pieceAtDestColor)
                        possibleMoves.add(new Move.CaptureMove(board, this, candidateMove, pieceAtDest));
                }
            }
        }
        return Collections.unmodifiableList(possibleMoves);
    }
    
    private static boolean isColumnExcluded(final int candidateMove, final int coordOffset) {
        return isFirstColumnExcluded(candidateMove, coordOffset) || isSecondColumnExcluded(candidateMove, coordOffset) ||
                isSeventhColumnExcluded(candidateMove, coordOffset) || isEighthColumnExcluded(candidateMove, coordOffset);
    }
        
    private static boolean isFirstColumnExcluded(final int candidateMove, final int coordOffset) {
        return BoardUtils.isFirstColumn(candidateMove) && 
                (coordOffset == -17 || coordOffset == -10 || coordOffset == 6 || coordOffset == 15);
    }
    
    private static boolean isSecondColumnExcluded(final int candidateMove, final int coordOffset) {
        return BoardUtils.isSecondColumn(candidateMove) && (coordOffset == -10 || coordOffset == 6);
    }
    
    private static boolean isSeventhColumnExcluded(final int candidateMove, final int coordOffset) {
        return BoardUtils.isSeventhColumn(candidateMove) && (coordOffset == -6 || coordOffset == 17);
    }
        
    private static boolean isEighthColumnExcluded(final int candidateMove, final int coordOffset) {
        return BoardUtils.isEighthColumn(candidateMove) &&
                (coordOffset == -15 || coordOffset == -6 || coordOffset == 10 || coordOffset == 17);
    }

    @Override
    public Piece movePiece(Move move) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
