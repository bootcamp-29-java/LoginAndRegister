/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.GeneralDAO;
import daos.IGeneralDAO;
import icontrollers.IEmployeeController;
import models.Employee;
import org.hibernate.SessionFactory;

/**
 *
 * @author Lenovo
 */
public class EmployeeController implements IEmployeeController{
    private IGeneralDAO<Employee> igdao;

    public EmployeeController(SessionFactory factory) {
        igdao = new GeneralDAO<Employee>(factory, Employee.class);
    }
  @Override
    public Employee getByEmail(String email) {
        return igdao.getByEmail(email);
    }  
}
