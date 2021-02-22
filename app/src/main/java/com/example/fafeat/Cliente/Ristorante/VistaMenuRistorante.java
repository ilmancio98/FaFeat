package com.example.fafeat.Cliente.Ristorante;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.fafeat.Cliente.Cart;
import com.example.fafeat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class VistaMenuRistorante extends AppCompatActivity {

    ImageButton antipasti, primi, secondi, contorni, dolci, panini, pizze_rosse, pizze_bianche, bibite;
    Button btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_menu_ristorante);
        Intent intent = getIntent();
        String gestore  = intent.getStringExtra("gestore");
        String nome_ristorante = intent.getStringExtra("ristorante");
        btnCart = findViewById(R.id.btnCart);

        btnCart.setOnClickListener((View)->{
            Intent cartIntent = new Intent(VistaMenuRistorante.this, Cart.class);
            cartIntent.putExtra("gestore", gestore);
            cartIntent.putExtra("ristorante", nome_ristorante);
            startActivity(cartIntent);
        });

        antipasti = findViewById(R.id.btn_antipasti_menu);
        antipasti.setOnClickListener(v ->{
            Intent intent1 = new Intent(getApplicationContext(), AntipastiMenu.class);
            intent1.putExtra("gestore", gestore);
            intent1.putExtra("ristorante", nome_ristorante);
            startActivity(intent1);
        });
        primi = findViewById(R.id.btn_primi_menu);
        primi.setOnClickListener(v -> {
            Intent intent2 = new Intent(getApplicationContext(), PrimiMenu.class);
            intent2.putExtra("gestore", gestore);
            intent2.putExtra("ristorante", nome_ristorante);
            startActivity(intent2);
        });
        secondi = findViewById(R.id.btn_secondi_menu);
        secondi.setOnClickListener(v -> {
            Intent intent3 = new Intent(getApplicationContext(), SecondiMenu.class);
            intent3.putExtra("gestore", gestore);
            intent3.putExtra("ristorante", nome_ristorante);
            startActivity(intent3);
        });
        contorni = findViewById(R.id.btn_contorni_menu);
        contorni.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), ContorniMenu.class);
            intent1.putExtra("gestore", gestore);
            intent1.putExtra("ristorante", nome_ristorante);
            startActivity(intent1);
        });
        dolci = findViewById(R.id.btn_dolci_menu);
        dolci.setOnClickListener(v ->{
            Intent intent1 = new Intent(getApplicationContext(), DolciMenu.class);
            intent1.putExtra("gestore", gestore);
            intent1.putExtra("ristorante", nome_ristorante);
            startActivity(intent1);
        });
        panini = findViewById(R.id.btn_panini_menu);
        panini.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), PaniniMenu.class);
            intent1.putExtra("gestore", gestore);
            intent1.putExtra("ristorante", nome_ristorante);
            startActivity(intent1);
        });
        pizze_rosse = findViewById(R.id.btn_pizze_rosse_menu);
        pizze_rosse.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), Pizze_RosseMenu.class);
            intent1.putExtra("gestore", gestore);
            intent1.putExtra("ristorante", nome_ristorante);
            startActivity(intent1);
        });
        pizze_bianche = findViewById(R.id.btn_pizze_bianche_menu);
        pizze_bianche.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), Pizze_BiancheMenu.class);
            intent1.putExtra("gestore", gestore);
            intent1.putExtra("ristorante", nome_ristorante);
            startActivity(intent1);
        });
        bibite = findViewById(R.id.btn_bibite_menu);
        bibite.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), BevandeMenu.class);
            intent1.putExtra("gestore", gestore);
            intent1.putExtra("ristorante", nome_ristorante);
            startActivity(intent1);
        });
    }

}