package com.example.pokemongeo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pokemon.class}, version = 3)
public abstract class PokemonDatabase extends RoomDatabase {
    public abstract PokemonDao pokemonDao();
}