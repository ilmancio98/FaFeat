package com.example.fafeat.Gestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fafeat.Cliente.RequestGestore;
import com.example.fafeat.Cliente.Ristorante.Order;
import com.example.fafeat.R;

import java.util.ArrayList;

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.MyViewHolder> {

    private ArrayList<RequestGestore> orderList;
    private Context context;

    public AdapterOrder (Context context, ArrayList<RequestGestore>orderList){

        this.context = context;
        this.orderList = orderList;
    }


    @NonNull
    @Override
    public AdapterOrder.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemorder, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RequestGestore requestGestore = this.orderList.get(position);
        holder.nome_cli.setText(requestGestore.getName());
        holder.time.setText(requestGestore.getTime());
        StringBuilder foodBuilder = new StringBuilder();
        for ( Order food : requestGestore.getFoods()){
            foodBuilder.append(food.getProductName()).append(" ").append(food.getQuantity()).append("\n");
        }
        holder.ordine.setText(foodBuilder.toString());
        holder.totale.setText(requestGestore.getTotal());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome_cli, time, ordine, totale;

        public MyViewHolder(View itemView){
            super(itemView);

            nome_cli= itemView.findViewById(R.id.client_name);
            time = itemView.findViewById(R.id.time_arrive);
            ordine = itemView.findViewById(R.id.Food);
            totale = itemView.findViewById(R.id.total_order);
        }
    }
}
