/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entities;

import java.util.Date;

/**
 *
 * @author nativi
 */
public class Employee {

    //++++++++++++atributos++++++++++++++++++++++
    private int empNo;//numero correlativo
    private Date birthDate;//fecha de nacimiento
    private String firstName;//primer nombre
    private String lastName;//primer apellido    
    private char gender; // permite M o F 
    private Date hireDate;// fecha de pago
    private String email;//primer apellido
    private Position positionNo;
    private Department deptNo;

    //+++++++++++++++++auditoria++++++++++++++++++++
    private String UserCreate; //usuario de auditoria crear
    private Date dateCreate;//fecha de creacion
    private String userChange;//usuario de auditoria actualizar
    private Date dateChange;//fecha de actualizacion

    //+++++++++++++++++++constructores++++++++++++++++++
    /**
     * Constructor vacio, crea un nuevo objeto Employee vacio
     */
    public Employee() {
    }

    /**
     * Contructor recibe ID de Employee
     *
     * @param int id
     */
    public Employee(int empNo) {
        this.empNo = empNo;
    }

    public Employee(int empNo, Date birthDate, String firstName, String lastName, char gender, Date hireDate, String email, Position positionNo, Department deptNo, String UserCreate, Date dateCreate, String userChange, Date dateChange) {
        this.empNo = empNo;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
        this.email = email;
        this.positionNo = positionNo;
        this.deptNo = deptNo;
        this.UserCreate = UserCreate;
        this.dateCreate = dateCreate;
        this.userChange = userChange;
        this.dateChange = dateChange;
    }
    

    //+++++++++++++++++++++++++++Get y Set++++++++++++++++++++++++++++++
    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
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


    public Position getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(Position positionNo) {
        this.positionNo = positionNo;
    }

    public Department getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Department deptNo) {
        this.deptNo = deptNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
       
    //+++++++++++++++++++++toString+++++++++++++++++++++++++++++++

    @Override
    public String toString() {
        return "Employee{" + "empNo=" + empNo + ", birthDate=" + birthDate + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", hireDate=" + hireDate + ", positionNo=" + positionNo + ", deptNo=" + deptNo + ", UserCreate=" + UserCreate + ", dateCreate=" + dateCreate + ", userChange=" + userChange + ", dateChange=" + dateChange + '}';
    }
  
}
