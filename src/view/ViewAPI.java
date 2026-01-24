package view;

public class ViewAPI{

    private static ViewAPI instance;

    public static ViewAPI GetInstance(){
        if (instance == null){
            instance = new ViewAPI();
        }
        return instance;
    }

    private ViewAPI(){}

    private static final Window window = Window.GetInstance();
    public Window GetWindow(){ return window; }

}