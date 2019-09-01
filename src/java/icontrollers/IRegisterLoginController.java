/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icontrollers;

import models.Account;

/**
 *
 * @author Lenovo
 */
public interface IRegisterLoginController {
    public String updateAccountStatus(String token);
    public String sendForgotPassword(String token, String email);
    public Account getByEmail(String email);
    public String updatePassword(String token, String password);
    public String login(String username, String password);
}
