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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CarlosAlex
 */
public class PayrollManagement {

    Validate val;
    PayrollDao pay;
    BillDao bill;
    Payroll py;
    Bill bl;
    Scanner scan = new Scanner(System.in);
    RrhhManagement rh = new RrhhManagement();

    public void CrearPago(int id, String user) {
        boolean flag = true;
        while (!flag) {
            System.out.println("¿Desea Ingresar informacion? [S] para SÍ [Cualquier tecla] para NO");
            String rs = scan.nextLine();
            if (rs.equalsIgnoreCase("S")) {
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
            } else {
                flag = false;
            }

        }
    }

    public void CreatePayroll(String user) {
        py = new Payroll(0);

        Employee employ = new Employee(val.isNumeric(scan));
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
/**
 * Permite buscar un historial de planilla especificando fechas
 * @param user
 * @return List<Payroll>
 */
    public List<Payroll> payRollByDates(String user) {
        System.out.println("Ingrese la fecha de inicio en formato dd-MM-yyyy para la búsqueda o presiones [cancel] para cancelar");
        py = new Payroll();
        pay = new PayrollDao();
        List<Payroll> lsPayroll = null;
        try {
            py.setFromDate(DatesControls.stringToDate(rh.getCapture(user)));
            System.out.println("Ingrese la fecha de corte en formato dd-MM-yyyy para la búsqueda o presiones [cancel] para cancelar");
            py.setToDate(DatesControls.stringToDate(rh.getCapture(user)));

            lsPayroll = pay.payrollByDates(py.getFromDate(), py.getToDate());

           

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PayrollManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsPayroll;
    }
/**
 * Muestra las planillas del corte actual
 * @return List<Payroll>
 */
    public List<Payroll> payrollCurrentMonth() {
        pay = new PayrollDao();
        GregorianCalendar c1 = new GregorianCalendar();
        c1.setTime(new Date());
        py = new Payroll();
        List<Payroll> lsPayroll = null;
        py.setToDate(DatesControls.stringToDate(c1.get(Calendar.YEAR) + "-" + c1.get(Calendar.MONTH) + "-25"));
        py.setFromDate(DatesControls.stringToDate(c1.get(Calendar.YEAR) + "-" + (c1.get(Calendar.MONTH) - 1) + "-26"));
        try {
           lsPayroll = pay.payrollByDates(DatesControls.dateSqlFormat(py.getFromDate()), DatesControls.dateSqlFormat(py.getToDate()));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PayrollManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsPayroll;
    }
    /**
     * Imprimir Listas List<Payroll>
     * @param List<Payroll> lsPayroll 
     */
    public void show(List<Payroll> lsPayroll){
    
        for(Payroll p :lsPayroll){
            StringBuilder stb=new StringBuilder();
            stb.append("Id de planilla: ").append(p.getPayrollNo());
            stb.append(", Empleado: ").append(p.getEmpNo().getFirstName()+" ").append(p.getEmpNo().getLastName()+" ").append(p.getEmpNo().getDeptNo().getDeptName());
            stb.append(", Fecha de inicio ").append(p.getFromDate()).append(", Fecha de corte").append(p.getToDate());
            System.out.println(stb.toString());
        }
    
    }
}
