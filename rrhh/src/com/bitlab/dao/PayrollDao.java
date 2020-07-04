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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 */
public class PayrollDao extends AbstractDao<Payroll>{

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
        ps.setInt(8, entity.getPayrollNo());
        ps.setInt(1, entity.getEmpNo().getEmpNo());
        ps.setString(2, DatesControls.dateToString(entity.getFromDate()));
        ps.setString(3, DatesControls.dateToString(entity.getToDate()));
        ps.setString(4, entity.getUserCreate());
        ps.setString(5, DatesControls.dateToString(entity.getDateCreate()));
        ps.setString(6, entity.getUserChange());
        ps.setString(7, DatesControls.dateToString(entity.getDateChange()));
    }
    
}
