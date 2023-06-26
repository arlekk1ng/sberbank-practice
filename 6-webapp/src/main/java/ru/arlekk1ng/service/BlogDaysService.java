package ru.arlekk1ng.service;

import org.springframework.stereotype.Service;

/**
 * Хранит и возвращает записанные дни в блоге.
 */
@Service
public class BlogDaysService implements BlogService {
    private int[] blogDays = { 4, 5, 6, 7, 11 };

    public String[] getBlogDays() {
        String[] blogDaysStrings = new String[blogDays.length];
        for (int i = 0; i < blogDays.length; i++) {
            blogDaysStrings[i] = String.valueOf(blogDays[i]);
        }
        return blogDaysStrings;
    }
}
