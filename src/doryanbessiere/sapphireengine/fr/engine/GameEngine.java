package doryanbessiere.sapphireengine.fr.engine;

import doryanbessiere.sapphireengine.fr.commons.Time;
import doryanbessiere.sapphireengine.fr.commons.Tools;
import doryanbessiere.sapphireengine.fr.commons.logger.Logger;
import doryanbessiere.sapphireengine.fr.commons.logger.exceptions.ExceptionManager;
import doryanbessiere.sapphireengine.fr.engine.exceptions.WindowCannotBeCreatedException;
import doryanbessiere.sapphireengine.fr.engine.scene.Scene;
import doryanbessiere.sapphireengine.fr.engine.window.GameWindow;
import doryanbessiere.sapphireengine.fr.engine.game.Game;

import java.io.File;

public class GameEngine implements IGameBase, IEngine {

    private Game game;
    private GameWindow gameWindow;

    public GameEngine(Game game) {
        this.game = game;
        this.gameWindow = new GameWindow(this, game.getName(), 1280, 720);
    }

    @Override
    public void build(){
        getLogger().debug("Starting the game engine...");

        try {
            this.gameWindow.build();
            getLogger().debug("Starting the game loop.");
            this.loop();
        } catch (WindowCannotBeCreatedException e){
            ExceptionManager.pushException(e);
        }
    }

    private void loop(){
        float beginTime = Time.getTime();
        float endTime = Time.getTime();
        float dt = -1;

        while(!gameWindow.shouldClose()){
            gameWindow.update();

            if(dt >= 0)
                update(dt);

            gameWindow.swapBuffers();

            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;

            render();
        }
        getLogger().debug("Game loop finish.");

        gameWindow.destroy();
    }

    private Scene scene = null;

    public void setScene(Scene scene) {
        this.scene = scene;
        this.scene.init();
    }

    @Override
    public void update(float dt) {
        if(scene != null)scene.update(dt);
    }

    @Override
    public void render() {
        if(scene != null)scene.render();
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
