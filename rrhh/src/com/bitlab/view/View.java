/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.view;
import com.bitlab.controller.UserManagement;
/**
 *
 * @author nativi
 */
public class View {
    
    public static void main(String[] args) {
        UserManagement uMn= new UserManagement();
        System.out.println("-----------Sistema de administrcación de recursos humanos-----------");
        System.out.println("Inicie sesion con sus credenciales para comenzar");
        uMn.login();
    }
}
