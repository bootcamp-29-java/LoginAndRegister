/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.GeneralDAO;
import daos.IGeneralDAO;
import icontrollers.IAccountController;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.Account;
import models.Employee;
import org.hibernate.SessionFactory;
import tools.BCrypt;
import tools.Token;

/**
 *
 * @author Lenovo
 */
public class AccountController implements IAccountController{
    private IGeneralDAO<Account> igdao;
    private IGeneralDAO<Employee> egdao;

    public AccountController(SessionFactory factory) {
        igdao = new GeneralDAO<Account>(factory, Account.class);
        egdao = new GeneralDAO<Employee>(factory, Employee.class);
    }

    @Override
    public Account getById(String id) {
        return igdao.getById(id);
    }
    
    @Override
    public String login(String username, String password) {
        String result = "";
        Account account = igdao.getByUsername(username);
        if (BCrypt.checkpw(password, account.getPassword())) {
            result = "Berhasil Login";
        }else{
            result = "Gagal Login";
        }
        return result;
    }

    @Override
    public String register(String id, String username, String password, String status,String email) {
         String result = "";
//         Token token = new Token();
        String token = Token.generateToken();

        Account account = new Account();
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(id));
        account.setEmployee(employee);
        account.setStatus(status);
        account.setId(Integer.parseInt(id));
        account.setUsername(username);
        account.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        account.setStatus(status);
        account.setToken(token);
        if (igdao.saveOrDelete(account, true)) {
            result = "Cek Email Untuk Verifikasi Akun !";
        } else {
            result = "Ooops Kesalahan";
        }
        
        send(email, token,username);
        
        return result;
    }

    @Override
    public String verifRegister(String token, String username) {
        String result = "";
        Account account = igdao.getById(username);
        if (token.equals(account.getToken())) {
            result = "Verifikasi Berhasil";
        }else{
            result = "Verifikasi Gagal";
        }
        return result;
    }

    @Override
    public String updatePassword(String password, String username) {
        String result = "";
        Account account = new Account();
            try {
                account.setId(Integer.parseInt(username));
                account.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                if (igdao.saveOrDelete(account, true)) {
                    result = "Password Berhasil Dirubah";
                } else {
                    result = "Password Gagal Dirubah";
                }
            } catch (Exception e) {
                result = "ID Tidak Terdaftar";
            }
            return result;
    }

    @Override
    public boolean cekEmail(String id, String email) {
         boolean result = false;
        Employee employee = egdao.getById(Integer.parseInt(id));
        if (employee.getEmail().equals(email)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public void send(String to, String token, String username) {
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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Verifikasi Akun "+username);
            message.setText("Silahkan klik disini untuk memverifikasi akun http://localhost:8084/JavaWebProject/registerservlet?v="+token);
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    
}
