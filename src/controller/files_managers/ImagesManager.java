package controller.files_managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImagesManager{

    private static ImagesManager instance;

    public static ImagesManager GetInstance(){
        if (instance == null){
            instance = new ImagesManager();
        }
        return instance;
    }

    private static int id = 1;
    private final String extension = ".png";
    private final String folder = "src/images/";

    private final Map < ImagesControl, FileInfo > images = new HashMap<>();

    private ImagesManager(){
        CreateFile(ImagesControl.GRASS, "world/");
    }

    private void CreateFile(final ImagesControl image_controller, final String specific_folder){
        this.images.put(
            image_controller, 
            new FileInfo(
                ImagesManager.id, 
                image_controller.GetName(), 
                specific_folder
            )
        );
        
        ImagesManager.id++;
    }   

    public ImagesControl GetImage(final int id){
        for ( Map.Entry< ImagesControl, FileInfo > entry : this.images.entrySet() ){
            if ( entry.getValue().GetID() == id ){
                return entry.getKey();
            }
        }
        return null;
    }

    public ArrayList<ImagesControl> GetImages(){
        return new ArrayList<>(this.images.keySet());
    }

    public String GetFilePath(ImagesControl image_controller){
        final FileInfo file_manager = this.images.get(image_controller);

        return this.folder + file_manager.GetSpecificFolder() + file_manager.GetName() + this.extension;
    }
}