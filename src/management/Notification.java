package management;

public enum Notification{
    NEW_GAME,
    LOAD_GAME,
    SETTINGS;

    public int GetID() {
        return this.ordinal();
    }

}