package com.example.fafeat.Databases;

public class GestoreHelperClass {
    String _fullName,_usernamegestore, _email, _passwordgestore;

    public GestoreHelperClass() {
    }

    public GestoreHelperClass(String _fullName,String _usernamegestore, String _email,String _passwordgestore) {
        this._fullName = _fullName;
        this._usernamegestore = _usernamegestore;
        this._email = _email;
        this._passwordgestore = _passwordgestore;
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

}
