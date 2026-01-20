package view;

import java.awt.Dimension;
import javax.swing.JPanel;

public abstract class Panel extends JPanel {

    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 600;

    protected Panel() {
        this.setPreferredSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
    }

}
