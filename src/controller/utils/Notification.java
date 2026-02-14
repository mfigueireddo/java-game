package controller.utils;

/**
 * Description:
 * 1. Enum defining all notification types for the observer system.
 * 2. Each constant represents a distinct event that components can register for and react to.
 * 3. Uses the ordinal value as a unique notification ID.
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