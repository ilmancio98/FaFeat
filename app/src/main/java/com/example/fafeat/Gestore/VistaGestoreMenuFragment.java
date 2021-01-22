package com.example.fafeat.Gestore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.fafeat.Gestore.Menu.Antipasti;
import com.example.fafeat.Gestore.Menu.Bevande;
import com.example.fafeat.Gestore.Menu.Contorni;
import com.example.fafeat.Gestore.Menu.Dolci;
import com.example.fafeat.Gestore.Menu.Panini;
import com.example.fafeat.Gestore.Menu.Pizze_Bianche;
import com.example.fafeat.Gestore.Menu.Pizze_Rosse;
import com.example.fafeat.Gestore.Menu.Primi;
import com.example.fafeat.Gestore.Menu.Secondi;
import com.example.fafeat.R;

public class VistaGestoreMenuFragment extends Fragment {

    ImageButton antipasti, primi, secondi, contorni, dolci, panini, pizze_rosse, pizze_bianche, bibite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_vista_gestore_menu, container, false);

        antipasti = root.findViewById(R.id.btn_antipasti);
        antipasti.setOnClickListener(v -> startActivity(new Intent(getActivity(), Antipasti.class)));
        primi = root.findViewById(R.id.btn_primi);
        primi.setOnClickListener(v -> startActivity(new Intent(getActivity(), Primi.class)));
        secondi = root.findViewById(R.id.btn_secondi);
        secondi.setOnClickListener(v -> startActivity(new Intent(getActivity(), Secondi.class)));
        contorni = root.findViewById(R.id.btn_contorni);
        contorni.setOnClickListener(v -> startActivity(new Intent(getActivity(), Contorni.class)));
        dolci = root.findViewById(R.id.btn_dolci);
        dolci.setOnClickListener(v -> startActivity(new Intent(getActivity(), Dolci.class)));
        panini = root.findViewById(R.id.btn_panini);
        panini.setOnClickListener(v -> startActivity(new Intent(getActivity(), Panini.class)));
        pizze_rosse = root.findViewById(R.id.btn_pizze_rosse);
        pizze_rosse.setOnClickListener(v -> startActivity(new Intent(getActivity(), Pizze_Rosse.class)));
        pizze_bianche = root.findViewById(R.id.btn_pizze_bianche);
        pizze_bianche.setOnClickListener(v -> startActivity(new Intent(getActivity(), Pizze_Bianche.class)));
        bibite = root.findViewById(R.id.btn_bibite);
        bibite.setOnClickListener(v -> startActivity(new Intent(getActivity(), Bevande.class)));

        return root;
    }

}