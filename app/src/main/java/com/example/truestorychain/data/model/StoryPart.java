package com.example.truestorychain.data.model;

public class StoryPart {
    private String id;
    private String text;
    private String authorId;
    private long timestamp;
    private int votes;

    public StoryPart(String id, String text, String authorId, long timestamp, int votes) {
        this.id = id;
        this.text = text;
        this.authorId = authorId;
        this.timestamp = timestamp;
        this.votes = votes;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAuthorId() {
        return authorId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getVotes() {
        return votes;
    }

    public void addVote() {
        this.votes += 1;
    }
}