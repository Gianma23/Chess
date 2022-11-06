package it.unipi.chess.gui;

import it.unipi.chess.board.Board;



public class JChess {
    public static void main(String[] args) {
        
       Board board = Board.getStartBoard();
       System.out.println(board);
        
        Gui table = new Gui();
    }
}
