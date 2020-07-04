/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entities;

import java.util.Date;

/**
 *
 * @author CarlosAlex
 */
public class User {

     //-------------------Atributos------------------------------
    private int userNo; // Indica numero de identificacion de usuario
    private String user; // Indica tipo de usuario 
    private String password; // Indica la contraseña de identificacion de usuario
    private Rol rolNo; // Recibe los atributos de la clase Rol

    //-----------------------Auditoria---------------------------
    private String UserCreate; //usuario de auditoria crear
    private Date dateCreate;//fecha de creacion
    private String userChange;//usuario de auditoria actualizar
    private Date dateChange;//fecha de actualizacion

     //-----------------------Constructores-------------------------
    /**
     * Constructor vacio, crea un nuevo objeto User vacio
     */
    public User() {
    }
    
     /**
     *Contructor recibe todos los campos de User 
     * 
    *@param int userNo recibe numero de usuario 
    *@paramString user recibe el tipo de usuario
    *@param String password recibe la contraseña del usuario 
    *@param  Rol rolNo recibe el numero de rol y se lo envia a la clase Rol
   
    *@param String UserCreate usuario de auditoria crear
    *@param GregorianCalendar dateCreate fecha de creacion
    *@param String userChange usuario de auditoria actualizar
    *@param GregorianCalendar dateChange fecha de actualizacion
     * 
     */
    public User(int userNo, String user, String password, Rol rolNo, String UserCreate, Date dateCreate, String userChange, Date dateChange) {
        this.userNo = userNo;
        this.user = user;
        this.password = password;
        this.rolNo = rolNo;
        this.UserCreate = UserCreate;
        this.dateCreate = dateCreate;
        this.userChange = userChange;
        this.dateChange = dateChange;

    }

    public User(int userNo) {
        this.userNo = userNo;
    }

    
 //------------------------------Getters and Setters----------------------------
    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRolNo() {
        return rolNo;
    }

    public void setRolNo(Rol rolNo) {
        this.rolNo = rolNo;
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

    //------------------------toString------------------------------
    @Override
    public String toString() {
        return "User{" + "userNo=" + userNo + ", user=" + user + ", password=" + password + ", rolNo=" + rolNo + ", UserCreate=" + UserCreate + ", dateCreate=" + dateCreate + ", userChange=" + userChange + ", dateChange=" + dateChange + '}';
    }

}
