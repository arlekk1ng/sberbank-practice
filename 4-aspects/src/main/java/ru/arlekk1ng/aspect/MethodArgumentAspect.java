package ru.arlekk1ng.aspect;

import org.aspectj.lang.JoinPoint;
import ru.arlekk1ng.exception.EmptyMethodArgumentException;

public interface MethodArgumentAspect {

    void checkMethodArguments(JoinPoint joinPoint) throws EmptyMethodArgumentException;
}
