package ru.arlekk1ng.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.arlekk1ng.config.ProjectConfig;
import ru.arlekk1ng.model.Person;

public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Person person = context.getBean(Person.class);
        System.out.println(person);
    }
}
