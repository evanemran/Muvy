package com.evanemran.muvy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evanemran.muvy.Models.Titles;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    Context context;
    List<Titles> list;
    OnClickedListener listener;

    public MovieAdapter(Context context, List<Titles> list, OnClickedListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.movies_container, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getImage()).into(holder.imageView_poster);
        holder.textView_movie_name.setText(list.get(position).getTitle());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClicked(list.get(position).getId());
            }
        });
        holder.textView_movie_name.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
