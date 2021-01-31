package com.example.fafeat.Gestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fafeat.Databases.RestaurantHelperClass;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapterGestore extends RecyclerView.Adapter<MyAdapterGestore.MyViewHolder> {

    private ArrayList<RestaurantHelperClass> mList;
    private Context context;

    String username;
    SessionManagerGestore sessionManagerGestore;

    FirebaseDatabase rootNode;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    public  MyAdapterGestore(Context context, ArrayList<RestaurantHelperClass> mList){

        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyAdapterGestore.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemgestore, parent, false );
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RestaurantHelperClass restaurantData = this.mList.get(position);
        Glide.with(context).load(mList.get(position).get_restaurant_img()).into(holder.img);
        holder.textViewNomeRis.setText(mList.get(position).get_restaurant_name());
        holder.textViewIndRis.setText(mList.get(position).get_restaurant_address());
        holder.textViewNumRis.setText(mList.get(position).get_restaurant_phone());


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class MyViewHolder extends  RecyclerView.ViewHolder {

        ImageView img;
        TextView textViewNomeRis,textViewIndRis,textViewNumRis;
        Button edit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_ristorante);
            textViewNomeRis= itemView.findViewById(R.id.nome_ristorante);
            textViewIndRis = itemView.findViewById(R.id.indirizzo_ristorante);
            textViewNumRis = itemView.findViewById(R.id.numero_ristorante);

            edit = itemView.findViewById(R.id.upd_ristorante);

        }
    }
}
