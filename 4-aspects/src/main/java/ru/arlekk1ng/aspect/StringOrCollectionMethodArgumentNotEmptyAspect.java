package ru.arlekk1ng.aspect;

import java.util.Collection;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.arlekk1ng.exception.EmptyMethodArgumentException;

/**
 * Аспект сервиса, который проверяет аргументы методов, помеченных аннотацией {@link NotEmpty}, на null или пустоту
 */
@Aspect
@Component
public class StringOrCollectionMethodArgumentNotEmptyAspect {

    @Before("@annotation(NotEmpty)")
    public void checkMethodArguments(JoinPoint joinPoint) throws EmptyMethodArgumentException {
        for (Object arg: joinPoint.getArgs()) {
            if (arg == null) {
                throw new EmptyMethodArgumentException("В аргументах метода присутствует null");
            } else if (arg instanceof String string) {
                if (string.isEmpty()) {
                    throw new EmptyMethodArgumentException("В аргументах метода присутствует пустая строка");
                }
            } else if (arg instanceof Collection collection) {
                if (collection.isEmpty()) {
                    throw new EmptyMethodArgumentException("В аргументах метода присутствует пустая коллекция");
                }
            }
        }
    }
}
