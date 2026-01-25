package view.panels;

import controller.utils.Notification;
import controller.utils.Observer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import view.renderers.Character;
import view.renderers.Map;

public class Game extends Panel{

    private static Game instance;

    public static Game GetInstance(){   
        if (instance == null){
            instance = new Game();
        }
        return instance;
    }

    private final Observer observer = Observer.GetInstance();

    private final Map map = Map.GetInstance();
    private final Character character = Character.GetInstance();

    private Game(){
        RegisterObservers();
    }

    private void RegisterObservers(){
        observer.Register(Notification.NEW_GAME, this::OnNewGame);
        observer.Register(Notification.WINDOW_RESIZED, this::OnWindowResize);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        final Graphics2D graphics_2d = (Graphics2D)graphics;

        map.Render(graphics_2d, this);
        character.Render(graphics_2d, this);
    }

    private void OnNewGame(){
        map.LoadMainMap();
    }
    
    // Might cause problems: Window's constructor doesn't notify resizing, only when changing the displayed panel
    private void OnWindowResize(){
        map.SetScreenSize(getWidth(), getHeight());
        map.CalculateBlockDimensions();
    }
}