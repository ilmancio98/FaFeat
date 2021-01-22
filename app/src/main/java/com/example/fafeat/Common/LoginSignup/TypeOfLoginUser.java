package com.example.fafeat.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.example.fafeat.Cliente.LoginCliente;
import com.example.fafeat.Gestore.LoginGestore;
import com.example.fafeat.R;

public class TypeOfLoginUser extends AppCompatActivity {

    Button cliente, gestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_login_user);

        cliente = findViewById(R.id.cliente_btn);
        gestore= findViewById(R.id.gestore_btn);

    }

    public void callRegistrationCliente(View view) {

        Intent intent = new Intent(getApplicationContext(), LoginCliente.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.cliente_btn), "transition_cliente");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(TypeOfLoginUser.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }


    public void callRegistrationGestore(View view) {

        Intent intent = new Intent(getApplicationContext(), LoginGestore.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.gestore_btn), "transition_gestore");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(TypeOfLoginUser.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }
}