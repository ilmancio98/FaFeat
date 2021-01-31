package com.example.fafeat.Gestore.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fafeat.Databases.PietanzaHelperClass;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ModificaPietanza extends AppCompatActivity {

    EditText et_nome_pietanza,et_ingredienti_pietanza,et_prezzo_pietanza;
    Button btnUpdate;
    String nome,ingredienti,prezzo, img;


    String username, categoria;

    SessionManagerGestore sessionManagerGestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_pietanza);

        et_nome_pietanza = findViewById(R.id.et_nome_pietanza);
        et_ingredienti_pietanza = findViewById(R.id.et_ingredienti_pietanza);
        et_prezzo_pietanza = findViewById(R.id.et_prezzo_pietanza);

        btnUpdate = findViewById(R.id.upd_pietanza);

        Intent intent= getIntent();
        categoria = intent.getStringExtra("_categoria");
        nome = intent.getStringExtra("_name_pietanza");
        ingredienti = intent.getStringExtra("_ingredienti_pietanza");
        prezzo = intent.getStringExtra("_prezzo_pietanza");
        img = intent.getStringExtra("_img_pietanza");

        et_nome_pietanza.setText(nome);
        et_ingredienti_pietanza.setText(ingredienti);
        et_prezzo_pietanza.setText(prezzo);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManagerGestore = new SessionManagerGestore(ModificaPietanza.this, SessionManagerGestore.SESSION_USERSESSION);
                username = sessionManagerGestore.getUsersDetailFromSession().get(SessionManagerGestore.KEY_USERNAME);

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Gestori/" + username + "/Menu/"+ categoria).child(nome);
                databaseReference.removeValue();
                String uNome,uIngredienti,uPrezzo;

                uNome = et_nome_pietanza.getText().toString();
                uIngredienti = et_ingredienti_pietanza.getText().toString();
                uPrezzo = et_prezzo_pietanza.getText().toString();

                PietanzaHelperClass pietanzaHelperClass = new PietanzaHelperClass(uNome,uIngredienti,uPrezzo,img);

                databaseReference.setValue(pietanzaHelperClass);
                Toast.makeText(ModificaPietanza.this, "Pietanza Modificata", Toast.LENGTH_SHORT).show();

                finish();

            }
        });
    }
}