package ru.arlekk1ng.aspect;

import java.util.Collection;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.arlekk1ng.exception.EmptyMethodArgumentException;

/**
 * Класс аспекта приложения, который проверяет аргументы методов, помеченных аннотацией NotEmpty
 */
@Aspect
@Component
@Primary
public class StringOrCollectionMethodArgumentNotEmptyAspect implements MethodArgumentAspect {
    private Logger aspectLogger = Logger.getLogger(
            StringOrCollectionMethodArgumentNotEmptyAspect.class.getName());

    private void writeToAspectLoggerAndThrowEmptyMethodArgumentException(String message)
            throws EmptyMethodArgumentException {
        aspectLogger.warning(message);
        throw new EmptyMethodArgumentException(message);
    }

    @Before("@annotation(NotEmpty)")
    public void checkMethodArguments(JoinPoint joinPoint) throws EmptyMethodArgumentException {
        for (Object arg: joinPoint.getArgs()) {
            if (arg == null) {
                writeToAspectLoggerAndThrowEmptyMethodArgumentException(
                        "В аргументах метода присутсвует null");
            } else if (arg instanceof String string) {
                if (string.isEmpty()) {
                    writeToAspectLoggerAndThrowEmptyMethodArgumentException(
                            "В аргументах метода присутсвует пустая строка");
                }
            } else if (arg instanceof Collection collection) {
                if (collection.isEmpty()) {
                    writeToAspectLoggerAndThrowEmptyMethodArgumentException(
                            "В аргументах метода присутсвует пустая коллекция");
                }
            }
        }
    }

    public void setAspectLogger(Logger aspectLogger) {
        this.aspectLogger = aspectLogger;
    }
}
