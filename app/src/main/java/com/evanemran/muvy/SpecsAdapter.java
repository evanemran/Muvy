package com.evanemran.muvy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SpecsAdapter extends RecyclerView.Adapter<DetailsViewHolder> {
    Context context;
    List<List<String>> list;

    public SpecsAdapter(Context context, List<List<String>> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailsViewHolder(LayoutInflater.from(context).inflate(R.layout.details_container, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        holder.textView_cast_name.setText(list.get(position).get(0));
        holder.textView_cast_character.setText(list.get(position).get(1));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
