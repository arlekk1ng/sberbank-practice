package ru.arlekk1ng.model;

import org.springframework.stereotype.Component;

@Component
public class Dog {
    private String name = "Zhuzha";

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
