/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.chess.pieces;


public enum PieceType {

    PAWN("P"),
    KNIGHT("N"),
    BISHOP("B"),
    ROOK("R"),
    QUEEN("Q"),
    KING("K");

    private final String pieceName;

    PieceType(final String pn) {
        pieceName = pn;
    }

    @Override
    public String toString() {
        return pieceName;
    }
}
