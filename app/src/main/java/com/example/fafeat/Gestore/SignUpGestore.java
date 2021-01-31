package com.example.fafeat.Gestore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fafeat.Cliente.LoginCliente;
import com.example.fafeat.Databases.GestoreHelperClass;
import com.example.fafeat.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SignUpGestore extends AppCompatActivity {

    //Variables
    ImageView backBtn;
    Button next, login;
    TextView titleText, slideText;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    FirebaseDatabase rootNode;
    DatabaseReference reference;




    //Get Data Variables
    TextInputLayout fullName, usernamegestore, email, passwordgestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_gestore);
        //Hooks for animation
        backBtn = findViewById(R.id.signup_back_button);
        next= findViewById(R.id.next_btn);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);
        slideText = findViewById(R.id.signup_slide_text);

        //Hooks for getting data
        fullName = findViewById(R.id.signup_fullname);
        email = findViewById(R.id.signup_email);
        usernamegestore = findViewById(R.id.signup_username);
        passwordgestore = findViewById(R.id.signup_password);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

    }

    public void callSignUpGestore2 (View view) {


            if (!validateFullName() | !validateUsername() | !validateEmail() | !validatePassword()) {
                return;
            }

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Gestori");

        String _name = fullName.getEditText().getText().toString();
        String _usernamegestore = usernamegestore.getEditText().getText().toString();
        String _email = email.getEditText().getText().toString();
        String _passwordgestore = passwordgestore.getEditText().getText().toString();

        GestoreHelperClass gestoreHelperClass = new GestoreHelperClass(_name,_usernamegestore,_email,_passwordgestore);

        reference.child(_usernamegestore).setValue(gestoreHelperClass);


        Intent intent = new Intent(getApplicationContext(), SignUpGestore2.class);

        intent.putExtra("username", _usernamegestore);


        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.next_btn), "transition_next_btn");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpGestore.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }



    /*
    Validation Functions
     */

    private boolean validateFullName() {
        String val = fullName.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullName.setError("Field can not be empty");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateUsername() {
        String val = usernamegestore.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";

        if (val.isEmpty()) {
            usernamegestore.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            usernamegestore.setError("Username is too large!");
            return false;
        } else if (!val.matches(checkspaces)) {
            usernamegestore.setError("No White spaces are allowed!");
            return false;
        } else {
            usernamegestore.setError(null);
            usernamegestore.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword() {
        String val = passwordgestore.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            passwordgestore.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            passwordgestore.setError("Password should contain 4 characters!");
            return false;
        } else {
            passwordgestore.setError(null);
            passwordgestore.setErrorEnabled(false);
            return true;
        }

    }


    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), LoginGestore.class));
        finish();
    }
}