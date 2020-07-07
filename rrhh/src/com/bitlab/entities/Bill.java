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
 * @author Andrea
 */
public class Bill {
     //++++++++++++atributos++++++++++++++++++++++
    private int billNo;
    private Payroll payrollNo; //Llave de planilla
    private double bilValue; // Valor
    private String bilDescription; //Descripcion del valor
    
    //+++++++++++++++++auditoria++++++++++++++++++++
    private String UserCreate; //usuario de auditoria crear
    private Date dateCreate;//fecha de creacion
    private String userChange;//usuario de auditoria actualizar
    private Date dateChange;//fecha de actualizacion
/**
 * Constructor vacio
 * 
 */
    public Bill() {
    }
/**
 * Constructor con id primario 
 * @param int 
 */
    public Bill(int billNo) {
        this.billNo = billNo;
    }
    
   
   
    /**
    *Contructor recibe todos los campos de Employee
    *@param  Payroll payrollNo numero correlativo
    *@param  double bilValue valor
    *@param  String bilDescription Descripcion del valor
    *@param  String payrollNo numero correlativo
    *@param  Payroll payrollNo numero correlativo
    *@param String UserCreate usuario de auditoria crear
    *@param GregorianCalendar dateCreate fecha de creacion
    *@param String userChange usuario de auditoria actualizar
    *@param GregorianCalendar dateChange fecha de actualizacion    
    * 
    */
   

    public Bill(int billNo, Payroll payrollNo, double bilValue, String bilDescription, String UserCreate, Date dateCreate, String userChange, Date dateChange) {
        this.billNo = billNo;
        this.payrollNo = payrollNo;
        this.bilValue = bilValue;
        this.bilDescription = bilDescription;
        this.UserCreate = UserCreate;
        this.dateCreate = dateCreate;
        this.userChange = userChange;
        this.dateChange = dateChange;
    }
/**
 * Constructor con objeto payroll
 * @param payrollNo 
 */
    public Bill(Payroll payrollNo) {
        this.payrollNo = payrollNo;
    }

    public Payroll getPayrollNo() {
        return payrollNo;
    }

    public void setPayrollNo(Payroll payrollNo) {
        this.payrollNo = payrollNo;
    }

    public double getBilValue() {
        return bilValue;
    }

    public void setBilValue(double bilValue) {
        this.bilValue = bilValue;
    }

    public String getBilDescription() {
        return bilDescription;
    }

    public void setBilDescription(String bilDescription) {
        this.bilDescription = bilDescription;
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

    public int getBillNo() {
        return billNo;
    }

    public void setBillNo(int billNo) {
        this.billNo = billNo;
    }

    @Override
    public String toString() {
        return "Bill{" + "billNo=" + billNo + ", payrollNo=" + payrollNo + ", bilValue=" + bilValue + ", bilDescription=" + bilDescription + ", UserCreate=" + UserCreate + ", dateCreate=" + dateCreate + ", userChange=" + userChange + ", dateChange=" + dateChange + '}';
    }

    
    

    
    
    
}
