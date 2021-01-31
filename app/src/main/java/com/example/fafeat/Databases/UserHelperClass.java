package com.example.fafeat.Databases;


public class UserHelperClass {

    String _fullName,_username, _email, _password;

    public UserHelperClass() {
    }

    public UserHelperClass(String _fullName,String _username, String _email,String _password) {
        this._fullName = _fullName;
        this._username = _username;
        this._email = _email;
        this._password = _password;
    }

    public String getFullName() {
        return _fullName;
    }

    public void setFullName(String _fullName) {
        this._fullName = _fullName;
    }

    public String getUsername() { return _username; }

    public void setUsername(String _username) { this._username = _username; }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }


    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

}