package view.renderers;

import controller.files_controllers.ImageController;
import controller.files_controllers.TextFileController;
import controller.utils.ErrorStatus;
import controller.utils.GameExit;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import view.panels.Panel;

public class Map extends Renderer{

    private static Map instance;

    public static Map GetInstance(){
        if (instance == null){
            instance = new Map();
        }
        return instance;
    }

    private String current_map_name = "";
    private String current_map = "";

    private int map_width;
    private int map_height;

    private float block_width;
    private float block_height;

    private Map(){}

    public boolean LoadMainMap(){
        return LoadMap("main");
    }

    private boolean LoadMap(final String map_name){
        if (current_map_name.equals(map_name)){
            return false; // Map already loaded
        }
        current_map_name = map_name;

        if ( !used_images.isEmpty() ){
            used_images.clear();
        }

        // Storing a possible empty map isn't a problem with the current implementation because it throws an exception that ends the program
        final TextFileController map_controller = textfile_manager.GetTextFile(map_name);
        current_map = textfile_loader.GetTextFile(map_controller);
        if ( current_map.isEmpty() ){
            GameExit.Exit(ErrorStatus.EMPTY_TEXTFILE, " - " + textfile_manager.GetFileFullName(map_controller));
        }
        
        LoadImages(current_map);
        RegisterMapDimensions(current_map);
        return true;
    }

    private void LoadImages(final String map){
        final String[] map_elements = map.trim().split("\\s+");
        for (final String map_element : map_elements) {
            try {
                final int element_id = Integer.parseInt(map_element);
                final ImageController element_controller = image_manager.GetImage(element_id);
                final BufferedImage element_image = image_loader.GetImage(element_controller);
                used_images.put(element_id, element_image);
            } 
            catch (NumberFormatException number_format_exception) {
                GameExit.Exit(ErrorStatus.UNRECOGNIZED_MAP_ELEMENT, " - " + map_element);
            }
        }
    }

    private void RegisterMapDimensions(final String map){
        // No need to check if there is any element in the file because it would have been caught by TextFileLoader
        final String[] lines = map.trim().split("\\n");
        final String[] first_row_elements = lines[0].trim().split("\\s+");

        map_height = lines.length;
        map_width = first_row_elements.length;

        CalculateBlockDimensions();
    }

    public void CalculateBlockDimensions(){
        block_width = (float) screen_width / map_width;
        block_height = (float) screen_height / map_height;
    }
    
    // Before creating a viewport, the whole map will be rendered in the screen
    @Override
    public void Render(final Graphics2D graphics_2d, final Panel panel){

        final String[] map_rows = current_map.trim().split("\\n");
        for (int row = 0; row < map_rows.length; row++) {
            
            final String[] row_elements = map_rows[row].trim().split("\\s+");
            for (int column = 0; column < row_elements.length; column++) {
                
                // No need to try catch because it would have been caught before in LoadImages()
                final int element_id = Integer.parseInt(row_elements[column]);
                final BufferedImage element_image = used_images.get(element_id);

                final int current_x_position = (int) (column * block_width);
                final int current_y_position = (int) (row * block_height);

                final int next_x_position = (int) ( (column + 1) * block_width);
                final int next_y_position = (int) ( (row + 1) * block_height);

                // Some tiles will have a slighty bigger size to compensate for the unexact screen size, avoiding unpainted areas
                final int tile_width = next_x_position - current_x_position;
                final int tile_height = next_y_position - current_y_position;

                graphics_2d.drawImage(element_image, current_x_position, current_y_position, tile_width, tile_height, panel);
            }
        }
    }
}