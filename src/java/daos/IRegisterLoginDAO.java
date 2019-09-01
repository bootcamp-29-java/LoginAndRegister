/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import models.Account;
import models.Employee;

/**
 *
 * @author Lenovo
 */
public interface IRegisterLoginDAO {
    public boolean updateAccountStatus(Account account);
    public Account getByToken(String token);
    public Account getByEmail(String account);
    public Account getByUsername(String username);
}
