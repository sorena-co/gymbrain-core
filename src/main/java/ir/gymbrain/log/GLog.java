package ir.gymbrain.log;

import java.time.ZonedDateTime;

public class GLog {

    private Class<?> classObj;

    private GLog() {
    }

    public GLog(Class<?> classObj) {
        this.classObj = classObj;
    }

    public Class<?> getClassObj() {
        return classObj;
    }

    public void error(String message) {
        String stringBuilder = getPrefixMessage(message);
        System.err.println(stringBuilder);
    }

    public void log(String message) {
        String stringBuilder = getPrefixMessage(message);
        System.out.println(stringBuilder);
    }

    private String getPrefixMessage(String message) {
        return classObj.getCanonicalName() +
                " at " +
                ZonedDateTime.now() +
                "\t" +
                message;
    }
}
