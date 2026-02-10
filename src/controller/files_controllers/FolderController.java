package controller.files_controllers;

public class FolderController extends Controller{

    private static int id_tracker = 1;

    public FolderController(final String name){
        super(FolderController.id_tracker++, name);
    }
}