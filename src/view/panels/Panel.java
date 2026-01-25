package view.panels;

import controller.utils.ErrorStatus;
import controller.utils.Observer;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;

public abstract class Panel extends JPanel {

    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 600;

    private int screen_width;
    private int screen_height;

    protected Observer observer;

    protected Panel() {
        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        this.screen_width = screen_size.width;
        this.screen_height = screen_size.height;
        if (this.screen_width < MIN_WIDTH || this.screen_height < MIN_HEIGHT){
            final ErrorStatus error = ErrorStatus.MIN_SCREEN_SIZE;
            System.err.println("Error: " + error.GetErrorMessage() + " - " + screen_width + "x" + screen_height);
            System.exit( error.GetExitCode() );
        }

        this.setPreferredSize(new Dimension(screen_width, screen_height));

        observer = Observer.GetInstance();
    }

    public int GetScreenWidth(){
        return screen_width;
    }

    public boolean SetScreenWidth(int screen_width){
        if (screen_width < MIN_WIDTH){
            return false;
        }

        this.screen_width = screen_width;
        return true;
    }

    public int GetScreenHeight(){
        return screen_height;
    }

    public boolean SetScreenHeight(int screen_height){
        if (screen_height < MIN_HEIGHT){
            return false;
        }

        this.screen_height = screen_height;
        return true;
    }

}