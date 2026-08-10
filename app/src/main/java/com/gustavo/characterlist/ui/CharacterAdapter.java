package com.gustavo.characterlist.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gustavo.characterlist.R;
import com.gustavo.characterlist.model.Characters;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private final List<Characters> personagens;

    // construtor
    public CharacterAdapter(List<Characters> personagens) {
        this.personagens = personagens;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character,parent,false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Characters personagem = personagens.get(position);
        holder.textCharacter.setText(personagem.getName());
        holder.locationCharacter.setText(personagem.getLocation().getName());
        // holder.imageCharacter.set
    }

    @Override
    public int getItemCount() {
        return personagens.size();
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