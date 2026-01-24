package controller.files_managers;

public enum ImagesControl {
    GRASS;
    
    public int GetCode() {
        return this.ordinal();
    }
    
    public String GetName() {
        return this.name().toLowerCase();
    }
}