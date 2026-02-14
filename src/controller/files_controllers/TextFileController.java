package controller.files_controllers;

/**
 * Text file controller for tracking text file resources.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This class MUST be public as it is:
 *   1. Returned by public TextFileManager methods accessed cross-package from renderers
 *   2. Used as a parameter type in TextFileLoader methods accessed cross-package
 */
public class TextFileController extends Controller{

    private static int id_tracker = 1;

    /**
     * Parameters:
     * - name: The text file name without extension (e.g., "main").
     */
    public TextFileController(final String name){
        super(TextFileController.id_tracker++, name);
    }
}