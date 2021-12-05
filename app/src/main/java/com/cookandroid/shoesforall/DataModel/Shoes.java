package com.cookandroid.shoesforall.DataModel;

public class Shoes {
    private String brand;
    private String description;
    private String image;
    private String information;
    private String name;
    private String price;
    private String shoes;
    public Shoes() {

    }

    public Shoes(String brand, String description, String image, String information, String name, String price,String shoes) {
        this.brand = brand;
        this.description = description;
        this.image = image;
        this.information = information;
        this.name = name;
        this.price = price;
        this.shoes = shoes;
    }

    public String getShoes() {
        return shoes;
    }

    public void setShoes(String shoes) {
        this.shoes = shoes;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
