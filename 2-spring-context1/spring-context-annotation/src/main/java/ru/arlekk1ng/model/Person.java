package ru.arlekk1ng.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.arlekk1ng.model.parrot.Parrot;

@Component
public class Person {
    private String name = "Vasiliy";
    private Parrot parrot1, parrot2;
    private Cat cat;
    private Dog dog;

    @Autowired
    public Person(@Qualifier("parrotKesha") Parrot parrot1, @Qualifier("parrotArkasha") Parrot parrot2,
                  Cat cat, Dog dog) {
        this.parrot1 = parrot1;
        this.parrot2 = parrot2;
        this.cat = cat;
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", parrot1=" + parrot1 +
                ", parrot2=" + parrot2 +
                ", cat=" + cat +
                ", dog=" + dog +
                '}';
    }
}
