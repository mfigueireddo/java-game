package controller.files_managers;

import controller.files_controllers.Controller;
import controller.files_controllers.FolderController;
import controller.files_controllers.TextFileController;
import java.util.ArrayList;

public class TextFileManager extends Manager{

    private static TextFileManager instance;

    public static TextFileManager GetInstance(){
        if (instance == null){
            instance = new TextFileManager();
        }
        return instance;
    }

    private TextFileManager(){
        super("src/persistance/", ".txt");

        RegisterMapTextFiles();
    }

    // TODO: maybe this could be even more generic
    private void RegisterMapTextFiles(){
        final FolderController folder = new FolderController("maps/");

        final ArrayList<Controller> maps = new ArrayList<>();
        maps.add(new TextFileController("main"));

        RegisterControllers(folder, maps);
    }

    public TextFileController GetImage(final int id){
        return (TextFileController) GetController(id);
    }

    public ArrayList<TextFileController> GetMaps(){
        final ArrayList<Controller> controllers = GetControllers();
        final ArrayList<TextFileController> maps = new ArrayList<>();
        
        for (Controller controller : controllers){
            maps.add( (TextFileController) controller );
        }
        return maps;
    }
}