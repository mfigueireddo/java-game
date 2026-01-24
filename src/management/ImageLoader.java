package management;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    private final Map < Images, BufferedImage > loaded_images = new HashMap<>();

    private ImageLoader(){}

    public void Load(){
        final String source_path = "src/images/";
        
        for ( Images image : Images.values() ) {
            final String full_path = source_path + image.GetFolder() + image.GetFileName();
            LoadImage(image, full_path);
        }
    }

    private void LoadImage(Images image_key, String file_path){
        try {
            File image_file = new File(file_path);
            if ( !image_file.exists() ) {
                final ErrorStatus error = ErrorStatus.IMAGE_NOT_FOUND_OR_UNSUPPORTED_FORMAT;
                System.err.println("Error: " + error.GetErrorMessage() + " - " + file_path);
                System.exit( error.GetExitCode() );
            }
            
            BufferedImage buffered_image = ImageIO.read(image_file);
            if (buffered_image == null) {
                final ErrorStatus error = ErrorStatus.IMAGE_NOT_FOUND_OR_UNSUPPORTED_FORMAT;
                System.err.println("Error: " + error.GetErrorMessage() + " - " + file_path);
                System.exit( error.GetExitCode() );
            }
            
            loaded_images.put(image_key, buffered_image);
        } 
        catch (IOException exception) {
            final ErrorStatus error = ErrorStatus.FAILED_LOADING_IMAGE;
            System.err.println("Error: " + error.GetErrorMessage() + " - " + file_path);
            System.exit( error.GetExitCode() );
        }
    }

    public BufferedImage GetImage(Images image_key){
        BufferedImage image = loaded_images.get(image_key);
        if (image == null) {
            final ErrorStatus error = ErrorStatus.IMAGE_NOT_FOUND;
            System.err.println("Error: " + error.GetErrorMessage() + " - " + image_key);
            System.exit( error.GetExitCode() );
        }
        return image;
    }

}