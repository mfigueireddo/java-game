package controller.files_controllers;

/**
 * Description:
 * Abstract base class that provides a safe way to store and track files and folders.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This abstract base class MUST be public as it is:
 *   1. Extended by controller subclasses (FolderController, ImageController, TextFileController)
 *   2. Used as a type parameter in Manager and Loader classes across packages
 *   3. Returned by public Manager methods accessed cross-package
 */
public abstract class Controller {
    private final int id;
    private final String name;
    
    protected Controller(final int id, final String name) {
        this.id = id;
        this.name = name;
    }
    
    public int GetID() {
        return id;
    }
    
    public String GetName() {
        return name;
    }
}