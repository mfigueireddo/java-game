package controller.files_managers;

import controller.files_controllers.Controller;
import controller.files_controllers.FolderController;
import controller.files_controllers.TextFileController;
import java.util.ArrayList;

/**
 * Description:
 * Singleton manager responsible for registering and organizing all text file resources.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This class MUST be public as it is:
 *   1. Accessed cross-package from view.renderers (Map) to retrieve text file metadata
 *   2. Provides singleton instance via GetInstance() to multiple subsystems
 */
public class TextFileManager extends Manager{

    private static TextFileManager instance;

    public static TextFileManager GetInstance(){
        if (instance == null){
            instance = new TextFileManager();
        }
        return instance;
    }

    /**
     * Description:
     * Transform a generic Controller ArrayList to a TextFileController Arraylist
     */
    private ArrayList<TextFileController> ToTextFileControllers(ArrayList<Controller> controllers){
        final ArrayList<TextFileController> textfiles = new ArrayList<>();
        
        for (Controller controller : controllers){
            textfiles.add( (TextFileController) controller );
        }
        return textfiles;
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

    public TextFileController GetTextFile(final int id){
        return (TextFileController) GetController(id);
    }

    public TextFileController GetTextFile(final String name){
        return (TextFileController) GetController(name);
    }

    public ArrayList<TextFileController> GetFolderTextFiles(final FolderController folder){
        final ArrayList<Controller> controllers = GetFolderControllers(folder);
        return ToTextFileControllers(controllers);
    }

    public ArrayList<TextFileController> GetTextFiles(){
        final ArrayList<Controller> controllers = GetControllers();
        return ToTextFileControllers(controllers);
    }
}