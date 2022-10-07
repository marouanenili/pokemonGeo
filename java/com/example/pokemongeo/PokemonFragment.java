package com.example.pokemongeo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pokemongeo.databinding.PokemonFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PokemonFragment  extends Fragment {
    Pokemon pokemon=null;
    public PokemonFragment(Pokemon pokemon){
        this.pokemon=pokemon;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PokemonFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.pokemon_fragment, container, false);

//dans les deux cas
        binding.pokemonName.setText(pokemon.getName());
        String type1 = pokemon.getType1String();
        binding.pokemonT1.setText(type1);
        int frontRessource1 = getResources().getIdentifier(type1.toLowerCase() , "drawable" ,binding.getRoot().getContext().getPackageName());
        binding.imageType1.setImageDrawable(getResources().getDrawable(frontRessource1));
        binding.pokemonHP.setText("HP: "+pokemon.getHP());
        binding.pokemonAttack.setText("Attack: "+pokemon.getAttack());
        binding.pokemonDefense.setText("Defense: "+pokemon.getDefense());
        binding.pokemonSpeed.setText("Vitesse : "+pokemon.getSpeed());
        if (pokemon.getType2()!=null){
            String type2 =pokemon.getType2String();
            binding.pokemonT2.setText(type2);
            int frontRessource2 = getResources().getIdentifier(type2.toLowerCase() , "drawable" ,binding.getRoot().getContext().getPackageName());
            binding.imageType2.setImageDrawable(getResources().getDrawable(frontRessource2));}
        else{
            binding.imageType2.setVisibility(View.GONE);
        }

        binding.pokemonImage.setImageDrawable(getResources().getDrawable(pokemon.getFrontResource()));

        binding.buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("click", "onClick: retour");
                listener.OnClickOnRetour();
            }
        });

        return binding.getRoot();

    }
    private OnClickOnNoteListener listener;
    public void setOnClickOnNoteListener(OnClickOnNoteListener listener)
    {
        this.listener = listener;
    }

}