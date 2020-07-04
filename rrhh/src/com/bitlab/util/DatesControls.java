/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author nativi
 */
public class DatesControls {

    //+++++++++++++++++++Atributos++++++++++++++++++++++++++++++++++
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//formato de dates
    private Date today;//fecha actual
    private static GregorianCalendar cal;//calendariogregoriano

    /**
     * Metodo estatico que convierte fechas de String a Date
     *
     * @param String fecha
     * @return Date
     */
    public static Date stringToDate(String date) {
        try {
            return format.parse(date);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Metodo estatico que convierte fechas de Date a GregorianCalendar
     *
     * @param Date date
     * @return GregorianCalendar
     */
    public static GregorianCalendar dateToGregorian(Date date) {
        cal.setTime(date);
        return cal;
    }

    /**
     * Metodo estatico que convierte fechas de Date a String
     *
     * @param Date fecha
     * @return String
     */
    public static String dateToString(Date date) {
        return format.format(date);
    }

    public GregorianCalendar getCal() {
        return cal;
    }

    public void setCal(GregorianCalendar cal) {
        this.cal = cal;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public static Date getToday() {
        return new Date();
    }
    
    public static void main(String[] args) {
        System.out.println( DatesControls.dateToGregorian(new Date()));
    }
    

}
