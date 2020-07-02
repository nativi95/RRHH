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
public class User {

    private int userNo;
    private float user;
    private String password;
    private Rol rolNo;

    private String UserCreate; //usuario de auditoria crear
    private GregorianCalendar dateCreate;//fecha de creacion
    private String userChange;//usuario de auditoria actualizar
    private GregorianCalendar dateChange;//fecha de actualizacion

    public User(int userNo, float user, String password, Rol rolNo, String UserCreate, GregorianCalendar dateCreate, String userChange, GregorianCalendar dateChange) {
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

    public User() {
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public float getUser() {
        return user;
    }

    public void setUser(float user) {
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
        return "User{" + "userNo=" + userNo + ", user=" + user + ", password=" + password + ", rolNo=" + rolNo + ", UserCreate=" + UserCreate + ", dateCreate=" + dateCreate + ", userChange=" + userChange + ", dateChange=" + dateChange + '}';
    }

}
