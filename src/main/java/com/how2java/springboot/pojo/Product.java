package com.how2java.springboot.pojo;

public class Product {
    private int id;
    private String name;
    private float price;
    private int cid;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getCid() {
        return cid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
