/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controller;

import com.bitlab.dao.BillDao;
import com.bitlab.dao.PayrollDao;
import com.bitlab.entities.Bill;
import com.bitlab.entities.Employee;
import com.bitlab.entities.Payroll;
import com.bitlab.util.DatesControls;
import com.bitlab.util.Validate;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CarlosAlex
 */
public class PayrollManagement {

  Validate val=new Validate();
  PayrollDao pay=new PayrollDao();
  BillDao bill=new BillDao();
  Payroll py;
  Bill bl;
   Scanner scan =new Scanner(System.in);

  
    public void CrearPago(int id, String user){ 
        boolean flag = true;
        while (!flag){
            System.out.println("¿Desea Ingresar informacion? [S] para SÍ [Cualquier tecla] para NO");
            String rs=scan.nextLine();
            if(rs.equalsIgnoreCase("S")){
                bl = new Bill();
                System.out.println("Ingrese Valor de Pago");
                bl.setBilValue(Double.valueOf(scan.nextLine()));
                System.out.println("Ingrese Descripcion del Pago");
                bl.setBilDescription(scan.nextLine());

                try {
                    bill.create(bl);
                } catch (SQLException ex) {
                    Logger.getLogger(PayrollManagement.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PayrollManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                flag = false;
            }
    
       }
    }
    public void CrearPlanilla(String user){
        py=new Payroll(0);

        Employee employ=new Employee(val.isNumeric(scan));
        System.out.println("Ingrese ID de Empleado");
        py.setEmpNo(employ);
        System.out.println("Ingrese Fecha de Inicio de Pago");
        py.setFromDate(DatesControls.stringToDate(scan.nextLine()));
         System.out.println("Ingrese Fecha de Corte de Pago");
        py.setToDate(DatesControls.stringToDate(scan.nextLine()));

        py.setUserCreate(user);
        py.setUserChange(user);
        py.setDateCreate(new Date());
        py.setDateChange(new Date());

          try {
              pay.create(py);
              CrearPago(pay.getLastInsertIdPayroll(), user);
          } catch (SQLException ex) {
              java.util.logging.Logger.getLogger(PayrollManagement.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ClassNotFoundException ex) {
              java.util.logging.Logger.getLogger(PayrollManagement.class.getName()).log(Level.SEVERE, null, ex);
          }    
    }
    public void VerHistorial(){
    
    }
    public void VerPlanilla (){
        
    }
    
}
