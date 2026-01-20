package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import management.ErrorStatus;
import management.Wrappers;

public abstract class Panel extends JPanel {

    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 600;

    protected Panel() {
        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        int screen_width = screen_size.width;
        int screen_height = screen_size.height;
        try {
            Wrappers.VerifyMinValue(screen_width, MIN_WIDTH);
            Wrappers.VerifyMaxValue(screen_height, MIN_HEIGHT);
        } 
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(ErrorStatus.MIN_SCREEN_SIZE.GetExitCode());
        }
        this.setPreferredSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
    }


}