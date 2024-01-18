package com.example.sql;

// CustomItem.java
public class CustomItem {
    private String name;
    private int age;
    private int id;

    public CustomItem(int id, String name,int age) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public int getId(){
        return  id;
    }

    public String getName() {
        return name;
    }
    public Integer getAge(){
        return age;
    }

}