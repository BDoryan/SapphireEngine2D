package doryanbessiere.sapphireengine.fr.engine.window;

import doryanbessiere.sapphireengine.fr.commons.logger.Logger;
import doryanbessiere.sapphireengine.fr.engine.GameEngine;
import doryanbessiere.sapphireengine.fr.engine.IBuilder;
import doryanbessiere.sapphireengine.fr.engine.IEngine;
import doryanbessiere.sapphireengine.fr.game.Game;

public class GameWindow implements IEngine {

    private GameEngine gameEngine;
    private String title;
    private int width, height;

    private long id;

    public GameWindow(GameEngine gameEngine, String title, int width, int height) {
        this.gameEngine = gameEngine;

        this.title = title;
        this.width = width;
        this.height = height;
    }

    @Override
    public void build(){
        gameEngine.getLogger().debug("Starting the game window...");
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    @Override
    public Game getGame() {
        return this.gameEngine.getGame();
    }

    @Override
    public Logger getLogger() {
        return this.gameEngine.getLogger();
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
