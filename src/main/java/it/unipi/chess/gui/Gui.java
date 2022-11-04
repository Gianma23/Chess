package it.unipi.chess.gui;

import java.awt.Dimension;
import javax.swing.*;

public class Gui {
    
    private final JFrame gameFrame;
    private static Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
    
    public Gui() {
        this.gameFrame = new JFrame("JChess");
        
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setVisible(true);
        
    }
}
