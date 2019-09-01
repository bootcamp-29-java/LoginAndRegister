/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icontrollers;

import java.util.List;
import models.Country;

/**
 *
 * @author Lenovo
 */
public interface ICountryController {
    public List<Country> getAll();
    public Country getById(String id);
    public String save(String id, String name,String regionId);
    public String delete(String id);
}
