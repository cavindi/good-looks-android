package com.ludowica.goodlooks.model;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("keyword")
    private String keyword;
    @SerializedName("image")
    private String image;


    public Category() {
    }

    public Category(int id, String name, String keyword, String image) {
        this.id = id;
        this.name = name;
        this.keyword = keyword;
        this.image = image;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
