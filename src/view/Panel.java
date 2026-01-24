package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import management.ErrorStatus;
import management.Observer;
import management.Wrappers;

public abstract class Panel extends JPanel {

    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 600;

    private int screen_width;
    private int screen_height;

    protected Observer observer;

    protected Panel() {
        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        // TODO: is it safer to store this info AFTER the wrapping?
        screen_width = screen_size.width;
        screen_height = screen_size.height;
        try {
            Wrappers.VerifyMinValue(screen_width, MIN_WIDTH);
            Wrappers.VerifyMinValue(screen_height, MIN_HEIGHT);
        } 
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(ErrorStatus.MIN_SCREEN_SIZE.GetExitCode());
        }
        this.setPreferredSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));

        observer = Observer.GetInstance();
    }

    // TODO: look for something like C++ std::pair to return the screen dimensions

    public int GetScreenWidth(){
        return screen_width;
    }

    public int GetScreenHeight(){
        return screen_height;
    }

}