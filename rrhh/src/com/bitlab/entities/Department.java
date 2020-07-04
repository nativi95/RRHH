/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entities;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author nativi
 */
public class Department {
    //++++++++++++atributos++++++++++++++++++++++
    private int deptNo;// llave primaria de departamento
    private String deptName;// nombre de departamanto
	//+++++++++++++++++auditoria++++++++++++++++++++
    private String UserCreate; //usuario de auditoria crear
    private Date dateCreate;//fecha de creacion
    private String userChange;//usuario de auditoria actualizar
    private Date dateChange;//fecha de actualizacion

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
    public Department(int deptNo) {
        this.deptNo = deptNo;
    }

    public Department(int deptNo, String deptName, String UserCreate, Date dateCreate, String userChange, Date dateChange) {
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.UserCreate = UserCreate;
        this.dateCreate = dateCreate;
        this.userChange = userChange;
        this.dateChange = dateChange;
    }

    //+++++++++++++++++++++++++++Get y Set++++++++++++++++++++++++++++++

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getUserChange() {
        return userChange;
    }

    public void setUserChange(String userChange) {
        this.userChange = userChange;
    }

    public Date getDateChange() {
        return dateChange;
    }

    public void setDateChange(Date dateChange) {
        this.dateChange = dateChange;
    }
   

    //+++++++++++++++++++++toString+++++++++++++++++++++++++++++++
    @Override
    public String toString() {
        return "Department{" + "deptNo=" + deptNo + ", deptName=" + deptName + ", UserCreate=" + UserCreate + ", dateCreate=" + dateCreate + ", userChange=" + userChange + ", dateChange=" + dateChange + '}';
    }
    
    
    
}
