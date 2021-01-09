/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.dao;

import edu.neu.final2.pojo.Cart;
import edu.neu.final2.pojo.Id;
import edu.neu.final2.pojo.Item;
import edu.neu.final2.pojo.Order;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang.RandomStringUtils;


/**
 *
 * @author Susan Sun
 */
public class OrderDao {
 
//    public static String random(int count, boolean letters, boolean numbers){
//        String random = RandomStringUtils.random(15, true, false);
//        System.out.println(random);
//    }
    public int addOrder(List<Cart> carts) {
        //Type here!
        Connection connection = null;
        int result = 0;
        String id=RandomStringUtils.random(10,true,true);
        long millis=System.currentTimeMillis();
         java.sql.Date date=new java.sql.Date(millis);
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            for(Cart cart: carts){
                String query1 = "INSERT INTO orders (id,customer,seller,store,item,num,price,date,totalPrice) VALUES (?,?,?,?,?,?,?,?,?)";
                result = queryRunner.update(connection, query1,id,cart.getCustomer(),cart.getSeller(),cart.getStore(),
                                            cart.getItem(),cart.getNum(),cart.getPrice(),date,cart.getTotalPrice());
            }
            
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }
    
        public List<Id> getIdbyCus(String customer) {
        Connection connection = null;
        List<Id> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Id>> h = new BeanListHandler<>(Id.class);
            String query = "SELECT distinct id FROM orders where customer= (?) ";
            result = queryRunner.query(connection, query, h,customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
        
        public List<Id> getIdbySeller(String seller) {
        Connection connection = null;
        List<Id> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Id>> h = new BeanListHandler<>(Id.class);
            String query = "SELECT distinct id FROM orders where seller= (?) ";
            result = queryRunner.query(connection, query, h,seller);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
        
        public List<List<Order>> getOrderbyId(List<String> ids) {
        Connection connection = null;
        List<List<Order>> results = new ArrayList<>();
        List<Order> result=null;
        for(String id:ids){
            try {
            
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Order>> h = new BeanListHandler<>(Order.class);
            String query = "SELECT * FROM orders where id= (?) ";
            result = queryRunner.query(connection, query, h,id);
            if(result.isEmpty()){
                System.out.println("single query not running");
            }else{
                System.out.println("results fail to add");
            }
            results.add(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        }
        
        return results;
    }
        
    public List<List<Order>> getOrderbyIdandSeller(List<String> ids,String seller) {
        Connection connection = null;
        List<List<Order>> results = new ArrayList<>();
        List<Order> result=null;
        for(String id:ids){
            try {
            
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Order>> h = new BeanListHandler<>(Order.class);
            String query = "SELECT * FROM orders where id= (?) and seller=? ";
            result = queryRunner.query(connection, query, h,id,seller);
            if(result.isEmpty()){
                System.out.println("single query not running");
            }else{
                System.out.println("results fail to add");
            }
            results.add(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        }
        
        return results;
    }
}
