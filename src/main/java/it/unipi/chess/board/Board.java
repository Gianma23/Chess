package it.unipi.chess.board;

import it.unipi.chess.Color;
import it.unipi.chess.Move;
import it.unipi.chess.board.player.*;
import it.unipi.chess.pieces.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public final class Board {
    
    private final Map<Integer, Piece> boardConfig;
    private final List<Tile> gameBoard;
    private final List<Piece> whiteSet;
    private final List<Piece> blackSet;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Color nextMoveColor;
    
    public Board() {
        boardConfig = new HashMap<>();
        setBoardConfig();
        gameBoard = setGameBoard();
        whiteSet = getPieceSet(Color.WHITE);
        blackSet = getPieceSet(Color.BLACK);
        
        final List<Move> whitePossibleMoves = getSetPossibleMoves(whiteSet);
        final List<Move> blackPossibleMoves = getSetPossibleMoves(blackSet);
        whitePlayer = new WhitePlayer(this, whitePossibleMoves, blackPossibleMoves);
        blackPlayer = new BlackPlayer(this, blackPossibleMoves, whitePossibleMoves);
        nextMoveColor = Color.WHITE;
    }
     
    private void setBoardConfig() {
        // Black Set
        setPiece(new Rook(0, Color.BLACK));
        setPiece(new Knight(1, Color.BLACK));
        setPiece(new Bishop(2, Color.BLACK));
        setPiece(new Queen(3, Color.BLACK));
        setPiece(new King(4, Color.BLACK));
        setPiece(new Bishop(5, Color.BLACK));
        setPiece(new Knight(6, Color.BLACK));
        setPiece(new Rook(7, Color.BLACK));
        setPiece(new Pawn(8, Color.BLACK));
        setPiece(new Pawn(9, Color.BLACK));
        setPiece(new Pawn(10, Color.BLACK));
        setPiece(new Pawn(11, Color.BLACK));
        setPiece(new Pawn(12, Color.BLACK));
        setPiece(new Pawn(13, Color.BLACK));
        setPiece(new Pawn(14, Color.BLACK));
        setPiece(new Pawn(15, Color.BLACK));
        // White Set
        setPiece(new Pawn(48, Color.WHITE));
        setPiece(new Pawn(49, Color.WHITE));
        setPiece(new Pawn(50, Color.WHITE));
        setPiece(new Pawn(51, Color.WHITE));
        setPiece(new Pawn(52, Color.WHITE));
        setPiece(new Pawn(53, Color.WHITE));
        setPiece(new Pawn(54, Color.WHITE));
        setPiece(new Pawn(55, Color.WHITE));
        setPiece(new Rook(56, Color.WHITE));
        setPiece(new Knight(57, Color.WHITE));
        setPiece(new Bishop(58, Color.WHITE));
        setPiece(new Queen(59, Color.WHITE));
        setPiece(new King(60, Color.WHITE));
        setPiece(new Bishop(61, Color.WHITE));
        setPiece(new Knight(62, Color.WHITE));
        setPiece(new Rook(63, Color.WHITE));
    }
    
    private List<Tile> setGameBoard() {
        final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
        for(int i = 0; i < BoardUtils.NUM_TILES; i++) 
            tiles[i] = Tile.createTile(i, boardConfig.get(i));
        
        return Collections.unmodifiableList(Arrays.asList(tiles));
    }
    
    private List<Piece> getPieceSet(final Color color) {
        final List<Piece> activePieces = new ArrayList<>();
        
        for(final Tile tile : gameBoard) 
            if(tile.isOccupied() && tile.getPiece().getColor() == color)
                activePieces.add(tile.getPiece());
            
        return Collections.unmodifiableList(activePieces);
    }
    
    private List<Move> getSetPossibleMoves(final List<Piece> PieceSet) {
        final List<Move> possibleMoves = new ArrayList<>();
        
        for(final Piece piece : PieceSet) 
            possibleMoves.addAll(piece.getPossibleMoves(this));
        
        return Collections.unmodifiableList(possibleMoves);
    }
        
    private void setPiece(final Piece piece) {
        boardConfig.put(piece.getPosition(), piece);
    }

    private Color getNextMoveColor(final Color nmc) {
        return nextMoveColor;
    }
    
    public List<Piece> getWhiteSet() {
        return whiteSet;
    }
    
    public List<Piece> getBlackSet() {
        return blackSet;
    }
    
    public WhitePlayer getWhitePlayer() {
        return whitePlayer;
    }

    public BlackPlayer getBlackPlayer() {
        return blackPlayer;
    }
    
    public Player getCurrentPlayer() {
        return nextMoveColor == Color.WHITE ? whitePlayer : blackPlayer;
    }
    
    public Tile getTile(final int coord) {
        return gameBoard.get(coord);
    }  
    
    @Override 
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
            final String tileText = gameBoard.get(i).toString();
            builder.append(String.format("%3s", tileText));
            if((i+1) % BoardUtils.NUM_TILES_ROW == 0)
                builder.append("\n");
        }
        return builder.toString();
    }
}
