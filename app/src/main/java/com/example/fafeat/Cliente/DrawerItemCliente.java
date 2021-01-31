package com.example.fafeat.Cliente;

import android.view.ViewGroup;


public abstract class DrawerItemCliente<T extends DrawerAdapterCliente.ViewHolder> {

    protected boolean isChecked;

    public abstract void bindViewHolder(T holder);

    public abstract T createViewHolder(ViewGroup parent);

    public DrawerItemCliente<T> setChecked(boolean isChecked){
        this.isChecked = isChecked;
        return this;
    }


    public boolean isChecked(){
        return isChecked;
    }

    public boolean isSelectable(){
        return true;
    }
}
