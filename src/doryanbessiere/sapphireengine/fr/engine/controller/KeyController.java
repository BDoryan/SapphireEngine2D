package doryanbessiere.sapphireengine.fr.engine.controller;

import doryanbessiere.sapphireengine.fr.engine.window.GameWindow;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class KeyController {

    public static KeyController instance = new KeyController();

    private boolean[] pressed = new boolean[350];

    public static void keyCallback(GameWindow gameWindow, int key, int scancode, int action, int mods){
        if(key >= instance.pressed.length){
            gameWindow.getLogger().error("Unknown mouse button -> "+key+", "+scancode+", "+action+", "+mods);
            return;
        }

        instance.pressed[key] = action == GLFW_PRESS ? true : false;
    }

    public static boolean isPressed(int key){
        if(key >= instance.pressed.length){
            return false;
        }

        return instance.pressed[key];
    }

    public static KeyController getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "KeyController{" +
                "pressed=" + Arrays.toString(pressed) +
                '}';
    }
}
