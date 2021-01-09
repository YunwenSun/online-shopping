/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.dao;

import edu.neu.final2.pojo.Seller;
import edu.neu.final2.pojo.Store;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author Susan Sun
 */
public class SellerDao {
    
    public Seller searchSeller(String username) throws SQLException {    
        Connection connection=null;  
        Seller seller=null;         
         try{
             Dao dao=new Dao();
             connection=dao.getConnection();
             QueryRunner queryRunner = new QueryRunner();
             ResultSetHandler<List<Seller>> result=new BeanListHandler<>(Seller.class);
             String query="SELECT * FROM sellers WHERE username = (?) ";
             List<Seller> sellers=queryRunner.query(connection, query, result,username);
             if(!sellers.isEmpty())  seller=sellers.get(0);            
         }catch(Exception e){
             System.out.println(e.getMessage());
         }
         return seller;  
          

    }
    
        public int addSeller(Seller seller) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query = "INSERT INTO sellers (username,password) VALUES (?,?)";
            result = queryRunner.update(connection, query, seller.getUsername(),seller.getPassword());
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }
        

}
