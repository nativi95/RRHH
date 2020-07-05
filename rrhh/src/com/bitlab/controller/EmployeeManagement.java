/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controller;

import com.bitlab.abstracts.AbstractManagement;
import com.bitlab.dao.DepartmentDao;
import com.bitlab.dao.EmployeeDao;
import com.bitlab.dao.PositionDao;
import com.bitlab.entities.Department;
import com.bitlab.entities.Employee;
import com.bitlab.entities.Position;
import com.bitlab.util.DatesControls;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nativi
 */
public class EmployeeManagement extends AbstractManagement<Employee> {

    private EmployeeDao eDao;

    @Override
    protected List<Employee> findLike(String user) {
        try {
            System.out.println("Ingrese el nombre de empleado o [cancel] para cancelar");
            String name=getCapture(user);
            System.out.println("Ingrese el apellido de empleado o [cancel] para cancelar");
            String lastName=(getCapture(user));
            return eDao.findLike(name, lastName);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected List<Employee> getFindAll() {
        try {
            return eDao.findAll();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected Employee find(int id) {
        try {
            return eDao.find(id);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected String getFindToString(int id) {
        Employee e;
        try {
            e = eDao.find(id);
            return "--" + e.getEmpNo() + ", " + e.getFirstName() + ", " + e.getLastName() + ", nacimiento " + e.getBirthDate() + "Genero " + e.getGender() + ", contratación " + e.getHireDate() + ", Departamento " + e.getDeptNo().getDeptName() + ", Cargo " + e.getPositionNo().getPosition();
    
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }

    @Override
    public void updateRecord(String user) {
        eDao = new EmployeeDao();
        System.out.println("Ingrese el Id del registro por actualizar ");
        Employee e = new Employee();
        try {
            e = new Employee(validatedNumber(user));
            System.out.println("Estos son los registros actuales, escriba nuavemente los campos que no desea actualizar");
            System.out.println(getFindToString(e.getEmpNo()));
            e.setHireDate(eDao.find(e.getEmpNo()).getHireDate());
            captureData(e, user);
            eDao.update(e);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        e.setUserChange(user);
        e.setUserCreate(user);
        e.setDateChange(new Date());
        e.setDateCreate(new Date());
        e.setHireDate(new Date());
        captureData(e, user);
    }

    @Override
    public void captureData(Employee e, String user) {
        
        try {
            System.out.println("Ingrese el nombre de empleado o [cancel] para cancelar");
            e.setFirstName(getCapture(user));
            System.out.println("Ingrese el apellido de empleado o [cancel] para cancelar");
            e.setLastName(getCapture(user));
            System.out.println("Ingrese la fecha de nacimiento del empleado dd-MM-yyyy o [cancel] para cancelar");
            e.setBirthDate(DatesControls.stringToDate(getCapture(user)));
            DepartmentDao dDao = new DepartmentDao();
            List<Department> lsDeparment = dDao.findAll();
            System.out.println("Especifique a que departamento pertenece el empleado escribiendo el numero correspondiente posterior de [enter] o [cancel] para cancelar");
            boolean bandera = true;
            int captura;
            while (bandera) {
                for (byte i = 0; i < lsDeparment.size(); i++) {
                    System.out.println(i + ". " + lsDeparment.get(i).getDeptName());
                }
                captura = validatedNumber(user);
                if (captura < lsDeparment.size() && captura >= 0) {
                    e.setDeptNo(lsDeparment.get(captura));
                    bandera = false;
                } else {
                    System.out.println("Escriba una de las opciones de la lista\n");
                    bandera = true;
                }
            }
            bandera = true;
            PositionDao pDao = new PositionDao();
            List<Position> lsPosition = pDao.findAll();
            System.out.println("Especifique a que departamento pertenece el empleado escribiendo el numero correspondiente posterior de [enter] o [cancel] para cancelar");
            while (bandera) {
                for (byte i = 0; i < lsDeparment.size(); i++) {
                    System.out.println(i + ". " + lsPosition.get(i).getPosition());
                }
                captura = validatedNumber(user);
                if (captura < lsPosition.size() && captura >= 0) {
                    e.setPositionNo(lsPosition.get(captura));
                    bandera = true;
                } else {
                    bandera = true;
                }
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void remove(int id
    ) {
        eDao = new EmployeeDao();
        try {
            eDao.remove(id);
        } catch (SQLException | ClassNotFoundException ex) {

            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void addRecord(String user
    ) {
        eDao = new EmployeeDao();
        Employee e = new Employee(0);
        e.setUserChange(user);
        e.setUserCreate(user);
        e.setDateChange(new Date());
        e.setDateCreate(new Date());
        e.setHireDate(new Date());
        captureData(e, user);
        try {
            eDao.create(e);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
