package controller.files_controllers;

/**
 * Image controller for tracking image resources.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This class MUST be public as it is:
 *   1. Returned by public ImageManager methods accessed cross-package from renderers
 *   2. Used as a parameter type in ImageLoader methods accessed cross-package
 */
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