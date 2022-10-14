package com.example.pokemongeo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.pokemongeo.databinding.PokedexFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PokedexFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        PokedexFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.pokedex_fragment, container, false);
        binding.pokemonList.setLayoutManager(new LinearLayoutManager(
                binding.getRoot().getContext()));

        List<Pokemon> pokemonList = getPokemonListFromDb(binding);

        PokemonListAdapter adapter = new PokemonListAdapter(pokemonList);
        adapter.setOnClickOnNoteListener(listener);
        binding.pokemonList.setAdapter(adapter);
        return binding.getRoot();
    }


    private OnClickOnNoteListener listener;
    public void setOnClickOnNoteListener(OnClickOnNoteListener listener)
    {
        this.listener = listener;
    }

    public List<Pokemon> getPokemonListFromDb(PokedexFragmentBinding binding){
        PokemonDatabase db = Room.databaseBuilder(binding.getRoot().getContext(),
                PokemonDatabase.class, "database-name").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        PokemonDao pokemonDao = db.pokemonDao();

        List<Pokemon> pokemonList = pokemonDao.getAll();
        return pokemonList;
    }


    /*
    List<Pokemon> pokemonList = new ArrayList<>();
        int id = getResources().getIdentifier("jsonlist","raw",
                binding.getRoot().getContext().getPackageName());
        InputStreamReader isr = new InputStreamReader(getResources().openRawResource(id));
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder builder = new StringBuilder();
        String data = "";
//lecture du fichier. data == null => EOF
        while(data != null) {
            try {
                data = reader.readLine();
                builder.append(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//Traitement du fichier
        try {
            JSONArray array = new JSONArray(builder.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                JSONObject names = object.getJSONObject("name");
                String name= names.getString("french");
                String image = "p"+object.getString("id");
                int frontRessource = getResources().getIdentifier(image , "drawable" ,binding.getRoot().getContext().getPackageName());
                JSONArray types = object.getJSONArray("type");
                POKEMON_TYPE t1=null;
                POKEMON_TYPE t2=null;
                JSONObject base= object.getJSONObject("base");
                int HP =base.getInt("HP");
                int Attack = base.getInt("Attack");
                int Defense = base.getInt("Defense");
                int Speed =base.getInt("Speed");
                if (types.length()==1){
                    t1 =POKEMON_TYPE.valueOf(types.getString(0));
                    t2=null;
                }
                else{
                    t1 =POKEMON_TYPE.valueOf(types.getString(0));
                    t2=POKEMON_TYPE.valueOf(types.getString(1));
                }
                Pokemon pokemon = new Pokemon(i,name,frontRessource,t1,t2,HP,Attack,Defense,Speed);
                pokemonList.add(pokemon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
     */

}


