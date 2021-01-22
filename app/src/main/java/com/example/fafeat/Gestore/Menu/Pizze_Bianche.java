package com.example.fafeat.Gestore.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fafeat.Gestore.VistaGestore;
import com.example.fafeat.Gestore.VistaGestoreMenuFragment;
import com.example.fafeat.R;

public class Pizze_Bianche extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizze__bianche);
        back = findViewById(R.id.back_icon);
    }

    public void callVistaGestoreMenu(View view) {
        startActivity(new Intent(getApplicationContext(), VistaGestoreMenuFragment.class));
    }

    public void callAddPizzeBianche(View view) {
        startActivity(new Intent(getApplicationContext(), AggiuntaPizzeBianche.class));
    }
}