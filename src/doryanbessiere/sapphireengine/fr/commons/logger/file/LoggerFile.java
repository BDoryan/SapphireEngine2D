package doryanbessiere.sapphireengine.fr.commons.logger.file;

import doryanbessiere.sapphireengine.fr.commons.logger.Logger;
import doryanbessiere.sapphireengine.fr.commons.logger.LoggerListener;
import doryanbessiere.sapphireengine.fr.commons.logger.exceptions.ExceptionManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class LoggerFile implements LoggerListener {

    private Logger logger;

    private File file;
    private FileWriter writer;

    public LoggerFile(Logger logger, File directory){
        this.logger = logger;

        directory.mkdir();
        this.file = new File(directory, time()+"-0.log");
        int i = 1;
        while(this.file.exists()) {
            this.file = new File(directory, time()+"-"+i+".log");
            i++;
        }
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            ExceptionManager.pushException(e);
        }
        try {
            this.writer = new FileWriter(this.file);
        } catch (IOException e) {
            ExceptionManager.pushException(e);
        }
    }

    public void listen(){
        logger.addListener(this);
    }

    public void unlisten(){
        logger.removeListener(this);
    }

    public void close() {
        try {
            this.writer.close();
        } catch (IOException e) {
            ExceptionManager.pushException(e);
        }
    }

    public String time() {
        return new SimpleDateFormat("dd-MM-yyyy").format(System.currentTimeMillis());
    }

    @Override
    public void log(String log) {
        try {
            this.writer.write(log+"\n");
            this.writer.flush();
        } catch (IOException e) {
            ExceptionManager.pushException(e);
        }
    }
}
