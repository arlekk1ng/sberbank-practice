package ru.arlekk1ng.model;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {
    private String name = "Vasiliy";
    @Autowired
    private Parrot parrot1;
    private Parrot parrot2;
    @Autowired
    private Cat cat;
    @Autowired
    private Dog dog;

    public Person(@Qualifier("parrot2") Parrot parrot) {
        this.parrot2 = parrot;
    }

//    @PostConstruct
//    private void initParrot2() {
//        this.parrot2 = parrot2;
//    }

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
