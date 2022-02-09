package doryanbessiere.sapphireengine.fr.commons.logger;

import doryanbessiere.sapphireengine.fr.commons.logger.exceptions.ExceptionManager;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class Tools {

    public static File localDirectory(Class<?> class_) {
        try {
            File file = new File(class_.
                    getProtectionDomain().getCodeSource().getLocation().toURI())
                    .getParentFile();
            return file;
        } catch (URISyntaxException e) {
            ExceptionManager.pushException(e);
        }
        return null;
    }

    private static Random random = new Random();

    public static int randomInt(int min, int max) {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        //
        // In particular, do NOT do 'Random rand = new Random()' here or you
        // will get not very good / not very random results.

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = random.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static Integer toInteger(String string){
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException e){
            ExceptionManager.pushException(e);
        }
        return null;
    }

    public static boolean hasInternet(){
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static File localDirectory() {
        return localDirectory(Tools.class.getClass());
    }
}
