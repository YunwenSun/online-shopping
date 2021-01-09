/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.controller;

import edu.neu.final2.controller.*;
import edu.neu.final2.dao.ItemDao;
import edu.neu.final2.dao.StoreDao;
import edu.neu.final2.pojo.Item;
import edu.neu.final2.pojo.Seller;
import edu.neu.final2.pojo.Store;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Susan Sun
 */
@Controller
public class CustomerController extends HttpServlet{
    
    @GetMapping("/customerAdmin.htm")
    public ModelAndView getCustomer( HttpServletRequest request,ModelMap model,HttpSession session) throws SQLException {        
        return new ModelAndView("customerAdmin");
    }  
    
    
}
