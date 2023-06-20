package ru.arlekk1ng.model;

public class Person {
    private String name;
    private Parrot parrot1;
    private Parrot parrot2;
    private Cat cat;
    private Dog dog;

    public Person(String name, Parrot parrot1, Parrot parrot2, Cat cat, Dog dog) {
        this.name = name;
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
