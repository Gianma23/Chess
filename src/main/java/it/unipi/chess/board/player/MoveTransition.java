package it.unipi.chess.board.player;

import it.unipi.chess.Move;
import it.unipi.chess.board.Board;

public class MoveTransition {
    
    public enum MoveStatus {
        DONE,
        ILLEGAL,
        CHECKED
    }
    
    private final Move move;
    private final Board newBoard;
    private final MoveStatus moveStatus;
    
    public MoveTransition(final Board newBoard, final Move move, final MoveStatus moveStatus) {
        this.newBoard = newBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    } 

    public Move getMove() {
        return move;
    }

    public Board getNewBoard() {
        return newBoard;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }
    
}
