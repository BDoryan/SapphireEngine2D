package doryanbessiere.sapphireengine.fr.engine.window;

import doryanbessiere.sapphireengine.fr.commons.logger.Logger;
import doryanbessiere.sapphireengine.fr.engine.GameEngine;
import doryanbessiere.sapphireengine.fr.engine.IGameBase;
import doryanbessiere.sapphireengine.fr.engine.exceptions.WindowCannotBeCreatedException;
import doryanbessiere.sapphireengine.fr.game.Game;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.opengl.GL;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GameWindow implements IGameBase {

    private GameEngine gameEngine;
    private String title;
    private int width, height;

    private boolean maximized = false;
    private boolean resizable = true;

    private long id;

    public GameWindow(GameEngine gameEngine, String title, int width, int height) {
        this.gameEngine = gameEngine;

        this.title = title;
        this.width = width;
        this.height = height;
    }

    public GameWindow(GameEngine gameEngine, String title, int width, int height, boolean maximized, boolean resizable) {
        this(gameEngine, title, width, height);

        this.maximized = maximized;
        this.resizable = resizable;
    }

    /**
     * This method is used to launch the construction of the window and at the same time its display
     *
     *
     * @throws WindowCannotBeCreatedException The construction of the window can go wrong, hence the interest of being warned with an exception
     */
    @Override
    public void build() throws WindowCannotBeCreatedException {
        gameEngine.getLogger().debug("Starting the game window...");

        init();
    }

    private void init() throws WindowCannotBeCreatedException {
        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()){
            throw new WindowCannotBeCreatedException("unable to initialize the module GLFW.");
        }

        glfwDefaultWindowHints();

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_MAXIMIZED, maximized ? GLFW_TRUE : GLFW_FALSE);

        if(customHints().size() > 0){
            for(Map.Entry<Integer, Integer> hints : customHints().entrySet()){
                glfwWindowHint(hints.getKey(), hints.getValue());
            }
        }

        id = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if(id == NULL){
            throw new WindowCannotBeCreatedException("failed to create window");
        }

        glfwMakeContextCurrent(id);
        // V-SYNC
        glfwSwapInterval(1);

        glfwShowWindow(id);

        GL.createCapabilities();
    }

    public HashMap<Integer, Integer> customHints(){
        return new HashMap<Integer, Integer>();
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(id);
    }

    public void update() {
        glfwPollEvents();

        glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        glfwSwapBuffers(id);
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    @Override
    public Game getGame() {
        return this.gameEngine.getGame();
    }

    @Override
    public Logger getLogger() {
        return this.gameEngine.getLogger();
    }

    /**
     * This value is only the base value of the window, if you come to resize your window it will not be updated here
     *
     * @return the base height set to the window
     */
    public int getHeight() {
        return height;
    }

    /**
     * This value is only the base value of the window, if you come to resize your window it will not be updated here
     *
     * @return the base width set to the window
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     This will only allow you to change this information when the window is already displayed, this information is meant to be changed only before building the window
     *
     * @param maximized Do you want the window to be widescreen
     */
    public void setMaximized(boolean maximized) {
        this.maximized = maximized;
    }

    /**
     *
     This will only allow you to change this information when the window is already displayed, this information is meant to be changed only before building the window
     *
     * @param resizable Do you want the window to be resizable
     */
    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    /**
     *
     * @return This only gives you the value that was set for the window configuration.
     */
    public boolean isResizable() {
        return resizable;
    }

    /**
     *
     * @return This only gives you the value that was set for the window configuration.
     */
    public boolean isMaximized() {
        return maximized;
    }

    public long getId() {
        return id;
    }
}
