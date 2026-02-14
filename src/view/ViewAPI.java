package view;

/**
 * Description:
 * 1. Singleton facade that provides a simplified public API for the view layer.
 * 2. Delegates its calls, shielding the controller from direct interaction with view internals.
 */
public class ViewAPI{

    private static ViewAPI instance;

    public static ViewAPI GetInstance(){
        if (instance == null){
            instance = new ViewAPI();
        }
        return instance;
    }

    private static final Window window = Window.GetInstance();

    private ViewAPI(){}

    public void ShowMenu(){ 
        window.ShowMenu(); 
    }
}