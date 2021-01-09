/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.pojo;

import java.util.List;
import javax.persistence.OneToMany;

/**
 *
 * @author Susan Sun
 */
public class Store {
    @OneToMany(mappedBy = "seller")
    private String name;
    private String owner;
    

    public Store() {
    }

    public Store(String name) {
        this.name = name;
    }

    public Store(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
}
