package com.example.fafeat.Cliente;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


public class SpaceItemCliente extends  DrawerItemCliente<SpaceItemCliente.ViewHolder> {

    private int spaceDp;

    public SpaceItemCliente(int spaceDp){

        this.spaceDp = spaceDp;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent){
        Context c = parent.getContext();
        View view = new View(c);
        int height = (int) (c.getResources().getDisplayMetrics().density*spaceDp);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                height
        ));

        return new ViewHolder(view);
    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    @Override
    public void bindViewHolder(SpaceItemCliente.ViewHolder holder) {

    }


    static class ViewHolder extends DrawerAdapterCliente.ViewHolder{

        public  ViewHolder( View itemView){

            super(itemView);
        }
    }
}
