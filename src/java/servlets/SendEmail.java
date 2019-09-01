/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controllers.EmployeeController;
import controllers.RegisterLoginController;
import icontrollers.IEmployeeController;
import icontrollers.IRegisterLoginController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Account;
import models.Employee;
import org.hibernate.SessionFactory;
import tools.HibernateUtil;
import tools.Testing;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "SendEmail", urlPatterns = {"/sendemail"})
public class SendEmail extends HttpServlet {
    
    private SessionFactory factory = HibernateUtil.getSessionFactory();
    private IRegisterLoginController iec = new RegisterLoginController(factory);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        
        try (PrintWriter out = response.getWriter()) {
            out.println("Success");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String verif = request.getParameter("action");
        if (verif.equals("resetpassword")) {
        String id = request.getParameter("v");
            request.getSession().setAttribute("token", id);
            response.sendRedirect("resetpassword.jsp?v="+id);
        }
        
        
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email        = request.getParameter("emailVerif");
//        Employee employee   = iec.getByEmail(email);
//        String subject      = "Reset Password Akun ";
//        String link         = "http://localhost:8084/JavaWebProject/verifikasi?action=resetpassword&&v="+employee.getId()+"";
//        String meesage      = "Silahkan klik link berikut untuk mereset password anda :"+link;
//        if (employee.getId() != null) {
//            sendEmail.send("wahyusukses28@gmail.com", "sartim solikah", email, subject, meesage);
//            request.getSession().setAttribute("status", "Email Reset Password Telah Dikirimkan!");
//            response.sendRedirect("login.jsp");
//        }else{
//            request.getSession().setAttribute("status", "Email Anda Tidak Terdaftar!");
//            response.sendRedirect("login.jsp");            
//        }
        Account account = iec.getByEmail(email);
        if (account !=null) {
            iec.sendForgotPassword(account.getToken(), email);
            request.getSession().setAttribute("token", account.getToken());
            request.getSession().setAttribute("status", "Email Reset Password Telah Dikirimkan!");
            response.sendRedirect("login.jsp");
        }else{
            request.getSession().setAttribute("status", "Email Anda Tidak Terdaftar!");
            response.sendRedirect("login.jsp"); 
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
