package controller.utils;

/**
 * Description:
 * Utility class providing centralized error-based application exit.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This class MUST be public as it is:
 *   1. Used cross-package by loaders and renderers for error handling
 *   2. Provides static Exit() methods accessed from multiple subsystems
 *   3. Central utility for consistent error reporting throughout the application
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