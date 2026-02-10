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

    @Override
    protected void HandleMissingResource(final Controller image_controller) {
        GameExit.Exit(ErrorStatus.IMAGE_NOT_FOUND, " - " + image_controller);
    }
}