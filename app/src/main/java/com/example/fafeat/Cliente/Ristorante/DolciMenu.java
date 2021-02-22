package com.example.fafeat.Cliente.Ristorante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.fafeat.Databases.PietanzaHelperClass;
import com.example.fafeat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DolciMenu extends AppCompatActivity {

    RecyclerView recyclerView;
    private MyAdapterPietanze adapterPietanze;

    private ArrayList<PietanzaHelperClass> pietanze;
    String gestore, restaurant_name;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dolci_menu);

        recyclerView = findViewById(R.id.recyclerviewlistant4);
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

                    for (DataSnapshot dataSnapshotRist : dataSnapshot.child("Menu/Dolci").getChildren()) {
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