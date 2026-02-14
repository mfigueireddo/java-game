package view.renderers;

import controller.files_controllers.ImageController;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import view.panels.Panel;

/**
 * Description:
 * Singleton renderer responsible for loading and rendering the player character.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This class MUST be public as it:
 *   1. Extends Renderer (public class)
 *   2. Is instantiated and used by Game panel to render the character
 *   3. Provides public LoadMainCharacter(), LoadCharacter(), and Render() methods for future use
 */
public class Character extends Renderer{

    private static Character instance;

    /**
     * Description:
     * 1. Returns the existing singleton instance, or creates a new one if none exists.
     *
     * Expected Returns:
     * - Returns the singleton Character instance.
     */
    public static Character GetInstance(){
        if (instance == null){
            instance = new Character();
        }

        return instance;
    }

    private Character(){}

    /**
     * Description:
     * 1. Delegates to LoadCharacter("main") to load the main character sprites.
     *
     * Expected Returns:
     * - Returns true when the character sprites are successfully loaded.
     */
    public boolean LoadMainCharacter(){
        return LoadCharacter("main");
    }

    /**
     * Description:
     * 1. Clears the used_images cache if it contains data from a previous character.
     * 2. Iterates over the directional sprite names ("front", "back", "left", "right").
     * 3. For each sprite, retrieves the ImageController by name, loads the image via
     *    ImageLoader, and stores it in the used_images cache keyed by image ID.
     *
     * Parameters:
     * - character_name: The name of the character to load. Currently unused for path
     *   resolution, as sprites are looked up by fixed directional names.
     *
     * Expected Returns:
     * - Returns true when all character sprites are loaded.
     *
     * Assertives of Entrance:
     * - ImageManager and ImageLoader must be initialized with character sprites registered and loaded.
     *
     * Assertives of Departure:
     * - used_images contains BufferedImages for "front", "back", "left", and "right".
     */
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

    /**
     * Description:
     * 1. Placeholder for character rendering logic. Not yet implemented.
     */
    @Override
    public void Render(final Graphics2D graphics_2d, final Panel panel){
        // Fuck this will be so much work
    }
}