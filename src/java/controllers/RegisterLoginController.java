/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.GeneralDAO;
import daos.IGeneralDAO;
import daos.RegisterLoginDAO;
import models.Account;
import org.hibernate.SessionFactory;
import daos.IRegisterLoginDAO;
import icontrollers.IAccountController;
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
import models.Country;
import models.Employee;
import tools.BCrypt;
import tools.Token;

/**
 *
 * @author Lenovo
 */
public class RegisterLoginController implements IRegisterLoginController {

    private IRegisterLoginDAO ireg;
    IGeneralDAO<Account> igdao;
    public RegisterLoginController(SessionFactory factory) {
        ireg = new RegisterLoginDAO(factory);
        igdao = new GeneralDAO<Account>(factory, Account.class);
    }

    @Override
    public String updateAccountStatus(String token) {
        Account account = ireg.getByToken(token);
        account.setStatus("0");
        account.setToken("");
        String result;
        if (ireg.updateAccountStatus(account)) {
            result = "Akun Anda Telah Aktif";
        } else {
            result = "Akun Anda Belum Aktif";
        }
        return result;
    }

    @Override
    public String sendForgotPassword(String token, String email) {
        String result;
        Account account = ireg.getByEmail(email);
        account.setToken(Token.generateToken());
        igdao.saveOrDelete(account, true);
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
            message.setSubject("Verikasi Akun "+account.getUsername());
            message.setText("Silahkan klik disini untuk mereset password anda! http://localhost:8084/JavaWebProject/sendemail?action=resetpassword&&v=" + account.getToken());
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
    public String updatePassword(String token, String password) {
        Account account = ireg.getByToken(token);
        String pass = BCrypt.hashpw(password, BCrypt.gensalt());
        account.setPassword(pass);
        account.setStatus("0");
        String result;
        try {
        if (ireg.updateAccountStatus(account)) {
            result = "Password Sudah Dirubah";
        } else {
            result = "Password Gagal Dirubah";
        }
        } catch (Exception e) {
            result = "Error Ke Catch";
        }
        return result;
    }

    @Override
    public String login(String username, String password) {
        String result = "";
        try {
            Account account = ireg.getByUsername(username);
            if (account.getStatus().equalsIgnoreCase("Lock")) {
                result = "Akun Anda Terkunci";
            }else if(account.getStatus().equalsIgnoreCase("Belum Aktif")){
                result = "Akun Anda Belum Aktif";
            
            } else if (BCrypt.checkpw(password, account.getPassword()) && Integer.parseInt(account.getStatus()) < 3) {
                Account acc = ireg.getByUsername(username);
                acc.setStatus("0");
                ireg.updateAccountStatus(acc);
                result = "Berhasil Login";
            } else {
                int salah = Integer.parseInt(account.getStatus()) + 1;
                account.setStatus(String.valueOf(salah));
                System.out.println(salah);
                if (ireg.updateAccountStatus(account)) {
                    Account ac = ireg.getByUsername(username);
                    if (Integer.parseInt(ac.getStatus()) >= 3) {
                        ac.setStatus("Lock");
                        ireg.updateAccountStatus(ac);
                        result = "Akun Anda terkunci";
                    } else {
                        result = "Login Gagal";
                    }
                }
            }
        } catch (Exception e) {
            result = "Username Tidak terdaftar";
        }
        return result;
    }

    @Override
    public Account getByToken(String token) {
        return ireg.getByToken(token);
    }
}
