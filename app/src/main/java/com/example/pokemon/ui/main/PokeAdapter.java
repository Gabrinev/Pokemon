package com.example.pokemon.ui.main;

import android.content.Context;
import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokemon.R;

import java.util.List;

public class PokeAdapter extends ArrayAdapter<Pokemon> {
    public PokeAdapter(Context context, int resource, List<Pokemon> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtenim l'objecte en la possició corresponent
        Pokemon movie = getItem(position);
        Log.w("XXXX", movie.toString());

        // Mirem a veure si la View s'està reutilitzant, si no es així "inflem" la View
        // https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView#row-view-recycling
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_pokes_row, parent, false);
        }

        // Unim el codi en les Views del Layout
        TextView tvName = convertView.findViewById(R.id.tvName);


        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        tvName.setText(movie.getName());

        // Retornem la View replena per a mostrar-la
        return convertView;
    }

}
