/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.abstracts.AbstractDao;
import com.bitlab.entities.Department;
import com.bitlab.util.DatesControls;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nativi
 */
public class DepartmentDao extends AbstractDao<Department> {

    @Override
    public String getTableName() {
        return "emp_dep_department";
    }

    @Override
    protected String[] getTableColumns() {
        String[] columns = {"dep_dept_no", "dep_dept_name", "A_user_create", "A_date_create", "A_user_change", "A_date_change"};
        return columns;
    }

    @Override
    protected String getTableKey() {
        return "dep_dept_no";
    }

    @Override
    protected Department getMappingResults(ResultSet rs) throws SQLException {
        return new Department(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), 
                rs.getString(5), rs.getDate(6));
    }

    @Override
    protected void setMappingParamsToCreate(PreparedStatement ps, Department entity) throws SQLException {
        ps.setInt(1, entity.getDeptNo());
        ps.setString(2, entity.getDeptName());
        ps.setString(3, entity.getUserCreate());
        ps.setString(4, DatesControls.dateToString(entity.getDateCreate()));
        ps.setString(5, entity.getUserChange());
        ps.setString(6, DatesControls.dateToString(entity.getDateChange()));
    }

    @Override
    protected void setMappingParamsToUpdate(PreparedStatement ps, Department entity) throws SQLException {
        ps.setInt(4, entity.getDeptNo());
        ps.setString(1, entity.getDeptName());
        ps.setString(2, entity.getUserChange());
        ps.setString(3, DatesControls.dateToString(entity.getDateChange()));
    }

    @Override
    protected String getColumnLike() {
        return "dep_dept_name";
    }
}
