package com.example.fafeat.Cliente;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fafeat.Databases.RestaurantHelperClass;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VistaClienteListRestFragment extends Fragment {

    RecyclerView recyclerView;
    private MyAdapterCliente adapterCliente;

    private ArrayList<RestaurantHelperClass> ristoranti;
    SessionManagerGestore sessionManagerGestore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_vista_cliente_list_rest, container, false);
        sessionManagerGestore = new SessionManagerGestore(getContext(), SessionManagerGestore.SESSION_USERSESSION);
        recyclerView = root.findViewById(R.id.recyclerviewlistrest);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Gestori");
        ristoranti = new ArrayList<>();
        adapterCliente = new MyAdapterCliente(getContext(), ristoranti);
        recyclerView.setAdapter(adapterCliente);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    for (DataSnapshot dataSnapshotRist : dataSnapshot.child("Ristorante").getChildren()) {
                        RestaurantHelperClass restaHelperClass = dataSnapshotRist.getValue(RestaurantHelperClass.class);
                        ristoranti.add(restaHelperClass);
                    }
                }
                adapterCliente.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}