package doryanbessiere.sapphireengine.fr.engine.scene;

import doryanbessiere.sapphireengine.fr.engine.IEngine;

import java.util.UUID;

public abstract class Scene implements IEngine {

    private String name;

    public Scene(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void init();
}
