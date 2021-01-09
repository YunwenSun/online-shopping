/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.dao;

import edu.neu.final2.pojo.Cart;
import edu.neu.final2.pojo.Item;
import edu.neu.final2.pojo.Review;
import java.sql.Connection;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author Susan Sun
 */
public class ReviewDao {
    public List<Review> testReview(String item,String store,String customer) {
        Connection connection = null;
        List<Review> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Review>> h = new BeanListHandler<>(Review.class);
            String query = "SELECT * FROM reviews where customer= ? and store=? and item=? ";
            result = queryRunner.query(connection, query, h,customer,store,item);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
    
    public List<Review> getToReview(String item,String store) {
        Connection connection = null;
        List<Review> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Review>> h = new BeanListHandler<>(Review.class);
            String query = "SELECT * FROM reviews where  store=? and item=? and feedback is not null";
            result = queryRunner.query(connection, query, h,store,item);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
    public int addNewReview(String item,String store,String customer) {
        Connection connection = null;
        int result=0;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            //ResultSetHandler<List<Review>> h = new BeanListHandler<>(Review.class);
            String query = "INSERT INTO reviews (customer,store,item) VALUES(?,?,?)";
            result = queryRunner.update(connection, query,customer,store,item);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //return null;
        }
        return result;
    }
    
    public int updateReview(Review review) {
        Connection connection = null;
        int result=0;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            //ResultSetHandler<List<Review>> h = new BeanListHandler<>(Review.class);
            String query = "UPDATE reviews SET rate=?,feedback=? WHERE customer= ? and store=? and item=? ";
            result = queryRunner.update(connection, query,review.getRate(),review.getFeedback(),review.getCustomer(),review.getStore(),review.getItem());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //return null;
        }
        return result;
    }
    
    public List<Review> getReviewNum(String item,String store) {
        Connection connection = null;
        List<Review> result = null;
        try {
            Dao dao = new Dao();
            connection = dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Review>> h = new BeanListHandler<>(Review.class);
            String query = "SELECT * FROM reviews where rate  is NOT NULL and store=? and item=? ";
            result = queryRunner.query(connection, query, h,store,item);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
}
