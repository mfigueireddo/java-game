package view.panels;

import controller.utils.ErrorStatus;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JPanel;

public abstract class Panel extends JPanel {

    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 600;

    protected Panel() {
        // Doesn't contabilize Windows' taskbar and the game's window toolbar
        Rectangle maximum_window_bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        final int screen_width = maximum_window_bounds.width;
        final int screen_height = maximum_window_bounds.height;
        if (screen_width < MIN_WIDTH || screen_height < MIN_HEIGHT){
            final ErrorStatus error = ErrorStatus.MIN_SCREEN_SIZE;
            System.err.println("Error: " + error.GetErrorMessage() + " - " + screen_width + "x" + screen_height);
            System.exit( error.GetExitCode() );
        }

        setPreferredSize(new java.awt.Dimension(screen_width, screen_height));
    }
}