package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
    
    private static Window instance;

    public static Window GetInstance() {
        if (instance == null) {
            instance = new Window();
        }
        return instance;
    }

    private final Menu menu = Menu.GetInstance();
    
    private Window() {
        this.setTitle("Java 2D Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        // TO-DO: make it resizable
        this.setResizable(false);
    }

    private void Show(){
        this.setVisible(true);
    }

    private void ChangePanel(JPanel panel){
        this.getContentPane().removeAll();
        this.add(panel);
        this.pack(); this.revalidate(); this.repaint();
        this.Show();
    }

    public void ShowMenu(){
        ChangePanel(menu);
    }
}
