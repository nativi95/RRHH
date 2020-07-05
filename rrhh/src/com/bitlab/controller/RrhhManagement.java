/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controller;
import com.bitlab.dao.EmployeeDao;
import com.bitlab.entities.Employee;
import com.bitlab.util.Validate;
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
    private EmployeeManagement eMa = new EmployeeManagement();
    
    //Objeto de clase que contiene metodos de validacion
    private Validate validate = new Validate();
    
 
    
 /**
 * metodo que ingresara la informacion para facilitar los parametros de busqueda ById
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
 * @param id
 * @param user
 * @throws SQLException
 * @throws ClassNotFoundException 
 */
    public void show(int id, String user) throws SQLException, ClassNotFoundException {

        if(!eMa.getFindToString(id).isEmpty()){
            System.out.println(eMa.getFindToString(id));
        }else{
            System.out.println("No se encontró el registro");
        }
        this.rrhhManagement(user);

    }
 /**
 * metodo que mostra la lista de los primeros 50 registros
 * @param user
 * @throws SQLException
 * @throws ClassNotFoundException 
 */
    public void show(String user) throws SQLException, ClassNotFoundException {

        eMa.showList();
        rrhhManagement(user);

    }  
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
                        menuManagement(user);
                        search = false;
                        break;
                    case "b":
//                        findById(user);
                        search = false;
                        break;
                    case "c":
//                        findLike(user);
                        search = false;
                        break;
                    case "d":
//                        menuResultados(user);
                        search = false;
                        break;
                    default:
//                        System.out.println("Escriba una opción valida");
                        search = true;
                        break;
                }
            } catch (SQLException ex) {
                logger.error("Ha ocurrido una excepcion en la creacion", ex);
            } /*catch (ClassNotFoundException ex) {
                logger.error("Ha ocurrido una excepcion en la creacion", ex);
            }*/
        }       
    }
    
    public void menuManagement(String user){
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        boolean search = true;

        System.out.println("Desea ingresar más parámetros de búsqueda escriba la opión y pulse [enter] ");
        System.out.println("A. Contratación de empleados");
        System.out.println("B. Actualización de registros empleados");
        System.out.println("C. Desactivación de empleados por despido");
        System.out.println("D. Buscar primeros 50 registros");
        System.out.println("E. Buscar por código identificador registros");
        System.out.println("F. Buscar similares");
        System.out.println("G. Encontré mi resultado y deseo realizar cambios usando el número de registro");
        System.out.println("H. Generación de pagos en planilla");
        System.out.println("I. Salir");
        
        while (search) {
            try {
                switch (getCapture(user).toLowerCase()) {
                    case "a":
                        eMa.addRecord(user);
                        break;
                    case "b":
                        eMa.updateRecord(user);
                        break;
                    case "c":
                        eMa.removeRecord(user);
                        break;
                    case "d":
                        eMa.getFindAll();
                        this.show(user);
                        break;
                    case "e":
                        this.findById(user);
                        break;
                    case "f":
                        eMa.findLike(user);   
                        search = false;
                        break;
                    case "g":
                        this.rrhhManagement(user);
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
