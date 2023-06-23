package ru.arlekk1ng.service;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.arlekk1ng.config.ProjectConfig;
import ru.arlekk1ng.exception.EmptyMethodArgumentException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ProjectConfig.class })
public class StackOverflowCommentServiceTest {
    private static final String NOT_EMPTY_STRING = "not empty string";
    private static final String EMPTY_STRING = "";
    private static final String NULL_STRING = null;
    private static final List<String> NOT_EMPTY_COLLECTION = List.of("first", "second");
    private static final List<String> EMPTY_COLLECTION = List.of();
    private static final List<String> NULL_COLLECTION = null;
    private static final LocalDate LOCAL_DATE = LocalDate.now();

    @Autowired
    private CommentService commentService;

    @Test
    @DisplayName("Опубликовывает комментарий с непустой строкой")
    public void testMethodArgumentAspectWithoutEmptyStringArgument() {
        boolean isPublished = commentService.publishComment(NOT_EMPTY_STRING);

        Assertions.assertTrue(isPublished);
    }

    @Test
    @DisplayName("Ловит исключение при публикации комментария с пустой строкой")
    public void testMethodArgumentAspectWithEmptyStringArgument() {
        Executable executable = () -> {
            commentService.publishComment(NOT_EMPTY_STRING, EMPTY_STRING);
        };

        Assertions.assertThrows(EmptyMethodArgumentException.class, executable);
    }

    @Test
    @DisplayName("Ловит исключение при публикации комментария с null строкой")
    public void testMethodArgumentAspectWithNullStringArgument() {
        Executable executable = () -> {
            commentService.publishComment(NOT_EMPTY_STRING, NULL_STRING, LOCAL_DATE);
        };

        Assertions.assertThrows(EmptyMethodArgumentException.class, executable);
    }

    @Test
    @DisplayName("Опубликовывает комментарии из непустой коллекции")
    public void testMethodArgumentAspectWithoutEmptyCollectionArgument() {
        boolean isPublished = commentService.publishComments(NOT_EMPTY_COLLECTION);

        Assertions.assertTrue(isPublished);
    }

    @Test
    @DisplayName("Ловит исключение при публикации комментариев из пустой коллекции")
    public void testMethodArgumentAspectWithEmptyCollectionArgument() {
        Executable executable = () -> {
            commentService.publishComments(EMPTY_COLLECTION, LOCAL_DATE);
        };

        Assertions.assertThrows(EmptyMethodArgumentException.class, executable);
    }

    @Test
    @DisplayName("Ловит исключение при публикации комментариев из null коллекции")
    public void testMethodArgumentAspectWithNullCollectionArgument() {
        Executable executable = () -> {
            commentService.publishComments(NULL_COLLECTION, NOT_EMPTY_STRING, LOCAL_DATE);
        };

        Assertions.assertThrows(EmptyMethodArgumentException.class, executable);
    }
}
