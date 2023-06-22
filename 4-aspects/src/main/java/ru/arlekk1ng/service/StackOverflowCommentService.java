package ru.arlekk1ng.service;

import java.time.LocalDate;
import java.util.Collection;
import org.springframework.stereotype.Service;
import ru.arlekk1ng.aspect.NotEmpty;

@Service
public class StackOverflowCommentService implements CommentService {

    @NotEmpty
    public void publishComment(String comment) {
    }

    @NotEmpty
    public void publishComment(String comment, LocalDate date) {
    }

    @NotEmpty
    public void publishComment(String comment, String author) {
    }

    @NotEmpty
    public void publishComment(String comment, String author, LocalDate date) {
    }

    @NotEmpty
    public void publishComments(Collection<String> comments) {
    }

    @NotEmpty
    public void publishComments(Collection<String> comments, LocalDate date) {
    }

    @NotEmpty
    public void publishComments(Collection<String> comments, String author) {
    }

    @NotEmpty
    public void publishComments(Collection<String> comments, String author, LocalDate date) {
    }
}
