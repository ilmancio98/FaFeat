package com.example.fafeat.Databases;

public class GestoreHelperClass {
    String _fullName,_usernamegestore, _email, _passwordgestore, _restaurant_name,  _restaurant_address, _restaurant_phone, _restaurant_img;

    public GestoreHelperClass() {
    }

    public GestoreHelperClass(String _fullName,String _usernamegestore, String _email,String _passwordgestore, String _restaurant_name, String _restaurant_address, String _restaurant_phone, String _restaurant_img) {
        this._fullName = _fullName;
        this._usernamegestore = _usernamegestore;
        this._email = _email;
        this._passwordgestore = _passwordgestore;
        this._restaurant_name = _restaurant_name;
        this._restaurant_address = _restaurant_address;
        this._restaurant_phone = _restaurant_phone;
        this._restaurant_img = _restaurant_img;
    }

    public String getFullName() {
        return _fullName;
    }

    public void setFullName(String _fullName) {
        this._fullName = _fullName;
    }

    public String getUsername() { return _usernamegestore; }

    public void setUsername(String _usernamegestore) { this._usernamegestore = _usernamegestore; }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }


    public String getPassword() {
        return _passwordgestore;
    }

    public void setPassword(String _passwordgestore) {
        this._passwordgestore = _passwordgestore;
    }

    public String get_restaurant_name() { return _restaurant_name; }

    public void set_restaurant_name(String _restaurant_name) { this._restaurant_name = _restaurant_name; }

    public String get_restaurant_address() { return _restaurant_address; }

    public void set_restaurant_address(String _restaurant_address) { this._restaurant_address = _restaurant_address; }

    public String get_restaurant_phone() { return _restaurant_phone; }

    public void set_restaurant_phone(String _restaurant_phone) { this._restaurant_phone = _restaurant_phone; }

    public String getImg() {
        return _restaurant_img;
    }

    public void setImg(String _restaurant_img) {
        this._fullName = _restaurant_img;
    }


}
