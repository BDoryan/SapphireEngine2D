package doryanbessiere.sapphireengine.fr.engine;

import doryanbessiere.sapphireengine.fr.commons.logger.Logger;
import doryanbessiere.sapphireengine.fr.game.Game;

public interface IGameBase extends IBuilder {

    public abstract Game getGame();
    public abstract Logger getLogger();

}
