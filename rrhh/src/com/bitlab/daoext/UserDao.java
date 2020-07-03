/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.daoext;

import com.bitlab.dao.AbstractDao;
import com.bitlab.entities.Rol;
import com.bitlab.entities.User;
import com.bitlab.util.DatesControls;
import com.bitlab.util.Sha;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CarlosAlex
 */
public class UserDao extends AbstractDao<User> {

    @Override
    public String getTableName() {
         return "emp_usr_user"; 
    }

    @Override
    protected String[] getTableColumns() {
        String[] columns={"usr_user_no","usr_user","user_password","usr_rol_no","A_user_create","A_date_create","A_user_change","A_date_change"};
        return columns;
    }

    @Override
    protected String getTableKey() {
        return "usr_user_no";
    }

    @Override
    protected User getMappingResults(ResultSet rs) throws SQLException {
       return new User(rs.getInt(1),rs.getString(2),rs.getString(3), (new Rol(rs.getInt(4))),rs.getString(5), DatesControls.dateToGregorian(rs.getDate(6)),rs.getString(7), DatesControls.dateToGregorian(rs.getDate(8)));
    }

   

    @Override
    protected void setMappingParamsToCreate(PreparedStatement ps, User entity) throws SQLException {
        ps.setInt(1, entity.getUserNo());
        ps.setString(2, entity.getUser());
        ps.setString(3, Sha.encrypt(entity.getPassword()));
        ps.setInt(4, entity.getRolNo().getRolRolNo());
        ps.setString(5, entity.getUserCreate());
        ps.setString(6, entity.getDateCreate().toString());
        ps.setString(7, entity.getUserChange());
        ps.setString(8, entity.getDateChange().toString());
    }

    @Override
    protected void setMappingParamsToUpdate(PreparedStatement ps, User entity) throws SQLException {
        ps.setInt(8, entity.getUserNo());
        ps.setString(1, entity.getUser());
        ps.setString(2, Sha.encrypt(entity.getPassword()));
        ps.setInt(3, entity.getRolNo().getRolRolNo());
        ps.setString(4, entity.getUserCreate());
        ps.setString(5, entity.getDateCreate().toString());
        ps.setString(6, entity.getUserChange());
        ps.setString(7, entity.getDateChange().toString());
    }


    
    
}
