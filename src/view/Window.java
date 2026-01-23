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
    private final Game game = Game.GetInstance();
    
    private Window() {
        this.setTitle("Java 2D Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
    }

    private void Show(){
        this.setVisible(true);
    }

    // TODO: this logic resizes the window each time its called
    private void ChangePanel(JPanel panel){
        this.getContentPane().removeAll();
        this.add(panel);
        this.pack(); this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.revalidate(); this.repaint();
        this.Show();
    }

    public void ShowMenu(){
        ChangePanel(menu);
    }

    public void ShowGame(){
        ChangePanel(game);
    }

}