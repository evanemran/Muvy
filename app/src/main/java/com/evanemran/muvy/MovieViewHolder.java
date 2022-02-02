package com.evanemran.muvy;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView_poster;
    TextView textView_movie_name;
    CardView container;
    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_poster = itemView.findViewById(R.id.imageView_poster);
        textView_movie_name = itemView.findViewById(R.id.textView_movie_name);
        container = itemView.findViewById(R.id.movie_container);
    }
}
