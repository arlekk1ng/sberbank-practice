package ru.arlekk1ng.aspect;

import java.util.Collection;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.arlekk1ng.exception.EmptyMethodArgumentException;

@Aspect
@Component
public class StringOrCollectionMethodArgumentNotEmptyAspect implements MethodArgumentAspect {

    @Before("@annotation(NotEmpty)")
    public void checkMethodArguments(JoinPoint joinPoint) throws EmptyMethodArgumentException {
        for (Object arg: joinPoint.getArgs()) {
            if (arg == null) {
                throw new EmptyMethodArgumentException("В аргументах метода присутсвует null");
            } else if (arg instanceof String string) {
                if (string.isEmpty()) {
                    throw new EmptyMethodArgumentException(
                            "В аргументах метода присутсвует пустая строка");
                }
            } else if (arg instanceof Collection collection) {
                if (collection.isEmpty()) {
                    throw new EmptyMethodArgumentException(
                            "В аргументах метода присутсвует пустая коллекция");
                }
            }
        }
    }
}
