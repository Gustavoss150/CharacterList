package com.gustavo.characterlist.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gustavo.characterlist.R;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageCharacter;
        TextView textCharacter, locationCharacter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageCharacter = itemView.findViewById(R.id.imageCharacter);
            textCharacter = itemView.findViewById(R.id.textCharacter);
            locationCharacter = itemView.findViewById(R.id.locationCharacter);

        }
    }


}
