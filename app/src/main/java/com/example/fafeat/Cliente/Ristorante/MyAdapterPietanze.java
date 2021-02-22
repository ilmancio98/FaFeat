package com.example.fafeat.Cliente.Ristorante;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.fafeat.Cliente.MyAdapterCliente;
import com.example.fafeat.Databases.DatabaseOrder;
import com.example.fafeat.Databases.PietanzaHelperClass;
import com.example.fafeat.Databases.RestaurantHelperClass;
import com.example.fafeat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class MyAdapterPietanze extends  RecyclerView.Adapter<MyAdapterPietanze.MyViewHolder> {

    private ArrayList<PietanzaHelperClass> mList;
    private Context context;
    private String gestore;
    private String ristorante;


    public MyAdapterPietanze (Context context, ArrayList<PietanzaHelperClass> mList, String gestore, String ristorante){

        this.context = context;
        this.mList = mList;
        this.gestore = gestore;
        this.ristorante = ristorante;

    }

    @NonNull
    @Override

    public MyAdapterPietanze.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.pietanza_item,parent, false);
        return  new MyViewHolder(view);
    }

    @Override

    public  void  onBindViewHolder(@NonNull MyViewHolder holder, int position){


        PietanzaHelperClass pietanzaHelperClass = this.mList.get(position);
        Glide.with(context).load(pietanzaHelperClass.get_img_pietanza()).into(holder.img);
        holder.textViewNomePiet.setText(pietanzaHelperClass.get_name_pietanza());
        holder.textViewIngPiet.setText(pietanzaHelperClass.get_ingredienti_pietanza());
        holder.textViewPrezPiet.setText(pietanzaHelperClass.get_prezzo_pietanza());
        holder.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatabaseOrder(context).addToCart(new Order(
                        gestore,
                        ristorante,
                        pietanzaHelperClass.get_name_pietanza(),
                        holder.numberButton.getNumber(),
                        pietanzaHelperClass.get_prezzo_pietanza()

                ));

                Toast.makeText(context, "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
            }
        });

        /*holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), VistaMenuRistorante.class);
                v.getContext().startActivity(i);
            }
        });

         */
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public  static  class  MyViewHolder extends RecyclerView.ViewHolder{


        ImageView img;
        TextView textViewNomePiet,textViewIngPiet,textViewPrezPiet;
        CardView cardView;
        ElegantNumberButton numberButton;
        Button floatingActionButton;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            img = itemView.findViewById(R.id.imageViewClient);
            textViewNomePiet= itemView.findViewById(R.id.nome_pietanza_client);
            textViewIngPiet = itemView.findViewById(R.id.ingredienti_pietanza_client);
            textViewPrezPiet = itemView.findViewById(R.id.prezzo_pietanza_client);
            cardView = itemView.findViewById(R.id.cardView);
            numberButton = itemView.findViewById(R.id.number_button);
            floatingActionButton = itemView.findViewById(R.id.addToCartBtn);
        }
    }
}
