/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controller;
import com.bitlab.abstracts.AbstractManagement;
import com.bitlab.dao.DepartmentDao;
import com.bitlab.entities.Department;
import com.bitlab.entities.User;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Andrea
 */
public class DepartmentManagement extends AbstractManagement<Department>{
    private static Logger logger = LoggerFactory.getLogger(DepartmentManagement.class);
    
    DepartmentDao dDao = new DepartmentDao();
    @Override
    protected List<Department> findLike(String user) {
        System.out.println("Ingrese el nombre del departamento para buscar similitudes o presiones [cancel] para cancelar");
        try {
            return dDao.findLike(getCapture(user));
        } catch (SQLException | ClassNotFoundException ex) {
            logger.error("Ha ocurrido una excepcion en la busqueda de registros", ex);
        }
        return null;
    }

    @Override
    protected List<Department> getFindAll() {
        try {
            logger.debug("Se obtiene toda la lista de deparatamentos");
            return dDao.findAll();
        } catch (SQLException ex) {
            logger.error("Ha ocurrido una excepcion en la obtencion de datos", ex);
        } catch (ClassNotFoundException ex) {
            logger.error("Ha ocurrido una excepcion en la obtencion de datos", ex);
        }
        return null;
    }

    @Override
    protected Department find(int id) {
        try {
            logger.debug("Se obtiene los datos del registro de Departamento");
            return dDao.find(id);
        } catch (SQLException ex) {
            logger.error("Ha ocurrido una excepcion en la extracciond de datos", ex);
        } catch (ClassNotFoundException ex) {
            logger.error("Ha ocurrido una excepcion en la extracciond de datos", ex);
        }
        return null;
    }

    @Override
    protected String getFindToString(int id) {
        logger.debug("Obtiene los datos segun id de registro");
        try {
            return "-- [No.Departamento] " + find(id).getDeptNo() + " -- [Nombre] " + find(id).getDeptName() + "--";

        } catch (Exception e) {
            return "No se encontraron resultados";
        }
    }

    @Override
    public void updateRecord(String user) {
        try {
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        logger.debug("Actualización de registro de departamento");
        System.out.println("Ingrese el codigo de registro y posteriormente presione [enter] o [Cancel] para cancelar");
        Department d = new Department(validatedNumber(user));
        
        System.out.println("Estos son los registros actuales, actualice los registros que desee y escriba nuevamente los que no desea actualizar o [Cancel] para cancelar:");
        System.out.println(getFindToString(d.getDeptNo()));
        d.setDeptName(user);
        d.setUserChange(user);
        d.setDateChange(new Date());
        logger.debug("Se ejecuta el metodo captuteData");
        captureData(d, user);
        
            dDao.create(d);
        } catch (SQLException ex) {
            logger.error("Ha ocurrido una excepcion en la actualización", ex);
        } catch (ClassNotFoundException ex) {
            logger.error("Ha ocurrido una excepcion en la actualización", ex);
        }
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    
    @Override
    public void remove(int id) {
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        logger.debug("Eliminación de registro departamento");
        try {
            dDao.remove(id);
        } catch (SQLException ex) {
            logger.error("Ha ocurrido una excepcion en la actualizacion", ex);
        } catch (ClassNotFoundException ex) {
            logger.error("Ha ocurrido una excepcion en la actualizacion", ex);
        }
    }

    @Override
    public void addRecord(String user) {
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        logger.debug("Creacion de registro de departamento");
        Department d = new Department(0);
        d.setDeptName(user);
        d.setUserChange(user);
        d.setDateChange(new Date());
        d.setUserCreate(user);
        d.setDateCreate(new Date());
        logger.debug("Se ejecuta el metodo captuteData");
        captureData(d, user);
        try {
            dDao.create(d);
        } catch (SQLException ex) {
            logger.error("Ha ocurrido una excepcion en la creacion", ex);
        } catch (ClassNotFoundException ex) {
            logger.error("Ha ocurrido una excepcion en la creacion", ex);
        }
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    @Override
    public void captureData(Department u, String user) {
        try {   
            logger.debug("Captura de datos a guardar");
            System.out.print("Escriba nombre del nuevo departamento [enter] o [Cancel] para cancelar");
            u.setDeptName(getCapture(user));
         
        } catch (SQLException ex) {
            logger.error("Ha ocurrido una excepcion en la creacion", ex);
        }
    }
    
}
