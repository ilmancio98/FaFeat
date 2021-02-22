package com.example.fafeat.Cliente;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.fafeat.R;

public class SimpleItemCliente extends DrawerItemCliente<SimpleItemCliente.ViewHolder> {

    private int selectedItemIconTint;
    private int selectedItemTextTint;

    private int normalItemIconTint;
    private int normalItemTextTint;

    private Drawable icon;
    private String title;

    public SimpleItemCliente(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    @Override
    public SimpleItemCliente.ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_option_cliente, parent, false );
        return new ViewHolder(v);
    }



    @Override
    public void bindViewHolder(SimpleItemCliente.ViewHolder holder) {

        holder.title.setText(title);
        holder.icon.setImageDrawable(icon);

        holder.title.setTextColor(isChecked ? selectedItemTextTint : normalItemTextTint);
        holder.icon.setColorFilter(isChecked ? selectedItemIconTint : normalItemIconTint);

    }

    public SimpleItemCliente withSelectedIconTint(int SelectedItemIconTint){
        this.selectedItemIconTint = SelectedItemIconTint;
        return this;
    }

    public SimpleItemCliente withSelectedTextTint(int SelectedItemTextTint){
        this.selectedItemTextTint = SelectedItemTextTint;
        return this;
    }

    public SimpleItemCliente withIconTint(int NormalItemIconTint){
        this.normalItemIconTint = NormalItemIconTint;
        return this;
    }

    public SimpleItemCliente withTextTint(int NormalItemTextTint){
        this.normalItemTextTint = NormalItemTextTint;
        return this;
    }

    static class ViewHolder extends DrawerAdapterCliente.ViewHolder{

        private ImageView icon;
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            icon =  itemView.findViewById(R.id.icon_cliente);
            title = itemView.findViewById(R.id.title_cliente);
        }
    }
}
