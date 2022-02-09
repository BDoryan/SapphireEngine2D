package doryanbessiere.sapphireengine.fr.commons.logger.exceptions;

import java.util.ArrayList;

public class ExceptionManager {

    private static ArrayList<ExceptionListener> listeners = new ArrayList<>();

    public static ArrayList<ExceptionListener> getListeners() {
        return listeners;
    }

    public static void pushException(Exception exception){
        listeners.forEach(listeners -> {
            listeners.call(true, exception);
        });
    }

    public static void pushException(boolean alert, Exception exception){
        listeners.forEach(listeners -> {
            listeners.call(alert, exception);
        });
    }
}
