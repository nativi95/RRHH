/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controller;

import com.bitlab.abstracts.AbstractManagement;
import com.bitlab.dao.RolDao;
import com.bitlab.entities.Rol;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author CarlosAlex
 */
public class RolManagement extends AbstractManagement<Rol> {
    
    private static Logger logger = LoggerFactory.getLogger(RolDao.class);
    RolDao rDao=new RolDao();

    @Override
    protected List<Rol> getFindAll() {
        try {
            logger.debug("Se obtiene lista de roles de usuario");
            return rDao.findAll();
        } catch (SQLException ex) {

            logger.error("Ha ocurrido una excepcion en la eliminación", ex);
        } catch (ClassNotFoundException ex) {

            logger.error("Ha ocurrido una excepcion en la eliminación", ex);
        }
        return null;
    }

    @Override
    protected Rol find(int id) {
         try {
            logger.debug("Se obtiene los datos del registro de rol del usuario");
            return rDao.find(id);
        } catch (SQLException ex) {

            logger.error("Ha ocurrido una excepcion en la eliminación", ex);
        } catch (ClassNotFoundException ex) {

            logger.error("Ha ocurrido una excepcion en la eliminación", ex);
        }
        return null;
    }

    @Override
    protected String getFindToString(int id) {
        logger.debug("Obtiene los datos segun id de registro");
        try {
            return "--" + find(id).getRolRolNo() + " " + find(id).getRolRol() + " [contraseña] " + "--";

        } catch (Exception e) {
            return "No se encontraron resultados";
        }
    }

    @Override
    public void updateRecord(String user) {
         try {
            System.out.println("\n----------------------------------------------------------------\n");
            logger.debug("Actualización de Rol de usuario");
            System.out.println("Ingrese el codigo de registro y posteriormente presione [enter] o [Cancel] para cancelar");

            Rol u = new Rol(validatedNumber(user));
            System.out.println("Estos son los registros actuales, actualice los registros que desee y escriba nuevamente los que no desea actualizar o [Cancel] para cancelar:");
            System.out.println(getFindToString(u.getRolRolNo()));
            u.setUserChange(user);
            u.setDateChange(new Date());
            logger.debug("Ejectuta metodo captureData");
            captureData(u, user);
            try {
                rDao.update(u);
            } catch (SQLException | ClassNotFoundException ex) {
                logger.error("Ha ocurrido una excepcion en la actualizacion", ex);

            }
            System.out.println("\n------------------------------------------------------------------\n");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void captureData(Rol u, String user) {
        try {
            logger.debug("Captura de datos a guardar");
            System.out.print("Escriba el  nuevo rol del usuario y despues presione [enter] o [Cancel] para cancelar");
            u.setRolRol(getCapture(user));
       
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(RolManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(int id) {
         System.out.println("\n-----------------------------------------------------------\n");
        logger.debug("Eliminación de Rol de usuario");
        try {
            rDao.remove(id);
        } catch (SQLException ex) {

            logger.error("Ha ocurrido una excepcion en la actualizacion", ex);
        } catch (ClassNotFoundException ex) {

            logger.error("Ha ocurrido una excepcion en la actualizacion", ex);
        }
    }

    @Override
    public void addRecord(String user) {
        System.out.println("\n----------------------------------------------------\n");
        logger.debug("Creacion de Rol de Usuario");
        Rol r = new Rol(0);
        r.setUserChange(user);
        r.setDateChange(new Date());
        r.setUserCreate(user);
        r.setDateCreate(new Date());
        logger.debug("Se ejecuta el metodo captuteData");
        captureData(r, user);
        try {
            rDao.create(r);
        } catch (SQLException ex) {

            logger.error("Ha ocurrido una excepcion en la creacion", ex);
        } catch (ClassNotFoundException ex) {

            logger.error("Ha ocurrido una excepcion en la creacion", ex);
        }
        System.out.println("\n------------------------------------------------------\n");
    }

    @Override
    protected List<Rol> findLike(String user) {
    System.out.println("Ingrese el nombre del rol para buscar similitudes o presiones [cancel] para cancelar");
        try {
            return rDao.findLike(getCapture(user));
        } catch (SQLException | ClassNotFoundException ex) {
            logger.error("Ha ocurrido una excepcion en la busqueda de registros", ex);
        }
        return null;
    }

}
