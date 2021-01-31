package com.example.fafeat.Cliente;

import android.graphics.drawable.ScaleDrawable;
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
import android.widget.TextView;

import com.example.fafeat.Databases.RestaurantHelperClass;
import com.example.fafeat.Databases.SessionManager;
import com.example.fafeat.Databases.UserHelperClass;
import com.example.fafeat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class VistaClienteFragment extends Fragment {


    TextView textViewNomeCliente,textViewUsernameCliente,textViewEmailCliente;
    Button edit;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    SessionManager sessionManager;

    private ArrayList<UserHelperClass> cliente;
    private  static final String CLIENTI = "Clienti";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vista_cliente, container, false);
        sessionManager = new SessionManager(getContext(), SessionManager.SESSION_USERSESSION);

        textViewNomeCliente = root.findViewById(R.id.nome_Cliente);
        textViewUsernameCliente = root.findViewById(R.id.username_cliente);
        textViewEmailCliente = root.findViewById(R.id.email_cliente);

        edit = root.findViewById(R.id.upd_cliente);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(CLIENTI);


        return root;
    }

    @Override
    public  void onResume() {
        super.onResume();
        String username = sessionManager.getUsersDetailFromSession().get(SessionManager.KEY_USERNAME);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    if (ds.child("username").getValue().equals(username)){
                        textViewNomeCliente.setText(ds.child("fullName").getValue(String.class));
                        textViewUsernameCliente.setText(ds.child("username").getValue(String.class));
                        textViewEmailCliente.setText(ds.child("email").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}