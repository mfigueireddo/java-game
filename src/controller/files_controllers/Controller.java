package controller.files_controllers;

/**
 * Description:
 * 1. Abstract base class that provides a safe way to store and track files and folders.
 * 2. Each instance holds an immutable unique integer ID and a string name.
 * 3. Subclasses (FolderController, ImageController, TextFileController) provide
 *    auto-incrementing IDs for their respective resource types.
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