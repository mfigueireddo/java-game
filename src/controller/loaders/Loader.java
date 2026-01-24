package controller.loaders;

import controller.files_controllers.Controller;
import controller.files_managers.Manager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// An abstraction to simplify files' loading
public abstract class Loader<Data> {
    protected final Map<Controller, Data> loaded_resources = new HashMap<>();

    protected Loader(){}

    protected abstract Manager GetManager();
    protected abstract Data LoadData(final Controller resource_controller, final String file_path);
    protected abstract void HandleMissingResource(final Controller resource_controller);

    public void Load(){
        final Manager manager = GetManager();

        final ArrayList<Controller> controllers = manager.GetControllers();

        for (Controller controller : controllers){
            final String file_path = manager.GetFilePath(controller);
            final Data loaded_data = LoadData(controller, file_path);
            loaded_resources.put(controller, loaded_data);
        }
    }

    public Data GetData(final Controller resource_controller) {
        final Data resource = loaded_resources.get(resource_controller);
        if (resource == null) {
            HandleMissingResource(resource_controller);
        }
        return resource;
    }
}