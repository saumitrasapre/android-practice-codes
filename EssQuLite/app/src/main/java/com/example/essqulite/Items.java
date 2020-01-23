package com.example.essqulite;

public class Items {

    private int _id;
    private String _itemName;

    public  Items()
    {

    }

    public Items(String itemName) {
        this._itemName = itemName;
    }


    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_itemName(String _itemName) {
        this._itemName = _itemName;
    }

    public int get_id() {
        return _id;
    }

    public String get_itemName() {
        return _itemName;
    }
}
