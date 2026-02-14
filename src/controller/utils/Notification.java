package controller.utils;

/**
 * Description:
 * Enum defining all notification types for the observer system.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This enum MUST be public as it is:
 *   1. Used cross-package by both controller and view packages
 *   2. Required by Observer.Register() and Observer.Notify() methods used throughout the application
 */
public enum Notification{
    NEW_GAME,
    LOAD_GAME,
    SETTINGS,
    IMAGE_LOADING_FAILED,
    IMAGE_NOT_FOUND,
    WINDOW_RESIZED;

    /**
     * Description:
     * 1. Returns the ordinal value of this enum constant as the notification ID.
     *
     * Expected Returns:
     * - Returns an integer corresponding to the enum's ordinal position.
     */
    public int GetID() {
        return this.ordinal();
    }
}