package controller.loaders;

import controller.files_controllers.Controller;
import controller.files_controllers.ImageController;
import controller.files_managers.ImageManager;
import controller.files_managers.Manager;
import controller.utils.ErrorStatus;
import controller.utils.GameExit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Description:
 * 1. Singleton loader responsible for loading image files from disk using ImageIO.
 * 2. Extends Loader with BufferedImage as the data type.
 * 3. Uses ImageManager as its corresponding manager for file path resolution.
 * 4. Exits the application with descriptive error codes when images are missing,
 *    corrupted, or in unsupported formats.
 */
public class ImageLoader extends Loader<BufferedImage>{
    private static ImageLoader instance;

    public static ImageLoader GetInstance(){
        if (instance == null){
            instance = new ImageLoader();
        }
        return instance;
    }

    private ImageLoader(){}
    
    public BufferedImage GetImage(final ImageController image_controller){
        return GetData(image_controller);
    }

    @Override
    protected Manager GetManager(){
        return ImageManager.GetInstance();
    }

    /**
     * Description:
     * 1. Creates a File object from the given file path.
     * 2. Verifies the file exists on disk; exits with IMAGE_NOT_FOUND_OR_UNSUPPORTED_FORMAT if not.
     * 3. Reads the image using ImageIO.read(); exits with IMAGE_NOT_FOUND_OR_UNSUPPORTED_FORMAT
     *    if the result is null (unsupported format).
     * 4. Returns the loaded BufferedImage.
     *
     * Parameters:
     * - image_controller: The controller identifying the image resource being loaded.
     * - file_path: The resolved file path to the image on disk.
     *
     * Expected Returns:
     * - Returns the loaded BufferedImage on success.
     * - Returns null only if an IOException occurs (after exiting the application).
     *
     * Restrictions:
     * - Exits the application on any loading failure rather than throwing exceptions.
     */
    @Override
    protected BufferedImage LoadData(final Controller image_controller, final String file_path){
        try {
            final File image_file = new File(file_path);
            if ( !image_file.exists() ) {
                GameExit.Exit(ErrorStatus.IMAGE_NOT_FOUND_OR_UNSUPPORTED_FORMAT, " - " + file_path);
            }
            
            BufferedImage buffered_image = ImageIO.read(image_file);
            if (buffered_image == null) {
                GameExit.Exit(ErrorStatus.IMAGE_NOT_FOUND_OR_UNSUPPORTED_FORMAT, " - " + file_path);
            }
            
            return buffered_image;
        } 
        catch (IOException exception) {
            GameExit.Exit(ErrorStatus.FAILED_LOADING_IMAGE, " - " + file_path);
            return null;
        }
    }

    /**
     * Description:
     * 1. Exits the application with IMAGE_NOT_FOUND error and the controller's string
     *    representation as additional info.
     */
    @Override
    protected void HandleMissingResource(final Controller image_controller) {
        GameExit.Exit(ErrorStatus.IMAGE_NOT_FOUND, " - " + image_controller);
    }
}