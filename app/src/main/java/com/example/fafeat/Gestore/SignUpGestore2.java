package com.example.fafeat.Gestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fafeat.Cliente.LoginCliente;
import com.example.fafeat.Common.LoginSignup.TypeOfLoginUser;
import com.example.fafeat.Databases.GestoreHelperClass;
import com.example.fafeat.Databases.RestaurantHelperClass;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.Gestore.Menu.Antipasti;
import com.example.fafeat.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class SignUpGestore2 extends AppCompatActivity {

    private ImageView restaurant_img;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    Button register;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    SessionManagerGestore sessionManagerGestore;

    TextInputLayout restaurant_name, restaurant_address, restaurant_phone;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_gestore2);
        restaurant_img = findViewById(R.id.restaurant_img);
        restaurant_name = findViewById(R.id.signup_gestore_risto_name);
        restaurant_address = findViewById(R.id.signup_gestore_risto_ind);
        restaurant_phone = findViewById(R.id.signup_gestore_risto_phone);
        register = findViewById(R.id.register_gestore_btn);
        sessionManagerGestore = new SessionManagerGestore(SignUpGestore2.this, SessionManagerGestore.SESSION_USERSESSION);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        register.setOnClickListener(view -> {

            if (!validateRestaurantName() | !validateRestaurantAddress() | !validateRestaurantPhone()| !validateImg()) {
                return;
            }

            uploadImg();
        });

        restaurant_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });

    }

    private void  createGestore(String img_path){

        String username = getIntent().getStringExtra("username");


        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Gestori/" +username+ "/Ristorante");

        String _restaurant_name = restaurant_name.getEditText().getText().toString();
        String _restaurant_address = restaurant_address.getEditText().getText().toString();
        String _restaurant_phone = restaurant_phone.getEditText().getText().toString();
        String _resturant_img = img_path;


        RestaurantHelperClass helperClass = new RestaurantHelperClass( _restaurant_name, _restaurant_address, _restaurant_phone, _resturant_img);

        reference.child(_restaurant_name).setValue(helperClass);

        Intent intent = new Intent(getApplicationContext(), TypeOfLoginUser.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.register_gestore_btn), "transition_login");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpGestore2.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    private void  choosePicture(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            restaurant_img.setImageURI(imageUri);
        }
    }

    private void uploadImg(){

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Caricamento immagine...");
        pd.show();

        final String[] img_path = {(UUID.randomUUID().toString())};

        StorageReference riversRef = FirebaseStorage.getInstance().getReference().child(img_path[0]);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                final Uri downloadUrl = uri;
                                img_path[0] = downloadUrl.toString();

                                pd.dismiss();
                                Snackbar.make(findViewById(android.R.id.content), "Image Uploaded", Snackbar.LENGTH_SHORT).show();
                                createGestore(img_path[0]);

                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Immagine non caricata", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>(){
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot){
                        double progressPercent = (100.00 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        pd.setMessage("Percentuale:"+(int) progressPercent + "%");
                    }
                });
    }

    private boolean validateRestaurantName() {
        String val = restaurant_name.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            restaurant_name.setError("Field can not be empty");
            return false;
        } else {
            restaurant_name.setError(null);
            restaurant_name.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateRestaurantAddress() {
        String val = restaurant_address.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            restaurant_address.setError("Field can not be empty");
            return false;
        } else {
            restaurant_address.setError(null);
            restaurant_address.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateRestaurantPhone() {
        String val = restaurant_phone.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            restaurant_phone.setError("Field can not be empty");
            return false;
        } else {
            restaurant_phone.setError(null);
            restaurant_phone.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateImg(){

        return imageUri != null;

    }

    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), LoginCliente.class));
        finish();
    }
}