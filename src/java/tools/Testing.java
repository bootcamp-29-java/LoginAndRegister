/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import icontrollers.IEmployeeController;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Lenovo
 */
public class Testing {

    public static void send(String from, String password, String to, String sub, String msg) {
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
                return new PasswordAuthentication(from, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

//        SessionFactory factory = HibernateUtil.getSessionFactory();
//        IRegisterLoginController ireg =  new RegisterLoginController(factory);
//        Account account = ireg.getByToken("QLPnGL2d8DDDsax4duvD");
//        System.out.println(account);
//        if (account == null) {
//            System.out.println("kesini");
//        }
//        Timer timer;
//
//    
//
//    public Testing(int seconds) {
//        timer = new Timer();
//        timer.schedule(new RemindTask(), seconds * 1000);
//    }
//
//    class RemindTask extends TimerTask {
//
//        public void run() {
//            System.out.println("Time's up!");
//            timer.cancel(); //Terminate the timer thread
//        }
//    }
    public static void main(String args[]) throws java.text.ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        Date date = new Date();
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime = dateParser.parse(format.format(date));
        long gmtMilliSeconds = dateTime.getTime();
        System.out.println(dateTime);
    }

}
