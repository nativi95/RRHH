/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.abstracts.AbstractDao;
import com.bitlab.entities.Bill;
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
public class BillDao extends AbstractDao<Bill>{

    @Override
    public String getTableName() {
        return "emp_bil_bill";
    }

    @Override
    protected String[] getTableColumns() {
        String[] columns = {"bil_payroll_no", "bil_value", "bil_description", "A_user_create", "Adate_create", "A_user_change", "A_date_change"};
        return columns;
    }

    @Override
    protected String getTableKey() {
        return "bil_payroll_no";
    }

    @Override
    protected Bill getMappingResults(ResultSet rs) throws SQLException {
        PayrollDao pDao = new PayrollDao();
        try {
            return new Bill(pDao.find(rs.getInt(1)), rs.getDouble(2), rs.getString(3), rs.getString(4),
                    rs.getDate(5), rs.getString(6), rs.getDate(7));
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PayrollDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void setMappingParamsToCreate(PreparedStatement ps, Bill entity) throws SQLException {
        ps.setInt(1, entity.getPayrollNo().getPayrollNo());
        ps.setDouble(2, entity.getBilValue());
        ps.setString(3, entity.getBilDescription());
        ps.setString(4, entity.getUserCreate());
        ps.setString(5, DatesControls.dateToString(entity.getDateCreate()));
        ps.setString(6, entity.getUserChange());
        ps.setString(7, DatesControls.dateToString(entity.getDateChange()));
    }

    @Override
    protected void setMappingParamsToUpdate(PreparedStatement ps, Bill entity) throws SQLException {
        ps.setInt(5, entity.getPayrollNo().getPayrollNo());
        ps.setDouble(1, entity.getBilValue());
        ps.setString(2, entity.getBilDescription());
        ps.setString(3, entity.getUserChange());
        ps.setString(4, DatesControls.dateToString(entity.getDateChange()));
    }

    @Override
    protected String getColumnLike() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
