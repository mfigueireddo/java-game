package view;

import java.awt.Graphics2D;

public class Map extends Renderer{

    private static Map instance;

    public static Map GetInstance(){
        if (instance == null){
            instance = new Map();
        }
        return instance;
    }

    private Map(){}
    
    @Override
    public void Render(final Graphics2D graphics_2d, final int screen_width, final int screen_height){
        // Temporary implementation just to check if the class is working properly
        graphics_2d.setColor(java.awt.Color.BLACK);
        graphics_2d.fillRect(0, 0, screen_width, screen_height);
    }

}