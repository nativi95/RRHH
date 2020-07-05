/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.util;

import java.sql.SQLException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Andrea
 */
public class Validate {
     private Logger logger = LoggerFactory.getLogger(Validate.class);
    
    /**
 * metodo que recibira la captura para posteriormente evaluarla si es u  numero entero y retornara el mismo
 * @param user
 * @return int numero capturado en consola
 * @throws SQLException 
 */
    public int validatedNumber(String val) throws SQLException {// Se repite
        boolean flag = true;

        while (flag) {
            
            flag = validateNumber(val.trim());
        }
        return Integer.parseInt(val.trim());
    }
/**
 * metodo que evalua si el estring es un numero
 * @param i
 * @return boolean
 */
    public boolean validateNumber(String i) {//Se repite
        try {
            int number = Integer.parseInt(i);
            return false;
        } catch (Exception e) {
            System.out.println("Por favor ingrese un numero");
            return true;
        }
    }
    
/**
 * metodo que evalua si el estring es un numero entero
 * @param Scanner scan 
 * @return int
 */
    
    public int isNumeric(Scanner scan){
        logger.debug("Validando valor ingresado");
        String num = "";
        boolean flag = false;
        int numero = 0;
        while(!flag){            
            num = scan.nextLine();
            try {
                logger.debug("Parseando valor ingresado");
                numero = Integer.parseInt(num);   
                flag = true;
            } catch (NumberFormatException nfe){      
                logger.error("Valor ingresado no numerico");
                System.out.println("Debe ingresar solo numeros enteros");
                System.out.println("Por favor, intente otra vez..");
                flag = false;
            }
        }
	if (flag) {
            logger.debug("Retornando valor numerico");
            return numero;
        }else{
            return 0;
        }
    }
}
