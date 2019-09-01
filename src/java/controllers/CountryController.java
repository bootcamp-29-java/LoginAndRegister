/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.GeneralDAO;
import daos.IGeneralDAO;
import icontrollers.ICountryController;
import java.math.BigDecimal;
import java.util.List;
import models.Country;
import models.Region;
import org.hibernate.SessionFactory;

/**
 *
 * @author Lenovo
 */
public class CountryController implements ICountryController{
    private IGeneralDAO<Country> igdao;

    public CountryController(SessionFactory factory) {
        igdao = new GeneralDAO<Country>(factory, Country.class);
    }

    @Override
    public List<Country> getAll() {
        return igdao.getAll();
    }

    @Override
    public Country getById(String id) {
        return igdao.getById(id);
    }

    @Override
    public String save(String id, String name, String regionId) {
        String result = "";
        Country country = new Country();
        Region region = new Region();
        region.setId(new BigDecimal(regionId));
        country.setId(id);
        country.setName(name);
        country.setRegion(region);
        try {
            if (igdao.saveOrDelete(country, true)) {
                result = "Data Berhasil Disimpan";
            }else{
                result = "Data Gagal Disimpan";
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return result;
    }

    @Override
    public String delete(String id) {
        String result = "";
        Country country = new Country();
        country.setId(id);
        try {
            if (igdao.saveOrDelete(country, false)) {
                result = "Data Berhasil Dihapus";
            }else{
                result = "Data Gagal Dihapus";
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return result;
    }
    
}
