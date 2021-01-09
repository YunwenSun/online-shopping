/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.final2.interceptor;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Susan Sun
 */
public class sellerLoginInterceptor extends HandlerInterceptorAdapter{

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView); //To change body of generated methods, choose Tools | Templates.
        request.setAttribute("msg", "");
        System.out.println("postHandle");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            Enumeration<String> paraNames = request.getParameterNames();
            while(paraNames.hasMoreElements()){
                String key = paraNames.nextElement();
                String val = request.getParameter(key);
                if(val==null){
                    request.setAttribute("msg", "content can not be null!");                      
                    request.getRequestDispatcher("/WEB-INF/jsp/sellerLogin.jsp").forward(request, response);
                    System.out.println("unsafe");
                    break;
                }else{
                    request.setAttribute("msg", "");
                    //request.getRequestDispatcher("customerAdmin").forward(request, response);
                    System.out.println("safe");
                }
                System.out.println("preHandle");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return true;
    }
    private boolean xssCheck(String value){
        if(value!=null){
            System.out.println("xssCheck");
            return (value.matches("<script>(.*?)</script>")||value.matches("\"<script(.*?)>\"")||value.matches("src[\r\n]*=[\r\n]*\\\"(.*?)\\\""));
        }
        return false;
    }
    
}
