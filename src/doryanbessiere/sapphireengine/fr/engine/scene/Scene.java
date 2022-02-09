package doryanbessiere.sapphireengine.fr.engine.scene;

import doryanbessiere.sapphireengine.fr.commons.logger.Logger;
import doryanbessiere.sapphireengine.fr.engine.IEngine;
import doryanbessiere.sapphireengine.fr.engine.IGameBase;
import doryanbessiere.sapphireengine.fr.engine.game.Game;

import java.util.UUID;

public abstract class Scene implements IGameBase, IEngine {

    protected Game game;
    protected String name;

    public Scene(Game game, String name){
        this.game = game;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public Logger getLogger() {
        return game.getLogger();
    }
}
