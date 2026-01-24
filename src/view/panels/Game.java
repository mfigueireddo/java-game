package view.panels;

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

    private final Map map = Map.GetInstance();
    private final Character character = Character.GetInstance();

    private Game(){}

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        final Graphics2D graphics_2d = (Graphics2D)graphics;

        map.Render(graphics_2d, GetScreenWidth(), GetScreenHeight());
        character.Render(graphics_2d, GetScreenWidth(), GetScreenHeight());
    }

}