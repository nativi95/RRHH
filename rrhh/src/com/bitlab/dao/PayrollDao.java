/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.abstracts.AbstractDao;
import com.bitlab.entities.Employee;
import com.bitlab.entities.Payroll;
import com.bitlab.util.DatesControls;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 */
public class PayrollDao extends AbstractDao<Payroll> {

    @Override
    public String getTableName() {
        return "emp_pay_payroll";
    }

    @Override
    protected String[] getTableColumns() {
        String[] columns = {"pay_payroll_no", "pay_emp_no", "pay_from_date", "pay_to_date", "A_user_create", "A_date_create", "A_user_change", "A_date_change"};
        return columns;
    }

    @Override
    protected String getTableKey() {
        return "pay_payroll_no";
    }

    @Override
    protected Payroll getMappingResults(ResultSet rs) throws SQLException {
        EmployeeDao eDao = new EmployeeDao();
        try {
            return new Payroll(rs.getInt(1), eDao.find(rs.getInt(2)), rs.getDate(3),
                    rs.getDate(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getDate(8));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PayrollDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void setMappingParamsToCreate(PreparedStatement ps, Payroll entity) throws SQLException {
        ps.setInt(1, entity.getPayrollNo());
        ps.setInt(2, entity.getEmpNo().getEmpNo());
        ps.setString(3, DatesControls.dateToString(entity.getFromDate()));
        ps.setString(4, DatesControls.dateToString(entity.getToDate()));
        ps.setString(5, entity.getUserCreate());
        ps.setString(6, DatesControls.dateToString(entity.getDateCreate()));
        ps.setString(7, entity.getUserChange());
        ps.setString(8, DatesControls.dateToString(entity.getDateChange()));
    }

    @Override
    protected void setMappingParamsToUpdate(PreparedStatement ps, Payroll entity) throws SQLException {
        ps.setInt(6, entity.getPayrollNo());
        ps.setInt(1, entity.getEmpNo().getEmpNo());
        ps.setString(2, DatesControls.dateToString(entity.getFromDate()));
        ps.setString(3, DatesControls.dateToString(entity.getToDate()));
        ps.setString(4, entity.getUserChange());
        ps.setString(5, DatesControls.dateToString(entity.getDateChange()));
    }

    @Override
    protected String getColumnLike() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getLastInsertIdPayroll() throws SQLException, ClassNotFoundException {
        int idPayroll = 0;
        String lastInsertIdSql = "SELECT last_insert_id()";
        Connection con = getConnection(); //Se conecta a la BD        
        PreparedStatement ps = con.prepareStatement(lastInsertIdSql); //Crea el Statement
        ResultSet rs = ps.executeQuery(); //Ejecuta la query
        while (rs.next()) { //Si la BD devolvio coincidencias (Registros)
            idPayroll = rs.getInt(1); //Se mapean y se asignan a la variable
        }
        closeJDBCObjects(con, ps, rs); //Cierra la conexión
        return idPayroll;
    }

    public List<Payroll> payrollByDates(Date DateFrom, Date dateTo) throws SQLException, ClassNotFoundException {
        String sql = getFindAllSQL() + SQL_WHERE + " pay_from_date <=? and pay_to_date >= ?";
        Connection con = getConnection(); //Se conecta a la BD        
        PreparedStatement ps = con.prepareStatement(sql); //Crea el Statement
        ps.setString(1, DatesControls.dateToString(DateFrom));
        ps.setString(2, DatesControls.dateToString(dateTo));
        ResultSet rs = ps.executeQuery(); //Ejecuta la query
        List<Payroll> lsPayroll = null;

        while (rs.next()) { //Si la BD devolvio coincidencias (Registros)
            lsPayroll.add(getMappingResults(rs));//Se mapean y se asignan a la variable
        }
        closeJDBCObjects(con, ps, rs); //Cierra la conexión
        return lsPayroll;
    }

    public List<Payroll> employeePayrollHistory(int id) throws SQLException, ClassNotFoundException {
        String sql = getFindAllSQL() + SQL_WHERE + " pay_emp_no=?";
        Connection con = getConnection(); //Se conecta a la BD        
        PreparedStatement ps = con.prepareStatement(sql); //Crea el Statement
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery(); //Ejecuta la query
        List<Payroll> lsPayroll = null;

        while (rs.next()) { //Si la BD devolvio coincidencias (Registros)
            lsPayroll.add(getMappingResults(rs));//Se mapean y se asignan a la variable
        }
        closeJDBCObjects(con, ps, rs); //Cierra la conexión
        return lsPayroll;
    }

}
