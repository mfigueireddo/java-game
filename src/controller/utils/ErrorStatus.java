package controller.utils;

/**
 * Description:
 * Enum defining all application error states with descriptive messages.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This enum MUST be public as it is:
 *   1. Used cross-package by loaders and renderers for error handling
 *   2. Required by GameExit.Exit() method used throughout the application
 *   3. Used by Panel class for screen size validation
 */
public enum ErrorStatus {
    SUCCESS("Success"),
    MIN_SCREEN_SIZE("User's screen doesn't have minimum requirements"),
    IMAGE_NOT_FOUND_OR_UNSUPPORTED_FORMAT("The image wasn't found OR it has an unsupported format"),
    FAILED_LOADING_IMAGE("The image's file is corrupted"),
    IMAGE_NOT_FOUND("The image wasn't loaded"),
    TEXTFILE_NOT_FOUND_OR_UNSUPPORTED_FORMAT("The textfile wasn't found OR it has an unsupported format"),
    FAILED_LOADING_TEXTFILE("The textfile's file is corrupted"),
    TEXTFILE_NOT_FOUND("The textfile wasn't loaded"),
    EMPTY_TEXTFILE("A necessary textfile is empty"),
    UNRECOGNIZED_MAP_ELEMENT("A unrecognized element was found during map loading");

    private final String error_message;

    ErrorStatus(String error_message){
        this.error_message = error_message;
    }

    public int GetExitCode() {
        return this.ordinal();
    }

    public String GetErrorMessage(){
        return error_message;
    }
}