/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controller;
import java.util.Scanner;


import java.sql.SQLException;

/**
 *
 * @author Andrea
 */
public class RrhhManagement {
    
    public String getCapture(String user) throws SQLException {
        capture = scan.nextLine();
        
       if(capture.toLowerCase().equals("cancel")){
           AbstractManagement(user);
       return null;    
       }else{
       return capture;
       } 
    }
    
    public void rrhhManagement(String user) throws SQLException {

        //capturo datos
        //
        boolean flag = true;
        while (flag) {
            System.out.println("Eliga la opción deseada escribiendo la letra correspondiente después presione [enter]:");
            System.out.println("A. Agregar un nuevo registro");
            System.out.println("B.Buscar primeros 50 registros");
            System.out.println("C.Buscar por código identificador registros");
            System.out.println("D.Buscar similares");
            System.out.println("E. Regresar al menu anterior");
           
            switch (getCapture(user).toLowerCase()) {
                case "a":
                    addRecord(user);
                    flag = true;
                    break;

                case "b": {
                    try {
                        show(user);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                flag = true;
                break;

                case "c": {
                    try {
                        findById(user);
                    } catch (SQLException ex) {
                        Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                flag = true;
                break;

                case "d":
                    flag = true;
                    break;
                case "e":
                    findLike(user);
                    flag = false;
                    break;
                default:
                    System.out.println("Escriba una opción valida");
                    flag = true;
                    break;
            }

        }
    }
}
