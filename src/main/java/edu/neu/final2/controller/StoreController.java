/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.controller;

import edu.neu.final2.dao.ItemDao;
import edu.neu.final2.dao.StoreDao;
import edu.neu.final2.pojo.Item;
import edu.neu.final2.pojo.Store;
import edu.neu.final2.controller.*;
import edu.neu.final2.dao.ReviewDao;
import edu.neu.final2.pojo.Review;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Susan Sun
 */
@Controller
public class StoreController {
    
    @GetMapping("/addItem.htm")
    public ModelAndView doGet (ModelMap model,HttpSession session) throws SQLException {
        
        Item item=new Item();
        model.addAttribute("newItem", item);
        return new ModelAndView("addItem");
    }
    
    @PostMapping("/addItem.htm")
    public ModelAndView addItem(ModelMap model,HttpSession session,@ModelAttribute("newItem")Item item) throws SQLException {
        ModelAndView mv;
        
        //String store=session.getAttribute("store").toString();
//        item.setSoldNum(0);
//        item.setRate(5);

        if(item.getName().isEmpty()||item.getType().isEmpty()||item.getDescription().isEmpty()){
            model.addAttribute("msg","Item has already existed!");
           return  new ModelAndView("redirect:/addItem.htm?name="+item.getStore());
        }
        if(item.getPrice()<=0){
           model.addAttribute("msg","Price should be positive!");
           return  new ModelAndView("redirect:/addItem.htm?name="+item.getStore()); 
        }
        if(item.getCurrentNum()<0){
           model.addAttribute("msg","Amount should be positive!");
           return  new ModelAndView("redirect:/addItem.htm?name="+item.getStore()); 
        }
        ItemDao itemDao = new ItemDao();        
        List<Item> itemExist=itemDao.getItems(item.getName(),item.getStore());
        if(itemExist.isEmpty()){            
            int result = itemDao.addItem(item);
            if(result == 1) {
                mv = new ModelAndView("redirect:/updateStore.htm?name="+item.getStore());
                //System.out.println("New todo Id: " + todo.getTodoId());    
            } else mv =  new ModelAndView("error");
        }else{
           model.addAttribute("msg","Item has already existed!");
           mv =  new ModelAndView("redirect:/addItem.htm?name="+item.getStore()); 
        }
        
        return mv;
    }
    
    @GetMapping("/viewItem.htm")
    public ModelAndView viewItem(ModelMap model,HttpSession session,@RequestParam("name") String name) throws SQLException {
        ModelAndView mv;
        
        //String store=session.getAttribute("store").toString();
//        item.setSoldNum(0);
//        item.setRate(5);
        String store=session.getAttribute("store").toString();
        ItemDao itemDao = new ItemDao();        
        List<Item> items=itemDao.getItems(name,store);
        Item item=items.get(0);
        
        ReviewDao reviewDao=new ReviewDao();
        List<Review> reviews=reviewDao.getToReview(name, store);
        model.addAttribute("viewreviews", reviews);
              
        mv =  new ModelAndView("viewItem","item",item);
        return mv;
    }

    @GetMapping("/updateItem.htm")
    public ModelAndView getupdateItem(ModelMap model,HttpSession session,@RequestParam("name") String name) throws SQLException{
        ModelAndView mv;
        
        String store=session.getAttribute("store").toString();
        ItemDao itemDao = new ItemDao();        
        List<Item> items=itemDao.getItems(name,store);
        Item item=items.get(0);
        model.addAttribute("oldItem", item);
        mv =  new ModelAndView("updateItem","oldItem", item);
        return mv;
    }
            
    @PostMapping("/updateItem.htm")
    public ModelAndView updateItem(ModelMap model,HttpSession session,@ModelAttribute("oldItem") Item item) throws SQLException {
        ModelAndView mv;
        
        //String store=session.getAttribute("store").toString();
//        item.setSoldNum(0);
//        item.setRate(5);
        //Item titem=(Item)model.getAttribute("oldItem");
        if(item.getCurrentNum()<0){
            String msg="amount should be positive!";
            return new ModelAndView("updateItem","msg", msg);
        }
        ItemDao itemDao = new ItemDao();        
        int result=itemDao.updateItem(item);

        if(result==1) {
            mv = new ModelAndView("redirect:/viewItem.htm?name="+item.getName());
            //System.out.println("New todo Id: " + todo.getTodoId());    
        } else mv =  new ModelAndView("error");
//  mv=new ModelAndView("updateTest","titem",titem);
        
        
        return mv;
    }  
   
    @GetMapping("/deleteItem.htm")
    public ModelAndView deleteItem(@RequestParam("name") String name,HttpSession session) {
        ModelAndView mv=null;
        
        String store=session.getAttribute("store").toString();
        ItemDao itemDao = new ItemDao();
        int result = itemDao.deleteItem(name,store);
        if(result == 1) {
            mv = new ModelAndView("redirect:/updateStore.htm?name="+store);
            //System.out.println("Deleted todo Id: " + todoId);
        } else mv =  new ModelAndView("error");
        
       return mv;
    }
    
} 

