package com.example.qbhcomics.model;

public class Comment {
    private String name;
    private String content;

    public Comment(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return name + ": " + content;
    }
}