package controller.files_controllers;

/**
 * Folder controller for tracking folder resources.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This class MUST be public as it is:
 *   1. Used as a type parameter in Manager class methods accessed cross-package
 *   2. Instantiated in ImageManager and TextFileManager
 *   3. Passed to and returned from public Manager API methods
 */
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