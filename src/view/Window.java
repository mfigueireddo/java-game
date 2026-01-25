package view;

import controller.utils.Notification;
import controller.utils.Observer;
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

        setTitle("Java 2D Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        pack(); setLocationRelativeTo(null);
    }

    private void RegisterObservers(){
        observer.Register(Notification.NEW_GAME, this::ShowGame);
    }

    private void Show(){
        setVisible(true);
    }

    private void ChangePanel(JPanel panel){
        getContentPane().removeAll();
        add(panel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        revalidate(); repaint();

        observer.Notify(Notification.WINDOW_RESIZED);
        
        Show();
    }

    public void ShowMenu(){
        ChangePanel(menu);
    }

    public void ShowGame(){
        ChangePanel(game);
    }
}