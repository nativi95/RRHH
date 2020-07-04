/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controller;

import com.bitlab.abstracts.AbstractManagement;
import com.bitlab.dao.UserDao;
import com.bitlab.entities.User;
import com.bitlab.util.DatesControls;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nativi
 */
public class UserManagement extends AbstractManagement<User> {
    
    UserDao uDao = new UserDao();
    
    public UserManagement(String user) throws SQLException {
        super(user);
    }
    
    @Override
    public void captuteData(User u) {
        System.out.print("Escriba el " + uDao.getColumnsName().get(0));
        System.out.println("y despues presione [enter]");
        u.setUser(getCapture());
        System.out.print("Escriba el " + uDao.getColumnsName().get(1));
        System.out.println("y despues presione [enter]");
        u.setUser(getCapture());
        System.out.print("Escriba el " + uDao.getColumnsName().get(2));
        System.out.println("y despues presione [enter]");
        u.setUser(getCapture());
    }
    
    @Override
    public void addRecord(String user) {
        User u = new User(0);
        u.setUserChange(user);
        u.setDateChange(DatesControls.dateToGregorian(new Date()));
        u.setUserCreate(user);
        u.setDateCreate(DatesControls.dateToGregorian(new Date()));
        captuteData(u);
        try {
            uDao.create(u);
        } catch (SQLException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected String getFindToString(int id) {
        return find(id).toString();
    }
    
    @Override
    public void updateRecord(String user) {
        System.out.println("Ingrese el codigo de registro y posteriormente presione [enter]");
        getCapture();
        User u = new User();
        System.out.println("Estos son los registros actuales, actualice los registros que desee y escriba nuevamente los que no desea actualizar:");
        System.out.println(getFindToString(validatedNumber()));
        u.setUserNo(validatedNumber());
        u.setUserChange(user);
        u.setDateChange(DatesControls.dateToGregorian(new Date()));
        captuteData(u);
        try {
            uDao.update(u);
            ////
        } catch (SQLException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void remove(int id) {
        try {
            uDao.remove(id);
        } catch (SQLException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected List<User> getFindAll() {
        try {
            return uDao.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    protected User find(int id) {
        try {
            return uDao.find(id);
        } catch (SQLException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
