package doryanbessiere.sapphireengine.fr.engine;

import doryanbessiere.sapphireengine.fr.engine.exceptions.WindowCannotBeCreatedException;

public interface IBuilder {

    public abstract void build() throws WindowCannotBeCreatedException;

}
