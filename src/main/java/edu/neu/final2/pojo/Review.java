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
public class Review {
    @OneToMany(mappedBy = "item")
    String item;
    String store;
    String feedback;
    String customer;
    double rate;

    public Review() {
    }

    public Review(String item, String store, String feedback, String customer, double rate) {
        this.item = item;
        this.store = store;
        this.feedback = feedback;
        this.customer = customer;
        this.rate = rate;
    }



    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
    
}
