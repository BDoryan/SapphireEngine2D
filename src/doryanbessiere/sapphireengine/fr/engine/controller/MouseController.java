package doryanbessiere.sapphireengine.fr.engine.controller;

import doryanbessiere.sapphireengine.fr.engine.window.GameWindow;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseController {

    public static MouseController instance = new MouseController();

    private double scrollX = 0, scrollY = 0;
    private double x = 0, y = 0, lastY = 0, lastX = 0;

    private boolean[] pressed = new boolean[3];
    private boolean isDragging = false;

    public static void mouseMoveCallback(GameWindow gameWindow, double x, double y){
        initLastMousePosition();

        instance.x = x;
        instance.y = y;
        instance.isDragging = instance.pressed[0] || instance.pressed[1] || instance.pressed[2];;
    }

    public static void mouseButtonCallback(GameWindow gameWindow, int button, int action, int mods){
        if(button >= instance.pressed.length){
            gameWindow.getLogger().error("Unknown mouse button -> "+button+", "+action+", "+mods);
            return;
        }
        instance.pressed[button] = action == GLFW_PRESS ? true : false;
        if(action == GLFW_RELEASE)
            instance.isDragging = false;
    }

    public static void mouseScrollCallback(GameWindow window, double xOffset, double yOffset){
        instance.scrollX = xOffset;
        instance.scrollY = yOffset;
    }

    public static void endScroll(){
        instance.scrollX = 0;
        instance.scrollY = 0;

        initLastMousePosition();
    }

    private static void initLastMousePosition(){
        instance.lastX = instance.x;
        instance.lastY = instance.y;
    }

    public static float getX(){
        return (float) instance.x;
    }

    public static float getY(){
        return (float) instance.y;
    }

    public static float getDistanceX(){
        return getLastX() - getX();
    }

    public static float getDistanceY(){
        return getLastY() - getY();
    }

    public static float getScrollX(){
        return (float) instance.scrollX;
    }

    public static float getScrollY(){
        return (float) instance.scrollY;
    }

    public static float getLastX(){
        return (float) instance.lastX;
    }

    public static float getLastY(){
        return (float) instance.lastY;
    }

    public static boolean isDragging(){
        return instance.isDragging;
    }

    public static boolean isButtonDown(int button){
        if(button >= instance.pressed.length){
            return false;
        }

        return instance.pressed[button];
    }

    public static boolean isPressed(int button){
        if(button >= instance.pressed.length){
            return false;
        }

        return instance.pressed[button];
    }

    public static MouseController getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "MouseController{" +
                "scrollX=" + scrollX +
                ", scrollY=" + scrollY +
                ", x=" + x +
                ", y=" + y +
                ", lastY=" + lastY +
                ", lastX=" + lastX +
                ", pressed=" + Arrays.toString(pressed) +
                ", isDragging=" + isDragging +
                '}';
    }
}
