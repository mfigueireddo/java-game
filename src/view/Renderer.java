package view;

import java.awt.Graphics2D;

public abstract class Renderer{

    protected Renderer(){};

    public abstract void Render(final Graphics2D graphics_2d, final int screen_width, final int screen_height);

}