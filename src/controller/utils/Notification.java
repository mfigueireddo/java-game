package controller.utils;

public enum Notification{
    NEW_GAME,
    LOAD_GAME,
    SETTINGS,
    IMAGE_LOADING_FAILED,
    IMAGE_NOT_FOUND,
    WINDOW_RESIZED;

    public int GetID() {
        return this.ordinal();
    }
}