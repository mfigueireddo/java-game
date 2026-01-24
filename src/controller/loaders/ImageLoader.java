package controller.loaders;

import controller.ErrorStatus;
import controller.GameExit;
import controller.files_managers.ImagesControl;
import controller.files_managers.ImagesManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class ImageLoader{

    private static ImageLoader instance;

    public static ImageLoader GetInstance(){
        if (instance == null){
            instance = new ImageLoader();
        }
        return instance;
    }

    private final ImagesManager images_manager = ImagesManager.GetInstance();
    private final Map < ImagesControl, BufferedImage > loaded_images = new HashMap<>();

    private ImageLoader(){}

    public void Load(){

        final ArrayList<ImagesControl> images_controllers = images_manager.GetImages();

        for (ImagesControl image_controller : images_controllers){
            final String file_path = images_manager.GetFilePath(image_controller);
            LoadImage(image_controller, file_path);
        }
    }

    private void LoadImage(final ImagesControl image_controller, final String file_path){
        try {
            File image_file = new File(file_path);
            if ( !image_file.exists() ) {
                GameExit.Exit(ErrorStatus.IMAGE_NOT_FOUND_OR_UNSUPPORTED_FORMAT, " - " + file_path);
            }
            
            BufferedImage buffered_image = ImageIO.read(image_file);
            if (buffered_image == null) {
                GameExit.Exit(ErrorStatus.IMAGE_NOT_FOUND_OR_UNSUPPORTED_FORMAT, " - " + file_path);
            }
            
            loaded_images.put(image_controller, buffered_image);
        } 
        catch (IOException exception) {
            GameExit.Exit(ErrorStatus.FAILED_LOADING_IMAGE, " - " + file_path);
        }
    }

    public BufferedImage GetImage(ImagesControl image_controller){
        BufferedImage image = loaded_images.get(image_controller);
        if (image == null) {
            GameExit.Exit(ErrorStatus.IMAGE_NOT_FOUND, " - " + image_controller);
        }
        return image;
    }
}