/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.chess.board.player;

import it.unipi.chess.Color;
import it.unipi.chess.Move;
import it.unipi.chess.board.Board;
import it.unipi.chess.pieces.Piece;
import java.util.List;

/**
 *
 * @author Utente
 */
public class BlackPlayer extends Player {
    
    public BlackPlayer(Board board, List<Move> possibleMoves,
           List<Move> opponentMoves) {
        super(board, possibleMoves, opponentMoves);
    }

    @Override
    public List<Piece> getPieces() {
        return board.getBlackSet();
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public Player getOpponent() {
        return board.getWhitePlayer();
    }
}
