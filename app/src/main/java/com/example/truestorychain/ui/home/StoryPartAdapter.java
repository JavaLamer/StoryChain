package com.example.truestorychain.ui.home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.truestorychain.R;
import com.example.truestorychain.data.model.StoryPart;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoryPartAdapter extends RecyclerView.Adapter<StoryPartAdapter.PartViewHolder> {

    private final List<StoryPart> parts;

    public StoryPartAdapter(List<StoryPart> parts) {
        this.parts = parts;
    }

    @NonNull
    @Override
    public PartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story_part, parent, false);
        return new PartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartViewHolder holder, int position) {
        StoryPart part = parts.get(position);
        holder.bind(part);
    }

    @Override
    public int getItemCount() {
        return parts != null ? parts.size() : 0;
    }

    static class PartViewHolder extends RecyclerView.ViewHolder {
        TextView partText, voteCount;

        PartViewHolder(@NonNull View itemView) {
            super(itemView);
            partText = itemView.findViewById(R.id.part_text);
            voteCount = itemView.findViewById(R.id.part_votes);
        }

        void bind(StoryPart part) {
            partText.setText(part.getText());
            voteCount.setText("Голоса: " + part.getVotes());
        }
    }
}