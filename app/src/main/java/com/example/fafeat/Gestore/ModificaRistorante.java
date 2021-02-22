package com.example.fafeat.Gestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fafeat.Databases.RestaurantHelperClass;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModificaRistorante extends AppCompatActivity {

    EditText et_nome_ristorante,et_indirizzo_ristorante,et_numero_ristorante;
    Button btnUpdate;
    String nome,indirizzo,numero, img;

    String username;

    SessionManagerGestore sessionManagerGestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_ristorante);

        et_nome_ristorante = findViewById(R.id.et_nome_ristorante);
        et_indirizzo_ristorante = findViewById(R.id.et_indirizzo_ristorante);
        et_numero_ristorante = findViewById(R.id.et_numero_ristorante);

        btnUpdate = findViewById(R.id.update_ristorante);

        Intent intent = getIntent();
        indirizzo = intent.getStringExtra("_restaurant_address");
        img = intent.getStringExtra("_restaurant_img");
        nome = intent.getStringExtra("_restaurant_name");
        numero = intent.getStringExtra("_restaurant_phone");

        et_nome_ristorante.setText(nome);
        et_indirizzo_ristorante.setText(indirizzo);
        et_numero_ristorante.setText(numero);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManagerGestore = new SessionManagerGestore(ModificaRistorante.this, SessionManagerGestore.SESSION_USERSESSION);
                username = sessionManagerGestore.getUsersDetailFromSession().get(SessionManagerGestore.KEY_USERNAME);

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Gestori/" + username + "/Ristorante");
                databaseReference.removeValue();
                String uNome,uIndirizzo,uNumero;

                uNome = et_nome_ristorante.getText().toString();
                uIndirizzo = et_indirizzo_ristorante.getText().toString();
                uNumero = et_numero_ristorante.getText().toString();

                RestaurantHelperClass restaurantHelperClass = new RestaurantHelperClass(uNome,uIndirizzo,uNumero, img, null);

                databaseReference.setValue(restaurantHelperClass);
                Toast.makeText(ModificaRistorante.this, "Ristorante Modificato", Toast.LENGTH_SHORT).show();

                finish();

            }
        });
    }
}