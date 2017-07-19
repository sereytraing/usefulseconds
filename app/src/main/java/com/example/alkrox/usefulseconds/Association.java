package com.example.alkrox.usefulseconds;

/**
 * Created by AlkRox on 19/07/2017.
 */

public class Association {
    private int id;
    private String name;
    private String catetory;
    private int money;

    public Association(int id, String name, String category, int money) {
        this.id = id;
        this.name = name;
        this.catetory = category;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatetory() {
        return catetory;
    }

    public void setCatetory(String catetory) {
        this.catetory = catetory;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
