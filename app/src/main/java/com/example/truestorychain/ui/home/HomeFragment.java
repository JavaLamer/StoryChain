package com.example.truestorychain.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truestorychain.R;
import com.example.truestorychain.data.model.Story;
import com.example.truestorychain.data.repository.StoryRepository;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private StoryAdapter storyAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_stories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Story> storyList = StoryRepository.getInstance().getAllStories();

        storyAdapter = new StoryAdapter(storyList, story -> {
            // ✅ Переход через NavController
            NavController navController = Navigation.findNavController(view);
            Bundle bundle = new Bundle();
            bundle.putString("storyId", story.getId());
            navController.navigate(R.id.storyDetailFragment, bundle);
        });

        recyclerView.setAdapter(storyAdapter);
    }
}
