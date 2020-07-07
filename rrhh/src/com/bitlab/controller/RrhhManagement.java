/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controller;

import com.bitlab.dao.EmployeeDao;
import com.bitlab.entities.Employee;
import com.bitlab.util.DatesControls;
import com.bitlab.util.Validate;
import java.util.Scanner;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Andrea
 */
public class RrhhManagement {

    // Variable logger
    private static Logger logger = LoggerFactory.getLogger(RrhhManagement.class);

    //Objeto de clase escanner para leer datos
    Scanner scan = new Scanner(System.in);

    //String capture donde se guardara los valores de entrada
    private String capture = " ";

    //Objeto de clase EmployeeDao
    private EmployeeDao eDao = new EmployeeDao();
    private EmployeeManagement eMa = new EmployeeManagement();
    private PayrollManagement pMa /*= new PayrollManagement()*/;

    //Objeto de clase que contiene metodos de validacion
    private Validate validate = new Validate();

    /**
     * metodo que ingresara la informacion para facilitar los parametros de
     * busqueda ById
     *
     * @param user
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void findById(String user) throws SQLException, ClassNotFoundException {
        System.out.println("Ingrese el codigo de registro");
        show(validate.isNumeric(scan), user);

    }

    /**
     * metodo que imprimira el listar ById
     *
     * @param id
     * @param user
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void show(int id, String user) throws SQLException, ClassNotFoundException {

        if (!eMa.getFindToString(id).isEmpty()) {
            System.out.println(eMa.getFindToString(id));
        } else {
            System.out.println("No se encontró el registro");
        }
        this.rrhhManagement(user);

    }

    /**
     * Imprime las listas de tipo employee
     *
     * @param lsEmploye
     */
    public void showList(List<Employee> lsEmploye) {
        for (Employee e : lsEmploye) {
            System.out.println("Id: " + e.getEmpNo() + ", Nombre: " + e.getFirstName() + ", Apellido: " + e.getLastName() + ", Nacimiento: " + e.getBirthDate() + ", Contratado: " + e.getHireDate());
        }
    }

    /**
     * metodo que mostra la lista de los primeros 50 registros
     *
     * @param user
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void show(String user) throws SQLException, ClassNotFoundException {

        eMa.showList();

    }

    /**
     * Metodo getCapture para realizad la captura de datos
     *
     * @param string de usuario
     * @return un objeto de tipo <T>
     */
    public String getCapture(String user) throws SQLException {
        capture = scan.nextLine();

        if (capture.toLowerCase().equals("cancel")) {
            rrhhManagement(user);
            return null;
        } else {
            return capture;
        }
    }

    public void rrhhManagement(String user) {
        boolean search = true;
        while (search) {
            logger.debug("Mostrando menu principal");
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

            System.out.println("Escriba la letra de la opción deseada y pulse [enter] ");
//            System.out.println("A. Gestión de empleados");
            System.out.println("A. Gestion empleado");
            System.out.println("B. Historial de planillas del mes actual");
            System.out.println("C. Ver Historial de planillas segun mes espacífico");
            System.out.println("D. Salir");
            try {
                pMa = new PayrollManagement();
                switch (getCapture(user).toLowerCase()) {
                    case "a":
                        logger.debug("--- Ejecutando funcion para crear plaillas");
                        menuManagement(user);
                        search = true;
                        break;
                    case "b":
                        pMa.payrollCurrentMonth();
                        search = true;
                        break;
                    case "c":
                        pMa.payRollByDates(user);
                        search = true;
                        break;
                    case "d":

                        search = false;
                        break;
                    default:
//                        System.out.println("Escriba una opción valida");
                        search = true;
                        break;
                }
            } catch (SQLException ex) {
                logger.error("Ha ocurrido una excepcion en la creacion", ex);
            }
            /*catch (ClassNotFoundException ex) {
                logger.error("Ha ocurrido una excepcion en la creacion", ex);
            }*/
        }
    }

    /**
     * Menu de mantenimiento de empleado por parte de rrhh
     *
     * @param user
     */
    public void menuManagement(String user) {
        boolean search = true;

        while (search) {
            try {
                logger.debug("Mostrando menu principal de RRHH");
                System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

                System.out.println("Desea ingresar más parámetros de búsqueda escriba la opión y pulse [enter] ");
                System.out.println("A. Contratación de empleados");
                System.out.println("B. Actualización de registros empleados");
                System.out.println("C. Desactivación de empleados por despido");
                System.out.println("D. Buscar primeros 50 registros");
                System.out.println("E. Buscar por código identificador registros");
                System.out.println("F. Buscar similares");
                System.out.println("G. Generación de planilla de empleado");
                System.out.println("H. Ver planilla de empleado");
                System.out.println("I. Salir");
                switch (getCapture(user).toLowerCase()) {
                    case "a":
                        eMa.addRecord(user);
                        search = true;
                        break;
                    case "b":
                        eMa.updateRecord(user);
                        search = true;
                        break;
                    case "c":
                        eMa.removeRecord(user);
                        search = true;
                        break;
                    case "d":
                        eMa.getFindAll();
                        this.show(user);
                        search = true;
                        break;
                    case "e":
                        this.findById(user);
                        search = true;
                        break;
                    case "f":
                        eMa.findLike(user);
                        this.show(user);
                        search = true;
                        break;
                    case "g":
                        pMa.CreatePayroll(user);
                        break;
                    case "h":
                        pMa.checkPayroll(user);
                        search = true;
                        break;
                        case "i":
                        search = false;
                        break;
                    default:
                        System.out.println("Escriba una opción valida");
                        search = true;
                        break;
                }
            } catch (SQLException ex) {
                logger.error("Ha ocurrido una excepcion en la creacion", ex);
            } catch (ClassNotFoundException ex) {
                logger.error("Ha ocurrido una excepcion en la creacion", ex);
            }
        }
    }
}
