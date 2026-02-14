package view.renderers;

import controller.files_controllers.ImageController;
import controller.files_controllers.TextFileController;
import controller.utils.ErrorStatus;
import controller.utils.GameExit;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import view.panels.Panel;

/**
 * Description:
 * Singleton renderer responsible for parsing and rendering tile-based maps from text files.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This class MUST be public as it:
 *   1. Extends Renderer (public class)
 *   2. Is instantiated and used by Game panel to render maps
 *   3. Provides public LoadMainMap(), CalculateBlockDimensions(), and Render() methods accessed from Game
 */
public class Map extends Renderer{

    private static Map instance;

    /**
     * Description:
     * 1. Returns the existing singleton instance, or creates a new one if none exists.
     *
     * Expected Returns:
     * - Returns the singleton Map instance.
     */
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

    /**
     * Description:
     * 1. Delegates to LoadMap("main") to load the main map.
     *
     * Expected Returns:
     * - Returns true when the map was successfully loaded.
     * - Returns false when the "main" map is already loaded.
     */
    public boolean LoadMainMap(){
        return LoadMap("main");
    }

    /**
     * Objective: Loads a named map, resolving its images and calculating tile dimensions.
     *
     * Description:
     * 1. Checks if the requested map is already loaded; returns false if so.
     * 2. Clears the used_images cache if it contains data from a previous map.
     * 3. Retrieves the map text file content via TextFileLoader.
     * 4. Exits with EMPTY_TEXTFILE if the map content is empty.
     * 5. Calls LoadImages() to parse tile IDs and cache their corresponding images.
     * 6. Calls RegisterMapDimensions() to determine map width/height and calculate block sizes.
     *
     * Parameters:
     * - map_name: The name of the map to load (e.g., "main"), matching a registered TextFileController.
     *
     * Expected Returns:
     * - Returns true when the map was successfully loaded.
     * - Returns false when the map with the given name is already loaded.
     *
     * Assertives of Entrance:
     * - The TextFileManager and TextFileLoader must be initialized with the map registered and loaded.
     *
     * Assertives of Departure:
     * - current_map_name and current_map are updated to the new map.
     * - used_images contains all tile images referenced by the map.
     * - map_width, map_height, block_width, and block_height are calculated.
     */
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

    /**
     * Description:
     * 1. Splits the entire map content by whitespace to extract all tile element strings.
     * 2. Parses each element as an integer tile ID.
     * 3. Resolves each ID to an ImageController via ImageManager, then loads the image
     *    via ImageLoader.
     * 4. Stores each tile ID and its corresponding BufferedImage in the used_images cache.
     *
     * Parameters:
     * - map: The full map text content with space-separated integer tile IDs.
     *
     * Restrictions:
     * - Exits the application with UNRECOGNIZED_MAP_ELEMENT if a non-numeric element is found.
     */
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

    /**
     * Description:
     * 1. Splits the map content by newlines to determine the number of rows.
     * 2. Splits the first row by whitespace to determine the number of columns.
     * 3. Sets map_height and map_width accordingly.
     * 4. Calls CalculateBlockDimensions() to compute tile sizes based on screen and map dimensions.
     *
     * Parameters:
     * - map: The full map text content.
     *
     * Assertives of Entrance:
     * - The map content is non-empty (validated earlier by LoadMap).
     *
     * Assertives of Departure:
     * - map_width and map_height reflect the map's column and row counts.
     * - block_width and block_height are calculated.
     */
    private void RegisterMapDimensions(final String map){
        // No need to check if there is any element in the file because it would have been caught by TextFileLoader
        final String[] lines = map.trim().split("\\n");
        final String[] first_row_elements = lines[0].trim().split("\\s+");

        map_height = lines.length;
        map_width = first_row_elements.length;

        CalculateBlockDimensions();
    }

    /**
     * Description:
     * 1. Divides the screen width by the map width to get the tile width.
     * 2. Divides the screen height by the map height to get the tile height.
     *
     * Assertives of Entrance:
     * - map_width and map_height must be greater than zero.
     * - screen_width and screen_height should be set via SetScreenSize().
     */
    public void CalculateBlockDimensions(){
        block_width = (float) screen_width / map_width;
        block_height = (float) screen_height / map_height;
    }
    
    /**
     * Description:
     * 1. Splits the current map content into rows by newline.
     * 2. For each row, splits by whitespace to get individual tile IDs.
     * 3. For each tile, parses the integer ID and retrieves the cached BufferedImage.
     * 4. Calculates the tile's position using the column/row index multiplied by block dimensions.
     * 5. Calculates the tile's rendered width and height by computing the difference between
     *    the current and next tile positions, compensating for fractional pixel rounding
     *    to avoid unpainted areas.
     * 6. Draws each tile image at the calculated position with the calculated dimensions.
     *
     * Parameters:
     * - graphics_2d: The Graphics2D context used for rendering.
     * - panel: The Panel used as the ImageObserver for drawImage().
     *
     * Assertives of Entrance:
     * - A map must be loaded (current_map is non-empty, used_images is populated).
     * - Screen dimensions and block dimensions must be calculated.
     */
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