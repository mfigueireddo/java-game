package controller.files_controllers;

public class ImageController extends Controller{

    private static int id_tracker = 1;

    public ImageController(final String name){
        super(ImageController.id_tracker++, name);
    }
}