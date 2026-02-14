package controller.loaders;

import controller.files_controllers.Controller;
import controller.files_controllers.TextFileController;
import controller.files_managers.Manager;
import controller.files_managers.TextFileManager;
import controller.utils.ErrorStatus;
import controller.utils.GameExit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Description:
 * Singleton loader responsible for loading text files from disk using BufferedReader.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This class MUST be public as it is:
 *   1. Accessed from GameRunner to load text files at startup via Load()
 *   2. Accessed cross-package from view.renderers to retrieve loaded text files via GetTextFile()
 *   3. Provides singleton instance to multiple subsystems
 */
public class TextFileLoader extends Loader<String>{
    private static TextFileLoader instance;

    public static TextFileLoader GetInstance(){
        if (instance == null){
            instance = new TextFileLoader();
        }
        return instance;
    }

    private TextFileLoader(){}
    
    public String GetTextFile(final TextFileController textfile_controller){
        return GetData(textfile_controller);
    }

    @Override
    protected Manager GetManager(){
        return TextFileManager.GetInstance();
    }

    /**
     * Description:
     * 1. Creates a File object from the given file path.
     * 2. Verifies the file exists on disk; exits with TEXTFILE_NOT_FOUND_OR_UNSUPPORTED_FORMAT if not.
     * 3. Reads the file line by line using BufferedReader, appending each line with
     *    a system line separator to a StringBuilder.
     * 4. Returns the full file content as a String.
     *
     * Parameters:
     * - textfile_controller: The controller identifying the text file resource being loaded.
     * - file_path: The resolved file path to the text file on disk.
     *
     * Expected Returns:
     * - Returns the file content as a String on success.
     * - Returns null only if an IOException occurs (after exiting the application).
     *
     * Restrictions:
     * - Exits the application on any loading failure rather than throwing exceptions.
     */
    @Override
    protected String LoadData(final Controller textfile_controller, final String file_path){
        try {
            final File textfile = new File(file_path);
            if ( !textfile.exists() ) {
                GameExit.Exit(ErrorStatus.TEXTFILE_NOT_FOUND_OR_UNSUPPORTED_FORMAT, " - " + file_path);
            }
            
            StringBuilder textfile_content = new StringBuilder();
            try (BufferedReader buffered_reader = new BufferedReader(new FileReader(textfile))) {
                String line;
                while ( (line = buffered_reader.readLine()) != null ) {
                    textfile_content.append(line).append(System.lineSeparator());
                }
            }
            
            return textfile_content.toString();
        } 
        catch (IOException exception) {
            GameExit.Exit(ErrorStatus.FAILED_LOADING_TEXTFILE, " - " + file_path);
            return null;
        }
    }

    /**
     * Description:
     * 1. Exits the application with TEXTFILE_NOT_FOUND error and the controller's string
     *    representation as additional info.
     *
     * Restrictions:
     * - Always terminates the application; never returns normally.
     */
    @Override
    protected void HandleMissingResource(final Controller textfile_controller) {
        GameExit.Exit(ErrorStatus.TEXTFILE_NOT_FOUND, " - " + textfile_controller);
    }
}