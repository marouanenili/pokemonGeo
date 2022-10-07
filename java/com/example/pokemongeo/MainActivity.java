package com.example.pokemongeo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;


import com.example.pokemongeo.databinding.ActivityMainBindingImpl;
import com.example.pokemongeo.databinding.PokemonFragmentBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity<UsersAdapter> extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView =(BottomNavigationView) findViewById(R.id.bottomNavigationBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        // Create the adapter to convert the array to views
        // Attach the adapter to a ListView
        showStartup();


    }

    public void showStartup() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokedexFragment fragment = new PokedexFragment();
        fragment.setOnClickOnNoteListener(listener);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }
    public void showPokemon(Pokemon pokemon){
        Log.d("test",pokemon.toString());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokemonFragment fragment = new PokemonFragment(pokemon);
        fragment.setOnClickOnNoteListener(listener);
        transaction.replace(R.id.fragment_container,fragment) ;
        transaction.commit();

    }

    OnClickOnNoteListener listener = new OnClickOnNoteListener(){
        @Override
        public void onClickOnNote(Pokemon pokemon){
            //showNoteDetail(noteId);
            Log.d("test","msg");
            showPokemon(pokemon);
        }

        public void OnClickOnRetour(){
            //showNoteDetail(noteId);
            Log.d("test","msg");
            showStartup();
        }
    };




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        switch(id){
            case R.id.map:
                return true;
            case R.id.pokedex:
                 showStartup();
                 return true;

        }
        return false;
    }
}