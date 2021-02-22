package com.example.fafeat.Cliente;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.example.fafeat.Common.LoginSignup.Login;
import com.example.fafeat.Databases.SessionManager;
import com.example.fafeat.R;


import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;


import java.util.Arrays;
public class VistaCliente extends AppCompatActivity implements DrawerAdapterCliente.OnItemSelectedListener {

    private static final int POS_CLOSE = 0;
    private static final int POS_MY_PROFILE = 1;
    private static final int POS_ORDER = 2;
    private static final int POS_CARRELLO = 3;
    private static final int POS_LOGOUT = 4;

    private  String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_cliente);


        sessionManager = new SessionManager(this, SessionManager.SESSION_USERSESSION);

        Toolbar toolbar_cliente = findViewById(R.id.toolbar_cliente);
        setSupportActionBar(toolbar_cliente);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar_cliente)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu_cliente)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapterCliente adapterCliente = new DrawerAdapterCliente(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_MY_PROFILE).setChecked(true),
                createItemFor(POS_ORDER),
                createItemFor(POS_CARRELLO),
                new SpaceItemCliente(260),
                createItemFor(POS_LOGOUT)
        ));
        adapterCliente.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list_cliente);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapterCliente);

        adapterCliente.setSelected(POS_MY_PROFILE);

    }


    private DrawerItemCliente createItemFor(int position){
        return new SimpleItemCliente(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.colorPrimary))
                .withTextTint(color(R.color.colorPrimary))
                .withSelectedIconTint(color(R.color.colorPrimaryDark))
                .withSelectedTextTint(color(R.color.colorPrimaryDark));
    }

    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(this,res);
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitlesCliente);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.id_activityScreenIconsCliente);
        Drawable[] icons = new Drawable[typedArray.length()];
        for (int i = 0; i < typedArray.length(); i++){
            int id = typedArray.getResourceId(i, 0);
            if (id!=0){
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        typedArray.recycle();
        return icons;
    }

    @Override
    public void onBackPressed() {

        finish();
    }

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (position == POS_MY_PROFILE){
            VistaClienteFragment profFragment = new VistaClienteFragment();
            transaction.replace(R.id.container_cliente, profFragment);
        }

        else if (position == POS_ORDER){
            VistaClienteOrderFragment orderFragment = new VistaClienteOrderFragment();
            transaction.replace(R.id.container_cliente, orderFragment);
        }

        else if (position == POS_CARRELLO){
            VistaClienteListRestFragment cartFragment = new VistaClienteListRestFragment();
            transaction.replace(R.id.container_cliente, cartFragment);
        }

        else if (position == (POS_LOGOUT +1)) {

            sessionManager.logoutClienteFromSession();
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }

        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }


}