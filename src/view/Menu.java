package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import management.ErrorStatus;
import management.Wrappers;

public class Menu extends JPanel{

    private static Menu instance;

    public static Menu GetInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 600;

    private Menu(){
        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        int screen_width = screen_size.width;
        int screen_height = screen_size.height;
        try {
            Wrappers.VerifyMinValue(screen_width, MIN_WIDTH);
            Wrappers.VerifyMinValue(screen_height, MIN_HEIGHT);
        } 
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(ErrorStatus.MIN_SCREEN_SIZE.GetExitCode());
        }
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        
        this.setBackground(Color.BLACK);
    }

}