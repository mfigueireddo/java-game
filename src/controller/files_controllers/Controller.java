package controller.files_controllers;

// A safer way to store and track files and folders
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