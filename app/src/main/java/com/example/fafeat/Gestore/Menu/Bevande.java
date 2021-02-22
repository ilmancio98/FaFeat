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

public class Bevande extends AppCompatActivity {

    ImageView back, add_antipasti;

    RecyclerView recyclerView;

    private MyAdapter adapter;

    SessionManagerGestore sessionManagerGestore;

    private ArrayList<PietanzaHelperClass> bevande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bevande);
        sessionManagerGestore = new SessionManagerGestore(Bevande.this, SessionManagerGestore.SESSION_USERSESSION);

        back = findViewById(R.id.back_icon);
        add_antipasti = findViewById(R.id.btn_add_antipasti);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onResume() {
        super.onResume();

        String username = sessionManagerGestore.getUsersDetailFromSession().get(SessionManagerGestore.KEY_USERNAME);
        DatabaseReference root = FirebaseDatabase.getInstance().getReference("Gestori/" + username + "/Ristoranti/Ristorante/Menu/Bevande");

        bevande = new ArrayList<>();
        adapter = new MyAdapter(this, bevande, "Bevande");
        recyclerView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PietanzaHelperClass pietanzaHelperClass = dataSnapshot.getValue(PietanzaHelperClass.class);
                    bevande.add(pietanzaHelperClass);
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
    public void callAddBevande(View view) {
        startActivity(new Intent(getApplicationContext(), AggiuntaBevande.class));
    }
}