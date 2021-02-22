package com.example.fafeat.Cliente;

import com.example.fafeat.Cliente.Ristorante.Order;

import java.util.List;

public class RequestGestore {

    private  String name;
    private  String time;
    private  String total;
    private List<Order> foods;

    public RequestGestore() {
    }

    public RequestGestore(String name, String time, String total, List<Order> foods) {
        this.name = name;
        this.time = time;
        this.total = total;
        this.foods = foods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
