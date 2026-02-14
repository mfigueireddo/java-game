package controller.utils;

/**
 * Description:
 * 1. Enum defining all application error states with descriptive messages.
 * 2. Each constant carries an error message and uses its ordinal value as the exit code.
 * 3. Used by GameExit to print formatted error messages and exit the application.
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