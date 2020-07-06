/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controller;

import java.util.Scanner;
import com.bitlab.controller.UserManagement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author nativi
 */
public class AdminManagement {

    public void adminMenu(String user) {
        Scanner scan = new Scanner(System.in);
        UserManagement uMn;
        RolManagement rMn;
        DepartmentManagement dMn;
        EmployeeManagement eMn;
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Ingrese una de las opciones escribiendo el literal correspondiente despues de un [enter]\n");
                System.out.println("A. Gestion de usuarios");
                System.out.println("B. Gestion de Roles");
                System.out.println("C. Gestion de empleados");
                System.out.println("D. Gestion de departamentos");
                System.out.println("E. Salir");
                switch (scan.nextLine().toLowerCase()) {
                    case "a":
                        uMn = new UserManagement();
                        uMn.AbstractManagement(user);
                        flag = true;
                        break;
                    case "b":
                        rMn = new RolManagement();
                        rMn.AbstractManagement(user);
                        flag = true;
                        break;
                    case "c":
                        eMn = new EmployeeManagement();
                        eMn.AbstractManagement(user);
                        flag = true;
                        break;
                    case "d":
                        dMn = new DepartmentManagement();
                        dMn.AbstractManagement(user);
                        flag = true;
                        break;
                    case "e":
                        flag = false;
                        break;
                    default:
                        System.out.println("Ingrese una opción valida");
                        flag = true;
                        break;
                        
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
