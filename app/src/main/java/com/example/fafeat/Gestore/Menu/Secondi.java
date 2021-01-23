package com.example.fafeat.Gestore.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fafeat.Gestore.VistaGestore;
import com.example.fafeat.Gestore.VistaGestoreMenuFragment;
import com.example.fafeat.R;

public class Secondi extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondi);

        back = findViewById(R.id.back_icon);
    }

    public void callVistaGestoreMenu(View view) {
        onBackPressed();
    }
    public void callAddSecondi(View view) {
        startActivity(new Intent(getApplicationContext(), AggiuntaSecondi.class));
    }
}