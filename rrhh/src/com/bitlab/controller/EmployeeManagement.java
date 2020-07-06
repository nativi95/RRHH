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
import com.bitlab.util.Validate;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nativi
 */
public class EmployeeManagement extends AbstractManagement<Employee> {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(DepartmentManagement.class);
    private EmployeeDao eDao;
    Scanner scan = new Scanner(System.in);
    String fromCmd = null;
    Validate v;

    @Override
    protected List<Employee> findLike(String user) {
        eDao = new EmployeeDao();
        System.out.println("Ingrese el nombre de empleado o [cancel] para cancelar");
        String name = scan.nextLine();
        if (!name.toLowerCase().equals("cancel")) {
            System.out.println("Ingrese el apellido de empleado o [cancel] para cancelar");
            String lastName = scan.nextLine();

            if (!name.toLowerCase().equals("cancel")) {
                List<Employee> lsEmployee = eDao.findLike(name, lastName);
                if (!lsEmployee.isEmpty()) {
                    return lsEmployee;
                }
                return null;
            }
            return null;
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
            System.out.println("Objeto: " + e.toString());
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
        Employee e;
        v = new Validate();
        try {
            e = new Employee(v.isNumeric());
            if (e.getEmpNo() != 0) {

                System.out.println("Estos son los registros actuales, escriba nuavemente los campos que no desea actualizar");
                System.out.println(getFindToString(e.getEmpNo()));
                e.setHireDate(eDao.find(e.getEmpNo()).getHireDate());

                e.setUserChange(user);
                e.setDateChange(new Date());
                e.setHireDate(eDao.find(e.getEmpNo()).getHireDate());
                System.out.println("Ingrese correo electronico del empleado o escriba [cancel] para cancelar");
                fromCmd = scan.nextLine().toLowerCase();
                if (!fromCmd.equals("cancel")) {
                    e.setEmail(fromCmd);
                    captureData(e, user);
                    if (e.getPositionNo() != null) {
                        eDao.update(e);
                    }
                }

            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void captureData(Employee e, String user) {
        v = new Validate();
        logger.debug("Ingresando a metodo captureData");
        try {
            logger.debug("Setendo nombre de empleado");
            System.out.println("Ingrese el nombre de empleado o [cancel] para cancelar");
            fromCmd = scan.nextLine();
            if (!fromCmd.equalsIgnoreCase("cancel")) {
                e.setFirstName(fromCmd);
                logger.debug("Seteando apellido de empleado");
                System.out.println("Ingrese el apellido de empleado o [cancel] para cancelar");
                fromCmd = scan.nextLine();
                if (!fromCmd.equalsIgnoreCase("cancel")) {
                    e.setLastName(fromCmd);
                    logger.debug("Seteando fecha de nacimiento");
                    System.out.println("Ingrese la fecha de nacimiento del empleado dd-MM-yyyy o [cancel] para cancelar");
                    fromCmd = scan.nextLine();
                    if (!fromCmd.equalsIgnoreCase("cancel")) {
                        e.setBirthDate(DatesControls.stringToDate(fromCmd));

                            System.out.println("Ingrese el genero del nuevo empleado [F] femenino [M] Masculino o [cancel] para cancelar");
                            fromCmd = scan.nextLine().toLowerCase();
                            if (!fromCmd.equals("cancel") && (fromCmd.startsWith("f") || fromCmd.startsWith("m"))) {
                                e.setGender(fromCmd.charAt(0));
                                logger.debug("Creando istancia de DepartamentDao");
                                DepartmentDao dDao = new DepartmentDao();
                                logger.debug("Cargando lista de departamentos disponibles");
                                List<Department> lsDeparment = dDao.findAll();
                                System.out.println("Especifique a que departamento pertenece el empleado escribiendo el numero correspondiente posterior de [enter] o [cancel] para cancelar");
                                boolean bandera = true;
                                int captura = 0;

                                while (bandera) {
                                    for (byte i = 1; i < lsDeparment.size() + 1; i++) {
                                        System.out.println(i + ". " + lsDeparment.get(i - 1).getDeptName());
                                    }
                                    captura = v.isNumeric();
                                    if (captura != 0) {

                                        if (captura < lsDeparment.size() + 1 && captura > 0) {
                                            e.setDeptNo(lsDeparment.get(captura - 1));
                                            bandera = false;
                                        } else {
                                            System.out.println("Escriba una de las opciones de la lista\n");
                                            bandera = true;
                                        }
                                    } else {
                                        bandera = false;
                                    }
                                }
                                if (captura != 0) {
                                    bandera = true;
                                    PositionDao pDao = new PositionDao();
                                    List<Position> lsPosition = pDao.findAll();
                                    System.out.println("Especifique a que cargo pertenece el empleado escribiendo el numero correspondiente posterior de [enter] o [cancel] para cancelar");
                                    while (bandera) {
                                        for (byte i = 1; i < lsDeparment.size() + 1; i++) {
                                            System.out.println(i + ". " + lsPosition.get(i - 1).getPosition());
                                        }
                                        captura = v.isNumeric();
                                        if (captura != 0) {

                                            if (captura < lsPosition.size() + 1 && captura > 0) {
                                                e.setPositionNo(lsPosition.get(captura - 1));
                                                bandera = false;
                                            } else {
                                                bandera = true;
                                            }
                                        } else {
                                            bandera = false;
                                        }
                                    }
                                }
                            } else {
                                System.out.println("Datos no validos");
                            }
                        
                    }
                }
            }

        } catch (SQLException | ClassNotFoundException ex) {
            logger.error("Ha ocurrido un excepcion en la creacion del registro " + ex);
        }

    }

    @Override
    public void remove(int id) {
        eDao = new EmployeeDao();
        try {
            eDao.remove(id);
        } catch (SQLException | ClassNotFoundException ex) {

            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void addRecord(String user) {
        eDao = new EmployeeDao();
        Employee e = new Employee(0);
        e.setUserChange(user);
        e.setUserCreate(user);
        e.setDateChange(new Date());
        e.setDateCreate(new Date());
        e.setHireDate(new Date());
        System.out.println("Ingrese correo electronico del empleado o escriba [cancel] para cancelar");
        fromCmd = scan.nextLine().toLowerCase();
        if (!fromCmd.equals("cancel")) {
            e.setEmail(fromCmd.toLowerCase());
            captureData(e, user);
            try {
                if (e.getPositionNo() != null) {
                    eDao.create(e);
                }

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
