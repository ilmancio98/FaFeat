package com.example.fafeat.Cliente.Ristorante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.fafeat.Cliente.MyAdapterCliente;
import com.example.fafeat.Databases.PietanzaHelperClass;
import com.example.fafeat.Databases.RestaurantHelperClass;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AntipastiMenu extends AppCompatActivity {

    RecyclerView recyclerView;
    private MyAdapterPietanze adapterPietanze;

    String gestore, restaurant_name;

    private ArrayList<PietanzaHelperClass> pietanze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antipasti_menu);
        recyclerView = findViewById(R.id.recyclerviewlistant);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getApplicationContext()));
        Intent intent = getIntent();
        gestore  = intent.getStringExtra("gestore");
        restaurant_name = intent.getStringExtra("ristorante");

    }

    @Override
    public void onResume() {
        super.onResume();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Gestori/"+ gestore + "/Ristoranti/Ristorante");
        pietanze = new ArrayList<>();
        adapterPietanze = new MyAdapterPietanze(getApplicationContext(), pietanze, gestore, restaurant_name);
        recyclerView.setAdapter(adapterPietanze);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot dataSnapshotRist : dataSnapshot.child("Menu/Antipasti").getChildren()) {
                        PietanzaHelperClass pietanzaHelperClass = dataSnapshotRist.getValue(PietanzaHelperClass.class);
                        pietanze.add(pietanzaHelperClass);
                    }
                adapterPietanze.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}