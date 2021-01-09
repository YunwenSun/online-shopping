/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.controller;

import edu.neu.final2.dao.ItemDao;
import edu.neu.final2.pojo.Item;
import edu.neu.final2.controller.*;
import edu.neu.final2.dao.CartDao;
import edu.neu.final2.dao.ReviewDao;
import edu.neu.final2.dao.StoreDao;
import edu.neu.final2.pojo.Customer;
import edu.neu.final2.pojo.Review;
import edu.neu.final2.pojo.Store;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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

public class ShoppingController extends HttpServlet {
@GetMapping("/shopping.htm")
    public ModelAndView getShopping(ModelMap model){        
        ItemDao itemDao=new ItemDao();
        
        List<Item> searchedResults=itemDao.searchAllItems();
        return new ModelAndView("shopping","searchResults",searchedResults);
    }
    
    @PostMapping("/shopping.htm")
    public ModelAndView postShopping(HttpServletRequest request,ModelMap model,HttpSession session){
       
        String searchKey=request.getParameter("searchKey");
        String keyWord=request.getParameter("keyWord");
        
        List<Item> searchedResults;
        ItemDao itemDao=new ItemDao();
        if(searchKey.equals("name")){
            searchedResults=itemDao.searchItemsApprox(keyWord);   
        }else if(searchKey.equals("store")){
            searchedResults=itemDao.searchItemsbyStore(keyWord);
        }else{
            searchedResults=itemDao.searchItemsbyType(keyWord);
        }
        session.setAttribute("searchResults",searchedResults);
        
        return new ModelAndView("shopping","searchResults",searchedResults);
    }
    
    @GetMapping("/shopItem.htm")
    public ModelAndView shopItemget(@RequestParam("name") String name,@RequestParam("store") String store,HttpServletRequest request,ModelMap model,HttpSession session){
        ItemDao itemDao=new ItemDao();
        Item item=itemDao.getItems(name, store).get(0);
        
        ReviewDao reviewDao=new ReviewDao();
        List<Review> reviews=reviewDao.getToReview(name, store);
        model.addAttribute("viewreviews", reviews);
              
        session.setAttribute("item", item);
        return new ModelAndView("shopItem","item",item);
}    
    @PostMapping("/shopItem.htm")
    public ModelAndView shopItempost(HttpServletRequest request,ModelMap model,HttpSession session){
        ModelAndView mv;
        String msg;
        
        Customer customer=(Customer) session.getAttribute("customer");
        //Item item=(Item)model.getAttribute("item");
              
        Item item=(Item)session.getAttribute("item");
        String storename=item.getStore();
        
        String n=request.getParameter("numToAdd");
        int num=Integer.valueOf(n);
        if(storename==null){
            return new ModelAndView("error");
        }
        StoreDao storeDao=new StoreDao();
        Store store=storeDao.getStores(storename).get(0);
        String seller=store.getOwner();
        
        CartDao cartDao=new CartDao();
        int result=0;
        
        if(cartDao.testItem(customer.getUsername(), item).isEmpty()){
            result=cartDao.addtoCart(customer.getUsername(),seller,item,num);
            msg="item added successfully!";
        }else{
            msg="item already exist in cart!";
        }
        
       if(result==1){
           mv=new ModelAndView("shopItem","msg",msg);
       }
       else mv=new ModelAndView("error");
       
       return mv;
}
    

}
