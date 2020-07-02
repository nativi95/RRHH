/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.imp;

import com.bitlab.dao.AbstractDao;
import com.bitlab.entities.Department;
import com.bitlab.entities.Employee;
import com.bitlab.entities.Position;
import com.bitlab.util.DatesControls;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author nativi
 */
public class EmployeeImp extends AbstractDao<Employee> {

    @Override
    public String getTableName() {
        return "emp_emp_employee";
    }

    @Override
    protected String[] getTableColumns() {
        String[] columns = {"emp_emp_no", "emp_birth_date", "emp_first_name", "emp_last_name", "emp_gender", "emp_hire_date", "emp_position_no", "emp_dept_no", "A_user_create", "A_date_create", "A_user_change", "A_date_change"};
        return columns;
    }

    @Override
    protected String getTableKey() {
        return "emp_emp_no";
    }

    @Override
    protected Employee getMappingResults(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("emp_emp_no"),
                DatesControls.dateToGregorian(rs.getDate("emp_birth_date")),
                rs.getString("emp_first_name"),
                rs.getString("emp_last_name"),
                (rs.getString("emp_gender")).charAt(0),
                DatesControls.dateToGregorian(rs.getDate("emp_hire_date")),
                (new Position(rs.getInt("emp_position_no"))),
                (new Department(rs.getString("emp_dept_no"))),
                rs.getString("A_user_create"),
                DatesControls.dateToGregorian(rs.getDate("A_date_create")),
                rs.getString("A_user_change"),
                DatesControls.dateToGregorian(rs.getDate("A_user_change"))
        );
    }

    @Override
    protected void setMappingParamsToCreate(PreparedStatement ps, Employee entity) throws SQLException {
        ps.setInt(1, entity.getEmpNo());
        ps.setString(2, entity.getBirthDate().toString());
        ps.setString(3, entity.getFirstName());
        ps.setString(4, entity.getLastName());
        ps.setString(5, String.valueOf(entity.getGender()));
        ps.setString(6, entity.getHireDate().toString());
        ps.setInt(7, entity.getPositionNo().getPositionNo());
        ps.setString(8, entity.getDeptNo().getDeptNo());
        ps.setString(9, entity.getUserCreate());
        ps.setString(10, entity.getDateCreate().toString());
        ps.setString(11, entity.getUserChange());
        ps.setString(12, entity.getUserCreate());
    }

    @Override
    protected void setMappingParamsToUpdate(PreparedStatement ps, Employee entity) throws SQLException {
        ps.setInt(12, entity.getEmpNo());
        ps.setString(1, entity.getBirthDate().toString());
        ps.setString(2, entity.getFirstName());
        ps.setString(3, entity.getLastName());
        ps.setString(4, String.valueOf(entity.getGender()));
        ps.setString(5, entity.getHireDate().toString());
        ps.setInt(6, entity.getPositionNo().getPositionNo());
        ps.setString(7, entity.getDeptNo().getDeptNo());
        ps.setString(8, entity.getUserCreate());
        ps.setString(9, entity.getDateCreate().toString());
        ps.setString(10, entity.getUserChange());
        ps.setString(11, entity.getUserCreate());
    }

}
