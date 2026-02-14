package view.panels;

import controller.utils.Notification;
import controller.utils.Observer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;

/**
 * Description:
 * 1. Singleton main menu screen extending Panel.
 * 2. Displays three buttons: "New Game", "Load Game", and "Settings".
 */
public class Menu extends Panel {

    private static Menu instance;

    public static Menu GetInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    private final Observer observer = Observer.GetInstance();

    private final JButton new_game = new JButton("New Game");
    private final JButton load_game = new JButton("Load Game");
    private final JButton settings = new JButton("Settings");

    private Menu() {    
        super();

        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());

        SetupButtons();
        SetupButtonsVBox();

        // Temporary behavior while the proper actions aren't implemented
        final boolean should_enable_buttons = false;
        // new_game.setEnabled(should_enable_buttons);
        load_game.setEnabled(should_enable_buttons);
        settings.setEnabled(should_enable_buttons);
    }

    /**
     * Objective: Configures all button properties including alignment, focus behavior,
     * dimensions, action listeners, and hover effects.
     *
     * Description:
     * 1. Sets center alignment for all buttons.
     * 2. Disables focus painting on all buttons.
     * 3. Sets fixed dimensions (200x40) for all buttons, including max size to prevent
     *    stretching by the Box layout.
     * 4. Attaches action listeners that delegate to OnNewGame(), OnLoadGame(), OnSettings().
     * 5. Applies hover color effects (light gray normal, light blue hover) to all buttons.
     */
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
        // JavaSwing's Boxes will try to stretch its elements. To avoid it, set the preferred and maximum size to be the same
        new_game.setPreferredSize(buttons_dimensions);
        load_game.setPreferredSize(buttons_dimensions);
        settings.setPreferredSize(buttons_dimensions);
        new_game.setMaximumSize(buttons_dimensions);
        load_game.setMaximumSize(buttons_dimensions);
        settings.setMaximumSize(buttons_dimensions);

        // Buttons action listeners
        new_game.addActionListener(e -> OnNewGame());
        load_game.addActionListener(e -> OnLoadGame());
        settings.addActionListener(e -> OnSettings());

        // Buttons coloring (normal and hover)
        final Color button_normal_color = new Color(238, 238, 238);  // Light gray
        final Color button_hover_color = new Color(200, 220, 255);   // Light blue
        SetupButtonHoverEffect(new_game, button_normal_color, button_hover_color);
        SetupButtonHoverEffect(load_game, button_normal_color, button_hover_color);
        SetupButtonHoverEffect(settings, button_normal_color, button_hover_color);
    }

    /**
     * Description:
     * 1. Sets the button's normal background color.
     * 2. Adds a mouse listener that changes the background to the hover color on mouse enter
     *    and reverts to the normal color on mouse exit.
     */
    private void SetupButtonHoverEffect(JButton button, Color normal_color, Color hover_color) {
        button.setBackground(normal_color);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hover_color);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(normal_color);
            }
        });
    }

    /**
     * Description:
     * 1. Creates a vertical Box container with a black border and padding.
     * 2. Adds the three buttons to the box with rigid area spacers between them.
     * 3. Adds the box to the menu panel.
     */
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

        add(buttons_vbox);
    }

    private void OnNewGame() {
        observer.Notify(Notification.NEW_GAME);
    }

    private void OnLoadGame() {
        observer.Notify(Notification.LOAD_GAME);
    }

    private void OnSettings() {
        observer.Notify(Notification.SETTINGS);
    }
}