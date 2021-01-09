/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.controller;

import edu.neu.final2.controller.*;
import edu.neu.final2.dao.CustomerDao;
import edu.neu.final2.dao.SellerDao;
import edu.neu.final2.dao.StoreDao;
import edu.neu.final2.pojo.Customer;
import edu.neu.final2.pojo.Seller;
import edu.neu.final2.pojo.Store;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Surya
 */

@Controller
//@RequestMapping("/login")
public class LoginController extends HttpServlet {
 
    @GetMapping("/error.htm")
    public ModelAndView doError(ModelMap model) {    
        return new ModelAndView("error");
    } 
    
    @GetMapping("/index.htm")
    public ModelAndView doGet(ModelMap model) {    
        return new ModelAndView("index");
    }  
    
    @GetMapping("/sellerLogin.htm")
    public ModelAndView doSeller(ModelMap model,HttpSession session) { 
//        String msg="";
//        session.setAttribute("msg", msg);
        return new ModelAndView("sellerLogin");
    } 
    
    @GetMapping("/customerLogin.htm")
    public ModelAndView doCustomer(ModelMap model,HttpSession session) { 
//        String msg="";
//        session.setAttribute("msg", msg);
        return new ModelAndView("customerLogin");
    }
    
    @GetMapping("/AdminLogin.htm")
    public ModelAndView doAdmin(ModelMap model,HttpSession session) {    
        return new ModelAndView("adminLogin");
    }
    
    
    
    @RequestMapping(value="/sellerLogin.htm",method=RequestMethod.POST)
    public String SellerLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws SQLException, IOException {
       
        String mv;
        
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String rol=request.getParameter("rol");
        String msg;
        
        if(username.isEmpty()||password.isEmpty()||rol.isEmpty()){
            msg="content can not be null!";
            session.setAttribute("msg", msg);
            return "redirect:/sellerLogin.htm";
        }
        
        SellerDao sellerDao=new SellerDao();
        Seller seller=sellerDao.searchSeller(username);
        if(rol.equals("register")){
            if(seller!=null){
                
                msg="Seller has already exist!";                 
                //mv = new ModelAndView("sellerLogin","msg",msg);
                session.setAttribute("msg", msg);
                mv="redirect:/sellerLogin.htm";
                
            }else{
                Seller newseller=new Seller(username,password);
                sellerDao.addSeller(newseller);
                msg="";
                session.setAttribute("msg", msg);
                session.setAttribute("seller", newseller);
                //mv = new ModelAndView("redirect:/sellerAdmin.htm");
                mv="redirect:/sellerAdmin.htm";
            }
        }
        else if(rol.equals("login")){
            if(seller==null){
                msg="Seller doesn't exist!"; 
                //mv = new ModelAndView("sellerLogin","msg",msg);
                session.setAttribute("msg", msg);
                mv="redirect:/sellerLogin.htm";
            }
            else if(password.equals(seller.getPassword())){ 
                msg="";
                session.setAttribute("msg", msg);
                session.setAttribute("seller", seller);
                //mv = new ModelAndView("redirect:/sellerAdmin.htm");
                mv="redirect:/sellerAdmin.htm";
                
            }else{
                msg="Password doesn't match with the username!"; 
                //mv = new ModelAndView("sellerLogin","msg",msg);
                session.setAttribute("msg", msg);
                mv="redirect:/sellerLogin.htm";
            }            
        }
        else mv="redirect:/error.htm";
        
        return mv;
    }
    
    @RequestMapping(value="/customerLogin.htm",method=RequestMethod.POST)
    public String customerLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws SQLException, IOException {
       
        String mv;
        
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String rol=request.getParameter("rol");
        String msg;
        
         if(username.isEmpty()||password.isEmpty()||rol.isEmpty()){
            msg="content can not be null!";
            session.setAttribute("msg", msg);
            return "redirect:/customerLogin.htm";
        }
         
        CustomerDao customerDao=new CustomerDao();
        Customer customer=customerDao.searchCustomer(username);
        if(rol.equals("register")){
            if(customer!=null){
                
                msg="Customer has already exist!";                 
                //mv = new ModelAndView("sellerLogin","msg",msg);
                session.setAttribute("msg", msg);
                mv="redirect:/customerLogin.htm";
                
            }else{
                Customer newcustomer=new Customer(username,password);
                customerDao.addCustomer(newcustomer);
                msg="";
                session.setAttribute("msg", msg);
                session.setAttribute("customer", newcustomer);
                //mv = new ModelAndView("redirect:/sellerAdmin.htm");
                mv="redirect:/customerAdmin.htm";
            }
        }
        else if(rol.equals("login")){
            if(customer==null){
                msg="Customer doesn't exist!"; 
                //mv = new ModelAndView("sellerLogin","msg",msg);
                session.setAttribute("msg", msg);
                mv="redirect:/customerLogin.htm";
            }
            else if(password.equals(customer.getPassword())){  
                msg="";
                session.setAttribute("msg", msg);
                session.setAttribute("customer", customer);
                //mv = new ModelAndView("redirect:/sellerAdmin.htm");
                mv="redirect:/customerAdmin.htm";
                
            }else{
                msg="Password doesn't match with the username!"; 
                //mv = new ModelAndView("sellerLogin","msg",msg);
                session.setAttribute("msg", msg);
                mv="redirect:/customerLogin.htm";
            }            
        }
        else mv="redirect:/error.htm";
        
        return mv;
    }
    
//    @RequestMapping(value="/customerLogin.htm",method=RequestMethod.POST)
//    public String adminLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws SQLException, IOException {
//       
//        String mv;
//        
//        String username=request.getParameter("username");
//        String password=request.getParameter("password");
//        String rol=request.getParameter("rol");
//        String msg;
//        
//         if(username.isEmpty()||password.isEmpty()||rol.isEmpty()){
//            msg="content can not be null!";
//            session.setAttribute("msg", msg);
//            return "redirect:/customerLogin.htm";
//        }
//         
//        CustomerDao customerDao=new CustomerDao();
//        Customer customer=customerDao.searchCustomer(username);
//        if(rol.equals("register")){
//            if(customer!=null){
//                
//                msg="Customer has already exist!";                 
//                //mv = new ModelAndView("sellerLogin","msg",msg);
//                session.setAttribute("msg", msg);
//                mv="redirect:/customerLogin.htm";
//                
//            }else{
//                Customer newcustomer=new Customer(username,password);
//                customerDao.addCustomer(newcustomer);
//                msg="";
//                session.setAttribute("msg", msg);
//                session.setAttribute("customer", newcustomer);
//                //mv = new ModelAndView("redirect:/sellerAdmin.htm");
//                mv="redirect:/customerAdmin.htm";
//            }
//        }
//        else if(rol.equals("login")){
//            if(customer==null){
//                msg="Customer doesn't exist!"; 
//                //mv = new ModelAndView("sellerLogin","msg",msg);
//                session.setAttribute("msg", msg);
//                mv="redirect:/customerLogin.htm";
//            }
//            else if(password.equals(customer.getPassword())){  
//                msg="";
//                session.setAttribute("msg", msg);
//                session.setAttribute("customer", customer);
//                //mv = new ModelAndView("redirect:/sellerAdmin.htm");
//                mv="redirect:/customerAdmin.htm";
//                
//            }else{
//                msg="Password doesn't match with the username!"; 
//                //mv = new ModelAndView("sellerLogin","msg",msg);
//                session.setAttribute("msg", msg);
//                mv="redirect:/customerLogin.htm";
//            }            
//        }
//        else mv="redirect:/error.htm";
//        
//        return mv;
//    }

    
  
//    @PostMapping("/sellerAdmin.htm")     
//    public ModelAndView addStore (HttpServletRequest request, HttpServletResponse response, ModelMap model) throws SQLException {
//        ModelAndView mv;
//        String owner=request.getParameter("seller");
//        String name=request.getParameter("storeName");
//        
//        SellerDao sellerDao=new SellerDao();
//        Seller seller=sellerDao.searchSeller(owner);
//        
//        StoreDao storeDao = new StoreDao();
//        Store store=new Store(name);
//        int result = storeDao.addStore(seller,store);
//        
//        
//        if(result == 1) {
//            mv = new ModelAndView("sellerAdmin.htm");
//            //System.out.println("New todo Id: " + todo.getTodoId());    
//        } else mv =  new ModelAndView("error");
//        
//        return mv;
//    }  
    
      
     
    

}
