package com.example.fafeat.Gestore.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.fafeat.Databases.PietanzaHelperClass;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.Gestore.VistaGestore;
import com.example.fafeat.Gestore.VistaGestoreMenuFragment;
import com.example.fafeat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Antipasti extends AppCompatActivity {

    ImageView back, add_antipasti, img_anti;

    PietanzaHelperClass[] antipasti;

    SessionManagerGestore sessionManagerGestore;
    FirebaseDatabase rootNode;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManagerGestore = new SessionManagerGestore(Antipasti.this, SessionManagerGestore.SESSION_USERSESSION);
        setContentView(R.layout.activity_antipasti);

        back = findViewById(R.id.back_icon);
        add_antipasti = findViewById(R.id.btn_add_antipasti);
        img_anti = findViewById(R.id.imgAnti);
    }

    public void callVistaGestoreMenu(View view) {
        onBackPressed();
    }

    public void callAddAntipasti(View view) {
        startActivity(new Intent(getApplicationContext(), AggiuntaAntipasti.class));
    }

    public void loadantipasti(){

    }


}