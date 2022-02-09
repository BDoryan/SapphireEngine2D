package doryanbessiere.sapphireengine.fr.engine.window;

import doryanbessiere.sapphireengine.fr.engine.IBuilder;

public class GameWindow implements IBuilder {

    private String title;
    private int width, height;

    private long id;

    public GameWindow(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    @Override
    public void build(){

    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public long getId() {
        return id;
    }
}
