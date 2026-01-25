package controller.files_managers;

import controller.files_controllers.Controller;
import controller.files_controllers.FolderController;
import controller.files_controllers.ImageController;
import java.util.ArrayList;

public class ImageManager extends Manager{

    private static ImageManager instance;

    public static ImageManager GetInstance(){
        if (instance == null){
            instance = new ImageManager();
        }
        return instance;
    }

    private ImageManager(){
        super("src/images/", ".png");

        RegisterWorldImages();
        RegisterMainCharacter();
    }

    // TODO: maybe this could be even more generic
    private void RegisterWorldImages(){
        final FolderController folder = new FolderController("world/");

        final ArrayList<Controller> images = new ArrayList<>();
        images.add(new ImageController("grass"));

        RegisterControllers(folder, images);
    }

    // The current implementation of FolderController doesn't allow having folders inside other folders
    private void RegisterMainCharacter(){
        final FolderController folder = new FolderController("main_character/");

        final ArrayList<Controller> images = new ArrayList<>();
        images.add(new ImageController("back"));
        images.add(new ImageController("front"));
        images.add(new ImageController("left"));
        images.add(new ImageController("right"));

        RegisterControllers(folder, images);
    }

    public ImageController GetImage(final int id){
        return (ImageController) GetController(id);
    }

    public ImageController GetImage(final String name){
        return (ImageController) GetController(name);
    }

    public ArrayList<ImageController> GetImages(){
        final ArrayList<Controller> controllers = GetControllers();
        final ArrayList<ImageController> images = new ArrayList<>();
        
        for (Controller controller : controllers){
            images.add( (ImageController) controller );
        }
        return images;
    }
}