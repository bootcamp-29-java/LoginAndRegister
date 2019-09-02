/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import controllers.EmployeeController;
import controllers.RegisterLoginController;
import daos.IRegisterLoginDAO;
import daos.RegisterLoginDAO;
import icontrollers.IEmployeeController;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*; 
import models.Account;
import models.Employee;
import org.hibernate.SessionFactory;
import icontrollers.IRegisterLoginController;
/**
 *
 * @author Lenovo
 */
public class Testing {
  public static void send(String from,String password,String to,String sub,String msg){  
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);   
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    
             
    }  
  
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        IRegisterLoginController ireg =  new RegisterLoginController(factory);
        System.out.println(ireg.login("Diana12", "Diana123"));
    }
}