package doryanbessiere.sapphireengine.fr.game;

import doryanbessiere.sapphireengine.fr.engine.GameEngine;
import doryanbessiere.sapphireengine.fr.engine.IBuilder;

public class Game implements IBuilder {

    private String name;
    private String version;

    private GameEngine gameEngine;

    public Game(String name, String version) {
        this.name = name;
        this.version = version;

        this.gameEngine = new GameEngine(this);
    }

    @Override
    public void build(){
        this.gameEngine.build();
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
