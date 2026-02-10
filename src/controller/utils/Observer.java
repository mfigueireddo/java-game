package controller.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Observer{
    private static Observer instance;

    public static Observer GetInstance(){
        if (instance == null){
            instance = new Observer();
        }
        return instance;
    }
    
    private final Map< Notification, List<Runnable> > observers = new HashMap<>();

    private Observer(){}

    public boolean Register(Notification notification, Runnable function){
        if ( !observers.containsKey(notification) ) {
            List<Runnable> function_list = new ArrayList<>();
            function_list.add(function);

            observers.put(notification, function_list);
            return true;
        }
        
        List<Runnable> function_list = observers.get(notification);
        if ( function_list.contains(function) ) {
            return false; // Function already registered
        }
        
        function_list.add(function);
        return true;
    }

    public boolean Unregister(Notification notification, Runnable function){
        if ( !observers.containsKey(notification) ) {
            return false;
        }
        
        List<Runnable> function_list = observers.get(notification);
        if ( !function_list.contains(function) ) {
            return false; // Function not registered
        }
        
        function_list.remove(function);
        return true;
    }

    public boolean Notify(Notification notification){
        if ( !observers.containsKey(notification) ) {
            return false;
        }
        
        List<Runnable> function_list = observers.get(notification);
        for (Runnable function : function_list) {
            function.run();
        }
        return true;
    }
}