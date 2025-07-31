package com.example.truestorychain.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truestorychain.R;
import com.example.truestorychain.data.model.Story;
import com.example.truestorychain.data.model.StoryPart;
import com.example.truestorychain.data.repository.StoryRepository;

import java.util.List;

public class StoryDetailFragment extends Fragment {

    private static final String ARG_STORY_ID = "story_id";

    private Story story;
    private TextView titleView, initialTextView;
    private RecyclerView partsRecycler;
    private StoryPartAdapter partAdapter;

    public StoryDetailFragment() {}

    public static StoryDetailFragment newInstance(String storyId) {
        StoryDetailFragment fragment = new StoryDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STORY_ID, storyId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_story_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleView = view.findViewById(R.id.story_detail_title);
        initialTextView = view.findViewById(R.id.story_detail_initial_text);
        partsRecycler = view.findViewById(R.id.recycler_parts);
        Button addPartButton = view.findViewById(R.id.button_add_part);

        // Получаем ID истории из аргументов
        String storyId = getArguments() != null ? getArguments().getString(ARG_STORY_ID) : null;

        if (storyId == null) {
            Toast.makeText(getContext(), "ID истории не передан", Toast.LENGTH_SHORT).show();
            return;
        }

        story = StoryRepository.getInstance().getStoryById(storyId);

        if (story == null) {
            Toast.makeText(getContext(), "История не найдена", Toast.LENGTH_SHORT).show();
            return;
        }

        // Устанавливаем данные
        titleView.setText(story.getTitle());
        initialTextView.setText(story.getInitialText());

        List<StoryPart> parts = story.getParts();
        partAdapter = new StoryPartAdapter(parts);
        partsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        partsRecycler.setAdapter(partAdapter);

        // Кнопка "Добавить продолжение"
        addPartButton.setOnClickListener(v -> {
            AddPartFragment addPartFragment = AddPartFragment.newInstance(story.getId());

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, addPartFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}
