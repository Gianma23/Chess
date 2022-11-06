package it.unipi.chess.board.player;

import it.unipi.chess.Move.MoveTransition;
import it.unipi.chess.Color;
import it.unipi.chess.Move.Move;
import it.unipi.chess.board.Board;
import it.unipi.chess.Move.MoveStatus;
import it.unipi.chess.pieces.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player {
    
    protected final Board board;
    protected final King playerKing;
    protected final List<Move> possibleMoves;
    protected final boolean isChecked;
    protected final boolean isBottom;
    
    public abstract Color getColor();
    public abstract Player getOpponent();
    public abstract List<Piece> getPieces();
    
    Player(final Board board, final List<Move> possibleMoves,
           final List<Move> opponentMoves, final boolean isBottom) {
        this.board = board;
        this.isBottom = isBottom;
        this.playerKing = setKing();
        this.possibleMoves = possibleMoves;
        this.isChecked = !(getCapturesOnTile(playerKing.getPosition(), opponentMoves).isEmpty());
    }
    
    /* Getters */
    public Board getBoard() {
        return board;
    }

    public King getPlayerKing() {
        return playerKing;
    }

    public List<Move> getPossibleMoves() {
        return possibleMoves;
    }

    public boolean isIsChecked() {
        return isChecked;
    }

    public boolean isIsBottom() {
        return isBottom;
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
    
    public boolean canKingSideCastle() {
        // bottom right castle
        if (isBottom &&
            playerKing.isFirstMove() && !isChecked &&
            board.getPiece(61) == null && board.getPiece(62) == null &&
            getCapturesOnTile(61, getOpponent().getPossibleMoves()).isEmpty() &&
            getCapturesOnTile(62, getOpponent().getPossibleMoves()).isEmpty()) {
            
            final Piece KingSideRook = board.getPiece(63);
            if(KingSideRook.getType() == PieceType.ROOK && KingSideRook.isFirstMove())
                return true;
        }
        // top right castle
        else if(playerKing.isFirstMove() && !isChecked &&
                board.getPiece(5) == null && board.getPiece(6) == null &&
                getCapturesOnTile(5, getOpponent().getPossibleMoves()).isEmpty() &&
                getCapturesOnTile(6, getOpponent().getPossibleMoves()).isEmpty()){
         
            final Piece KingSideRook = board.getPiece(7);
            if(KingSideRook.getType() == PieceType.ROOK && KingSideRook.isFirstMove())
                 return true;
        }
        return false;
    }
    
    public boolean canQueenSideCastle() {
        // bottom left castle
        if (isBottom &&
            playerKing.isFirstMove() && !isChecked &&
            board.getPiece(57) == null && board.getPiece(58) == null && board.getPiece(59) == null &&
            getCapturesOnTile(57, getOpponent().getPossibleMoves()).isEmpty() &&
            getCapturesOnTile(58, getOpponent().getPossibleMoves()).isEmpty() &&
            getCapturesOnTile(59, getOpponent().getPossibleMoves()).isEmpty()) {
             
            final Piece KingSideRook = board.getPiece(56);
            if(KingSideRook.getType() == PieceType.ROOK && KingSideRook.isFirstMove())
                return true;
        }
        // top left castle
        else if(playerKing.isFirstMove() && !isChecked &&
                board.getPiece(1) == null && board.getPiece(2) == null && board.getPiece(3) == null &&
                getCapturesOnTile(1, getOpponent().getPossibleMoves()).isEmpty() &&
                getCapturesOnTile(2, getOpponent().getPossibleMoves()).isEmpty() &&
                getCapturesOnTile(3, getOpponent().getPossibleMoves()).isEmpty()) {
         
            final Piece KingSideRook = board.getPiece(0);
            if(KingSideRook.getType() == PieceType.ROOK && KingSideRook.isFirstMove())
                 return true;
        }
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
}

