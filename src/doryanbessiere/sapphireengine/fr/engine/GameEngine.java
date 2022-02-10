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
    public void initialize(){
        getLogger().debug("Starting the game engine...");

        try {
            this.gameWindow.build();
            getLogger().debug("Starting the game loop.");
        } catch (WindowCannotBeCreatedException e){
            ExceptionManager.pushException(e);
        }
    }

    public void start(){
        this.loop();
    }

    private int maximumUPS = 60;
    private int maximumFPS = 60;

    private void loop(){
        long initialTime = System.nanoTime();
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while (!gameWindow.shouldClose()) {
            final double timeU = 1000000000 / maximumUPS;
            final double timeF = 1000000000 / maximumFPS;

            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                update((float)deltaU);
                ticks++;
                deltaU = 0;
            }

            if (deltaF >= 1) {
                gameWindow.update();
                render();
                gameWindow.swapBuffers();
                frames++;
                deltaF = 0;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                    System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
        getLogger().debug("Game loop finish.");

        gameWindow.destroy();
    }

    private Scene scene = null;

    public void setScene(Scene scene) {
        this.scene = scene;
        this.scene.initialize();
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
