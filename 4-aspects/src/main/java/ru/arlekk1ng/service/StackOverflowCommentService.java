package ru.arlekk1ng.service;

import java.time.LocalDate;
import java.util.Collection;
import org.springframework.stereotype.Service;
import ru.arlekk1ng.aspect.NotEmpty;

/**
 * Класс сервиса по публикации комментариев на StackOverflow
 */
@Service
public class StackOverflowCommentService implements CommentService {

    @NotEmpty
    public boolean publishComment(String comment) {
        // ..
        return true;
    }

    @NotEmpty
    public boolean publishComment(String comment, LocalDate date) {
        // ..
        return true;
    }

    @NotEmpty
    public boolean publishComment(String comment, String author) {
        // ..
        return true;
    }

    @NotEmpty
    public boolean publishComment(String comment, String author, LocalDate date) {
        // ..
        return true;
    }

    @NotEmpty
    public boolean publishComments(Collection<String> comments) {
        // ..
        return true;
    }

    @NotEmpty
    public boolean publishComments(Collection<String> comments, LocalDate date) {
        // ..
        return true;
    }

    @NotEmpty
    public boolean publishComments(Collection<String> comments, String author) {
        // ..
        return true;
    }

    @NotEmpty
    public boolean publishComments(Collection<String> comments, String author, LocalDate date) {
        // ..
        return true;
    }
}
