/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.dbutils.DbUtils;
/**
 *
 * @author Susan Sun
 */
public class Dao {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/buyit?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASSWORD = "Suesyw123";
    private Connection connection;

    public Connection getConnection() throws Exception {
        try {
            DbUtils.loadDriver(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        return connection;
    }    
}
