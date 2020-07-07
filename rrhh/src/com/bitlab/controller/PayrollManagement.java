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
import org.slf4j.LoggerFactory;

/**
 *
 * @author CarlosAlex
 */
public class PayrollManagement {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(PayrollManagement.class);
    Validate val;
    PayrollDao pay;
    BillDao bill;
    Payroll py;
    Bill bl;
    Scanner scan = new Scanner(System.in);
    RrhhManagement rh = new RrhhManagement();

    public void wages(int id, String user) {
        try {

            bill = new BillDao();
            logger.debug("--- Iniciando metodo para ingresar valores a la planilla");
            boolean flag = true;
            logger.debug("--- Entrando a bucle para lectura de datos");
            logger.debug("--- Iniciando la lectura de datos");
            System.out.println("¿Desea agregar ingresos a esta planilla? [S] para Si [Cualquier tecla] para NO");
            String rs = scan.nextLine();
            if (rs.equalsIgnoreCase("S")) {
                while (flag) {

                    bl = new Bill(0);
                    System.out.println("Ingrese Valor de Pago $0.00");
                    bl.setBilValue(Double.valueOf(scan.nextLine()));
                    System.out.println("Ingrese Descripcion del Pago");
                    logger.debug("--- Cargando los datos");
                    bl.setBilDescription(scan.nextLine());
                    bl.setUserCreate(user);
                    bl.setUserChange(user);
                    py = new Payroll(id);
                    bl.setPayrollNo(py);

                    logger.debug("--- Creando registros de bill en la base de datos");
                    bill.create(bl);
                    logger.debug("--- Creacion de planilla realizada con exito");

                    System.out.println("Desea agregar otros ingresos [s] para si [cualquier tecla] para no");
                    if (scan.nextLine().toLowerCase().equals("s")) {
                        flag = true;

                    } else {
                        charges(id, user);
                        flag = false;
                    }

                }
            }

        } catch (SQLException | ClassNotFoundException ex) {
        }
    }

    /**
     * Crea una nueva planilla de un empleado
     *
     * @param user
     */
    public void CreatePayroll(String user) {
        val = new Validate();
        logger.debug("--- Iniciando proceso de creación de plaillas");
        py = new Payroll(0);
        logger.debug("--- Obteniendo id del empleado ");
        pay = new PayrollDao();
        logger.debug("--- Ingresando proceso de lectura de datos");
        System.out.println("Ingrese ID de Empleado");
        Employee employ = new Employee(val.isNumeric(scan));
        py.setEmpNo(employ);
        System.out.println("Ingrese Fecha de Inicio de Pago posterior al dia 26 del mes anterior");
        py.setFromDate(DatesControls.stringToDate(scan.nextLine()));
        System.out.println("Ingrese Fecha de Corte de Pago igual o menor al 25 del mes actual");
        py.setToDate(DatesControls.stringToDate(scan.nextLine()));
        logger.debug("--- Ingresando datos de auditoria automaticamente");
        py.setUserCreate(user);
        py.setUserChange(user);
        py.setDateCreate(new Date());
        py.setDateChange(new Date());
        logger.debug("--- Datos ingresados correctamente");
        try {
            logger.debug("--- Creando registro de planilla en la base de datos");
            System.out.println("el objeto " + py.toString());
            pay.create(py);
            logger.debug("--- Llamando metodo para guardar registros en Bill");
            wages(pay.getLastInsertIdPayroll(), user);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PayrollManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PayrollManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permite buscar un historial de planilla especificando fechas
     *
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
     *
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
     *
     * @param List<Payroll> lsPayroll
     */
    public void show(List<Payroll> lsPayroll) {

        for (Payroll p : lsPayroll) {
            StringBuilder stb = new StringBuilder();
            stb.append("Id de planilla: ").append(p.getPayrollNo());
            stb.append(", Empleado: ").append(p.getEmpNo().getFirstName() + " ").append(p.getEmpNo().getLastName() + " ").append(p.getEmpNo().getDeptNo().getDeptName());
            stb.append(", Fecha de inicio ").append(p.getFromDate()).append(", Fecha de corte").append(p.getToDate());
            System.out.println(stb.toString());
        }

    }

    public void charges(int id, String user) throws SQLException, ClassNotFoundException {
        bill = new BillDao();
        double i = bill.wagesValue(id);
        if (i <= 0) {
            System.out.println("Esta planilla no cuenta con ingresos\n");
            wages(id, user);
        } else {
            try {
                boolean flag = true;
                System.out.println("Se realizaran los siguientes descuentos a la planillas");
                bl = afp(id, i, user);
                System.out.println("AFP :$" + bl.getBilValue());
                bill.create(bl);
                bl = isss(id, i, user);
                System.out.println("ISSS :$" + bl.getBilValue());
                bill.create(bl);
                while (flag) {
                    System.out.println("Si desea otros descuentos a esta planilla presiones [S] para Si [Cualquier tecla] para NO");
                    String rs = scan.nextLine();
                    if (rs.equalsIgnoreCase("S")) {
                        bl = new Bill(0);
                        py = new Payroll(id);
                        bl.setPayrollNo(py);
                        bl.getPayrollNo().setPayrollNo(id);
                        System.out.println("Ingrese Valor de Pago $0.00");
                        bl.setBilValue(Double.valueOf(scan.nextLine()));
                        System.out.println("Ingrese Descripcion del Pago");
                        bl.setBilDescription(scan.nextLine());
                        bl.setUserCreate(user);
                        bl.setUserChange(user);
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
            } catch (SQLException ex) {
                Logger.getLogger(PayrollManagement.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PayrollManagement.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public Bill afp(int id, double value, String user) {
        py = new Payroll(id);
        bl.setBillNo(0);
        bl = new Bill(py);
        bl.setBilDescription("AFP");
        bl.setUserCreate(user);
        bl.setUserChange(user);
        if (value <= 0) {
            System.out.println("Esta planilla no cuenta con ingresos\n");
            wages(id, user);
        } else {

            bl.setBilValue(0.725 * -value);
        }
        return bl;
    }

    public Bill isss(int id, double value, String user) {
        py = new Payroll(id);
        bl.setBillNo(0);
        bl = new Bill(py);
        bl.setBilDescription("isss");
        bl.setUserCreate(user);
        bl.setUserChange(user);
        if (value <= 0) {
            System.out.println("Esta planilla no cuenta con ingresos\n");
            wages(id, user);
        } else {

            bl.setBilValue(-value * 0.75);
        }
        return bl;
    }

    public List<Payroll> employeePayrollHistory(String user) {
        List<Payroll> lsPayroll = null;
        try {
            pay = new PayrollDao();
            py = new Payroll();
            val = new Validate();
            System.out.println("Ingrese el Id del empleado");
            py.getEmpNo().setEmpNo(val.isNumeric());

            lsPayroll = pay.employeePayrollHistory(py.getEmpNo().getEmpNo());

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PayrollManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsPayroll;
    }

    public void showBills(int id) {
        bill = new BillDao();
        List<Bill> lsBill = null;
        double total = 0;
        lsBill = bill.billDescription(id);
        for (Bill bl : lsBill) {
            System.out.println("Id: " + bl.getBillNo() + " Descripción: " + bl.getBilDescription() + " valor: " + bl.getBilValue() + "\n");
            total = total + bl.getBilValue();
        }
        System.out.println("====================================\n");
        System.out.println((double)Math.round(total * 100d) / 100d);
    }

    public void checkPayroll(String user) {
        pay = new PayrollDao();
        py = new Payroll();
        val = new Validate();
        System.out.println("Ingrese el Id de la planilla");
        py.setPayrollNo(val.isNumeric());
        try {
            py = pay.find(py.getPayrollNo());

            System.out.println("==================================================\n");
            System.out.println("Empleado: " + py.getEmpNo().getFirstName() + " " + py.getEmpNo().getLastName());
            System.out.println("Cargo: " + py.getEmpNo().getPositionNo().getPosition() + " Departamento: " + py.getEmpNo().getDeptNo().getDeptName());
            System.out.println("==================================================\n");
            showBills(py.getPayrollNo());
            System.out.println("\n\n Si desea actualizar algun valor  de algún cargo, escriba Si y presiones [enter]");
            if (rh.getCapture(user).toLowerCase().equals("si")) {
                updateBill(user);
            }

        } catch (SQLException | ClassNotFoundException ex) {

            Logger.getLogger(PayrollManagement.class
                    .getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void updateBill(String user) {
        System.out.println("Ingrese el numero de registro del cargo");
        val = new Validate();
        rh = new RrhhManagement();
        bl = new Bill(val.isNumeric());
        bill = new BillDao();
        System.out.println("Ingrese La Descripción");

        try {
            bl.setBilDescription(rh.getCapture(user));
            System.out.println("Ingrese el nuevo cargo");
            bl.setBilValue(Double.parseDouble(rh.getCapture(user)));
            bl.setUserChange(user);
            bill.update(bl);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PayrollManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
