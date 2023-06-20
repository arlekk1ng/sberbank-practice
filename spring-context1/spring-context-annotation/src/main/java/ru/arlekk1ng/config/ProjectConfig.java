package ru.arlekk1ng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.arlekk1ng.model.Parrot;

@Configuration
@ComponentScan(basePackages = "ru/arlekk1ng/model")
public class ProjectConfig {

    @Bean
    @Primary
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
}
