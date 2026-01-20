package controller;

import view.Window;

public class GameRunner {

    static Window window;
    public static void main(String[] args) {
        window = Window.GetInstance();
        window.ShowMenu();
    }
}
