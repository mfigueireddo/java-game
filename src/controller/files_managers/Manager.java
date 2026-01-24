package controller.files_managers;

import controller.files_controllers.Controller;
import controller.files_controllers.FolderController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// An abstraction to simplify files' management
public abstract class Manager {
    private final String directory;
    private final String extension; // At the moment, only one extension is supported per file type

    // Every file MUST belong to a specific folder
    // Folder manipulation isn't important, therefore not implemented
    private final Map<FolderController, ArrayList<Controller>> resources = new HashMap<>();

    protected Manager(final String directory, final String extension){
        this.directory = directory;
        this.extension = extension;
    }

    protected void RegisterControllers(final FolderController folder_controller, final ArrayList<Controller> controllers){
        this.resources.put(folder_controller, controllers);
    }

    public Controller GetController(final int id){
        for (ArrayList<Controller> controllers : this.resources.values()){
            for (Controller controller : controllers){
                if (controller.GetID() == id){
                    return controller;
                }
            }
        }
        return null;
    }

    public ArrayList<Controller> GetControllers(){
        final ArrayList<Controller> all_controllers = new ArrayList<>();
        for (ArrayList<Controller> controllers : this.resources.values()){
            all_controllers.addAll(controllers);
        }
        return all_controllers;
    }

    public String GetFilePath(Controller controller){
        for (Map.Entry<FolderController, ArrayList<Controller>> entry : this.resources.entrySet()){
            if ( entry.getValue().contains(controller) ){
                final FolderController folder = entry.getKey();
                return this.directory + folder.GetName() + controller.GetName() + this.extension;
            }
        }
        return null;
    }
}