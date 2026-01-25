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

    @Override
    protected void HandleMissingResource(final Controller textfile_controller) {
        GameExit.Exit(ErrorStatus.TEXTFILE_NOT_FOUND, " - " + textfile_controller);
    }
}