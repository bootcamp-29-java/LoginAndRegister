/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icontrollers;

import models.Region;
import java.util.List;

/**
 *
 * @author Reza
 */
public interface IRegionController {
    public List<Region> getAll();
    public Region getById(String id);
    public String save(String id, String name);
    public String delete(String id);
}
