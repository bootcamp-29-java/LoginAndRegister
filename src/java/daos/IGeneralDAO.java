/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.util.List;
import models.Account;

/**
 *
 * @author asus
 */
public interface IGeneralDAO<T> {

    public List<T> getAll();

    public T getById(Object id);
    public T getByUsername(Object username);
    public T getByEmail(Object email);
    public boolean saveOrDelete(T model, boolean issave);
    
}
