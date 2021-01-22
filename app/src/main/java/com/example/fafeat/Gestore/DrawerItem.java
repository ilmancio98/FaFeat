package com.example.fafeat.Gestore;

import android.view.ViewGroup;

public abstract class DrawerItem<T extends DrawerAdapter.ViewHolder> {

    protected boolean isChecked;

    public abstract void bindViewHolder(T holder);

    public abstract T createViewHolder(ViewGroup parent);

    public DrawerItem <T>setChecked(boolean isChecked){
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
