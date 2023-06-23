package ru.arlekk1ng.exception;

import ru.arlekk1ng.aspect.NotEmpty;
import ru.arlekk1ng.aspect.StringOrCollectionMethodArgumentNotEmptyAspect;

/**
 * Исключение аспекта {@link StringOrCollectionMethodArgumentNotEmptyAspect} сервиса,
 * которое выбрасывается при наличии пустых аргументов метода, помеченных аннотацией {@link NotEmpty}
 */
public class EmptyMethodArgumentException extends RuntimeException {

    public EmptyMethodArgumentException(String message) {
        super(message);
    }
}
