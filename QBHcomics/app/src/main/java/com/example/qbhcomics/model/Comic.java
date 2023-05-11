package com.example.qbhcomics.model;

public class Comic {

    private String name;
    private String author;
    private String image;
    private String description;
    private String content;

    public Comic(String name, String author, String image, String description, String content) {
        this.name = name;
        this.author = author;
        this.image = image;
        this.description = description;
        this.content=content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
