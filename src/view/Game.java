package view;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Game extends Panel{

    private static Game instance;

    public static Game GetInstance(){   
        if (instance == null){
            instance = new Game();
        }
        return instance;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        final Graphics2D graphics_2d = (Graphics2D)graphics;
        final ViewAPI view_api = ViewAPI.GetInstance();
        final Map map = view_api.GetMap();
        final Character character = view_api.GetCharacter();

        map.Render(graphics_2d, GetScreenWidth(), GetScreenHeight());
        character.Render(graphics_2d, GetScreenWidth(), GetScreenHeight());
    }

}