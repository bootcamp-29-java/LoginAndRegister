/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.GeneralDAO;
import daos.IGeneralDAO;
import icontrollers.IRegionController;
import java.math.BigDecimal;
import java.util.List;
import models.Region;
import org.hibernate.SessionFactory;
import tools.HibernateUtil;

/**
 *
 * @author asus
 */
public class RegionController implements IRegionController {

    private IGeneralDAO<Region> igdao;

    public RegionController(SessionFactory factory) {
        igdao = new GeneralDAO<Region>(factory, Region.class);
    }

    @Override
    public List<Region> getAll() {
        return igdao.getAll();
    }

    @Override
    public Region getById(String id) {
        return igdao.getById(new BigDecimal(id));
    }

    @Override
    public String save(String id, String name) {
        String result = "";
        Region region = new Region();
        region.setId(new BigDecimal(id));
        region.setName(name);
        if(igdao.saveOrDelete(region, true)){
            result ="Data Berhasil Disimpan";
        }else{
            result ="Maaf Data Gagal Disimpan";
        }
        return result;
    }

    @Override
    public String delete(String id) {
        String result = "";
        Region region = new Region();
        region.setId(new BigDecimal(id));
        if(igdao.saveOrDelete(region, false)){
            result ="Data Berhasil Dihapus";
        }else{
            result ="Maaf Data Gagal Dihapus";
        }
        return result;
    }

}
