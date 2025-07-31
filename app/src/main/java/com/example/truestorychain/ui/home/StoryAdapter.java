package com.example.truestorychain.ui.home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truestorychain.R;
import com.example.truestorychain.data.model.Story;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    public interface OnStoryClickListener {
        void onStoryClick(Story story);
    }

    private List<Story> storyList;
    private final OnStoryClickListener listener;

    public StoryAdapter(List<Story> storyList, OnStoryClickListener listener) {
        this.storyList = storyList;
        this.listener = listener;
    }

    public void setStories(List<Story> stories) {
        this.storyList = stories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Story story = storyList.get(position);
        holder.bind(story);
    }

    @Override
    public int getItemCount() {
        return storyList != null ? storyList.size() : 0;
    }

    class StoryViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView previewTextView;

        StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.story_title);
            previewTextView = itemView.findViewById(R.id.story_preview);
        }

        void bind(final Story story) {
            titleTextView.setText(story.getTitle());
            previewTextView.setText(story.getInitialText());

            itemView.setOnClickListener(v -> listener.onStoryClick(story));
        }
    }
}