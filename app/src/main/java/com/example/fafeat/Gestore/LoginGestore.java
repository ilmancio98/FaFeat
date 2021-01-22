package com.example.fafeat.Gestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fafeat.Cliente.SignUpCliente;
import com.example.fafeat.Cliente.VistaCliente;
import com.example.fafeat.Common.LoginSignup.ForgetPassword;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginGestore extends AppCompatActivity {

    //Variables
    ImageView backBtn;
    Button login, signup_btn;
    TextView titleText, slideText;
    FirebaseAuth firebaseAuth;

    //Get Data Variables
    TextInputLayout usernamegestore, passwordgestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_gestore);
        firebaseAuth = FirebaseAuth.getInstance();
        //Hooks for animation
        signup_btn = findViewById(R.id.signup_btn);
        backBtn = findViewById(R.id.signup_back_button);
        titleText = findViewById(R.id.signup_title_text);
        slideText = findViewById(R.id.signup_slide_text);

        login = findViewById(R.id.letTheUserLogIn);

        //Hooks for getting data
        usernamegestore = findViewById(R.id.login_username_gestore);
        passwordgestore = findViewById(R.id.login_password_gestore);

    }

    public void LoginUser(View view){

        if ( !validateEmail() | !validatePassword()) {
            return;
        }
        else {
            isGestore();
        }

    }

    private void isGestore() {


        String gestoreEnteredUsername= usernamegestore.getEditText().getText().toString().trim();
        String gestoreEnteredPassword= passwordgestore.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Gestori");
        Query checkGestori= reference.orderByChild("username").equalTo(gestoreEnteredUsername);


        checkGestori.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    usernamegestore.setError(null);
                    usernamegestore.setErrorEnabled(false);

                    String passwordgestorefromDB = dataSnapshot.child(gestoreEnteredUsername).child("password").getValue(String.class);



                    if (passwordgestorefromDB != null && passwordgestorefromDB.equals(gestoreEnteredPassword)){


                        String _username = dataSnapshot.child(gestoreEnteredUsername).child("username").getValue(String.class);
                        String _password = dataSnapshot.child(gestoreEnteredUsername).child("password").getValue(String.class);

                        //Create a session

                        SessionManagerGestore sessionManagerGestore = new SessionManagerGestore(LoginGestore.this, SessionManagerGestore.SESSION_USERSESSION);
                        sessionManagerGestore.createLoginSession(_username, _password );

                        Intent intent = new Intent(getApplicationContext(), VistaGestore.class);

                        startActivity(intent);

                    }
                    else{
                        passwordgestore.setError("Password non corretta");
                        passwordgestore.requestFocus();
                    }
                }
                else {
                    usernamegestore.setError("Utente non registrato");
                    usernamegestore.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private boolean validateEmail() {
        String val = usernamegestore.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            usernamegestore.setError("Field can not be empty");
            return false;
        } else {
            usernamegestore.setError(null);
            usernamegestore.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword() {
        String val = passwordgestore.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            passwordgestore.setError("Field can not be empty");
            return false;
        } else {
            passwordgestore.setError(null);
            passwordgestore.setErrorEnabled(false);
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