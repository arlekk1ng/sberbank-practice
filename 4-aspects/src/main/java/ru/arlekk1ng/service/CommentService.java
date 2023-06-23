package ru.arlekk1ng.service;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Интерфейс сервиса по публикации комментариев
 */
public interface CommentService {

    boolean publishComment(String comment);
    boolean publishComment(String comment, LocalDate date);
    boolean publishComment(String comment, String author);
    boolean publishComment(String comment, String author, LocalDate date);
    boolean publishComments(Collection<String> comments);
    boolean publishComments(Collection<String> comments, LocalDate date);
    boolean publishComments(Collection<String> comments, String author);
    boolean publishComments(Collection<String> comments, String author, LocalDate date);
}
