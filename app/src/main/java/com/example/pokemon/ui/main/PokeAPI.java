package com.example.pokemon.ui.main;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class PokeAPI {
    private final String BASE_URL = "https://pogoapi.net/api";
    private final String API_KEY = "<api-key>";

    ArrayList<Pokemon> getShinys(String pais) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("v1")
                .appendPath("shiny_pokemon.json")
                .appendQueryParameter("region", pais)
                .appendQueryParameter("api_key", API_KEY)
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    ArrayList<PokeStats> getStats(String pais) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("v1")
                .appendPath("pokemon_stats.json")
                .appendQueryParameter("region", pais)
                .appendQueryParameter("api_key", API_KEY)
                .build();
        String url = builtUri.toString();

        return doCall2(url);
    }


    private ArrayList<Pokemon> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processShiny(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<PokeStats> doCall2(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processStats(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Pokemon> processShiny(String jsonResponse) {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        try {

            JSONObject data = new JSONObject(jsonResponse);
            for (Iterator<String> it = data.keys(); it.hasNext(); ) {
                String key = it.next();

                JSONObject jsonPoke = data.getJSONObject(key);

                Pokemon pokemon = new Pokemon();
                pokemon.setIde(jsonPoke.getInt("id"));
                pokemon.setName(jsonPoke.getString("name"));
                pokemon.setFound_egg(jsonPoke.getBoolean("found_egg"));
                pokemon.setFound_raid(jsonPoke.getBoolean("found_raid"));
                pokemon.setFound_wild(jsonPoke.getBoolean("found_wild"));
                pokemon.setFound_research(jsonPoke.getBoolean("found_research"));
                pokemons.add(pokemon);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pokemons;
    }

    private ArrayList<PokeStats> processStats(String jsonResponse) {

        Log.w("AAAAAAAAAAAAAAA", "-------------------");
        ArrayList<PokeStats> pokes = new ArrayList<>();



        Gson gson = new Gson();
        PokeStats[] pokemons = gson.fromJson(jsonResponse, PokeStats[].class);

        pokes.addAll(Arrays.asList(pokemons));


        return pokes;
    }
}
