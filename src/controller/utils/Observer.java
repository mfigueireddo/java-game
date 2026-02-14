package controller.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Singleton central event/notification system for decoupled component communication.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This class MUST be public as it is:
 *   1. Accessed cross-package from both controller and view packages
 *   2. Used by Window, Game, Menu panels to register/notify events
 *   3. Provides singleton instance to multiple subsystems for inter-component communication
 */
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

    /**
     * Description:
     * 1. If no callbacks exist for the given notification, creates a new list with the function
     *    and stores it in the map.
     * 2. If callbacks already exist, checks whether the function is already registered.
     * 3. If not already registered, adds the function to the existing list.
     *
     * Parameters:
     * - notification: The notification type to register for.
     * - function: The Runnable callback to invoke when the notification is triggered.
     *
     * Expected Returns:
     * - Returns true when the function was successfully registered.
     * - Returns false when the function was already registered for this notification.
     */
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

    /**
     * Description:
     * 1. Checks if any callbacks are registered for the given notification.
     * 2. If found, checks whether the specific function exists in the list.
     * 3. If found, removes the function from the list.
     *
     * Parameters:
     * - notification: The notification type to unregister from.
     * - function: The Runnable callback to remove.
     *
     * Expected Returns:
     * - Returns true when the function was successfully removed.
     * - Returns false when the notification has no registered callbacks or the function is not found.
     */
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

    /**
     * Description:
     * 1. Checks if any callbacks are registered for the given notification.
     * 2. If found, iterates over all registered callbacks and invokes each one.
     *
     * Parameters:
     * - notification: The notification type to trigger.
     *
     * Expected Returns:
     * - Returns true when at least one callback was registered and all were invoked.
     * - Returns false when no callbacks are registered for this notification.
     */
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