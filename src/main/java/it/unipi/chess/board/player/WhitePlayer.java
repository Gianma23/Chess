package it.unipi.chess.board.player;

import it.unipi.chess.Color;
import it.unipi.chess.Move.Move;
import it.unipi.chess.board.Board;
import it.unipi.chess.pieces.*;
import java.util.List;

public class WhitePlayer extends Player {

    public WhitePlayer(final Board board, final List<Move> possibleMoves,
                       final List<Move> opponentMoves, final boolean isBottom) {
        super(board, possibleMoves, opponentMoves, isBottom);
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

    @Override
    public Player getOpponent() {
        return board.getBlackPlayer();
    }
    
    @Override
    public List<Piece> getPieces() {
        return board.getWhiteSet();
    }
}
