package view;

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

    public Window GetWindow(){ return window; }
}