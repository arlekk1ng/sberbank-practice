package ru.arlekk1ng.exception;

/**
 * Исключение приложения, которое выбрасывается при наличии пустых аргументов метода
 */
public class EmptyMethodArgumentException extends RuntimeException implements MethodArgumentException {

    public EmptyMethodArgumentException(String message) {
        super(message);
    }
}
