package view;

import java.awt.Color;

public class Menu extends Panel {

    private static Menu instance;

    public static Menu GetInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    private Menu() {
        super();
        this.setBackground(Color.BLACK);
    }

}