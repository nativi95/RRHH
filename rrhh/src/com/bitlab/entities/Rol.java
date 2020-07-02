/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entities;

import java.util.GregorianCalendar;

/**
 *
 * @author Liliana De Santos
 */
public class Rol {
    private int RolRolNo;        
    private String RolRol;
    private String UserChange;
  
    private String UserCreate; //usuario de auditoria crear
    private GregorianCalendar dateCreate;//fecha de creacion
    private String userChange;//usuario de auditoria actualizar
    private GregorianCalendar dateChange;//fecha de actualizacion    

    public Rol() {
    }

    public Rol(int RolRolNo, String RolRol, String UserChange, String UserCreate, GregorianCalendar dateCreate, String userChange, GregorianCalendar dateChange) {
        this.RolRolNo = RolRolNo;
        this.RolRol = RolRol;
        this.UserChange = UserChange;
        this.UserCreate = UserCreate;
        this.dateCreate = dateCreate;
        this.userChange = userChange;
        this.dateChange = dateChange;
    }

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

    public String getUserChange() {
        return UserChange;
    }

    public void setUserChange(String UserChange) {
        this.UserChange = UserChange;
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

    
    

    
    
    
}
