package doryanbessiere.sapphireengine.fr.engine;

import doryanbessiere.sapphireengine.fr.commons.Tools;
import doryanbessiere.sapphireengine.fr.commons.logger.Logger;
import doryanbessiere.sapphireengine.fr.commons.logger.exceptions.ExceptionManager;
import doryanbessiere.sapphireengine.fr.engine.exceptions.WindowCannotBeCreatedException;
import doryanbessiere.sapphireengine.fr.engine.window.GameWindow;
import doryanbessiere.sapphireengine.fr.game.Game;
import org.lwjgl.glfw.GLFW;

import java.io.File;

public class GameEngine implements IGameBase, IEngine {

    private Game game;
    private GameWindow gameWindow;

    public GameEngine(Game game) {
        this.game = game;
    }

    @Override
    public void build(){
        getLogger().debug("Starting the game engine...");

        this.gameWindow = new GameWindow(this, game.getName(), 1280, 720);
        try {
            this.gameWindow.build();
            this.loop();
        } catch (WindowCannotBeCreatedException e){
            ExceptionManager.pushException(e);
        }
    }

    private void loop(){
        while(!gameWindow.shouldClose()){
            gameWindow.update();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

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
