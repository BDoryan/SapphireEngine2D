package doryanbessiere.sapphireengine.fr.engine;

import doryanbessiere.sapphireengine.fr.engine.window.GameWindow;
import doryanbessiere.sapphireengine.fr.game.Game;

public class GameEngine implements IBuilder {

    private Game game;
    private GameWindow gameWindow;

    public GameEngine(Game game) {
        this.game = game;
    }

    @Override
    public void build(){
        this.gameWindow = new GameWindow(game.getName(), 1920, 1080);
        this.gameWindow.build();
    }
}
