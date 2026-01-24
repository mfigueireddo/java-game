package management;

public enum Images {
    Grass("world/");

    private final String folder;

    Images(String folder) {
        this.folder = folder;
    }

    public int GetImageCode() {
        return this.ordinal();
    }

    public String GetFolder() {
        return this.folder;
    }

    public String GetFileName() {
        return this.name().toLowerCase() + ".png";
    }
}