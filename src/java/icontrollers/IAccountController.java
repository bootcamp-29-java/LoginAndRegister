/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icontrollers;

import models.Account;
import models.Employee;

/**
 *
 * @author Lenovo
 */
public interface IAccountController {
    public String login(String username, String password);
    public String register(String id, String username, String password, String status, String to);
    public String verifRegister(String token, String username);
    public String updatePassword(String password,String username);
    public Account getById(String id);
    public boolean cekEmail(String id, String email);
    public void send(String to, String token);
}
