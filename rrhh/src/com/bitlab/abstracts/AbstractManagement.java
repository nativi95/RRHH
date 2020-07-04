/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.abstracts;

import com.bitlab.controller.UserManagement;
import com.bitlab.dao.UserDao;
import com.bitlab.entities.User;
import com.bitlab.util.DatesControls;
import java.sql.SQLException;
import java.util.Date;
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

    public abstract void captuteData(User u);

    public abstract void remove(int id);

    public abstract void addRecord(String user);//abstracto

    Scanner scan = new Scanner(System.in);

    private String capture = null;

    public String getCapture() {
        capture = scan.nextLine();
        return capture;
    }

    public AbstractManagement(String user) throws SQLException {

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
           capture=scan.nextLine();
            switch (capture.toLowerCase()) {
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
                        buscarById(user);
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

    public void buscarById(String user) throws SQLException, ClassNotFoundException {
        System.out.println("Ingrese el codigo de registro");
        getCapture();
        show(validatedNumber(), user);//abstracto

    }

    public void show(int id, String user) throws SQLException, ClassNotFoundException {

        if(!getFindToString(id).isEmpty()){
            System.out.println("getFindToString(id)");
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
        boolean search = true;

        System.out.println("Desea ingresar más parámetros de búsqueda escriba la opión y pulse [enter] ");
        System.out.println("A.Buscar primeros 50 registros");
        System.out.println("B.Buscar por código identificador registros");
        System.out.println("C.Buscar similares");
        System.out.println("D.Encontré mi resultado y deseo realizar cambios usando el número de registro");
        System.out.println("E.Cancelar");
        while (search) {
            try {
                getCapture();
                switch (capture.toLowerCase()) {
                    case "a":
                        show(user);
                        search = false;
                        break;
                    case "b":
                        buscarById(user);
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
        System.out.println("Escriba la opión de la acción que deseas realizar y pulse [enter] ");
        System.out.println("A.Editar registro");
        System.out.println("B.Eliminar registro");
        getCapture();
        boolean result = true;
        while (result) {
            switch (capture.toLowerCase()) {
                case "a":
                    updateRecord(user);
                    result = false;
                    break;
                case "b":
                    removeRecord();
                    result = false;
                default:
                    System.out.println("Escriba una opción valida");
                    result = true;
                    break;
            }
        }
    }

    public void removeRecord() throws ClassNotFoundException, SQLException {//se repite

        System.out.println("Ingrese el número de registro a eliminar");

        remove(validatedNumber());
    }

    public int validatedNumber() {// Se repite
        boolean flag = true;

        while (flag) {
            getCapture();
            flag = validateNumber(capture.trim());
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
