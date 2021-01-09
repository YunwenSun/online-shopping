 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.dao;

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
public class StoreDao {
    
    public List<Store> getAllStores(Seller seller) {
        Connection connection = null;
        List<Store> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Store>> h = new BeanListHandler<>(Store.class);
            String query = "SELECT * FROM stores where owner= (?) ";
            result = queryRunner.query(connection, query, h,seller.getUsername());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
    
        public List<Store> getStores(String store) {
        Connection connection = null;
        List<Store> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Store>> h = new BeanListHandler<>(Store.class);
            String query = "SELECT * FROM stores WHERE name= ?  ";
            result = queryRunner.query(connection, query, h,store);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }

    
    public int addStore(Store store) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query1 = "INSERT INTO stores (name,owner) VALUES (?,?);";
            result = queryRunner.update(connection, query1,store.getName(),store.getOwner());
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }
    
     public int deleteStore(String store) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query1 = "DELETE FROM stores WHERE name= ?";
            result = queryRunner.update(connection, query1,store);
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }
//        public int updateTodo(Integer todoId, String name) {
//        //Type here!
//        Connection connection = null;
//        int result = 0;
//        try {
//            BaseDao dbdao = new BaseDao();
//            connection = dbdao.getConnection();
//            QueryRunner queryRunner = new QueryRunner();
//            String query = "Update todo Set name = ? where todoId = ?" ;
//            result = queryRunner.update(connection, query, name, todoId);
//        } 
//        catch (Exception e) { System.out.println(e.getMessage()); }
//        return result;
//    }
}
