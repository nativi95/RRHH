/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entities;

import java.util.GregorianCalendar;

/**
 *
 * @author CarlosAlex
 */
public class Rol {
    //-------------------Atributos------------------------------
    private int RolRolNo; // Indica numero de rol del empleado        
    private String RolRol;// Indica tipo de rol del empleado 
   
  //-----------------------Auditoria---------------------------
    private String UserCreate; //usuario de auditoria crear
    private GregorianCalendar dateCreate;//fecha de creacion
    private String userChange;//usuario de auditoria actualizar
    private GregorianCalendar dateChange;//fecha de actualizacion    

     //-----------------------Constructores-------------------------
    /**
     * Constructor vacio, crea un nuevo objeto Rol vacio
     */
    public Rol() {
    }

      /**
     *Contructor recibe todos los campos de Rol 
     * 
    *@param int RolRolNo recibe el numero de rol de empleado 
    *@param String RolRol  recibe el tipo de rol del empleado 
   
   
    *@param String UserCreate usuario de auditoria crear
    *@param GregorianCalendar dateCreate fecha de creacion
    *@param String userChange usuario de auditoria actualizar
    *@param GregorianCalendar dateChange fecha de actualizacion
     * 
     */
    public Rol(int RolRolNo, String RolRol, String UserCreate, GregorianCalendar dateCreate, String userChange, GregorianCalendar dateChange) {
        this.RolRolNo = RolRolNo;
        this.RolRol = RolRol;
        this.UserCreate = UserCreate;
        this.dateCreate = dateCreate;
        this.userChange = userChange;
        this.dateChange = dateChange;
    }

     //------------------------------Getters and Setters----------------------------
    public int getRolRolNo() {
        return RolRolNo;
    }

    public void setRolRolNo(int RolRolNo) {
        this.RolRolNo = RolRolNo;
    }

    public String getRolRol() {
        return RolRol;
    }

    public void setRolRol(String RolRol) {
        this.RolRol = RolRol;
    }

    public String getUserCreate() {
        return UserCreate;
    }

    public void setUserCreate(String UserCreate) {
        this.UserCreate = UserCreate;
    }

    public GregorianCalendar getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(GregorianCalendar dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getUserChange() {
        return userChange;
    }

    public void setUserChange(String userChange) {
        this.userChange = userChange;
    }

    public GregorianCalendar getDateChange() {
        return dateChange;
    }

    public void setDateChange(GregorianCalendar dateChange) {
        this.dateChange = dateChange;
    }

    //------------------------toString------------------------------
    @Override
    public String toString() {
        return "Rol{" + "RolRolNo=" + RolRolNo + ", RolRol=" + RolRol + ", UserCreate=" + UserCreate + ", dateCreate=" + dateCreate + ", userChange=" + userChange + ", dateChange=" + dateChange + '}';
    }

    
    
    
    

    
    
    
}
