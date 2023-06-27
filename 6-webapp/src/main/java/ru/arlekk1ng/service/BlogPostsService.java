package ru.arlekk1ng.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.arlekk1ng.entity.BlogPost;

@Service
public class BlogPostsService {
    private List<BlogPost> blogPosts = List.of(
            new BlogPost("day-4", "День 4"),
            new BlogPost("day-5", "День 5"),
            new BlogPost("day-6", "День 6"),
            new BlogPost("day-7", "День 7"),
            new BlogPost("day-11", "День 11")
    );

    public List<BlogPost> findAll() {
        return new ArrayList<>(blogPosts);
    }
}
