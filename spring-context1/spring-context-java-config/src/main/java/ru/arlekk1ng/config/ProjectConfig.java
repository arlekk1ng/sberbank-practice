package ru.arlekk1ng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.arlekk1ng.model.*;

@Configuration
public class ProjectConfig {

    @Bean
    public Person person() {
        Person person = new Person(
                "Vasiliy",
                parrot1(),
                parrot2(),
                cat(),
                dog()
        );
        return person;
    }

    @Bean
    public Parrot parrot1() {
        Parrot parrot = new Parrot();
        parrot.setName("Kesha");
        return parrot;
    }

    @Bean
    public Parrot parrot2() {
        Parrot parrot = new Parrot();
        parrot.setName("Arkasha");
        return parrot;
    }

    @Bean
    public Cat cat() {
        Cat cat = new Cat();
        cat.setName("Lusik");
        return cat;
    }

    @Bean
    public Dog dog() {
        Dog dog = new Dog();
        dog.setName("Zhuzha");
        return dog;
    }
}
