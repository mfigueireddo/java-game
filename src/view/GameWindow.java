package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
    private JPanel gamePanel;
    
    public GameWindow() {
        // Set up the game panel
        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(800, 600));
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setDoubleBuffered(true);
        
        // Set up the window
        this.setTitle("Java 2D Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gamePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public JPanel getGamePanel() {
        return gamePanel;
    }
}
