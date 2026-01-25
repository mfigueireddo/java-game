package controller.utils;

public enum ErrorStatus {
    SUCCESS("Success"),
    MIN_SCREEN_SIZE("User's screen doesn't have minimum requirements"),
    IMAGE_NOT_FOUND_OR_UNSUPPORTED_FORMAT("The image wasn't found OR it has an unsupported format"),
    FAILED_LOADING_IMAGE("The image's file is corrupted"),
    IMAGE_NOT_FOUND("The image wasn't loaded"),
    TEXTFILE_NOT_FOUND_OR_UNSUPPORTED_FORMAT("The textfile wasn't found OR it has an unsupported format"),
    FAILED_LOADING_TEXTFILE("The textfile's file is corrupted"),
    TEXTFILE_NOT_FOUND("The textfile wasn't loaded");

    private final String error_message;

    ErrorStatus(String error_message){
        this.error_message = error_message;
    }

    public int GetExitCode() {
        return this.ordinal();
    }

    public String GetErrorMessage(){
        return this.error_message;
    }
}