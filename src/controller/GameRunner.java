package controller;

import controller.loaders.ImageLoader;
import controller.loaders.TextFileLoader;
import view.ViewAPI;
import view.Window;

public class GameRunner {

    static ViewAPI view;
    static Window window;

    static Observer observer;
    
    static ImageLoader image_loader;
    static TextFileLoader textfile_loader;

    public static void main(String[] args) {
        view = ViewAPI.GetInstance();
        window = view.GetWindow();

        RegisterObservers();
        LoadImages();
        LoadTextFiles();

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

    private static void LoadTextFiles(){
        textfile_loader = TextFileLoader.GetInstance();
        textfile_loader.Load();
    }
}