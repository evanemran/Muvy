package com.evanemran.muvy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evanemran.muvy.Models.Cast;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsViewHolder> {
    Context context;
    List<Cast> casts;

    public DetailsAdapter(Context context, List<Cast> casts) {
        this.context = context;
        this.casts = casts;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailsViewHolder(LayoutInflater.from(context).inflate(R.layout.details_container, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        holder.textView_cast_name.setText(casts.get(position).getActor());
        holder.textView_cast_character.setText(casts.get(position).getCharacter());
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }
}
