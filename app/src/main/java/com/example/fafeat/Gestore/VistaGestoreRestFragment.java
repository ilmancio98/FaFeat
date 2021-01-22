package com.example.fafeat.Gestore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fafeat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class VistaGestoreRestFragment extends Fragment {

    ImageView imageRest;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private  DatabaseReference img = databaseReference.child("img");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vista_gestore_rest, container, false);

        imageRest = root.findViewById(R.id.img_rest);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        img.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String reference = snapshot.getValue(String.class);
                Picasso.get().load(reference).into(imageRest);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}