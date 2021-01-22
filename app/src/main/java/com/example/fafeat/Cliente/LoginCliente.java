package com.example.fafeat.Cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fafeat.Common.LoginSignup.ForgetPassword;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.example.fafeat.R;

public class LoginCliente extends AppCompatActivity {

    //Variables
    ImageView backBtn;
    Button  login, signup_btn;
    TextView titleText, slideText;
    FirebaseAuth firebaseAuth;

    //Get Data Variables
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_cliente);
        firebaseAuth = FirebaseAuth.getInstance();
        //Hooks for animation
        signup_btn = findViewById(R.id.signup_btn);
        backBtn = findViewById(R.id.signup_back_button);
        titleText = findViewById(R.id.signup_title_text);
        slideText = findViewById(R.id.signup_slide_text);

        login = findViewById(R.id.letTheUserLogIn);

        //Hooks for getting data
        username = findViewById(R.id.login_phone_number);
        password = findViewById(R.id.login_password);

    }

    public void LoginUser(View view){

        if ( !validateEmail() | !validatePassword()) {
            return;
        }
        else {
            isCliente();
        }

    }

    private void isCliente() {


        String clienteEnteredUsername= username.getEditText().getText().toString().trim();
        String clienteEnteredPassword= password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Clienti");
        Query checkClienti= reference.orderByChild("username").equalTo(clienteEnteredUsername);


        checkClienti.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordfromDB = dataSnapshot.child(clienteEnteredUsername).child("password").getValue(String.class);

                    if (passwordfromDB != null && passwordfromDB.equals(clienteEnteredPassword)){

                        Intent intent = new Intent(getApplicationContext(), VistaCliente.class);

                        startActivity(intent);

                    }
                    else{
                        password.setError("Password non corretta");
                        password.requestFocus();
                    }
                }
                else {
                    username.setError("Utente non registrato");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private boolean validateEmail() {
        String val = username.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            username.setError("Field can not be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }


    /*
    Function to call
    the Forget Password
    Screen
     */
    public void callForgetPassword(View view) {
        startActivity(new Intent(getApplicationContext(), ForgetPassword.class));
    }


    public void callSignUpFromLogin(View view) {
        startActivity(new Intent(getApplicationContext(), SignUpCliente.class));
        finish();
    }
}
