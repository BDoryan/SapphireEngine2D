package doryanbessiere.sapphireengine.fr.engine;

import doryanbessiere.sapphireengine.fr.commons.logger.Logger;
import doryanbessiere.sapphireengine.fr.commons.logger.Tools;
import doryanbessiere.sapphireengine.fr.engine.window.GameWindow;
import doryanbessiere.sapphireengine.fr.game.Game;

import java.io.File;

public class GameEngine implements IEngine {

    private Game game;
    private GameWindow gameWindow;

    public GameEngine(Game game) {
        this.game = game;
    }

    @Override
    public void build(){
        getLogger().debug("Starting the game engine...");

        this.gameWindow = new GameWindow(this, game.getName(), 1920, 1080);
        this.gameWindow.build();
    }

    @Override
    public Game getGame() {
        return game;
    }

    public Logger getLogger(){
        return this.game.getLogger();
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public File getGameDirectory() {
        return Tools.localDirectory(GameEngine.class);
    }
}
