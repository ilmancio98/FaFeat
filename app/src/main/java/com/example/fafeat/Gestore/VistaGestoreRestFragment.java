package com.example.fafeat.Gestore;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fafeat.Databases.CategoriaHelperClass;
import com.example.fafeat.Databases.GestoreHelperClass;
import com.example.fafeat.Databases.PietanzaHelperClass;
import com.example.fafeat.Databases.RestaurantHelperClass;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.Gestore.Menu.Antipasti;
import com.example.fafeat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class VistaGestoreRestFragment extends Fragment {


    RecyclerView recyclerView;

    private MyAdapterGestore adapterGestore;

    SessionManagerGestore sessionManagerGestore;

    private ArrayList<RestaurantHelperClass> ristoranti;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vista_gestore_rest, container, false);
        sessionManagerGestore = new SessionManagerGestore(getContext(), SessionManagerGestore.SESSION_USERSESSION);
        recyclerView = root.findViewById(R.id.recyclerviewgestore);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        String username = sessionManagerGestore.getUsersDetailFromSession().get(SessionManagerGestore.KEY_USERNAME);
        DatabaseReference root = FirebaseDatabase.getInstance().getReference("Gestori/" + username + "/Ristoranti");
        ristoranti = new ArrayList<>();
        adapterGestore = new MyAdapterGestore(getContext(),ristoranti);
        recyclerView.setAdapter(adapterGestore);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshotrest : dataSnapshot.getChildren()){
                    RestaurantHelperClass restaHelperClass = dataSnapshotrest.getValue(RestaurantHelperClass.class);
                    ristoranti.add(restaHelperClass);
                }

                adapterGestore.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}