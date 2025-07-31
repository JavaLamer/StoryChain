package com.example.truestorychain.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.truestorychain.R;
import com.example.truestorychain.data.model.StoryPart;
import com.example.truestorychain.data.repository.StoryRepository;

import java.util.UUID;

public class AddPartFragment extends Fragment {

    private static final String ARG_STORY_ID = "story_id";

    public static AddPartFragment newInstance(String storyId) {
        AddPartFragment fragment = new AddPartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STORY_ID, storyId);
        fragment.setArguments(args);
        return fragment;
    }

    private String storyId;

    public AddPartFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_part, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText input = view.findViewById(R.id.edit_part_text);
        Button saveButton = view.findViewById(R.id.button_save_part);

        if (getArguments() != null) {
            storyId = getArguments().getString(ARG_STORY_ID);
        }

        saveButton.setOnClickListener(v -> {
            String text = input.getText().toString().trim();
            if (TextUtils.isEmpty(text)) {
                Toast.makeText(getContext(), "Введите текст", Toast.LENGTH_SHORT).show();
                return;
            }

            StoryPart part = new StoryPart(
                    UUID.randomUUID().toString(),
                    text,
                    "user123", // временно захардкоженный автор
                    System.currentTimeMillis(),
                    0
            );

            StoryRepository.getInstance().addStoryPart(storyId, part);

            Toast.makeText(getContext(), "Добавлено!", Toast.LENGTH_SHORT).show();

            // Вернуться назад
            getParentFragmentManager().popBackStack();
        });
    }
}