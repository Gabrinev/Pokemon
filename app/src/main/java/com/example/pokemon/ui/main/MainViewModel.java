package com.example.pokemon.ui.main;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemon.ui.main.movieDao.AppDatabase;
import com.example.pokemon.ui.main.movieDao.MovieDao;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private final Application app;
    private final AppDatabase appDatabase;
    private final MovieDao movieDao;
    private LiveData<List<Pokemon>> movies;

    public MainViewModel(Application application) {
        super(application);

        this.app = application;
        this.appDatabase = AppDatabase.getDatabase(
                this.getApplication());
        this.movieDao = appDatabase.getMovieDao();
    }

    public LiveData<List<Pokemon>> getMovies() {
        return movieDao.getPokemons();
    }


    public void reload() {
        // do async operation to fetch users
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(
                    app.getApplicationContext()
            );
            String pais = preferences.getString("pais", "es");
            String tipusConsulta = preferences.getString(
                    "tipus_consulta", "vistes"
            );

            PokeAPI api = new PokeAPI();
            ArrayList<Pokemon> result;

                result = api.getShinys(pais);


            movieDao.deletePokemons();
            movieDao.addPokemons(result);

            return null;
        }

    }

}