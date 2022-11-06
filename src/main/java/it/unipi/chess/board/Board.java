package it.unipi.chess.board;

import it.unipi.chess.Color;
import it.unipi.chess.Move.Move;
import it.unipi.chess.board.player.*;
import it.unipi.chess.pieces.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Board {
    
    private final Map<Integer, Piece> boardConfig;
    private final List<Piece> whiteSet;
    private final List<Piece> blackSet;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Color nextMoveColor;
    private static final Board START_BOARD = setStartBoard();
    
    private Board(final BoardBuilder builder) {
        this.boardConfig = Collections.unmodifiableMap(builder.boardConfig);
        
        whiteSet = getPieceSet(Color.WHITE);
        blackSet = getPieceSet(Color.BLACK);        
        final List<Move> whitePossibleMoves = getSetPossibleMoves(whiteSet);
        final List<Move> blackPossibleMoves = getSetPossibleMoves(blackSet);
        whitePlayer = new WhitePlayer(this, whitePossibleMoves, blackPossibleMoves, true);
        blackPlayer = new BlackPlayer(this, blackPossibleMoves, whitePossibleMoves, false);
        
        this.nextMoveColor = builder.nextMoveColor;
    }
     
    private static Board setStartBoard() {
        final BoardBuilder startBoard = new BoardBuilder();
        // Black Set
        startBoard.setPiece(new Rook(0, Color.BLACK));
        startBoard.setPiece(new Knight(1, Color.BLACK));
        startBoard.setPiece(new Bishop(2, Color.BLACK));
        startBoard.setPiece(new Queen(3, Color.BLACK));
        startBoard.setPiece(new King(4, Color.BLACK));
        startBoard.setPiece(new Bishop(5, Color.BLACK));
        startBoard.setPiece(new Knight(6, Color.BLACK));
        startBoard.setPiece(new Rook(7, Color.BLACK));
        startBoard.setPiece(new Pawn(8, Color.BLACK));
        startBoard.setPiece(new Pawn(9, Color.BLACK));
        startBoard.setPiece(new Pawn(10, Color.BLACK));
        startBoard.setPiece(new Pawn(11, Color.BLACK));
        startBoard.setPiece(new Pawn(12, Color.BLACK));
        startBoard.setPiece(new Pawn(13, Color.BLACK));
        startBoard.setPiece(new Pawn(14, Color.BLACK));
        startBoard.setPiece(new Pawn(15, Color.BLACK));
        // White Set
        startBoard.setPiece(new Pawn(48, Color.WHITE));
        startBoard.setPiece(new Pawn(49, Color.WHITE));
        startBoard.setPiece(new Pawn(50, Color.WHITE));
        startBoard.setPiece(new Pawn(51, Color.WHITE));
        startBoard.setPiece(new Pawn(52, Color.WHITE));
        startBoard.setPiece(new Pawn(53, Color.WHITE));
        startBoard.setPiece(new Pawn(54, Color.WHITE));
        startBoard.setPiece(new Pawn(55, Color.WHITE));
        startBoard.setPiece(new Rook(56, Color.WHITE));
        startBoard.setPiece(new Knight(57, Color.WHITE));
        startBoard.setPiece(new Bishop(58, Color.WHITE));
        startBoard.setPiece(new Queen(59, Color.WHITE));
        startBoard.setPiece(new King(60, Color.WHITE));
        startBoard.setPiece(new Bishop(61, Color.WHITE));
        startBoard.setPiece(new Knight(62, Color.WHITE));
        startBoard.setPiece(new Rook(63, Color.WHITE));
        
        startBoard.setNextMoveColor(Color.WHITE);
        return startBoard.build();
    }
    
    private List<Piece> getPieceSet(final Color color) {
        return boardConfig.values().stream()
               .filter(piece -> piece.getColor() == color)
               .collect(Collectors.toList());
    }
    
    private List<Move> getSetPossibleMoves(final List<Piece> PieceSet) {
        final List<Move> possibleMoves = new ArrayList<>();
        
        for(final Piece piece : PieceSet) 
            possibleMoves.addAll(piece.getPossibleMoves(this));
        
        return Collections.unmodifiableList(possibleMoves);
    }

    private Color getNextMoveColor() {
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
    
    public static Board getStartBoard() {
        return START_BOARD;
    }
    
    public Piece getPiece(final int coord) {
        return boardConfig.get(coord);
    }  
    
    @Override 
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
            final String tileText = boardConfig.get(i) == null ? "-" : boardConfig.get(i).toString();
                builder.append(String.format("%3s", tileText));
                
            if((i+1) % BoardUtils.NUM_TILES_ROW == 0)
                builder.append("\n");
        }
        return builder.toString();
    }
    /*==================================================*/
    /*              Builder for Board Class             */
    /*==================================================*/
    public static class BoardBuilder {
        
        Map<Integer, Piece> boardConfig;
        Color nextMoveColor;
        
        public BoardBuilder() {
            boardConfig = new HashMap<>(32, 1.0f);
        }
        
        public BoardBuilder setPiece(final Piece piece) {
            boardConfig.put(piece.getPosition(), piece);
            return this;
        }
        
        public BoardBuilder setNextMoveColor(final Color nextMovecolor) {
            this.nextMoveColor = nextMovecolor;
            return this;
        }
        
        public Board build() {
            return new Board(this);
        }       
    }
}
