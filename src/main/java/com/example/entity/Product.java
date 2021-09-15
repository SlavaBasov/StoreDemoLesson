package com.example.entity;

public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private String imgPath;

    public Product() {
    }

    public Product(String name, String description, int price, String imgPath) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgPath = imgPath;
    }

    public Product(int id, String name, String description, int price, String imgPath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgPath = imgPath;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
