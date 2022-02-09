package doryanbessiere.sapphireengine.fr.commons.logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Logger {

    private String name;
    private LoggerType type = LoggerType.INFO;

    private ArrayList<LoggerListener> listeners = new ArrayList<>();
    private ArrayList<ThrowableListener> throwableListeners = new ArrayList<>();

    public Logger(String name) {
        this(name, LoggerType.INFO);
    }

    public Logger(String name, LoggerType type) {
        MAIN = this;
        this.name = name;
        this.type = type;

        initDefault();
    }

    public void initDefault(){
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                throwable("exception in thread '" + t.getName() + "' " + e.getClass().getName(), e);
                throwableListeners.forEach(throwableListener -> throwableListener.throwable(e));
            }
        });
    }

    public ArrayList<ThrowableListener> getThrowableListeners() {
        return throwableListeners;
    }

    public ArrayList<LoggerListener> getListeners() {
        return listeners;
    }

    public void addListener(LoggerListener event){
        listeners.add(event);
    }

    public void removeListener(LoggerListener event){
        listeners.remove(event);
    }

    public void setType(LoggerType type) {
        this.type = type;
    }

    public LoggerType getType() {
        return type;
    }

    public void exception(Exception exception) {
        throwable(exception.toString(), exception);
    }

    public void throwable(String localizedMessage, Throwable throwable) {
        error(localizedMessage);
        for (StackTraceElement element : throwable.getStackTrace()) {
            error("    at " + element.getClassName() + "." + element.getMethodName() + "(" + element.getFileName() + ":"
                    + element.getLineNumber() + ")");
        }

        Throwable ourCause = throwable.getCause();
        if (ourCause != null)
            throwable("Caused by: " + ourCause.getClass().getName(), ourCause);
    }

    private String time(){
        String time = new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis());
        return "["+time+"]";
    }

    private String prefix(){
        return "["+name+"]";
    }

    private String prelog(){
        return time() +" " +prefix()+" ";
    }

    public void info(String log){
        log(LoggerType.INFO, log);
    }

    public void warn(String log){
        log(LoggerType.WARNING, log);
    }

    public void error(String log){
        log(LoggerType.ERROR, log);
    }

    public void debug(String log){
        log(LoggerType.DEBUG, log);
    }

    public void fatal(String log){
        log(LoggerType.FATAL, log);
    }

    public void log(LoggerType type, String log){
        String message = prelog()+"["+type.toString()+"] "+log;
        System.out.println(message);
        logs += message+"\n";
        listeners.forEach(listener -> {
            listener.log(message);
        });
    }

    public void log(String log){
        log(this.type, log);
    }

    public String getName() {
        return name;
    }

    public static Logger MAIN = null;

    public static Logger getInstance(){return MAIN;}
    public static Logger instance(){return MAIN;}

    String logs = "";

    public String toLogs() {
        return logs;
    }
}
