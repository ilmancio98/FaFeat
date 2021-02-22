package com.example.fafeat.Cliente.Ristorante;

public class Order {
    private  String Gestore;
    private  String RestaurantName;
    private String ProductName;
    private String Quantity;
    private String Prezzo;

    public  Order (){

    }

    public Order(String gestore, String restaurantName, String productName, String quantity, String prezzo) {
        Gestore = gestore;
        RestaurantName = restaurantName;
        ProductName = productName;
        Quantity = quantity;
        Prezzo = prezzo;
    }

    public String getGestore() {
        return Gestore;
    }

    public void setGestore(String gestore) {
        Gestore = gestore;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(String prezzo) {
        Prezzo = prezzo;
    }
}


