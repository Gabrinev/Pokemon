package com.example.pokemon.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.pokemon.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private ArrayList<Pokemon> items;
    private PokeAdapter adapter;
    public ArrayList<PokeStats> listStats;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        RefreshDataTask task = new RefreshDataTask();
        RefreshDataTask2 task2 = new RefreshDataTask2();
        ListView lvPokes = view.findViewById(R.id.lvPokemons);



        items = new ArrayList<>();
        listStats = new ArrayList<>();

        adapter = new PokeAdapter(
                getContext(),
                R.layout.lv_pokes_row,
                items
        );
        lvPokes.setAdapter(adapter);


        //Obtencion datos api
        task.execute();
        task2.execute();


        //Abrir fragment de detalle
        lvPokes.setOnItemClickListener((adapter, fragment, i, l) -> {
            Pokemon poke = (Pokemon) adapter.getItemAtPosition(i);
            PokeStats pokeStats = listStats.get(poke.getIde());
            Intent intent = new Intent(getContext(), MainDetail.class);
            intent.putExtra("pokemon", poke);
            intent.putExtra("pokestats", pokeStats);
            startActivity(intent);
        });

        //BBDD
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getMovies().observe(this, pokes -> {
            adapter.clear();
            adapter.addAll(pokes);
        });


        return view;
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Pokemon>> {
        @Override
        protected ArrayList<Pokemon> doInBackground(Void... voids) {
            PokeAPI api = new PokeAPI();
            ArrayList<Pokemon> result = api.getShinys("es");
            return result;

        }

        @Override
        protected void onPostExecute(ArrayList<Pokemon> pokemons) {
            adapter.clear();
            for (Pokemon pokemon : pokemons) {
                adapter.add(pokemon);
            }

        }
    }

    private class RefreshDataTask2 extends AsyncTask<Void, Void, ArrayList<PokeStats>> {
        @Override
        protected ArrayList<PokeStats> doInBackground(Void... voids) {
            PokeAPI api = new PokeAPI();
            ArrayList<PokeStats> result = api.getStats("es");
            return result;

        }
        protected void onPostExecute(ArrayList<PokeStats> pokemons) {
            listStats.clear();
            listStats.addAll(pokemons);
            int i;
            for (i = 0; i < listStats.size(); i++) {
                System.out.println(listStats.get(i).toString());
            }

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_pokemons_fragments, menu);
        MenuItem menuItem = menu.findItem(R.id.search_mag_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search here!");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });
    }

}