package view.renderers;

import controller.files_managers.ImageManager;
import controller.files_managers.TextFileManager;
import controller.loaders.ImageLoader;
import controller.loaders.TextFileLoader;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import view.panels.Panel;

public abstract class Renderer{

    protected int screen_width;
    protected int screen_height;

    protected final TextFileManager textfile_manager = TextFileManager.GetInstance();
    protected final ImageManager image_manager = ImageManager.GetInstance();

    protected final TextFileLoader textfile_loader = TextFileLoader.GetInstance();
    protected final ImageLoader image_loader = ImageLoader.GetInstance();

    // Keeping the buffered used images saves time - better than using image_loader.GetImage() all the time when rendering
    // This way the image is duplicated, but it's still better than transfering the data all the time when rendering
    // TODO: actually, check if what is said above it's true - maybe in BufferedImage's works with pointers...
    protected final java.util.Map<Integer, BufferedImage> used_images = new HashMap<>();

    protected Renderer(){};

    public void SetScreenSize(final int screen_width, final int screen_height){
        this.screen_width = screen_width;
        this.screen_height = screen_height;
    }

    public abstract void Render(final Graphics2D graphics_2d, final Panel panel);
}