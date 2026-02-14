package controller.files_managers;

import controller.files_controllers.Controller;
import controller.files_controllers.FolderController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Abstract base class that simplifies file management by organizing resources into folder-based groups.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This abstract base class MUST be public as it is:
 *   1. Extended by manager subclasses (ImageManager, TextFileManager) accessed cross-package
 *   2. Provides public methods (GetController, GetFilePath, etc.) used by Loaders and Renderers
 */
public abstract class Manager {
    private final String directory;
    private final String extension; // At the moment, only one extension is supported per file type

    // Every file MUST belong to a specific folder
    // Folder manipulation isn't important, therefore not implemented
    private final Map<FolderController, ArrayList<Controller>> resources = new HashMap<>();

    /**
     * Parameters:
     * - directory: The base directory path for this resource type (e.g., "src/images/").
     * - extension: The file extension including the dot (e.g., ".png", ".txt").
     */
    protected Manager(final String directory, final String extension){
        this.directory = directory;
        this.extension = extension;
    }

    /**
     * Description:
     * 1. Registers a group of controllers under the given folder controller.
     * 2. Adds the folder-controllers mapping to the internal resources map.
     */
    protected void RegisterControllers(final FolderController folder_controller, final ArrayList<Controller> controllers){
        resources.put(folder_controller, controllers);
    }

    public Controller GetController(final int id){
        for (ArrayList<Controller> controllers : resources.values()){
            for (Controller controller : controllers){
                if (controller.GetID() == id){
                    return controller;
                }
            }
        }
        return null;
    }

    public Controller GetController(final String name){
        for (ArrayList<Controller> controllers : resources.values()){
            for (Controller controller : controllers){
                if (controller.GetName().equals(name)){
                    return controller;
                }
            }
        }
        return null;
    }

    public ArrayList<Controller> GetFolderControllers(final FolderController folder){
        final ArrayList<Controller> controllers = resources.get(folder);
        return controllers != null ? controllers : new ArrayList<>();
    }

    public ArrayList<Controller> GetControllers(){
        final ArrayList<Controller> all_controllers = new ArrayList<>();
        for (ArrayList<Controller> controllers : resources.values()){
            all_controllers.addAll(controllers);
        }
        return all_controllers;
    }

    /**
     * Expected Returns:
     * - Returns the file name with extension (e.g., "grass.png") when the controller is found.
     * - Returns null when the controller is not registered in any folder.
     */
    public String GetFileFullName(Controller controller){
        for (Map.Entry<FolderController, ArrayList<Controller>> entry : resources.entrySet()){
            if ( entry.getValue().contains(controller) ){
                return controller.GetName() + extension;
            }
        }
        return null;
    }

    /**
     * Expected Returns:
     * - Returns the full file path (e.g., "src/images/world/grass.png") when the controller is found.
     * - Returns null when the controller is not registered in any folder.
     */
    public String GetFilePath(Controller controller){
        for (Map.Entry<FolderController, ArrayList<Controller>> entry : resources.entrySet()){
            if ( entry.getValue().contains(controller) ){
                final FolderController folder = entry.getKey();
                return directory + folder.GetName() + controller.GetName() + extension;
            }
        }
        return null;
    }
}