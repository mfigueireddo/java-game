package controller;

import controller.loaders.ImageLoader;
import view.ViewAPI;
import view.Window;

public class GameRunner {

    static ViewAPI view;
    static Observer observer;
    static Window window;
    static ImageLoader image_loader;

    public static void main(String[] args) {
        view = ViewAPI.GetInstance();
        window = view.GetWindow();

        RegisterObservers();
        LoadImages();

        window.ShowMenu();
    }

    private static void RegisterObservers(){
        observer = Observer.GetInstance();
        observer.Register(Notification.NEW_GAME, window::ShowGame);
    }

    private static void LoadImages(){
        image_loader = ImageLoader.GetInstance();
        image_loader.Load();
    }

}