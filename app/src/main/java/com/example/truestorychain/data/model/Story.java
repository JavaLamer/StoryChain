package com.example.truestorychain.data.model;

import java.util.List;

public class Story {
    private String id;
    private String title;
    private String initialText;
    private List<StoryPart> parts;
    private boolean completed;

    public Story(String id, String title, String initialText, List<StoryPart> parts, boolean completed) {
        this.id = id;
        this.title = title;
        this.initialText = initialText;
        this.parts = parts;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getInitialText() {
        return initialText;
    }

    public List<StoryPart> getParts() {
        return parts;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setParts(List<StoryPart> parts) {
        this.parts = parts;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}