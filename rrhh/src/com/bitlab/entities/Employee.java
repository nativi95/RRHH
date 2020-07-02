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
public class Employee {

    //++++++++++++atributos++++++++++++++++++++++
    private int empNo;//numero correlativo
    private GregorianCalendar birthDate;//fecha de nacimiento
    private String firstName;//primer nombre
    private String lastName;//primer apellido
    private char gender; // permite M o F 
    private GregorianCalendar hireDate;// fecha de pago

    //+++++++++++++++++auditoria++++++++++++++++++++
    private String UserCreate; //usuario de auditoria crear
    private GregorianCalendar dateCreate;//fecha de creacion
    private String userChange;//usuario de auditoria actualizar
    private GregorianCalendar dateChange;//fecha de actualizacion

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

    /**
     * Contructor de Employee con parametros
     * 
     * @param int empNo numero correlativo 
     * @param GregorianCalendar birthDate fecha nacimiento
     * @param String firstName primer nombre String lastName primer apellido
     * @param GregorianCalendar hireDate fecha de pago
     * @param String UserCreate usuario de auditoria crea
     * @param GregorianCalendar dateCreate fecha de creacion
     * @param String userChange usuario de auditoria actualizar
     * @param GregorianCalendar dateChange fecha de actualizacion
     */
    public Employee(int empNo, GregorianCalendar birthDate, String firstName, String lastName, char gender, GregorianCalendar hireDate, String UserCreate, GregorianCalendar dateCreate, String userChange, GregorianCalendar dateChange) {
        this.empNo = empNo;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
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

    public GregorianCalendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(GregorianCalendar birthDate) {
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

    public GregorianCalendar getHireDate() {
        return hireDate;
    }

    public void setHireDate(GregorianCalendar hireDate) {
        this.hireDate = hireDate;
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
        return "Employee{" + "empNo=" + empNo + ", birthDate=" + birthDate + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", hireDate=" + hireDate + ", UserCreate=" + UserCreate + ", dateCreate=" + dateCreate + ", userChange=" + userChange + ", dateChange=" + dateChange + '}';
    }
    

}
