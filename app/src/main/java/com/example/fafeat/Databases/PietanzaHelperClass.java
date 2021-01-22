package com.example.fafeat.Databases;

public class PietanzaHelperClass {
    String _name_pietanza, _ingredienti_pietanza, _prezzo_pietanza, _img_pietanza;

    public PietanzaHelperClass() {
    }

    public PietanzaHelperClass(String _name_pietanza, String _ingredienti_pietanza, String _prezzo_pietanza, String _img_pietanza) {
        this._name_pietanza = _name_pietanza;
        this._ingredienti_pietanza = _ingredienti_pietanza;
        this._prezzo_pietanza = _prezzo_pietanza;
        this._img_pietanza = _img_pietanza;
    }

    public String get_name_pietanza() {
        return _name_pietanza;
    }

    public void set_name_pietanza(String _name_pietanza) {
        this._name_pietanza = _name_pietanza;
    }

    public String get_ingredienti_pietanza() {
        return _ingredienti_pietanza;
    }

    public void set_ingredienti_pietanza(String _ingredienti_pietanza) {
        this._ingredienti_pietanza = _ingredienti_pietanza;
    }

    public String get_prezzo_pietanza() {
        return _prezzo_pietanza;
    }

    public void set_prezzo_pietanza(String _prezzo_pietanza) {
        this._prezzo_pietanza = _prezzo_pietanza;
    }

    public String get_img_pietanza() {
        return _img_pietanza;
    }

    public void set_img_pietanza(String _img_pietanza) {
        this._img_pietanza = _img_pietanza;
    }
}
