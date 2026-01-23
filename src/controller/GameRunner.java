package controller;

import view.ViewAPI;
import view.Window;

public class GameRunner {

    static ViewAPI view;

    public static void main(String[] args) {
        view = ViewAPI.GetInstance();

        final Window window = view.GetWindow();
        window.ShowMenu();
    }
}
