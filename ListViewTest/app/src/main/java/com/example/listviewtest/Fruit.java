package com.example.listviewtest;

public class Fruit {
    private String name;
    private int ImageId;
    public Fruit(String name,int ImageId){
        this.name = name;
        this.ImageId = ImageId;
    }
    public String getName(){
        return name;
    }

    public int getImageId(){
        return ImageId;
    }
}
