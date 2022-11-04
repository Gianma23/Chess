package it.unipi.chess;

public enum Color {
    WHITE {
        @Override
        public int getPawnDirection() {
            return -1;
        }
    },
    BLACK {
        @Override
        public int getPawnDirection() {
            return 1;
        }
    };
    
    public abstract int getPawnDirection();
}
