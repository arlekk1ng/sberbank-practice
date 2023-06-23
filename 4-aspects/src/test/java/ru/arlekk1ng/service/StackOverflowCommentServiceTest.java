package ru.arlekk1ng.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.arlekk1ng.aspect.MethodArgumentAspect;
import ru.arlekk1ng.config.ProjectConfig;
import ru.arlekk1ng.exception.EmptyMethodArgumentException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ProjectConfig.class })
public class StackOverflowCommentServiceTest {
    private Logger aspectLogger;
    private MethodArgumentAspect methodArgumentAspect;
    private CommentService commentService;

    @Autowired
    public StackOverflowCommentServiceTest(
            MethodArgumentAspect methodArgumentAspect, CommentService commentService) {
        this.methodArgumentAspect = methodArgumentAspect;
        this.commentService = commentService;
    }

    @BeforeEach
    public void before() {
        this.aspectLogger = mock(Logger.class);
        methodArgumentAspect.setAspectLogger(aspectLogger);
    }

    @Test
    public void testMethodArgumentAspectWithoutEmptyStringArgument() {
        String notEmptyString = "not empty string";

        commentService.publishComment(notEmptyString);

        verify(aspectLogger, never()).warning(anyString());
    }

    @Test
    public void testMethodArgumentAspectWithEmptyStringArgument() {
        Executable executable = () -> {
            String notEmptyString = "not empty string";
            String emptyString = "";
            commentService.publishComment(notEmptyString, emptyString);
        };

        assertThrows(EmptyMethodArgumentException.class, executable);
        verify(aspectLogger).warning("В аргументах метода присутсвует пустая строка");
    }

    @Test
    public void testMethodArgumentAspectWithNullStringArgument() {
        Executable executable = () -> {
            String notEmptyString = "not empty string";
            String nullString = null;
            LocalDate localDate = LocalDate.now();
            commentService.publishComment(notEmptyString, nullString, localDate);
        };

        assertThrows(EmptyMethodArgumentException.class, executable);
        verify(aspectLogger).warning("В аргументах метода присутсвует null");
    }

    @Test
    public void testMethodArgumentAspectWithoutEmptyCollectionArgument() {
        List<String> notEmptyCollection = List.of("first", "second");

        commentService.publishComments(notEmptyCollection);

        verify(aspectLogger, never()).warning(anyString());
    }

    @Test
    public void testMethodArgumentAspectWithEmptyCollectionArgument() {
        Executable executable = () -> {
            List<String> emptyCollection = List.of();
            LocalDate localDate = LocalDate.now();
            commentService.publishComments(emptyCollection, localDate);
        };

        assertThrows(EmptyMethodArgumentException.class, executable);
        verify(aspectLogger).warning("В аргументах метода присутсвует пустая коллекция");
    }

    @Test
    public void testMethodArgumentAspectWithNullCollectionArgument() {
        Executable executable = () -> {
            List<String> nullCollection = null;
            String notEmptyString = "not empty string";
            LocalDate localDate = LocalDate.now();
            commentService.publishComments(nullCollection, notEmptyString, localDate);
        };

        assertThrows(EmptyMethodArgumentException.class, executable);
        verify(aspectLogger).warning("В аргументах метода присутсвует null");
    }
}
