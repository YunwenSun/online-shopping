/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.controller;

import edu.neu.final2.controller.*;
import edu.neu.final2.dao.CartDao;
import edu.neu.final2.dao.ItemDao;
import edu.neu.final2.dao.OrderDao;
import edu.neu.final2.pojo.Cart;
import edu.neu.final2.pojo.Customer;
import edu.neu.final2.pojo.Item;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Susan Sun
 */
@Controller
public class CartController extends HttpServlet{
    
        @GetMapping("/viewCart.htm")
    public ModelAndView viewCart(@RequestParam("msg") String msg, HttpServletRequest request,ModelMap model,HttpSession session) throws SQLException {        
        ModelAndView mv;
        
        Customer customer=(Customer)session.getAttribute("customer");
        
        CartDao cartDao=new CartDao();       
        List<Cart> carts=cartDao.getAllItems(customer.getUsername());
        if(!carts.isEmpty()){
            int calculate=cartDao.calculateTotalPrice(customer.getUsername());
            if(calculate==-1) return new ModelAndView("error");
            else carts=cartDao.getAllItems(customer.getUsername());
        }
        
//        ItemDao itemDao=new ItemDao();       
//        for(Cart cart:carts){
//            Item item=itemDao.getItems(cart.getItem(), cart.getStore()).get(0);
//            cart.setPrice(item.getPrice());
//        }
        session.setAttribute("carts", carts);
        model.addAttribute("carts", carts);
        mv=new ModelAndView("viewCart","msg",msg);
//        mv.addObject();
//        mv.addObject("prices", prices);       
        return mv;
    }  
    
    @GetMapping("/addNum.htm")
        public ModelAndView addNum(@RequestParam("name") String name,@RequestParam("store")String store, HttpServletRequest request,ModelMap model,HttpSession session) throws SQLException {        
        ModelAndView mv;
        int result=0;
        String msg=null;
        
        Customer customer=(Customer)session.getAttribute("customer");
        
        CartDao cartDao=new CartDao();        
        ItemDao itemDao=new ItemDao();
        Item item=itemDao.getItems(name,store).get(0);
        Cart cart=cartDao.testItem(customer.getUsername(), item).get(0);
        if(cart.getNum()<item.getCurrentNum()){
            result=cartDao.updateCart1(customer.getUsername(), item);
            if(result==1)mv=new ModelAndView("redirect:/viewCart.htm?msg=");
            else mv=new ModelAndView("error");
        }
        else{
            msg="Not enough items available!";
            mv=new ModelAndView("redirect:/viewCart.htm?msg="+msg);
        }
             
        return mv;
    }  
        
    @GetMapping("/minusNum.htm")
    public ModelAndView minusNum(@RequestParam("name") String name,@RequestParam("store")String store, HttpServletRequest request,ModelMap model,HttpSession session) throws SQLException {        
        ModelAndView mv;
        int result=0;
        String msg=null;
        
        Customer customer=(Customer)session.getAttribute("customer");
        
        CartDao cartDao=new CartDao();        
        ItemDao itemDao=new ItemDao();
        Item item=itemDao.getItems(name,store).get(0);
        Cart cart=cartDao.testItem(customer.getUsername(), item).get(0);
        if(cart.getNum()>1){
            result=cartDao.updateCart2(customer.getUsername(), item);
            if(result==1) mv=new ModelAndView("redirect:/viewCart.htm?msg=");
            else mv=new ModelAndView("error");
        }
        else if(cart.getNum()==1){
            result=cartDao.removeCart(customer.getUsername(), item);
            if(result==1) mv=new ModelAndView("redirect:/viewCart.htm?msg=");
            else mv=new ModelAndView("error");
        }else   {
            mv=new ModelAndView("error");
        }         
        return mv;
    }  
    
        
    @GetMapping("/removeNum.htm")
        public ModelAndView removeNum(@RequestParam("name") String name,@RequestParam("store")String store, HttpServletRequest request,ModelMap model,HttpSession session) throws SQLException {        
        ModelAndView mv;
        String msg;
        
        Customer customer=(Customer)session.getAttribute("customer");
        
        CartDao cartDao=new CartDao();
        ItemDao itemDao=new ItemDao();
        Item item=itemDao.getItems(name,store).get(0);
        int result=cartDao.removeCart(customer.getUsername(), item);
        if(result==1){
            mv=new ModelAndView("redirect:/viewCart.htm?msg=");
        }else{
            mv=new ModelAndView("error");
        }
             
        return mv;
    }  
    
        
    @PostMapping("/viewCart.htm")               
    public ModelAndView checkout( HttpServletRequest request,ModelMap model,HttpSession session) throws SQLException {        
        ModelAndView mv;
        String msg="fail to clear";
        
        Customer customer=(Customer) session.getAttribute("customer");
        List<Cart> carts=(List<Cart>)session.getAttribute("carts");
        //update soldNum and currentNum of Items
        
        ItemDao itemDao=new ItemDao();
        for(Cart cart:carts){
            Item item=itemDao.getItems(cart.getItem(), cart.getStore()).get(0);
            int result=itemDao.checkOutItem(item, cart.getNum());
            if(result!=1){
                msg="fail to modify num";
                return new ModelAndView("error","errormsg",msg);
            }
        }
        
        //ceate an order of this cart
        OrderDao orderDao=new OrderDao();
        int addOrder=orderDao.addOrder(carts);
        int result=0;
        //clear the cart
        if(addOrder!=0){
            CartDao cartDao=new CartDao();       
            result=cartDao.clearCart(customer.getUsername());    
        }
        
        if(addOrder==0) msg="fail to add";
        
        if(result!=0){
            mv=new ModelAndView("checkOut","carts", carts);
        }else mv=new ModelAndView("error","errormsg",msg);
            
        return mv;
    }  
    
}
