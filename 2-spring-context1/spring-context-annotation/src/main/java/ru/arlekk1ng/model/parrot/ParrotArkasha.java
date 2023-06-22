package ru.arlekk1ng.model.parrot;

import org.springframework.stereotype.Component;

@Component("parrotArkasha")
public class ParrotArkasha implements Parrot {
    private String name = "Arkasha";

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Parrot{" +
                "name='" + name + '\'' +
                '}';
    }
}
