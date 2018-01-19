package com.example.simon.dycard.model;

/**
 * Created by s.sako.15 on 18/01/2018.
 */

public class Item {
    private String name;
    private boolean isChecked;

    public Item () {

    }


    public Item(String name, boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
