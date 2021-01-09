/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.pojo;

import java.sql.Date;
import java.util.List;
import javax.persistence.OneToMany;
import org.apache.xpath.XPathVisitable;

/**
 *
 * @author Susan Sun
 */
public class Order {
    @OneToMany(mappedBy = "customer")
    private String id;
    private int num;
    private String customer;
    private String seller;
    private String store;
    private String item;
    private double price;
    private double totalPrice;
    private Date date;

    public Order() {
    }

    public Order(String id, int num, String customer, String seller, String store, String item, double price, Date date,double totalPrice) {
        this.id = id;
        this.num = num;
        this.customer = customer;
        this.seller = seller;
        this.store = store;
        this.item = item;
        this.price = price;
        this.date = date;
        this.totalPrice=totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
//    public String toString(){
//        return id;
//    }
}
