package com.example.fafeat.Gestore.Menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fafeat.Databases.PietanzaHelperClass;
import com.example.fafeat.Databases.SessionManager;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class AggiuntaPizzeBianche extends AppCompatActivity {

    ImageView backBtn;
    Button add_antipasto;
    private ImageView img_antipasto;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    SessionManager sessionManagerGestore;


    FirebaseDatabase rootNode;
    DatabaseReference reference;



    //Get Data Variables
    TextInputLayout nome_antipasto, ingredienti_antipasto, prezzo_antipasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sessionManagerGestore = new SessionManager(this, SessionManagerGestore.SESSION_USERSESSION);
        setContentView(R.layout.activity_aggiunta_pizze_bianche);

        backBtn = findViewById(R.id.signup_back_button);
        add_antipasto = findViewById(R.id.add_antipasto);
        img_antipasto = findViewById(R.id.img_antipasto);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();



        //Hooks for getting data
        nome_antipasto = findViewById(R.id.nome_antipasto);
        prezzo_antipasto = findViewById(R.id.prezzo_antipasto);
        ingredienti_antipasto = findViewById(R.id.ingredienti_antipasto);


        img_antipasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });

        add_antipasto.setOnClickListener(view -> {

            if (!validateNomeAntipasto() | !validateIngredienti() | !validatePrezzo()) {
                return;
            }

            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Menu/PizzeBianche");

            String _name_pietanza= nome_antipasto.getEditText().getText().toString();
            String _ingredienti_pietanza= ingredienti_antipasto.getEditText().getText().toString();
            String _prezzo_pietanza= prezzo_antipasto.getEditText().getText().toString();
            String _img_antipasto = img_antipasto.toString();

            PietanzaHelperClass helperClass = new PietanzaHelperClass(_name_pietanza, _ingredienti_pietanza, _prezzo_pietanza, _img_antipasto);

            reference.child(_name_pietanza).setValue(helperClass);

            Intent intent = new Intent(getApplicationContext(), Pizze_Bianche.class);
            startActivity(intent);


        });
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
            img_antipasto.setImageURI(imageUri);
            uploadImg();
        }
    }

    private void uploadImg(){

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Caricamento immagine...");
        pd.show();

        final  String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/"+randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content), "Image Uploaded", Snackbar.LENGTH_SHORT).show();
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






    /*
    Validation Functions
     */

    private boolean validateNomeAntipasto() {
        String val = nome_antipasto.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            nome_antipasto.setError("Field can not be empty");
            return false;
        } else {
            nome_antipasto.setError(null);
            nome_antipasto.setErrorEnabled(false);
            return true;
        }

    }
    private boolean validateIngredienti() {
        String val = ingredienti_antipasto.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            ingredienti_antipasto.setError("Field can not be empty");
            return false;
        } else {
            ingredienti_antipasto.setError(null);
            ingredienti_antipasto.setErrorEnabled(false);
            return true;
        }

    }
    private boolean validatePrezzo() {
        String val = prezzo_antipasto.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            prezzo_antipasto.setError("Field can not be empty");
            return false;
        } else {
            prezzo_antipasto.setError(null);
            prezzo_antipasto.setErrorEnabled(false);
            return true;
        }

    }
}