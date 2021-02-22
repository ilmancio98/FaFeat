package com.example.fafeat.Cliente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fafeat.Cliente.Ristorante.Order;
import com.example.fafeat.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AdapterOrderCli extends RecyclerView.Adapter<AdapterOrderCli.MyViewHolder> {

    private ArrayList<RequestCliente> orderList;
    private Context context;

    public AdapterOrderCli(Context context, ArrayList<RequestCliente> orderList){
        this.context = context;
        this.orderList = orderList;
    }


    @NonNull
    @Override
    public AdapterOrderCli.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemordervistclie, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RequestCliente requestCliente = this.orderList.get(position);
        holder.nome_rist.setText(requestCliente.getName_restaurant());
        holder.total.setText(requestCliente.getTotal());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextInputEditText nome_rist,total;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            nome_rist= itemView.findViewById(R.id.nome_locale);
            total = itemView.findViewById(R.id.totale);
        }
    }
}
