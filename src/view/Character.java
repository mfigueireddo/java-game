package view;

import java.awt.Graphics2D;

public class Character extends Renderer{

    private static Character instance;

    public static Character GetInstance(){
        if (instance == null){
            instance = new Character();
        }

        return instance;
    }

    private Character(){}

    @Override
    public void Render(final Graphics2D graphics_2d, final int screen_width, final int screen_height){

    }

}