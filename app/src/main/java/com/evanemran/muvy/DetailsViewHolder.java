package com.evanemran.muvy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetailsViewHolder extends RecyclerView.ViewHolder {
    TextView textView_cast_name, textView_cast_character;
    public DetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_cast_name = itemView.findViewById(R.id.textView_cast_name);
        textView_cast_character = itemView.findViewById(R.id.textView_cast_character);
    }
}
