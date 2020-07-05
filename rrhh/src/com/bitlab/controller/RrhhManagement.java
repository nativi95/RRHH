/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controller;
import com.bitlab.dao.EmployeeDao;
import com.bitlab.entities.Employee;
import java.util.Scanner;


import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Andrea
 */
public class RrhhManagement {
    // Variable logger
    private static Logger logger = LoggerFactory.getLogger(DepartmentManagement.class);
    
    //Objeto de clase escanner para leer datos
    Scanner scan = new Scanner(System.in);
    
    //String capture donde se guardara los valores de entrada
    private String capture = " ";
    
    //Objeto de clase EmployeeDao
    private EmployeeDao eDao = new EmployeeDao();
    
 /**
 * Metodo getCapture para realizad la captura de datos
 * @param string de usuario
 * @return un objeto de tipo <T>
 */
    public String getCapture(String user) throws SQLException {
        capture = scan.nextLine();
        
       if(capture.toLowerCase().equals("cancel")){
           rrhhManagement(user);
       return null;    
       }else{
       return capture;
       } 
    }
    
    public void addRecord(String user) {
//        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
//        logger.debug("Creacion de registro de empleado");
//        Employee e = new Employee();
//        
//        this.getCapture(user);
//        try {
//            dDao.create(d);
//        } catch (SQLException ex) {
//            logger.error("Ha ocurrido una excepcion en la creacion", ex);
//        } catch (ClassNotFoundException ex) {
//            logger.error("Ha ocurrido una excepcion en la creacion", ex);
//        }
//        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }
    
    public void rrhhManagement(String user) {
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        boolean search = true;

        System.out.println("Desea ingresar más parámetros de búsqueda escriba la opión y pulse [enter] ");
        System.out.println("A. Gestion de empleados");        
        System.out.println("B. Historial de planillas del mes actual");
        System.out.println("C. Ver Historial de planillas segun mes espacífico");
        System.out.println("D. Salir");
        while (search) {
            try {
                switch (getCapture(user).toLowerCase()) {
                    case "a":
                        show(user);
                        search = false;
                        break;
                    case "b":
                        findById(user);
                        search = false;
                        break;
                    case "c":
                        findLike(user);
                        search = false;
                        break;
                    case "d":
                        menuResultados(user);
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
    
    public void menuManagement(String user){
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        boolean search = true;

        System.out.println("Desea ingresar más parámetros de búsqueda escriba la opión y pulse [enter] ");
        System.out.println("A. Contratación de empleados");
        System.out.println("B. Actualización de registros empleados");
        System.out.println("C. Desactivación de empleados por despido");
        System.out.println("D. Visualización de pagos generados");
        System.out.println("E. Generación de pagos en planilla");
        System.out.println("F. Salir");
        
        while (search) {
            try {
                switch (getCapture(user).toLowerCase()) {
                    case "a":
                        show(user);
                        search = false;
                        break;
                    case "b":
                        findById(user);
                        search = false;
                        break;
                    case "c":
                        findLike(user);
                        search = false;
                        break;
                    case "d":
                        menuResultados(user);
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
