package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewItem> {
    private List<FootballTeams> footballTeams;
    public RecyclerViewAdapter(List<FootballTeams> FootballTeams) {
        this.footballTeams = FootballTeams;
    }

    public void addData(ArrayList<FootballTeams> newData){
        this.footballTeams = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teamcard, parent, false);
        return new RecyclerViewItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItem holder, int position) {
        holder.id.setText(footballTeams.get(position).getId());
        holder.name.setText(footballTeams.get(position).getName());
        holder.location.setText(footballTeams.get(position).getLocation());
        holder.cost.setText(String.valueOf(footballTeams.get(position).getCost()));
        holder.size.setText(String.valueOf(footballTeams.get(position).getSize()));
    }

    @Override
    public int getItemCount() {
        return footballTeams.size();
    }
}