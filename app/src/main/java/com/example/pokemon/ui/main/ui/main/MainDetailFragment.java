package com.example.pokemon.ui.main.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pokemon.R;
import com.example.pokemon.ui.main.MainFragment;
import com.example.pokemon.ui.main.PokeStats;
import com.example.pokemon.ui.main.Pokemon;

import java.util.ArrayList;

public class MainDetailFragment extends Fragment {

    private MainDetailViewModel mViewModel;
    private View view;
    private TextView tvName;
    private TextView tvEgg;
    private TextView tvWild;
    private TextView tvRaid;
    private TextView tvResearch;
    private TextView tvAttack;
    private TextView tvDefense;
    private TextView tvStamina;
    private ArrayList<PokeStats> list;



    public static MainDetailFragment newInstance() {
        return new MainDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_detail_fragment, container, false);

        Intent i = getActivity().getIntent();

        if (i != null) {

            Pokemon poke = (Pokemon) i.getSerializableExtra("pokemon");

            PokeStats pokeStats = (PokeStats) i.getSerializableExtra("pokestats");

            if (poke != null) {

                updateUi(poke, pokeStats);
            }
        }

        return view;
    }

    private void updateUi(Pokemon poke, PokeStats pokeStats) {
        Log.d("POKEMON", poke.toString());

        tvName = view.findViewById(R.id.tvNameD);
        tvEgg = view.findViewById(R.id.tvEgg);
        tvWild = view.findViewById(R.id.tvWild);
        tvRaid = view.findViewById(R.id.tvRaid);
        tvResearch = view.findViewById(R.id.tvResearch);





        tvName.setText(poke.getName());

        tvEgg.setText("EGG");
        if (poke.isFound_egg()){
        tvEgg.setTextColor(Color.parseColor("#008f39"));
        }else if (!poke.isFound_egg()){
            tvEgg.setTextColor(Color.parseColor("#cb3234"));
        }

        tvRaid.setText("RAID");
        if (poke.isFound_raid()){
            tvRaid.setTextColor(Color.parseColor("#008f39"));
        }else if (!poke.isFound_raid()){
            tvRaid.setTextColor(Color.parseColor("#cb3234"));
        }

        tvWild.setText("WILD");
        if (poke.isFound_wild()){
            tvWild.setTextColor(Color.parseColor("#008f39"));
        }else if (!poke.isFound_wild()){
            tvWild.setTextColor(Color.parseColor("#cb3234"));
        }

        tvResearch.setText("REWARD");
        if (poke.isFound_research()){
            tvResearch.setTextColor(Color.parseColor("#008f39"));
        }else if (!poke.isFound_research()){
            tvResearch.setTextColor(Color.parseColor("#cb3234"));
        }



        tvAttack = view.findViewById(R.id.tvAtt);
        tvDefense = view.findViewById(R.id.tvDef);
        tvStamina = view.findViewById(R.id.tvSta);


        tvAttack.setText("pokeStats.getAttack()");
        tvDefense.setText("pokeStats.getDefense()");
        tvStamina.setText("pokeStats.getStamina()");


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}