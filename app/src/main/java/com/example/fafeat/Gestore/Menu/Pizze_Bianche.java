package com.example.fafeat.Gestore.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fafeat.Databases.PietanzaHelperClass;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pizze_Bianche extends AppCompatActivity {

    ImageView back, add_antipasti;

    RecyclerView recyclerView;

    private MyAdapter adapter;

    SessionManagerGestore sessionManagerGestore;



    private ArrayList<PietanzaHelperClass> pizze_bianche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizze__bianche);
        sessionManagerGestore = new SessionManagerGestore(Pizze_Bianche.this, SessionManagerGestore.SESSION_USERSESSION);
        back = findViewById(R.id.back_icon);
        String username = sessionManagerGestore.getUsersDetailFromSession().get(SessionManagerGestore.KEY_USERNAME);
        add_antipasti = findViewById(R.id.btn_add_antipasti);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference root = FirebaseDatabase.getInstance().getReference("Gestori/" + username + "/Menu/PizzeBianche");

        pizze_bianche = new ArrayList<>();
        adapter = new MyAdapter(this, pizze_bianche);
        recyclerView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PietanzaHelperClass pietanzaHelperClass = dataSnapshot.getValue(PietanzaHelperClass.class);
                    pizze_bianche.add(pietanzaHelperClass);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void callVistaGestoreMenu(View view) {
        onBackPressed();
    }
    public void callAddPizzeBianche(View view) {
        startActivity(new Intent(getApplicationContext(), AggiuntaPizzeBianche.class));
    }
}