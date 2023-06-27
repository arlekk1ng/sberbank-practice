package ru.arlekk1ng.entity;

public class BlogPost {
    /**
     * Имя фрагмента из шаблона blog-posts.html
     */
    private String fragmentName;
    private String text;

    public BlogPost(String fragmentName, String text) {
        this.fragmentName = fragmentName;
        this.text = text;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public String getText() {
        return text;
    }
}
