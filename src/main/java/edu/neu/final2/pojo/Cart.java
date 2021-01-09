/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.pojo;

import java.util.List;

/**
 *
 * @author Susan Sun
 */
public class Cart {
    private String customer;
    private String seller;
    private String store;
    private int num;
    private String item;
    private double price;
    private double totalPrice;

    public Cart() {
    }

    public Cart(String customer, String seller, String store, String item,int num,double price,double totalPrice) {
        this.customer = customer;
        this.seller = seller;
        this.store = store;
        this.item = item;
        this.num=num;
        this.price=price;
        this.totalPrice=totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        return item;
    }
}
