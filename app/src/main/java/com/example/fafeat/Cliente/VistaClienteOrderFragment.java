package com.example.fafeat.Cliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fafeat.Cliente.Ristorante.Order;
import com.example.fafeat.Databases.SessionManager;
import com.example.fafeat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VistaClienteOrderFragment extends Fragment {

    RecyclerView recyclerView;
    private  AdapterOrderCli adapterOrderCli;

    private ArrayList<RequestCliente> ordini;
    SessionManager sessionManager;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_vista_cliente_order, container, false);
        recyclerView = root.findViewById(R.id.recyclerviewlistorder);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sessionManager = new SessionManager(getContext(), SessionManager.SESSION_USERSESSION);
        return  root;
    }

    @Override
    public void onResume(){
        super.onResume();

        String username = sessionManager.getUsersDetailFromSession().get(SessionManager.KEY_USERNAME);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Clienti/"+ username+"/Ordini");
        ordini = new ArrayList<>();
        adapterOrderCli = new AdapterOrderCli(getContext(),ordini);
        recyclerView.setAdapter(adapterOrderCli);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        RequestCliente requestCliente =  dataSnapshot.getValue(RequestCliente.class);
                        ordini.add(requestCliente);
                    }
                    adapterOrderCli.notifyDataSetChanged();
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}