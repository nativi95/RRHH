/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.abstracts.AbstractDao;
import com.bitlab.entities.Rol;
import com.bitlab.util.DatesControls;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Andrea
 */
public class RolDao extends AbstractDao<Rol> {

    @Override
    public String getTableName() {
        return "emp_rol_rol";
    }

    @Override
    protected String[] getTableColumns() {
        String[] columns = {"rol_rol_no", "rol_rol", "A_user_create", "A_date_create", "A_user_change", "A_date_change"};
        return columns;
    }

    @Override
    protected String getTableKey() {
        return "rol_rol_no";
    }

    @Override
    protected Rol getMappingResults(ResultSet rs) throws SQLException {
        return new Rol(rs.getInt(1), rs.getString(2), rs.getString(3),
                rs.getDate(4), rs.getString(5), rs.getDate(6));
    }

    @Override
    protected void setMappingParamsToCreate(PreparedStatement ps, Rol entity) throws SQLException {
        ps.setInt(1, entity.getRolRolNo());
        ps.setString(2, entity.getRolRol());
        ps.setString(3, entity.getUserCreate());
        ps.setString(4, DatesControls.dateToString(entity.getDateCreate()));
        ps.setString(5, entity.getUserChange());
        ps.setString(6, DatesControls.dateToString(entity.getDateChange()));
    }

    @Override
    protected void setMappingParamsToUpdate(PreparedStatement ps, Rol entity) throws SQLException {
        ps.setInt(6, entity.getRolRolNo());
        ps.setString(1, entity.getRolRol());
        ps.setString(2, entity.getUserCreate());
        ps.setString(3, DatesControls.dateToString(entity.getDateCreate()));
        ps.setString(4, entity.getUserChange());
        ps.setString(5, DatesControls.dateToString(entity.getDateChange()));
    }

    @Override
    protected String getColumnLike() {
        return "rol_rol";
    }
}
