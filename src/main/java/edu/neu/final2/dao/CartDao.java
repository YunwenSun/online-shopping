/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.dao;

import edu.neu.final2.pojo.Cart;
import edu.neu.final2.pojo.Item;
import java.sql.Connection;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author Susan Sun
 */
public class CartDao {
    public int addtoCart(String customer,String seller,Item item,int num) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query1 = "INSERT INTO carts (customer,seller,store,item,price,num) VALUES (?,?,?,?,?,?)";
            result = queryRunner.update(connection, query1,customer,seller,item.getStore(),item.getName(),item.getPrice(),num);
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }
    
     public List<Cart> testItem(String customer,Item item) {
        Connection connection = null;
        List<Cart> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Cart>> h = new BeanListHandler<>(Cart.class);
            String query = "SELECT * FROM carts where customer= ? and store=? and item=? ";
            result = queryRunner.query(connection, query, h,customer,item.getStore(),item.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }

    
     
    public int updateCart1(String customer,Item item) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query1 = "UPDATE carts SET num=num+1 WHERE customer= ? and store=? and item=? ";
            result = queryRunner.update(connection, query1,customer,item.getStore(),item.getName());
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }
    public int updateCart2(String customer,Item item) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query1 = "UPDATE carts SET num=num-1 WHERE customer= ? and store=? and item=? ";
            result = queryRunner.update(connection, query1,customer,item.getStore(),item.getName());
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }
    public int removeCart(String customer,Item item) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query1 = "DELETE FROM carts WHERE customer= ? and store=? and item=? ";
            result = queryRunner.update(connection, query1,customer,item.getStore(),item.getName());
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }
     
    public int calculateTotalPrice(String customer) {
        Connection connection = null;
        List<Cart> result = null;
        int res=-1;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Cart>> h = new BeanListHandler<>(Cart.class);
            String query = "SELECT * FROM carts where customer= (?) ";
            result = queryRunner.query(connection, query, h,customer);
            double totalPrice=0;
            for(Cart cart:result){
                totalPrice+=cart.getNum()*cart.getPrice();
            }
            String query2="UPDATE carts SET totalPrice=? where customer=?";
            res=queryRunner.update(connection, query2,totalPrice,customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return res;
    }
    
    public List<Cart> getAllItems(String customer) {
        Connection connection = null;
        List<Cart> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Cart>> h = new BeanListHandler<>(Cart.class);
            String query = "SELECT * FROM carts where customer= (?) ";
            result = queryRunner.query(connection, query, h,customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }

    public int clearCart(String customer) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query1 = "DELETE FROM carts WHERE customer= ? ";
            result = queryRunner.update(connection, query1,customer);
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }
    
}
