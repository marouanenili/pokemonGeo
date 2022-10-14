package com.example.pokemongeo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    List<Pokemon> getAll();

    @Query("SELECT * FROM pokemon WHERE id IN (:pokemonIds)")
    List<Pokemon> loadAllByIds(int[] pokemonIds);

    @Query("SELECT * FROM pokemon WHERE name LIKE :name ")
    Pokemon findByName(String name);

    @Insert()
    void insertAll(Pokemon... pokemons);

    @Delete
    void delete(Pokemon pokemon);
}
