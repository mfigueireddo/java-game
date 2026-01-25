package controller;

import controller.loaders.ImageLoader;
import controller.loaders.TextFileLoader;
import view.ViewAPI;
import view.Window;

public class GameRunner {

    static ViewAPI view;
    static Window window;
    
    static ImageLoader image_loader;
    static TextFileLoader textfile_loader;

    public static void main(String[] args) {
        StartUp();

        Run();

        ShutDown();
    }

    private static void StartUp(){
        LoadImages();
        LoadTextFiles();
    }

    private static void Run(){
        view = ViewAPI.GetInstance();
        window = view.GetWindow();
        
        window.ShowMenu();
    }

    private static void ShutDown(){

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