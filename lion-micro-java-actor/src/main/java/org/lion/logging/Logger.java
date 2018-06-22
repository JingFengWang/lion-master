package org.lion.logging;

public interface Logger {

    void info(String message, Object... values);

    void trace(String messge, Object... values);

    void warning(String message, Object... values);

    void error(String message, Object... values);


    void notify(String message, Object... values);
}
