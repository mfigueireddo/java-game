package controller.utils;

/**
 * Description:
 * 1. Utility class providing centralized error-based application exit.
 * 2. Formats and prints error messages to stderr, then exits with the corresponding error code.
 * 3. Private constructor prevents instantiation.
 *
 * Restrictions:
 * - This class must not be instantiated; all methods are static.
 */
public class GameExit {
    
    private GameExit(){}

    public static void Exit(final ErrorStatus error){
        Exit(error, "");
    }

    /**
     * Description:
     * Exits the application with the error's exit code.
     */
    public static void Exit(final ErrorStatus error, String additional_info){
        String error_output = "Error: " + error.GetErrorMessage();
        if ( !additional_info.isEmpty() ){
            error_output += additional_info;
        }

        System.err.println(error_output);
        System.exit( error.GetExitCode() );
    }
}