package com.example.truestorychain.data.repository;


import com.example.truestorychain.data.model.Story;
import com.example.truestorychain.data.model.StoryPart;
import com.example.truestorychain.data.model.User;
import com.example.truestorychain.data.repository.StoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StoryRepository {

    private static StoryRepository instance;
    private final List<Story> stories;

    private StoryRepository() {
        stories = new ArrayList<>();
        seedData(); // Создаём тестовую историю
    }

    public static StoryRepository getInstance() {
        if (instance == null) {
            instance = new StoryRepository();
        }
        return instance;
    }

    private void seedData() {
        // Добавляем одну фейковую историю с пустыми ветками
        Story story = new Story(
                UUID.randomUUID().toString(),
                "Таинственный лес",
                "В один холодный осенний вечер, герой оказался у входа в густой и туманный лес...",
                new ArrayList<>(),
                false
        );
        stories.add(story);
    }

    public List<Story> getAllStories() {
        return stories;
    }

    public Story getStoryById(String storyId) {
        for (Story story : stories) {
            if (story.getId().equals(storyId)) {
                return story;
            }
        }
        return null;
    }

    public void addStoryPart(String storyId, StoryPart part) {
        Story story = getStoryById(storyId);
        if (story != null) {
            List<StoryPart> parts = story.getParts();
            parts.add(part);
            story.setParts(parts);
        }
    }

    public void voteForPart(String storyId, String partId) {
        Story story = getStoryById(storyId);
        if (story != null) {
            for (StoryPart part : story.getParts()) {
                if (part.getId().equals(partId)) {
                    part.addVote();
                    break;
                }
            }
        }
    }
}