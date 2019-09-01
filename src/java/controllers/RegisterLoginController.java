/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.RegisterLoginDAO;
import models.Account;
import org.hibernate.SessionFactory;
import daos.IRegisterLoginDAO;
import icontrollers.IRegisterLoginController;
import static java.lang.ProcessBuilder.Redirect.to;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tools.BCrypt;

/**
 *
 * @author Lenovo
 */
public class RegisterLoginController implements IRegisterLoginController{

    private IRegisterLoginDAO ireg;
    
    public RegisterLoginController(SessionFactory factory) {
       ireg =  new RegisterLoginDAO(factory);
    }
    
    @Override
    public String updateAccountStatus(String token) {
        Account account = ireg.getByToken(token);
        account.setStatus("Aktif");
        String result;
        if (ireg.updateAccountStatus(account)) {
            result = "Akun Anda Telah Aktif";
        }else{
            result = "Akun Anda Belum Aktif";
        }
        return result;
    }

    @Override
    public String sendForgotPassword(String token, String email) {
        String result;
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("wahyukncro@gmail.com", "terate1922");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Verikasi Email");
            message.setText("Silahkan klik disini untuk mereset password anda! http://localhost:8084/JavaWebProject/sendemail?action=resetpassword&&v="+token);
            Transport.send(message);
            result = "Silahkan Cek Email Anda!";
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Account getByEmail(String email) {
        return ireg.getByEmail(email);
    }
    
    @Override
    public String updatePassword(String token,String password) {
        Account account = ireg.getByToken(token);
        account.setPassword(password);
        String result;
        if (ireg.updateAccountStatus(account)) {
            result = "Password Sudah Dirubah";
        }else{
            result = "Password Gagal Dirubah";
        }
        return result;
    }

    @Override
    public String login(String username, String password) {
         String result = "";
        Account account = ireg.getByUsername(username);
        if (BCrypt.checkpw(password, account.getPassword())) {
            result = "Berhasil Login";
        }else{
            result = "Gagal Login";
        }
        return result;
}
}
