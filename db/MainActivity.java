package com.example.pokemongeo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity<UsersAdapter> extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        // Create the adapter to convert the array to views
        // Attach the adapter to a ListView
        savePokemonsInDb();
        showStartup();


    }

    public void showStartup() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokedexFragment fragment = new PokedexFragment();
        fragment.setOnClickOnNoteListener(listener);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    public void showPokemon(Pokemon pokemon) {
        Log.d("test", pokemon.toString());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokemonFragment fragment = new PokemonFragment(pokemon);
        fragment.setOnClickOnNoteListener(listener);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

    }

    OnClickOnNoteListener listener = new OnClickOnNoteListener() {
        @Override
        public void onClickOnNote(Pokemon pokemon) {
            //showNoteDetail(noteId);
            Log.d("test", "msg");
            showPokemon(pokemon);
        }

        public void OnClickOnRetour() {
            //showNoteDetail(noteId);
            Log.d("test", "msg");
            showStartup();
        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.map:
                return true;
            case R.id.pokedex:
                showStartup();
                return true;

        }
        return false;
    }

    public void savePokemonsInDb() {
        PokemonDatabase db = Room.databaseBuilder(getApplicationContext(),
                PokemonDatabase.class, "database-name").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        PokemonDao pokemonDao = db.pokemonDao();

        List<Pokemon> oldPokemonList = pokemonDao.getAll();
        if (oldPokemonList.size() == 0) {
        /*
        if (pokemonList.size() < 10) {
            List<Pokemon> newpokemonList = getPokemonsFromJson();
            pokemonDao.insertAll(newpokemonList);
        }
         */
            List<Pokemon> pokemonList = new ArrayList<>();
            InputStream isr = getResources().openRawResource(R.raw.jsonlist);
            BufferedReader reader = new BufferedReader(new InputStreamReader(isr));
            StringBuilder builder = new StringBuilder();
            String data = "";
//lecture du fichier. data == null => EOF
            while (data != null) {
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
                    String name = names.getString("french");
                    String image = "p" + object.getString("id");
                    int frontRessource = getResources().getIdentifier(image, "drawable", getApplicationContext().getPackageName());
                    JSONArray types = object.getJSONArray("type");
                    POKEMON_TYPE t1 = null;
                    POKEMON_TYPE t2 = null;
                    JSONObject base = object.getJSONObject("base");
                    int HP = base.getInt("HP");
                    int Attack = base.getInt("Attack");
                    int Defense = base.getInt("Defense");
                    int Speed = base.getInt("Speed");
                    if (types.length() == 1) {
                        t1 = POKEMON_TYPE.valueOf(types.getString(0));
                        t2 = null;
                    } else {
                        t1 = POKEMON_TYPE.valueOf(types.getString(0));
                        t2 = POKEMON_TYPE.valueOf(types.getString(1));
                    }
                    Pokemon pokemon = new Pokemon(i, name, frontRessource, t1, t2, HP, Attack, Defense, Speed);
                    pokemonList.add(pokemon);
                    pokemonDao.insertAll(pokemon);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}