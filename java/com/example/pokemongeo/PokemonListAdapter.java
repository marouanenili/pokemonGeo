package com.example.pokemongeo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemongeo.databinding.PokemonItemBinding;

import java.util.List;

public class PokemonListAdapter extends
        RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {
    List<Pokemon> pokemonList;
    PokemonViewModel viewModel = new PokemonViewModel();
    public      PokemonListAdapter(List<Pokemon> pokemonList) {
        assert pokemonList != null;
        this.pokemonList =pokemonList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.pokemon_item, parent, false);
        String number=binding.number.getText().toString();
        //number= number.substring(1);
        //int N= Integer.parseInt(number);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pokemon p=  pokemonList.get(Integer.parseInt(binding.number.getText().toString().substring(1)));
                listener.onClickOnNote(p);
                Log.d("click sur",pokemonList.get(Integer.parseInt(binding.number.getText().toString().substring(1))).toString() );//pokemonList.get(N).toString()
            }
        });
        return new ViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        
        holder.viewModel.setPokemon(pokemon);


    }
    @Override
    public int getItemCount() {
        return pokemonList.size();
    }


    private OnClickOnNoteListener listener;
    public void setOnClickOnNoteListener(OnClickOnNoteListener listener)
    {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private PokemonItemBinding binding;
        private PokemonViewModel viewModel = new PokemonViewModel();
        ViewHolder(PokemonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setPokemonViewModel(viewModel);
        }
    }
}