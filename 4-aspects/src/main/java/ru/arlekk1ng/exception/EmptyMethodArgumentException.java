package ru.arlekk1ng.exception;

public class EmptyMethodArgumentException extends Exception implements MethodArgumentException {

    public EmptyMethodArgumentException(String message) {
        super(message);
    }
}
