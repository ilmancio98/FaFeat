package com.example.fafeat.Gestore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fafeat.Cliente.RequestGestore;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VistaGestoreOrderFragment extends Fragment {

    RecyclerView recyclerView;
    private AdapterOrder adapterOrder;
    SessionManagerGestore sessionManagerGestore;

    private ArrayList<RequestGestore> ordini;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_vista_gestore_order, container, false);
        sessionManagerGestore = new SessionManagerGestore(getContext(),SessionManagerGestore.SESSION_USERSESSION);
        recyclerView = root.findViewById(R.id.recyclerviewordini);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return root;
    }

    @Override
    public  void onResume(){
        super.onResume();

        String username = sessionManagerGestore.getUsersDetailFromSession().get(SessionManagerGestore.KEY_USERNAME);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Gestori/" + username + "/Ristoranti/Ristorante/Ordini");
        ordini = new ArrayList<>();
        adapterOrder = new AdapterOrder(getContext(),ordini);
        recyclerView.setAdapter(adapterOrder);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    RequestGestore requestGestore = dataSnapshot.getValue(RequestGestore.class);
                    ordini.add(requestGestore);
                }

                adapterOrder.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}