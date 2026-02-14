package controller.files_controllers;

public class ImageController extends Controller{

    private static int id_tracker = 1;

    /**
     * Parameters:
     * - name: The image file name without extension (e.g., "grass", "front").
     */
    public ImageController(final String name){
        super(ImageController.id_tracker++, name);
    }
}