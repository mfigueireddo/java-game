package controller;

import management.Notification;
import management.Observer;
import view.ViewAPI;
import view.Window;

public class GameRunner {

    static ViewAPI view;
    static Observer observer;
    static Window window;

    public static void main(String[] args) {
        view = ViewAPI.GetInstance();
        window = view.GetWindow();

        RegisterObservers();

        window.ShowMenu();
    }

    private static void RegisterObservers(){
        observer = Observer.GetInstance();
        observer.Register(Notification.NEW_GAME, window::ShowGame);
    }
}
