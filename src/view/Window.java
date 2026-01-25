package view;

import controller.Notification;
import controller.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.panels.Game;
import view.panels.Menu;

public class Window extends JFrame {
    
    private static Window instance;

    public static Window GetInstance() {
        if (instance == null) {
            instance = new Window();
        }
        return instance;                        
    }

    private final Observer observer = Observer.GetInstance();
    
    private final Menu menu = Menu.GetInstance();
    private final Game game = Game.GetInstance();
    
    private Window() {
        RegisterObservers();

        this.setTitle("Java 2D Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.pack(); this.setLocationRelativeTo(null);
    }

    private void RegisterObservers(){
        observer.Register(Notification.NEW_GAME, this::ShowGame);
    }

    private void Show(){
        this.setVisible(true);
    }

    private void ChangePanel(JPanel panel){
        this.getContentPane().removeAll();
        this.add(panel);
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