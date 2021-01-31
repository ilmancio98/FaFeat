package com.example.fafeat.Databases;

public class RestaurantHelperClass {
    String _restaurant_name, _restaurant_address, _restaurant_phone, _restaurant_img;

    public RestaurantHelperClass() {
    }

    public RestaurantHelperClass(String _restaurant_name, String _restaurant_address, String _restaurant_phone, String _restaurant_img) {
        this._restaurant_name = _restaurant_name;
        this._restaurant_address = _restaurant_address;
        this._restaurant_phone = _restaurant_phone;
        this._restaurant_img = _restaurant_img;
    }


    public String get_restaurant_name() {
        return _restaurant_name;
    }

    public void set_restaurant_name(String _restaurant_name) {
        this._restaurant_name = _restaurant_name;
    }

    public String get_restaurant_address() {
        return _restaurant_address;
    }

    public void set_restaurant_address(String _restaurant_address) {
        this._restaurant_address = _restaurant_address;
    }

    public String get_restaurant_phone() {
        return _restaurant_phone;
    }

    public void set_restaurant_phone(String _restaurant_phone) {
        this._restaurant_phone = _restaurant_phone;
    }

    public String get_restaurant_img() {
        return _restaurant_img;
    }

    public void set_restaurant_img(String _restaurant_img) {
        this._restaurant_img = _restaurant_img;
    }

}
