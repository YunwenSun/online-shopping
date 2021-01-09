/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.pojo;

import javax.persistence.OneToMany;

/**
 *
 * @author Susan Sun
 */
public class Item {
    @OneToMany(mappedBy = "store")
    private String name;
    private String description;
    private int price;
    private int currentNum;
    
    private String type;
    private int soldNum;
    private double rate;        
    private String feedback;
    private String store;
    
    public Item() {
    }

    public Item(String name, String description, int price, int currentNum, String type, int soldNum, double rate, String feedback, String store) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.currentNum = currentNum;
        this.type = type;
        this.soldNum = soldNum;
        this.rate = rate;
        this.feedback = feedback;
        this.store = store;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(int soldNum) {
        this.soldNum = soldNum;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int num) {
        this.currentNum = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    
}
