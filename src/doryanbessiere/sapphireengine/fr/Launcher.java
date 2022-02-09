package doryanbessiere.sapphireengine.fr;

import doryanbessiere.sapphireengine.fr.commons.logger.Logger;
import doryanbessiere.sapphireengine.fr.engine.game.Game;
import doryanbessiere.sapphireengine.fr.engine.scene.scenes.LevelEditorScene;

public class Launcher extends Game {

    public static Launcher LAUNCHER = new Launcher();

    public Launcher() {
        super("Saphhire Engine 2D", "0.0.1");
    }

    @Override
    public void initialize() {
        super.initialize();

        getGameEngine().setScene(new LevelEditorScene(this));
        start();
    }

    public void start() {
        getGameEngine().start();
    }

    public static void main(String[] args) {
        LAUNCHER.initialize();
    }

    public static Logger getGameLogger(){
        return LAUNCHER.getLogger();
    }
}
