package view.renderers;

import java.awt.Graphics2D;
import view.panels.Panel;

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
    public void Render(final Graphics2D graphics_2d, final Panel panel){

    }
}