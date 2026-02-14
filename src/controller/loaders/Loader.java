package controller.loaders;

import controller.files_controllers.Controller;
import controller.files_managers.Manager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * 1. Abstract generic base class that simplifies file loading using the template method pattern.
 * 2. Iterates over all registered controllers from the corresponding manager, loads each
 *    resource from disk via the subclass's LoadData() implementation, and stores results.
 * 3. Provides access to loaded data by controller, delegating missing resource handling
 *    to the subclass.
 * 4. Subclasses (ImageLoader, TextFileLoader) define how to get the manager, load data,
 *    and handle missing resources.
 */
public abstract class Loader<Data> {
    protected final Map<Controller, Data> loaded_resources = new HashMap<>();

    protected Loader(){}

    protected abstract Manager GetManager();
    protected abstract Data LoadData(final Controller resource_controller, final String file_path);
    protected abstract void HandleMissingResource(final Controller resource_controller);

    /**
     * Description:
     * 1. Obtains the corresponding manager via GetManager().
     * 2. Retrieves all registered controllers from the manager.
     * 3. For each controller, resolves the file path and calls LoadData() to load the resource.
     * 4. Stores each loaded resource in the loaded_resources map keyed by controller.
     *
     * Assertives of Entrance:
     * - The corresponding Manager singleton must be initialized with all resources registered.
     */
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