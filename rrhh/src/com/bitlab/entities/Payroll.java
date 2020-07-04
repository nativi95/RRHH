/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entities;

import java.util.Date;

/**
 *
 * @author Andrea
 */
public class Payroll {
    private int payrollNo; // Numero de planilla
    private Employee empNo; // Llave de empleado
    private Date fromDate; // Fecha desde
    private Date toDate; //Fecha hasta
    
    //+++++++++++++++++auditoria++++++++++++++++++++
    private String UserCreate; //usuario de auditoria crear
    private Date dateCreate;//fecha de creacion
    private String userChange;//usuario de auditoria actualizar
    private Date dateChange;//fecha de actualizacion

    public Payroll() {
    }
    /**
    *@param int payrollNo numero de planilla
    *@param Employee id de empleado
    *@param Date fromDate fecha desde
    *@param Date toDate fecha hasta
    *@param String UserCreate usuario de auditoria crear
    *@param Date dateCreate fecha de creacion
    *@param String userChange usuario de auditoria actualizar
    *@param Date dateChange fecha de actualizacion  
    *
    */
    public Payroll(int payrollNo, Employee empNo, Date fromDate, Date toDate, String UserCreate, Date dateCreate, String userChange, Date dateChange) {
        this.payrollNo = payrollNo;
        this.empNo = empNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.UserCreate = UserCreate;
        this.dateCreate = dateCreate;
        this.userChange = userChange;
        this.dateChange = dateChange;
    }

    public Payroll(int payrollNo) {
        this.payrollNo = payrollNo;
    }

    public int getPayrollNo() {
        return payrollNo;
    }

    public void setPayrollNo(int payrollNo) {
        this.payrollNo = payrollNo;
    }

    public Employee getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Employee empNo) {
        this.empNo = empNo;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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

    @Override
    public String toString() {
        return "Payroll{" + "payrollNo=" + payrollNo + ", empNo=" + empNo + ", fromDate=" + fromDate + ", toDate=" + toDate + ", UserCreate=" + UserCreate + ", dateCreate=" + dateCreate + ", userChange=" + userChange + ", dateChange=" + dateChange + '}';
    }
    
    
}
