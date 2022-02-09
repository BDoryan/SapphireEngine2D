package doryanbessiere.sapphireengine.fr.engine.exceptions;

public class WindowCannotBeCreatedException extends Exception {

    public WindowCannotBeCreatedException(String reason) {
        super("The window cannot be created due to an error -> "+reason);
    }
}
