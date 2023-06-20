package ru.arlekk1ng.model;

import org.springframework.stereotype.Component;

@Component
public class Cat {
    private String name = "Lusik";

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
