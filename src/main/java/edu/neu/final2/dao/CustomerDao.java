/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.dao;

import edu.neu.final2.pojo.Customer;
import edu.neu.final2.pojo.Seller;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author Susan Sun
 */
public class CustomerDao {
    
        public Customer searchCustomer(String username) throws SQLException {    
        Connection connection=null;  
        Customer customer=null;         
         try{
             Dao dao=new Dao();
             connection=dao.getConnection();
             QueryRunner queryRunner = new QueryRunner();
             ResultSetHandler<List<Customer>> result=new BeanListHandler<>(Customer.class);
             String query="SELECT * FROM customers WHERE username = (?) ";
             List<Customer> customers=queryRunner.query(connection, query, result,username);
             if(!customers.isEmpty())  customer=customers.get(0);            
         }catch(Exception e){
             System.out.println(e.getMessage());
         }
         return customer;  
          

    }
    
        public int addCustomer(Customer customer) {
        //Type here!
        Connection connection = null;
        int result = 0;
        try {
            Dao dao=new Dao();
            connection=dao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query = "INSERT INTO customers (username,password) VALUES (?,?)";
            result = queryRunner.update(connection, query, customer.getUsername(),customer.getPassword());
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return result;
    }
}
