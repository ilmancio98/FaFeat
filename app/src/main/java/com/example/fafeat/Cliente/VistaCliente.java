package com.example.fafeat.Cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fafeat.Databases.SessionManager;
import com.example.fafeat.Gestore.SignUpGestore;
import com.example.fafeat.Gestore.SignUpGestore2;
import com.example.fafeat.R;
import com.google.firebase.auth.FirebaseAuth;


import java.util.HashMap;

public class VistaCliente extends AppCompatActivity {

    SessionManager sessionManager;

    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_cliente);


        sessionManager = new SessionManager(this, SessionManager.SESSION_USERSESSION);
        logout = findViewById(R.id.button_logout);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VistaClienteFragment()).commit();


    }



    public void logoutTheUserFromSession(View view){
        sessionManager.logoutClienteFromSession();
        startActivity(new Intent(getApplicationContext(), LoginCliente.class));
        finish();
    }


}