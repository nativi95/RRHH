/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entities;

import java.util.GregorianCalendar;

/**
 *
 * @author nativi
 */
public class Department {
    //++++++++++++atributos++++++++++++++++++++++
    private String deptNo;// llave primaria de departamento
    private String deptName;// nombre de departamanto
	//+++++++++++++++++auditoria++++++++++++++++++++
    private String UserCreate; //usuario de auditoria crear
    private GregorianCalendar dateCreate;//fecha de creacion
    private String userChange;//usuario de auditoria actualizar
    private GregorianCalendar dateChange;//fecha de actualizacion

    //+++++++++++++++++++constructores++++++++++++++++++
    /**
     * Constructor vacio, crea un nuevo objeto Department vacio
     */
    public Department() {
    }

     /**
     * Contructor recibe ID de Department
     *
     * @param String id
     */
    public Department(String deptNo) {
        this.deptNo = deptNo;
    }

    /**
     *Contructor recibe todos los campos de Department
    *@param String deptNo llave primaria de departamento
    *@param String deptName nombre de departamanto 
    *@param String UserCreate usuario de auditoria crear
    *@param GregorianCalendar dateCreate fecha de creacion
    *@param String userChange usuario de auditoria actualizar
    *@param GregorianCalendar dateChange fecha de actualizacion
     * 
     */
    public Department(String deptNo, String deptName, String UserCreate, GregorianCalendar dateCreate, String userChange, GregorianCalendar dateChange) {
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.UserCreate = UserCreate;
        this.dateCreate = dateCreate;
        this.userChange = userChange;
        this.dateChange = dateChange;
    }
    
    //+++++++++++++++++++++++++++Get y Set++++++++++++++++++++++++++++++

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    //+++++++++++++++++++++toString+++++++++++++++++++++++++++++++
    @Override
    public String toString() {
        return "Department{" + "deptNo=" + deptNo + ", deptName=" + deptName + ", UserCreate=" + UserCreate + ", dateCreate=" + dateCreate + ", userChange=" + userChange + ", dateChange=" + dateChange + '}';
    }
    
    
    
}
