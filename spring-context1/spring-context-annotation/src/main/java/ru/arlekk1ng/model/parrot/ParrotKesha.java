package ru.arlekk1ng.model.parrot;

import org.springframework.stereotype.Component;

@Component("parrotKesha")
public class ParrotKesha implements Parrot {
    private String name = "Kesha";

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
