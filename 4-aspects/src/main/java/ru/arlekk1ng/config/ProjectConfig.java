package ru.arlekk1ng.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Класс конфигурации контекста сервиса по публикации комментариев
 */
@Configuration
@ComponentScan(basePackages = {"ru.arlekk1ng.aspect", "ru.arlekk1ng.service"})
@EnableAspectJAutoProxy
public class ProjectConfig {
}
