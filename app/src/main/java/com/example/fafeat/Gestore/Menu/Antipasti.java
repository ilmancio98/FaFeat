package com.example.fafeat.Gestore.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fafeat.Gestore.VistaGestore;
import com.example.fafeat.Gestore.VistaGestoreMenuFragment;
import com.example.fafeat.R;

public class Antipasti extends AppCompatActivity {

    ImageView back, add_antipasti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antipasti);

        back = findViewById(R.id.back_icon);
        add_antipasti = findViewById(R.id.btn_add_antipasti);
    }

    public void callVistaGestoreMenu(View view) {
        startActivity(new Intent(getApplicationContext(), VistaGestoreMenuFragment.class));
    }

    public void callAddAntipasti(View view) {
        startActivity(new Intent(getApplicationContext(), AggiuntaAntipasti.class));
    }

}