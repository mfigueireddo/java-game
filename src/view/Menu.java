package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;

public class Menu extends Panel {

    private static Menu instance;

    public static Menu GetInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    private final JButton new_game = new JButton("New Game");
    private final JButton load_game = new JButton("Load Game");
    private final JButton settings = new JButton("Settings");

    private Menu() {
        super();

        this.setBackground(Color.WHITE);
        this.setLayout(new GridBagLayout());

        SetupButtons();
        SetupButtonsVBox();
    }

    private void SetupButtons(){
        // Buttons alignment
        final float buttons_alignment = Component.CENTER_ALIGNMENT;
        new_game.setAlignmentX(buttons_alignment);
        load_game.setAlignmentX(buttons_alignment);
        settings.setAlignmentX(buttons_alignment);

        // Buttons focus
        final boolean should_button_focus = false;
        new_game.setFocusable(should_button_focus);
        load_game.setFocusable(should_button_focus);
        settings.setFocusable(should_button_focus);

        // Buttons dimensions
        final int buttons_width = 200;
        final int buttons_height = 40;
        final Dimension buttons_dimensions = new Dimension(buttons_width, buttons_height);
        // JavaSwing's Boxes will try to strech its elements. To avoid it, set the preferred and maximum size to be the same
        new_game.setPreferredSize(buttons_dimensions);
        load_game.setPreferredSize(buttons_dimensions);
        settings.setPreferredSize(buttons_dimensions);
        new_game.setMaximumSize(buttons_dimensions);
        load_game.setMaximumSize(buttons_dimensions);
        settings.setMaximumSize(buttons_dimensions);
    }

    private void SetupButtonsVBox(){
        // Box around the buttons
        final int buttons_vbox_border_thickness = 2;
        final int buttons_vbox_margin = 10;
        final Color buttons_vbox_background = Color.WHITE;
        final Color buttons_vbox_border = Color.BLACK;
        Box buttons_vbox = Box.createVerticalBox();
        buttons_vbox.setBackground(buttons_vbox_background);
        buttons_vbox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(buttons_vbox_border, buttons_vbox_border_thickness),
            BorderFactory.createEmptyBorder(buttons_vbox_margin, buttons_vbox_margin, buttons_vbox_margin, buttons_vbox_margin)
        ));

        // Buttons' box disposal
        final int buttons_vbox_width_padding = 0;
        final int buttons_vbox_height_padding = 10;
        buttons_vbox.add(new_game);
        buttons_vbox.add(Box.createRigidArea(new Dimension(buttons_vbox_width_padding , buttons_vbox_height_padding)));
        buttons_vbox.add(load_game);
        buttons_vbox.add(Box.createRigidArea(new Dimension(buttons_vbox_width_padding , buttons_vbox_height_padding)));
        buttons_vbox.add(settings);

        this.add(buttons_vbox);
    }

}