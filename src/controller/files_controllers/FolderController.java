package controller.files_controllers;

public class FolderController extends Controller{

    private static int id_tracker = 1;

    /**
     * Parameters:
     * - name: The folder name, including a trailing slash (e.g., "world/", "main_character/").
     */
    public FolderController(final String name){
        super(FolderController.id_tracker++, name);
    }
}