/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.abstracts;

import com.bitlab.controller.UserManagement;

import com.bitlab.entities.User;

import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nativi
 */
public abstract class AbstractManagement<T> {

    protected abstract List<T> getFindAll();//se debera llenar con un listado del tipo

    protected abstract T find(int id);//se deberar de llenar con metodo find del tipo

    protected abstract String getFindToString(int id);//to string de find

    public abstract void updateRecord(String user);

    public abstract void captuteData(User u, String user);

    public abstract void remove(int id);

    public abstract void addRecord(String user);//abstracto

    Scanner scan = new Scanner(System.in);

    String capture = null;

    public String getCapture(String user) throws SQLException {
        capture = scan.nextLine();
        
       if(capture.toLowerCase().equals("cancel")){
           AbstractManagement(user);
       return null;    
       }else{
       return capture;
       } 
    }

    public void AbstractManagement(String user) throws SQLException {

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
                    flag = false;
                    break;
                default:
                    System.out.println("Escriba una opción valida");
                    flag = true;
                    break;
            }

        }
    }

    public void show(String user) throws SQLException, ClassNotFoundException {

        showList();
        searchMenu(user);

    }

    public void findById(String user) throws SQLException, ClassNotFoundException {
        System.out.println("Ingrese el codigo de registro");   
        show(validatedNumber(user), user);//abstracto

    }

    public void show(int id, String user) throws SQLException, ClassNotFoundException {

        if(!getFindToString(id).isEmpty()){
            System.out.println(getFindToString(id));
        }else{
            System.out.println("No se encontró el registro");
        }
        searchMenu(user);

    }

    public void showList() {//se repite

        for (T object : getFindAll()) {
            System.out.println(object.toString());
        }
    }

    public void searchMenu(String user) {//se repite
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        boolean search = true;

        System.out.println("Desea ingresar más parámetros de búsqueda escriba la opión y pulse [enter] ");
        System.out.println("A.Buscar primeros 50 registros");
        System.out.println("B.Buscar por código identificador registros");
        System.out.println("C.Buscar similares");
        System.out.println("D.Encontré mi resultado y deseo realizar cambios usando el número de registro");
        System.out.println("E.Cancelar");
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
                Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void menuResultados(String user) throws ClassNotFoundException, SQLException {//se repite
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("Escriba la opión de la acción que deseas realizar y pulse [enter] ");
        System.out.println("A.Editar registro");
        System.out.println("B.Eliminar registro");
        boolean result = true;
        while (result) {
            switch (getCapture(user).toLowerCase()) {
                case "a":
                    updateRecord(user);
                    result = false;
                    break;
                case "b":
                    removeRecord(user);
                    result = false;
                    break;
                default:
                    System.out.println("Escriba una opción valida");
                    result = true;
                    break;
            }
        }
    }

    public void removeRecord(String user) throws ClassNotFoundException, SQLException {//se repite

        System.out.println("Ingrese el número de registro a eliminar o [Cancel] para cancelar");

        remove(validatedNumber(user));

        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    public int validatedNumber(String user) throws SQLException {// Se repite
        boolean flag = true;

        while (flag) {
            
            flag = validateNumber(getCapture(user).trim());
        }
        return Integer.parseInt(capture.trim());
    }

    public boolean validateNumber(String i) {//Se repite
        try {
            int number = Integer.parseInt(i);
            return false;
        } catch (Exception e) {
            System.out.println("Por favor ingrese un numero");
            return true;
        }
    }

}
