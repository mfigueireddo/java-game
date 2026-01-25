package controller.utils;

public class GameExit {
    
    private GameExit(){}

    public static void Exit(final ErrorStatus error){
        Exit(error, "");
    }

    public static void Exit(final ErrorStatus error, String additional_info){
        String error_output = "Error: " + error.GetErrorMessage();
        if ( !additional_info.isEmpty() ){
            error_output += additional_info;
        }

        System.err.println(error_output);
        System.exit( error.GetExitCode() );
    }
}