package com.example.pokemon.ui.main.movieDao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pokemon.ui.main.Pokemon;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("select * from pokemon")
    LiveData<List<Pokemon>> getPokemons();

    @Insert
    void addPokemon(Pokemon carta);

    @Insert
    void addPokemons(List<Pokemon> pokemon);

    @Delete
    void deletePokemon(Pokemon pokemon);

    @Query("DELETE FROM Pokemon")
    void deletePokemons();

}
