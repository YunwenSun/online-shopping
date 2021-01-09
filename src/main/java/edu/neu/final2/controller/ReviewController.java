/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.controller;

import edu.neu.final2.dao.ItemDao;
import edu.neu.final2.dao.ReviewDao;
import edu.neu.final2.pojo.Customer;
import edu.neu.final2.pojo.Item;
import edu.neu.final2.pojo.Review;
import java.util.List;
import javax.servlet.http.HttpServlet;
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
public class ReviewController extends HttpServlet{
   @GetMapping("/review.htm")
   public ModelAndView review(ModelMap model, HttpSession session,@RequestParam("name") String name,@RequestParam("store") String store){
       ModelAndView mv;
       ReviewDao reviewDao=new ReviewDao();
       Customer customer=(Customer)session.getAttribute("customer");
       String cus=customer.getUsername();
       List<Review> reviews=reviewDao.testReview(name, store, cus);
       if(reviews.isEmpty()){
           reviewDao.addNewReview(name, store, cus);
           reviews=reviewDao.testReview(name, store, cus);
       }
       Review review=reviews.get(0);
       model.addAttribute("reviewmodel",review);
       mv= new ModelAndView("review");
       //@ModelAttribute("review")
       return mv;
   }
   
      @PostMapping("/review.htm")
   public ModelAndView reviewpost(ModelMap model, HttpSession session,@ModelAttribute("reviewmodel") Review review,@RequestParam("name") String name,@RequestParam("store") String store){
       ModelAndView mv;
       ReviewDao reviewDao=new ReviewDao();
       String msg="review added successfully!";
       //Review review=
       int result=reviewDao.updateReview(review);
       
       ItemDao itemDao=new ItemDao();
       List<Item> items=itemDao.getItems(review.getItem(), review.getStore());
       Item item=items.get(0);
       int  rateset=itemDao.reviewItemRate(item, review.getRate());
       if(rateset!=1){
           System.err.println("rate set fail");
       }
       
       if(result==1) {
            model.addAttribute("reviewmodel",review);
            model.addAttribute("msg", msg);
            mv= new ModelAndView("reviewSuccess","reviewmodel",review);    
       }else {
           mv=new ModelAndView("error");
       }
       
       //@ModelAttribute("review")
       return mv;
   }
   
   @GetMapping("/reviewSuccess.htm")
    public ModelAndView reviewSuccess(ModelMap model) {
        return new ModelAndView("reviewSuccess");
    }
//   public void reviewpost(ModelMap model, HttpSession session,@ModelAttribute("reviewmodel") Review review){
//          System.out.println(review.getItem());
//          System.out.println(review.getStore());
//          System.out.println(review.getCustomer());
//          System.out.println(review.getRate());
//          System.out.println(review.getFeedback());
          //System.out.println(review.getStore());
         // System.out.println();

//       ModelAndView mv;
//       ReviewDao reviewDao=new ReviewDao();
//       String msg="review added successfully!";
//       //Review review=
//       int result=reviewDao.updateReview(review);
//       
//       ItemDao itemDao=new ItemDao();
//       List<Item> items=itemDao.getItems(review.getItem(), review.getStore());
//       Item item=items.get(0);
//       int  rateset=itemDao.reviewItemRate(item, review.getRate());
//       if(rateset!=1){
//           System.err.println("rate set fail");
//       }
//       if(result==1) {
//            model.addAttribute("reviewmodel",review);
//            model.addAttribute("msg", msg);
//            mv= new ModelAndView("review","reviewmodel",review);    
//       }else {
//           mv=new ModelAndView("error");
//       }
//       
//       //@ModelAttribute("review")
//,@RequestParam("name") String name,@RequestParam("store") String store
//       return mv;
   //}
}
