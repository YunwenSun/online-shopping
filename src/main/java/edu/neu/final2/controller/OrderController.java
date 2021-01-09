/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Susan Sun
 */

import edu.neu.final2.dao.CartDao;
import edu.neu.final2.dao.ItemDao;
import edu.neu.final2.dao.OrderDao;
import edu.neu.final2.pojo.Cart;
import edu.neu.final2.pojo.Customer;
import edu.neu.final2.pojo.Id;
import edu.neu.final2.pojo.Item;
import edu.neu.final2.pojo.Order;
import edu.neu.final2.pojo.Seller;
import edu.neu.final2.view.MyPdfView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
public class OrderController  extends HttpServlet{
    
    @GetMapping("/viewOrderCus.htm")           
    public ModelAndView viewOdercus( HttpServletRequest request,ModelMap model,HttpSession session) throws SQLException {        
        ModelAndView mv;
        String msg="oders null";
        
        Customer customer=(Customer)session.getAttribute("customer");
        
        OrderDao orderDao=new OrderDao();       
        List<Id> ids= orderDao.getIdbyCus(customer.getUsername());
        List<String> orderId=new ArrayList<String>();
        if(ids==null) {msg="ids null";}
        else{
            for(Id id:ids){
                orderId.add(id.getId());
            }
        }
        List<List<Order>> orders=orderDao.getOrderbyId(orderId);
        double priceInAll=0;
        for(List<Order> cal:orders){
            priceInAll+=cal.get(0).getTotalPrice();
        }
        
        
        if(orders!=null){
            model.addAttribute("priceInAll", priceInAll);
            model.addAttribute("role",1);
            session.setAttribute("orders", orders);
            mv=new ModelAndView("viewOrder","orders", orders);
        }
        else {
            model.addAttribute("errormsg",msg);
            model.addAttribute("ids",orderId);
            mv=new ModelAndView("error");
        }
        
        return mv;
    }  
    
    @GetMapping("/viewOrderSell.htm")
    public ModelAndView viewOderSell( HttpServletRequest request,ModelMap model,HttpSession session) throws SQLException {        
        ModelAndView mv;
        String msg="oders null";
        
        Seller seller=(Seller)session.getAttribute("seller");
        
        OrderDao orderDao=new OrderDao();       
        List<Id> ids= orderDao.getIdbySeller(seller.getUsername());
        List<String> orderId=new ArrayList<String>();
        if(ids==null) {msg="ids null";}
        else{
            for(Id id:ids){
                orderId.add(id.getId());
            }
        }
        //List<List<Order>> orders=orderDao.getOrderbyId(orderId);
        
        List<List<Order>> orders=orderDao.getOrderbyIdandSeller(orderId,seller.getUsername());
        double priceInAll=0;
        for(List<Order> cal:orders){
            priceInAll+=cal.get(0).getTotalPrice();
        }
        
        
        
        if(orders!=null){
            model.addAttribute("priceInAll", priceInAll);
            model.addAttribute("role",0);
            session.setAttribute("orders", orders);
            mv=new ModelAndView("viewOrder","orders", orders);
        }
        else {
            model.addAttribute("errormsg",msg);
            model.addAttribute("ids",orderId);
            mv=new ModelAndView("error");
        } 
        
        return mv;
    } 
    
//        @GetMapping("/generatePDF.htm")
//    public ModelAndView createPDF( HttpServletRequest request,HttpServletResponse response,ModelMap model,HttpSession session) throws SQLException {        
//        //ModelAndView mv;
//        //List<List<Order>> orders =(List<List<Order>>)request.getAttribute("orders");
////        List<List<Order>> orders = (List<List<Order>>)session.getAttribute("orders");
////        if(orders==null || orders.isEmpty()){
////            System.out.print("no orders");
////        }else System.out.print(orders.size());
//        
//        View view=new MyPdfView(request,session);
//        return new ModelAndView(view);       
//    } 
}
