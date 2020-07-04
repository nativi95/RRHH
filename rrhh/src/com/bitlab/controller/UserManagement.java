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
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author nativi
 */
public class UserManagement extends AbstractManagement<User> {

    private static Logger logger = LoggerFactory.getLogger(UserDao.class);

    UserDao uDao = new UserDao();

    @Override
    public void captuteData(User u, String user) {
        try {
            logger.debug("Captura de datos a guardar");
            System.out.print("Escriba el usuario nuevo y despues presione [enter] o [Cancel] para cancelar");
            u.setUser(getCapture(user));
            System.out.print("Escriba la contraseña y despues presione [enter] o [Cancel] para cancelar");
            u.setPassword(getCapture(user));
            System.out.print("Escriba el ID de rol 1 es administrador y 2. rrhh y despues presione [enter] o [Cancel] para cancelar");
            Rol r = new Rol(validatedNumber(user));
            u.setRolNo(r);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addRecord(String user) {
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        logger.debug("Creacion de registro de usuario");
        User u = new User(0);
        u.setUserChange(user);
        u.setDateChange(new Date());
        u.setUserCreate(user);
        u.setDateCreate(new Date());
        logger.debug("Se ejecuta el metodo captuteData");
        captuteData(u, user);
        try {
            uDao.create(u);
        } catch (SQLException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la creacion", ex);
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la creacion", ex);
        }
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    @Override
    protected String getFindToString(int id) {
        logger.debug("Obtiene los datos segun id de registro");
        try {
            return "--" + find(id).getUserNo() + " " + find(id).getUser() + " [contraseña] " + find(id).getRolNo().getRolRol() + "--";

        } catch (Exception e) {
            return "No se encontraron resultados";
        }
    }

    @Override
    public void updateRecord(String user) {
        try {
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            logger.debug("Actualización de registro de usuario");
            System.out.println("Ingrese el codigo de registro y posteriormente presione [enter] o [Cancel] para cancelar");

            User u = new User(validatedNumber(user));
            System.out.println("Estos son los registros actuales, actualice los registros que desee y escriba nuevamente los que no desea actualizar o [Cancel] para cancelar:");
            System.out.println(getFindToString(u.getUserNo()));
            u.setUserChange(user);
            u.setDateChange(new Date());
            logger.debug("Ejectuta metodo captuteData");
            captuteData(u, user);
            try {
                uDao.update(u);
            } catch (SQLException | ClassNotFoundException ex) {
                logger.error("Ha ocurrido una excepcion en la actualizacion", ex);

            }
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void remove(int id) {
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
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
        UserManagement uM = new UserManagement();
        try {
            uM.AbstractManagement("roberto");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
