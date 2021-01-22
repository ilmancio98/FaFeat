package com.example.fafeat.Gestore;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fafeat.Cliente.LoginCliente;
import com.example.fafeat.Databases.SessionManager;
import com.example.fafeat.Databases.SessionManagerGestore;
import com.example.fafeat.R;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;

public class VistaGestore extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {
    private static final int POS_CLOSE = 0;
    private static final int POS_MY_RESTAURANT = 1;
    private static final int POS_ORDER = 2;
    private static final int POS_MENU = 3;
    private static final int POS_LOGOUT = 4;

    private  String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    SessionManagerGestore sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_gestore);

        sessionManager = new SessionManagerGestore(this, SessionManagerGestore.SESSION_USERSESSION);

        Toolbar toolbar_gestore = findViewById(R.id.toolbar_gestore);
        setSupportActionBar(toolbar_gestore);


        slidingRootNav = new SlidingRootNavBuilder(this)

                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
            .withToolbarMenuToggle(toolbar_gestore)
            .withMenuOpened(false)
            .withContentClickableWhenMenuOpened(false)
            .withSavedState(savedInstanceState)
            .withMenuLayout(R.layout.drawer_menu_gestore)
            .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_MY_RESTAURANT).setChecked(true),
                createItemFor(POS_ORDER),
                createItemFor(POS_MENU),
                new SpaceItem(260),
                createItemFor(POS_LOGOUT)
        ));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list_gestore);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_MY_RESTAURANT);

    }


    private  DrawerItem createItemFor(int position){
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.colorPrimary))
                .withTextTint(color(R.color.colorPrimary))
                .withSelectedIconTint(color(R.color.colorPrimary))
                .withSelectedTextTint(color(R.color.colorPrimaryDark));
    }

    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(this,res);
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitlesGestore);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.id_activityScreenIconsGestore);
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

        if (position == POS_MY_RESTAURANT){
            VistaGestoreRestFragment restFragment = new VistaGestoreRestFragment();
            transaction.replace(R.id.container_gestore, restFragment);
        }

        else if (position == POS_ORDER){
            VistaGestoreOrderFragment orderFragment = new VistaGestoreOrderFragment();
            transaction.replace(R.id.container_gestore, orderFragment);
        }

        else if (position == POS_MENU){
            VistaGestoreMenuFragment menuFragment = new VistaGestoreMenuFragment();
            transaction.replace(R.id.container_gestore, menuFragment);
        }

        else if (position == (POS_LOGOUT +1)) {

            sessionManager.logoutGestoreFromSession();
            startActivity(new Intent(getApplicationContext(), LoginGestore.class));
            finish();
        }

        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }

}