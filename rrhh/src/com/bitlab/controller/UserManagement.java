/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controller;

import com.bitlab.abstracts.AbstractManagement;
import com.bitlab.dao.UserDao;
import com.bitlab.entities.Rol;
import com.bitlab.entities.User;
import com.bitlab.util.DatesControls;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nativi
 */
public class UserManagement extends AbstractManagement<User> {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(UserDao.class);
    
    UserDao uDao = new UserDao();

    public UserManagement(String user) throws SQLException {
        super(user);
    }

    
    @Override
    public void captuteData(User u) {
        logger.debug("Captura de datos a guardar");
        System.out.print("Escriba el usuario nuevo y despues presione [enter]");
        u.setUser(getCapture());
        System.out.print("Escriba el contraseña y despues presione [enter]");
        u.setPassword(getCapture());
        System.out.print("Escriba el ID de rol 1 es administrador y 2. rrhh y despues presione [enter]");
        Rol r= new Rol(validatedNumber());
        u.setRolNo(r);
    }
    
    @Override
    public void addRecord(String user) {
        logger.debug("Creacion de registro de usuario");
        User u = new User(0);
        u.setUserChange(user);
        u.setDateChange(new Date());
        u.setUserCreate(user);
        u.setDateCreate(new Date());
        logger.debug("Se ejecuta el metodo captuteData");
        captuteData(u);
        try {
            uDao.create(u);
        } catch (SQLException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la creacion", ex);
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la creacion", ex);
        }
    }
    
    @Override
    protected String getFindToString(int id) {
        logger.debug("Obtiene los datos segun id de registro");
        return find(id).toString();
    }
    
    @Override
    public void updateRecord(String user) {
        logger.debug("Actualización de registro de usuario");
        System.out.println("Ingrese el codigo de registro y posteriormente presione [enter]");
        getCapture();
        User u = new User();
        System.out.println("Estos son los registros actuales, actualice los registros que desee y escriba nuevamente los que no desea actualizar:");
        System.out.println(getFindToString(validatedNumber()));
        u.setUserNo(validatedNumber());
        u.setUserChange(user);
        u.setDateChange(new Date());
        logger.debug("Ejectuta metodo captuteData");
        captuteData(u);
        try {
            uDao.update(u);
        } catch (SQLException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la actualizacion", ex);

        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la actualizacion", ex);

        }
    }
    
    @Override
    public void remove(int id) {
        logger.debug("Eliminación de registro usuario");
        try {
            uDao.remove(id);
        } catch (SQLException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la actualizacion", ex);
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la actualizacion", ex);
        }
    }
    
    @Override
    protected List<User> getFindAll() {
        try {
            logger.debug("Se obtiene toda la lista de usuarios");
            return uDao.findAll();
        } catch (SQLException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la eliminación", ex);
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la eliminación", ex);
        }
        return null;
    }
    
    @Override
    protected User find(int id) {
        try {
            logger.debug("Se obtiene los datos del registro de usuario");
            return uDao.find(id);
        } catch (SQLException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
               logger.error("Ha ocurrido una excepcion en la eliminación", ex);
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la eliminación", ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        try {
            UserManagement uM= new UserManagement("roberto");
        } catch (SQLException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
