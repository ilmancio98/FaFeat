package com.example.fafeat.Gestore;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.fafeat.R;

public  class SimpleItem extends DrawerItem<SimpleItem.ViewHolder> {

    private int selectedItemIconTint;
    private int selectedItemTextTint;

    private int normalItemIconTint;
    private int normalItemTextTint;

    private Drawable icon;
    private String title;

    public SimpleItem(Drawable icon, String title){
        this.icon = icon;
        this.title = title;
    }


    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_option_gestore, parent, false );
        return new ViewHolder(v);
    }



    @Override
    public void bindViewHolder(SimpleItem.ViewHolder holder) {

        holder.title.setText(title);
        holder.icon.setImageDrawable(icon);

        holder.title.setTextColor(isChecked ? selectedItemTextTint : normalItemTextTint);
        holder.icon.setColorFilter(isChecked ? selectedItemIconTint : normalItemIconTint);

    }

    public SimpleItem withSelectedIconTint(int SelectedItemIconTint){
        this.selectedItemIconTint = SelectedItemIconTint;
        return this;
    }

    public SimpleItem withSelectedTextTint(int SelectedItemTextTint){
        this.selectedItemTextTint = SelectedItemTextTint;
        return this;
    }

    public SimpleItem withIconTint(int NormalItemIconTint){
        this.normalItemIconTint = NormalItemIconTint;
        return this;
    }

    public SimpleItem withTextTint(int NormalItemTextTint){
        this.normalItemTextTint = NormalItemTextTint;
        return this;
    }

    static class ViewHolder extends DrawerAdapter.ViewHolder{

        private ImageView icon;
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
