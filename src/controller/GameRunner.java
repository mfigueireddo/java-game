package controller;

import controller.loaders.ImageLoader;
import controller.loaders.TextFileLoader;
import view.ViewAPI;

/**
 * Main entry point for the Java 2D Game application.
 * 
 * PUBLIC API JUSTIFICATION:
 * - This class MUST be public as it contains the main() method which serves as the application entry point.
 * - It cannot be package-private as the JVM requires main() to be in a public class.
 */
public class GameRunner {

    private static ViewAPI view;
    
    private static ImageLoader image_loader;
    private static TextFileLoader textfile_loader;

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

        view.ShowMenu();
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