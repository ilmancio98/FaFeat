package com.example.fafeat.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.fafeat.Cliente.Ristorante.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOrder extends SQLiteAssetHelper {
    private static final String DB_NAME = "FafeatDB.db";
    private static final  int DB_VER = 1;
    public DatabaseOrder(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Order> getCarts( String gestore, String nome_ristorante){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Gestore","NameRestaurant","ProductName","Quantity","Prezzo"};
        String sqlTable= "OrderDeatail";

        qb.setTables(sqlTable);
        qb.appendWhere("Gestore=? AND NameRestaurant=?");
        Cursor c = qb.query(db,sqlSelect,null, new String[]{String.valueOf(gestore),String.valueOf(nome_ristorante) },null,null,null);


        final List<Order> result = new ArrayList<>();
        if (c.moveToFirst())
        {
            do {
                result.add(new Order(c.getString(c.getColumnIndex("Gestore")),
                        c.getString(c.getColumnIndex("NameRestaurant")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Prezzo"))
                ));
            }while (c.moveToNext());
        }
        return result;
    }

    public  void addToCart(Order order){

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDeatail(Gestore, NameRestaurant, ProductName, Quantity, Prezzo) VALUES('%s', '%s','%s', '%s', '%s');",
                order.getGestore(),
                order.getRestaurantName(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrezzo());
        db.execSQL(query);
    }

    public  void clearCart(){

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDeatail");
        db.execSQL(query);
    }
}
