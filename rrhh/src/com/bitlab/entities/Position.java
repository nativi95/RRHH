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
public class Position {

    //++++++++++++atributos++++++++++++++++++++++
    private int positionNo; //llave primaria de position
    private String position;// position

    //+++++++++++++++++auditoria++++++++++++++++++++
    private String UserCreate; //usuario de auditoria crear
    private Date dateCreate;//fecha de creacion
    private String userChange;//usuario de auditoria actualizar
    private Date dateChange;//fecha de actualizacion

    //+++++++++++++++++++constructores++++++++++++++++++
    /**
     * Constructor vacio, crea un nuevo objeto Position vacio
     */
    public Position() {
    }

    /**
     * Contructor recibe ID de Position
     *
     * @param int id
     */
    public Position(int positionNo) {
        this.positionNo = positionNo;
    }

    /**
     * Contructor recibe todos los campos de Position
     *
     * @param int positionNo llave primaria de position
     * @param String position position
     * @param String UserCreate usuario de auditoria crear
     * @param Date dateCreate fecha de creacion
     * @param String userChange usuario de auditoria actualizar
     * @param Date dateChange fecha de actualizacion
     */
    public Position(int positionNo, String position, String UserCreate, Date dateCreate, String userChange, Date dateChange) {
        this.positionNo = positionNo;
        this.position = position;
        this.UserCreate = UserCreate;
        this.dateCreate = dateCreate;
        this.userChange = userChange;
        this.dateChange = dateChange;
    }

    //+++++++++++++++++++++++++++Get y Set++++++++++++++++++++++++++++++
    public int getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(int positionNo) {
        this.positionNo = positionNo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
        return "Position{" + "positionNo=" + positionNo + ", position=" + position + ", UserCreate=" + UserCreate + ", dateCreate=" + dateCreate + ", userChange=" + userChange + ", dateChange=" + dateChange + '}';
    }

}
