/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.abstracts.AbstractDao;
import com.bitlab.entities.Employee;
import com.bitlab.util.DatesControls;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nativi
 */
public class EmployeeDao extends AbstractDao<Employee> {

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

        PositionDao pDao = new PositionDao();
        DepartmentDao dDao = new DepartmentDao();
        try {
            return new Employee(
                    rs.getInt("emp_emp_no"),
                    rs.getDate("emp_birth_date"),
                    rs.getString("emp_first_name"),
                    rs.getString("emp_last_name"),
                    (rs.getString("emp_gender")).charAt(0),
                    rs.getDate("emp_hire_date"),
                    (pDao.find(rs.getInt("emp_position_no"))),
                    (dDao.find(rs.getInt("emp_dept_no"))),
                    rs.getString("A_user_create"),
                    rs.getDate("A_date_create"),
                    rs.getString("A_user_change"),
                    rs.getDate("A_user_change")
            );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void setMappingParamsToCreate(PreparedStatement ps, Employee entity) throws SQLException {
        ps.setInt(1, entity.getEmpNo());
        ps.setString(2, DatesControls.dateToString(entity.getBirthDate()));
        ps.setString(3, entity.getFirstName());
        ps.setString(4, entity.getLastName());
        ps.setString(5, String.valueOf(entity.getGender()));
        ps.setString(6, DatesControls.dateToString(entity.getHireDate()));
        ps.setInt(7, entity.getPositionNo().getPositionNo());
        ps.setInt(8, entity.getDeptNo().getDeptNo());
        ps.setString(9, entity.getUserCreate());
        ps.setString(10, DatesControls.dateToString(entity.getDateCreate()));
        ps.setString(11, entity.getUserChange());
        ps.setString(12, DatesControls.dateToString(entity.getDateChange()));
    }

    @Override
    protected void setMappingParamsToUpdate(PreparedStatement ps, Employee entity) throws SQLException {
        ps.setInt(12, entity.getEmpNo());
        ps.setString(1, DatesControls.dateToString(entity.getBirthDate()));
        ps.setString(2, entity.getFirstName());
        ps.setString(3, entity.getLastName());
        ps.setString(4, String.valueOf(entity.getGender()));
        ps.setString(5, DatesControls.dateToString(entity.getHireDate()));
        ps.setInt(6, entity.getPositionNo().getPositionNo());
        ps.setInt(7, entity.getDeptNo().getDeptNo());
        ps.setString(8, entity.getUserCreate());
        ps.setString(9, DatesControls.dateToString(entity.getDateCreate()));
        ps.setString(10, entity.getUserChange());
        ps.setString(11, DatesControls.dateToString(entity.getDateChange()));
    }

    @Override
    protected String getColumnLike() {
        return "emp_first_name";
    }

    public List<Employee> findLike(String nombre, String apellido) {
        try {
            String sql = getFindAllSQL() + SQL_WHERE +getColumnLike()+" like %?% or emp_last_name like %?%";
            Connection con = getConnection(); //Se obtiene la conexión
            PreparedStatement ps = con.prepareStatement(sql); //Se prepara el Statement
            ps.setObject(1, nombre); //Se aplican los parametros
            ps.setObject(2, apellido); //Se aplican los parametros
            ResultSet rs = ps.executeQuery(); //Se ejecuta y se utiliza un ResultSet para obtener los valores
            List<Employee> objects = new ArrayList<>();
            while (rs.next()) { //Si la BD encontro registros por cada uno itera
                objects.add(getMappingResults(rs)); //Agrega los datos
            }
            closeJDBCObjects(con, ps, rs); //Cierra la conexión
            return objects;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
return null;
    }

}
