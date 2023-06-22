package ru.arlekk1ng.main;

import java.time.LocalDate;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.arlekk1ng.config.ProjectConfig;
import ru.arlekk1ng.service.StackOverflowCommentService;

public class Main {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            var stackOverflowCommentService = context.getBean(StackOverflowCommentService.class);

            String notEmptyString = "not empty string";
            String emptyString = "";
            String nullString = null;

            LocalDate localDate = LocalDate.now();

            stackOverflowCommentService.publishComment(notEmptyString);
            // stackOverflowCommentService.publishComment(emptyString, localDate);
            // stackOverflowCommentService.publishComment(notEmptyString, nullString);
            stackOverflowCommentService.publishComment(notEmptyString, notEmptyString, localDate);

            List<String> notEmptyCollection = List.of("first", "second");
            List<String> emptyCollection = List.of();
            List<String> nullCollection = null;

            stackOverflowCommentService.publishComments(notEmptyCollection);
            // stackOverflowCommentService.publishComments(notEmptyCollection, emptyString);
            // stackOverflowCommentService.publishComments(emptyCollection, localDate);
            // stackOverflowCommentService.publishComments(nullCollection, notEmptyString, localDate);
        }
    }
}
