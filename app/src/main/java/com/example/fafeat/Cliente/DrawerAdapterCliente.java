package com.example.fafeat.Cliente;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawerAdapterCliente extends RecyclerView.Adapter<DrawerAdapterCliente.ViewHolder> {

    private List<DrawerItemCliente> itemsCliente;
    private Map<Class<? extends DrawerItemCliente>, Integer> viewTypesCliente;
    private SparseArray<DrawerItemCliente> holderFactoriesCliente;

    private OnItemSelectedListener listener;

    public DrawerAdapterCliente (List<DrawerItemCliente> items){
        this.itemsCliente = items;
        this.viewTypesCliente = new HashMap<>();
        this.holderFactoriesCliente = new SparseArray<>();

        processViewTypes();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent , int viewType){
        ViewHolder holder = holderFactoriesCliente.get(viewType).createViewHolder(parent);
        holder.drawerAdapterCliente = this;
        return  holder;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(@NonNull DrawerAdapterCliente.ViewHolder holder, int position) {
        itemsCliente.get(position).bindViewHolder(holder);
    }

    @Override
    public int getItemCount() {
        return itemsCliente.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypesCliente.get(itemsCliente.get(position).getClass());
    }

    private void processViewTypes() {
        int type = 0;
        for (DrawerItemCliente item : itemsCliente){
            if (!viewTypesCliente.containsKey(item.getClass())){
                viewTypesCliente.put(item.getClass(), type);
                holderFactoriesCliente.put(type, item);
                type++;
            }
        }
    }

    public void  setSelected(int position){
        DrawerItemCliente newChecked = itemsCliente.get(position);
        if (!newChecked.isSelectable()){
            return;
        }

        for (int i=0; i < itemsCliente.size(); i++){
            DrawerItemCliente item = itemsCliente.get(i);
            if (item.isChecked()){
                item.setChecked(false);
                notifyItemChanged(i);
                break;
            }
        }

        newChecked.setChecked(true);
        notifyItemChanged(position);

        if (listener != null){
            listener.onItemSelected(position);
        }
    }

    public void setListener(OnItemSelectedListener listener){

        this.listener = listener;
    }



    static abstract class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private DrawerAdapterCliente drawerAdapterCliente;

        public ViewHolder( View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void  onClick(View view){
            drawerAdapterCliente.setSelected(getAdapterPosition());
        }

    }
    public interface OnItemSelectedListener{
        void onItemSelected(int position);
    }

}


