package com.example.fafeat.Cliente;

import com.example.fafeat.Cliente.Ristorante.Order;

import java.util.List;

public class RequestCliente {
    private  String name_restaurant;
    private  String time;
    private  String total;
    private List<Order> foods;

    public RequestCliente() {
    }

    public RequestCliente( String name_restaurant, String time, String total, List<Order> foods) {
        this.name_restaurant = name_restaurant;
        this.time = time;
        this.total = total;
        this.foods = foods;
    }

    public String getName_restaurant() {
        return name_restaurant;
    }

    public void setName_restaurant(String name_restaurant) {
        this.name_restaurant = name_restaurant;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
