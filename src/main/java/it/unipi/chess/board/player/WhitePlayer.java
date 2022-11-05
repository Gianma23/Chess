package it.unipi.chess.board.player;

import it.unipi.chess.Color;
import it.unipi.chess.Move;
import it.unipi.chess.board.Board;
import it.unipi.chess.pieces.*;
import it.unipi.chess.pieces.Piece.PieceType;
import java.util.List;

public class WhitePlayer extends Player {

    public WhitePlayer(Board board, List<Move> possibleMoves,
           List<Move> opponentMoves) {
        super(board, possibleMoves, opponentMoves);
    }
        
    @Override
    public List<Piece> getPieces() {
        return board.getWhiteSet();
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

    @Override
    public Player getOpponent() {
        return board.getBlackPlayer();
    }
}
