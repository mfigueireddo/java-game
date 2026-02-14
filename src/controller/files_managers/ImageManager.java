package controller.files_managers;

import controller.files_controllers.Controller;
import controller.files_controllers.FolderController;
import controller.files_controllers.ImageController;
import java.util.ArrayList;

/**
 * Description:
 * 1. Singleton manager responsible for registering and organizing all image resources.
 * 2. Extends Manager with directory "src/images/" and extension ".png".
 */
public class ImageManager extends Manager{

    private static ImageManager instance;

    public static ImageManager GetInstance(){
        if (instance == null){
            instance = new ImageManager();
        }
        return instance;
    }

    /**
     * Description:
     * Transform a generic Controller ArrayList to a ImageController Arraylist
     */
    private ArrayList<ImageController> ToImageControllers(ArrayList<Controller> controllers){
         final ArrayList<ImageController> images = new ArrayList<>();
        
        for (Controller controller : controllers){
            images.add( (ImageController) controller );
        }
        return images;
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

    // TODO: maybe this could be even more generic
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

    public ArrayList<ImageController> GetFolderImages(final FolderController folder){
        final ArrayList<Controller> controllers = GetFolderControllers(folder);
        return ToImageControllers(controllers);
    }

    public ArrayList<ImageController> GetImages(){
        final ArrayList<Controller> controllers = GetControllers();
        return ToImageControllers(controllers);
    }
}