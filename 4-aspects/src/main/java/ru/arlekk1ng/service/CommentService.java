package ru.arlekk1ng.service;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Интерфейс приложения
 */
public interface CommentService {

    void publishComment(String comment);
    void publishComment(String comment, LocalDate date);
    void publishComment(String comment, String author);
    void publishComment(String comment, String author, LocalDate date);
    void publishComments(Collection<String> comments);
    void publishComments(Collection<String> comments, LocalDate date);
    void publishComments(Collection<String> comments, String author);
    void publishComments(Collection<String> comments, String author, LocalDate date);
}
