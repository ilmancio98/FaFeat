package com.example.fafeat.Gestore.Menu;

import android.content.Context;
import android.content.Intent;
import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fafeat.Databases.PietanzaHelperClass;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final String categoria;
    private ArrayList<PietanzaHelperClass> mList;
    private Context context;


    String username;

    SessionManagerGestore sessionManagerGestore;

    FirebaseDatabase rootNode;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();





    public MyAdapter(Context context, ArrayList<PietanzaHelperClass> mList, String categoria){

        this.context = context;
        this.mList = mList;
        this.categoria = categoria;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false );
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder,  int position) {

        PietanzaHelperClass pietanzaData = this.mList.get(position);
        Glide.with(context).load(mList.get(position).get_img_pietanza()).into(holder.imageView);
        holder.textViewNome.setText(mList.get(position).get_name_pietanza());
        holder.textViewIngredienti.setText(mList.get(position).get_ingredienti_pietanza());
        holder.textViewPrezzo.setText(mList.get(position).get_prezzo_pietanza());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManagerGestore = new SessionManagerGestore(context, SessionManagerGestore.SESSION_USERSESSION);
                username = sessionManagerGestore.getUsersDetailFromSession().get(SessionManagerGestore.KEY_USERNAME);

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Gestori/" + username + "/Menu/"+ categoria).child(pietanzaData.get_name_pietanza());
                reference.removeValue();
                Toast.makeText(context, "Pietanza Eliminata!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(), ModificaPietanza.class);
                i.putExtra("_categoria", categoria);
                i.putExtra("_name_pietanza", pietanzaData.get_name_pietanza());
                i.putExtra("_ingredienti_pietanza", pietanzaData.get_ingredienti_pietanza());
                i.putExtra("_prezzo_pietanza", pietanzaData.get_prezzo_pietanza());
                i.putExtra("_img_pietanza", pietanzaData.get_img_pietanza());
                view.getContext().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView, edit,delete;
        TextView textViewNome, textViewIngredienti, textViewPrezzo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.m_image);
            textViewNome = itemView.findViewById(R.id.nome_pietanza);
            textViewIngredienti = itemView.findViewById(R.id.ingredienti_pietanza);
            textViewPrezzo = itemView.findViewById(R.id.prezzo_pietanza);

            edit=itemView.findViewById(R.id.editicon);
            delete=itemView.findViewById(R.id.deleteicon);

        }
    }
}
