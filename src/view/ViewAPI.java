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

    private static final Map map = Map.GetInstance();
    public Map GetMap(){ return map; }

    private static final Character character = Character.GetInstance();
    public Character GetCharacter(){ return character; }

}