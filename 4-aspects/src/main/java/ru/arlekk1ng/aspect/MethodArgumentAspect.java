package ru.arlekk1ng.aspect;

import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import ru.arlekk1ng.exception.EmptyMethodArgumentException;

/**
 * Интерфейс аспекта приложения, который проверяет аргументы методов
 */
public interface MethodArgumentAspect {

    void checkMethodArguments(JoinPoint joinPoint) throws EmptyMethodArgumentException;
    void setAspectLogger(Logger aspectLogger);
}
