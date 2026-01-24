package controller.files_managers;

public class FileInfo {
    private final int id;
    private final String name;
    private final String specific_folder;

    public FileInfo(int id, String name, String specific_folder) {
        this.id = id;
        this.name = name;
        this.specific_folder = specific_folder;
    }

    public int GetID() {
        return this.id;
    }

    public String GetName(){
        return this.name;
    }

    public String GetSpecificFolder() {
        return this.specific_folder;
    }
}