package com.example.pokemon.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.pokemon.R;


import com.example.pokemon.ui.main.ui.main.MainDetailFragment;

public class MainDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_detail_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainDetailFragment.newInstance())
                    .commitNow();
        }
    }
}