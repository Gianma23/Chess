package it.unipi.chess.board.player;

import it.unipi.chess.Color;
import it.unipi.chess.Move;
import it.unipi.chess.board.Board;
import it.unipi.chess.board.player.MoveTransition.MoveStatus;
import it.unipi.chess.pieces.*;
import it.unipi.chess.pieces.Piece.PieceType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player {
    
    protected final Board board;
    protected final King playerKing;
    protected final List<Move> possibleMoves;
    protected final boolean isChecked;
    
    Player(final Board board, final List<Move> possibleMoves,
           final List<Move> opponentMoves) {
        this.board = board;
        this.playerKing = setKing();
        this.possibleMoves = possibleMoves;
        this.isChecked = !(getCapturesOnTile(playerKing.getPosition(), opponentMoves).isEmpty());
    }
    
    private static List<Move> getCapturesOnTile(int piecePosition, List<Move> moves) {
        final List<Move>  captureMoves = new ArrayList<>();
        for(final Move move : moves)
            if(piecePosition == move.getDestCoordinate()) 
                captureMoves.add(move);
        
        return Collections.unmodifiableList(captureMoves);
    }
    
    private King setKing() {
        for(final Piece piece : getPieces()) 
            if(piece.getType() == PieceType.KING)
                return (King) piece;
        throw new RuntimeException("King not found among pieces.");
    }
    
    protected boolean canEscape() {
        for(final Move move: possibleMoves) {
            final MoveTransition transition = makeMove(move);
            if(transition.getMoveStatus() == MoveStatus.DONE)
                return true;
        }
        return false;
    }
    
    public boolean isMovePossible(final Move move) {
        return possibleMoves.contains(move);
    }

    public boolean isChecked() {
        return isChecked;
    }
    
    public boolean isCheckMated() {
        return isChecked && !canEscape();
    }
    
    public boolean isStaleMate() {
        return !isChecked && !canEscape();
    }
    
    public boolean isCastled() {
        return false;
    }
    
    public MoveTransition makeMove(final Move move) {
        if(!isMovePossible(move)) 
            return new MoveTransition(board, move, MoveStatus.ILLEGAL);
        
        final Board newBoard = move.execute();
        if(newBoard.getCurrentPlayer().getOpponent().isChecked)
            return new MoveTransition(board, move, MoveStatus.CHECKED);
        
        return new MoveTransition(newBoard, move, MoveStatus.DONE);
    }
    
    public abstract List<Piece> getPieces();
    public abstract Color getColor();
    public abstract Player getOpponent();
}

