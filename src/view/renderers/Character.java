package view.renderers;

import controller.files_controllers.ImageController;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
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

    public boolean LoadMainCharacter(){
        return LoadCharacter("main");
    }

    // TODO: should it return false?
    public boolean LoadCharacter(final String character_name){
        if ( !used_images.isEmpty() ){
            used_images.clear();
        }

        // Storing a possible empty map isn't a problem with the current implementation because it throws an exception that ends the program
        final ArrayList<String> searched_images = new ArrayList<>( Arrays.asList( "front", "back", "left", "right" ) );
        for (final String searched_image : searched_images){
            final ImageController image_controller = image_manager.GetImage(searched_image);
            final int image_id = image_controller.GetID();
            final BufferedImage image = image_loader.GetImage(image_controller);
            used_images.put(image_id, image);
        }

        return true;
    }

    @Override
    public void Render(final Graphics2D graphics_2d, final Panel panel){
        // Fuck this will be so much work
    }
}