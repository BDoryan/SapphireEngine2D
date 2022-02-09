package doryanbessiere.sapphireengine.fr.engine.game;

import doryanbessiere.sapphireengine.fr.commons.logger.Logger;
import doryanbessiere.sapphireengine.fr.commons.logger.exceptions.ExceptionListener;
import doryanbessiere.sapphireengine.fr.commons.logger.exceptions.ExceptionManager;
import doryanbessiere.sapphireengine.fr.commons.logger.file.LoggerFile;
import doryanbessiere.sapphireengine.fr.engine.GameEngine;
import doryanbessiere.sapphireengine.fr.engine.IBuilder;

import java.io.File;

public class Game implements IBuilder {

    private Logger logger;

    private String name;
    private String version;

    private GameEngine gameEngine;

    public Game(String name, String version) {
        this.name = name;
        this.version = version;

        this.gameEngine = new GameEngine(this);

        initLogger();
    }

    private void initLogger(){
        logger = new Logger(name);
        LoggerFile loggerFile  = new LoggerFile(logger, new File(this.gameEngine.getGameDirectory(), "logs"));
        loggerFile.listen();

        ExceptionManager.getListeners().add(new ExceptionListener() {
            @Override
            public void call(boolean alert, Exception exception) {
                logger.exception(exception);
            }
        });
    }

    @Override
    public void build(){
        logger.log("Starting the game...");
        this.gameEngine.build();
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public Logger getLogger() {
        return logger;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
