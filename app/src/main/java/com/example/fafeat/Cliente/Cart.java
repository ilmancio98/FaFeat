package com.example.fafeat.Cliente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fafeat.Cliente.Ristorante.Order;
import com.example.fafeat.Databases.DatabaseOrder;
import com.example.fafeat.Databases.SessionManager;
import com.example.fafeat.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests, orders;

    TextView txtTotalPrice;
    Button btnPlace;

    String gestore, nome_ristorante;

    SessionManager sessionManager;


    

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        sessionManager = new SessionManager(Cart.this, SessionManager.SESSION_USERSESSION);
        String username_cliente = sessionManager.getUsersDetailFromSession().get(SessionManager.KEY_USERNAME);

        

        Intent intent = getIntent();
        gestore  = intent.getStringExtra("gestore");
        nome_ristorante= intent.getStringExtra("ristorante");

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Gestori/"+ gestore + "/Ristoranti/Ristorante/Ordini");
        orders = database.getReference("Clienti/"+ username_cliente + "/Ordini");

        //List
        recyclerView = findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = findViewById(R.id.total);
        btnPlace = findViewById(R.id.btnPlaceOrder);

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                showAlertDialog();
            }
        });
        
        loadListFood();
    }

    private void showAlertDialog() {

        

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("Un ultima informazione!");
        alertDialog.setMessage("Tra quanto tempo arriverai al ristorante?");

        final EditText editTime = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        editTime.setLayoutParams(lp);
        alertDialog.setView(editTime);

        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                String cliente = sessionManager.getUsersDetailFromSession().get(SessionManager.KEY_FULLNAME);

                
                RequestGestore requestGestore = new RequestGestore(
                        cliente,
                        editTime.getText().toString(),
                        txtTotalPrice.getText().toString(),
                        cart
                );

                requests.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(requestGestore);



                RequestCliente order = new RequestCliente(
                        nome_ristorante,
                        editTime.getText().toString(),
                        txtTotalPrice.getText().toString(),
                        cart

                );

                orders.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(order);

                new DatabaseOrder(getBaseContext()).clearCart();
                Toast.makeText(Cart.this, "Grazie, Ordine Effettuato", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        alertDialog.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();


    }

    private void loadListFood() {
        cart = new DatabaseOrder(this).getCarts(gestore, nome_ristorante);
        adapter = new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);

        int total = 0;
        for (Order order:cart)
                total+=(Integer.parseInt(order.getPrezzo()))*(Integer.parseInt(order.getQuantity()));
        txtTotalPrice.setText(total+ "€");
    }
}