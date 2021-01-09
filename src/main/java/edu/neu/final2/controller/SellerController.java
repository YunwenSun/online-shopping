/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.controller;

import edu.neu.final2.controller.*;
import edu.neu.final2.dao.Dao;
import edu.neu.final2.dao.ItemDao;
import edu.neu.final2.dao.SellerDao;
import edu.neu.final2.dao.StoreDao;
import edu.neu.final2.pojo.Item;
import edu.neu.final2.pojo.Seller;
import edu.neu.final2.pojo.Store;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Susan Sun
 */
@Controller
//@RequestMapping("/seller")
public class SellerController extends HttpServlet{
    @GetMapping("/sellerAdmin.htm")
        public ModelAndView getSeller( HttpServletRequest request,ModelMap model,HttpSession session) throws SQLException {        
        return new ModelAndView("sellerAdmin");
    }  
    
      @GetMapping("/storeAdmin.htm")
    public ModelAndView getStore( HttpServletRequest request,ModelMap model,HttpSession session) throws SQLException {

        Seller seller=(Seller)session.getAttribute("seller");
        StoreDao storeDao = new StoreDao();
        List<Store> stores = storeDao.getAllStores(seller);
        //session.setAttribute("stores", stores);
        model.addAttribute("stores",stores);
        
        Store store=new Store();
        model.addAttribute("newStore",store);
        
        return new ModelAndView("storeAdmin");
    }  
    
        @PostMapping("/storeAdmin.htm")
    public ModelAndView addStore(ModelMap model,@ModelAttribute("newStore") Store store,HttpSession session) throws SQLException {
        ModelAndView mv;
        
        StoreDao storeDao = new StoreDao();
        List<Store> storeExist=storeDao.getStores(store.getName());
        if(storeExist.isEmpty()){            
            int result = storeDao.addStore(store);

            if(result == 1) {
                mv = new ModelAndView("redirect:/storeAdmin.htm");
                //System.out.println("New todo Id: " + todo.getTodoId());    
            } else mv =  new ModelAndView("error");
        }
        
        else mv =  new ModelAndView("error");
        return mv;
    }
 
    
    
    @GetMapping("/removeStore.htm")
    public ModelAndView removeStore(@RequestParam("name") String name) {
        ModelAndView mv=null;
        
        StoreDao storeDao = new StoreDao();
        int result = storeDao.deleteStore(name);
        if(result == 1) {
            mv = new ModelAndView("redirect:/storeAdmin.htm");
            //System.out.println("Deleted todo Id: " + todoId);
        } else mv =  new ModelAndView("error");
        
       return mv;
    }
    
    @GetMapping("/updateStore.htm")
    public ModelAndView updateStore(@RequestParam("name") String name,HttpSession session) {
        ModelAndView mv;

        
        session.setAttribute("store", name);
        ItemDao itemDao=new ItemDao();
        List<Item> items=itemDao.getAllItems(name);
        
        mv=new ModelAndView("storeDetail","items",items);
//        TodoDao todoDao = new TodoDao();
//        Todo todo = todoDao.getTodo(todoId);
//        
//        if(todo != null) {
//            mv = new ModelAndView("todo", "existingtodo", todo);
//        } else mv =  new ModelAndView("error");
//        
        return mv;
    }
    
//    @PostMapping("/updatetodo.htm")
//    public ModelAndView updateTodoPost(@ModelAttribute("existingtodo") Todo todo) {
//        ModelAndView mv;
//        
//        TodoDao todoDao = new TodoDao();
//        int result = todoDao.updateTodo(todo.getTodoId(), todo.getName());
//        
//        if(result == 1) {
//            mv = new ModelAndView("redirect:/todo.htm");
//            System.out.println("Updated todo Id: " + todo.getTodoId());
//        } else mv =  new ModelAndView("error");
//        
//        return mv;
    }

