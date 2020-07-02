/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entities;

import java.util.GregorianCalendar;

/**
 *
 * @author Andrea
 */
public class Payroll {
    private int payrollNo;
    private Employee empNo;
    private GregorianCalendar fromDate;
    private GregorianCalendar toDate;
    
    //+++++++++++++++++auditoria++++++++++++++++++++
    private String UserCreate; //usuario de auditoria crear
    private GregorianCalendar dateCreate;//fecha de creacion
    private String userChange;//usuario de auditoria actualizar
    private GregorianCalendar dateChange;//fecha de actualizacion

    public Payroll() {
    }

    public Payroll(int payrollNo, Employee empNo, GregorianCalendar fromDate, GregorianCalendar toDate, String UserCreate, GregorianCalendar dateCreate, String userChange, GregorianCalendar dateChange) {
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

    public GregorianCalendar getFromDate() {
        return fromDate;
    }

    public void setFromDate(GregorianCalendar fromDate) {
        this.fromDate = fromDate;
    }

    public GregorianCalendar getToDate() {
        return toDate;
    }

    public void setToDate(GregorianCalendar toDate) {
        this.toDate = toDate;
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

    @Override
    public String toString() {
        return "Payroll{" + "payrollNo=" + payrollNo + ", empNo=" + empNo + ", fromDate=" + fromDate + ", toDate=" + toDate + ", UserCreate=" + UserCreate + ", dateCreate=" + dateCreate + ", userChange=" + userChange + ", dateChange=" + dateChange + '}';
    }
    
    
}
