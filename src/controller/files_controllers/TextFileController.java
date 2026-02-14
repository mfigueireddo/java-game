package controller.files_controllers;

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