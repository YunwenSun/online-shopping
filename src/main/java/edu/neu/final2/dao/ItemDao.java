/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.dao;

import edu.neu.final2.pojo.Item;
import edu.neu.final2.pojo.Review;
import edu.neu.final2.pojo.Seller;
import edu.neu.final2.pojo.Store;
import java.sql.Connection;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author Susan Sun
 */
public class ItemDao {
    
    public List<Item> getAllItems(String store) {
        Connection connection = null;
        List<Item> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Item>> h = new BeanListHandler<>(Item.class);
            String query = "SELECT * FROM items where store= (?) ";
            result = queryRunner.query(connection, query, h,store);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
        
    public List<Item> getItems(String item,String store) {
        Connection connection = null;
        List<Item> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Item>> h = new BeanListHandler<>(Item.class);
            String query = "SELECT * FROM items WHERE name= ? and store=?  ";
            result = queryRunner.query(connection, query, h,item,store);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
    
    public int addItem(Item item) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query1 = "INSERT INTO items (store,name,type,price,currentNum,description) VALUES (?,?,?,?,?,?)";
            result = queryRunner.update(connection, query1,item.getStore(),item.getName(),item.getType(),item.getPrice(),item.getCurrentNum(),item.getDescription());
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }
    
    public int updateItem(Item item) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query1 = "UPDATE items SET price=?,currentNum=?,description=? WHERE name=? and store=?";
            result = queryRunner.update(connection, query1,item.getPrice(),item.getCurrentNum(),item.getDescription(),item.getName(),item.getStore());
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }
    
    public int deleteItem(String item,String store) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query1 = "DELETE FROM items WHERE name=? and store=?";
            result = queryRunner.update(connection, query1,item,store);
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }
    
    public List<Item> searchAllItems() {
        Connection connection = null;
        List<Item> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Item>> h = new BeanListHandler<>(Item.class);
            String query = "SELECT * FROM items";
            result = queryRunner.query(connection, query, h);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
    
    public List<Item> searchItemsbyType(String keyWord) {
        Connection connection = null;
        List<Item> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Item>> h = new BeanListHandler<>(Item.class);
            String query = "SELECT * FROM items where type = ? ";
            result = queryRunner.query(connection, query, h,keyWord);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
    
    public List<Item> searchItemsbyStore(String keyWord) {
        Connection connection = null;
        List<Item> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Item>> h = new BeanListHandler<>(Item.class);
            String query = "SELECT * FROM items where store = ? ";
            result = queryRunner.query(connection, query, h,keyWord);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
        public List<Item> searchItemsApprox(String keyWord) {
            String key="%"+keyWord+"%";
        Connection connection = null;
        List<Item> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Item>> h = new BeanListHandler<>(Item.class);
            String query = "SELECT * FROM items where name Like ?";
            result = queryRunner.query(connection, query, h,key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
        
     public int checkOutItem(Item item,int num) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query1 = "UPDATE items SET currentNum=currentNum-?,soldNum=soldNum+? WHERE name=? and store=?";
            result = queryRunner.update(connection, query1,num,num,item.getName(),item.getStore());
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }   
     
     public int reviewItemRate(Item item,Double thisrate) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            
            ReviewDao reviewDao=new ReviewDao();
            List<Review> reviewnum=reviewDao.getReviewNum(item.getName(),item.getStore());
            int allnum=reviewnum.size();
            Double oldRate=item.getRate();
            Double newRate=(oldRate*(allnum-1)+thisrate)/allnum;
            String query1 = "UPDATE items SET rate=?,description=? WHERE name=? and store=?";
            result = queryRunner.update(connection, query1,newRate,item.getFeedback(),item.getName(),item.getStore());
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }

}
