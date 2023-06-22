package ru.arlekk1ng.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = {"ru.arlekk1ng.service", "ru.arlekk1ng.proxy", "ru.arlekk1ng.repository"})
public class ProjectConfig {
}
