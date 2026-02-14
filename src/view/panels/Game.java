package view.panels;

import controller.utils.Notification;
import controller.utils.Observer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import view.renderers.Character;
import view.renderers.Map;

/**
 * Description:
 * Singleton main game screen extending Panel where gameplay rendering occurs.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This class MUST be public as it:
 *   1. Extends Panel (public class)
 *   2. Is instantiated and managed by Window
 *   3. Provides singleton instance accessed by Window for panel switching
 */
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

    /**
     * Description:
     * 1. Calls the parent paintComponent() for default painting behavior.
     * 2. Casts the Graphics object to Graphics2D.
     * 3. Delegates rendering to the correct Renderers.
     */
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
    
    /**
     * Description:
     * 1. Updates the map renderer's screen dimensions with the current panel size.
     * 2. Recalculates block (tile) dimensions based on the new screen size.
     */
    // Might cause problems: Window's constructor doesn't notify resizing, only when changing the displayed panel
    private void OnWindowResize(){
        map.SetScreenSize(getWidth(), getHeight());
        map.CalculateBlockDimensions();
    }
}