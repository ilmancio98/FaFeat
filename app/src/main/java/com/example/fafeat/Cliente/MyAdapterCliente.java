package com.example.fafeat.Cliente;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fafeat.Cliente.Ristorante.VistaMenuRistorante;
import com.example.fafeat.Databases.RestaurantHelperClass;
import com.example.fafeat.R;

import java.util.ArrayList;

public class MyAdapterCliente extends RecyclerView.Adapter<MyAdapterCliente.MyViewHolder> {

    private ArrayList<RestaurantHelperClass> mList;
    private Context context;


    public MyAdapterCliente (Context context, ArrayList<RestaurantHelperClass> mList){

        this.context = context;
        this.mList = mList;

    }

    @NonNull
    @Override

    public MyAdapterCliente.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.itemristvistclie,parent, false);
        return  new MyViewHolder(view);
    }

    @Override

    public  void  onBindViewHolder(@NonNull MyViewHolder holder, int position){

        RestaurantHelperClass restaurantData = this.mList.get(position);
        Glide.with(context).load(restaurantData.get_restaurant_img()).into(holder.img);
        holder.textViewNomeRis.setText(restaurantData.get_restaurant_name());
        holder.textViewIndRis.setText(restaurantData.get_restaurant_address());
        holder.textViewNumRis.setText(restaurantData.get_restaurant_phone());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), VistaMenuRistorante.class);
                i.putExtra("gestore", restaurantData.get_gestore());
                i.putExtra("ristorante",restaurantData.get_restaurant_name());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public  static  class  MyViewHolder extends RecyclerView.ViewHolder{


        ImageView img;
        TextView textViewNomeRis,textViewIndRis,textViewNumRis;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            img = itemView.findViewById(R.id.image_locale);
            textViewNomeRis= itemView.findViewById(R.id.nome_locale);
            textViewIndRis = itemView.findViewById(R.id.indirizzo_locale);
            textViewNumRis = itemView.findViewById(R.id.numero_locale);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
