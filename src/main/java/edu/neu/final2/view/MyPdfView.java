/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.view;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import edu.neu.final2.dao.OrderDao;
import edu.neu.final2.pojo.Customer;
import edu.neu.final2.pojo.Id;
import edu.neu.final2.pojo.Order;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.document.AbstractPdfView;

/**
 *
 * @author Susan Sun
 */
public class MyPdfView extends AbstractPdfView {

    HttpServletRequest request;
    HttpSession session;

    public MyPdfView(HttpServletRequest request,HttpSession session) {
        this.request = request;
        this.session = session;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document doc, PdfWriter writer, HttpServletRequest req, HttpServletResponse res) throws Exception {
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String conPassword = request.getParameter("confirmPassword");
//        String picture = request.getParameter("picture");
//        String gender = request.getParameter("gender");
//        String country = request.getParameter("country");
//        String[] hobbies = request.getParameterValues("hobby");
//        String address = request.getParameter("address");
//        Paragraph p1 = new Paragraph("Email is:"+email);
//        Paragraph p2 = new Paragraph("Password is:"+password);
//        Paragraph p3 = new Paragraph("Confirmed-Password is:"+conPassword);
//        Paragraph p4 = new Paragraph("Picture is:"+picture);
//        Paragraph p5 = new Paragraph("Gender is:"+gender);
//        Paragraph p6 = new Paragraph("Country is:"+country);
//        String hs = "";
//        for(String s : hobbies){
//            hs = hs + s + " ";
//        }
//        Paragraph p7 = new Paragraph("Hobbies are:"+hs);
//        Paragraph p8 = new Paragraph("Address is:"+address);
//        doc.add(p1);
//        doc.add(p2);
//        doc.add(p3);
//        doc.add(p4);
//        doc.add(p5);
//        doc.add(p6);
//        doc.add(p7);
//        doc.add(p8);
        ModelAndView mv;
        String msg="oders null";
        
        Customer customer=(Customer)request.getSession().getAttribute("customer");
        
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
        
        for(List<Order> order :orders){
            Paragraph p1=new Paragraph("Order ID : "+order.get(0).getId()+"     Order Date : "+order.get(0).getDate()+"    Order ID : "+order.get(0).getId());
            doc.add(p1);
            for(Order or:order){
                Paragraph p2=new Paragraph("item: "+or.getItem());
                Paragraph p3=new Paragraph("store: "+or.getStore());
                Paragraph p4=new Paragraph("seller: "+or.getSeller());
                Paragraph p5=new Paragraph("price: "+or.getPrice());
                Paragraph p6=new Paragraph("num: "+or.getNum());
                doc.add(p2);
                doc.add(p3);
                doc.add(p4);
                doc.add(p5);
                doc.add(p6);
            }
            
            
        }
       
    }

}
