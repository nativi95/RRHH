/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.abstracts.AbstractDao;
import com.bitlab.entities.Position;
import com.bitlab.util.DatesControls;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nativi
 */
public class PositionDao extends AbstractDao<Position> {

    @Override
    public String getTableName() {
        return "emp_pos_position";
    }

    @Override
    protected String[] getTableColumns() {
        String[] columns = {"pos_position_no", "pos_position", "A_user_create", "A_date_create", "A_user_change", "A_date_change"};
        return columns;
    }

    @Override
    protected String getTableKey() {
        return "pos_position_no";
    }

    @Override
    protected Position getMappingResults(ResultSet rs) throws SQLException {
        return new Position(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getDate(6));
    }

    @Override
    protected void setMappingParamsToCreate(PreparedStatement ps, Position entity) throws SQLException {
        ps.setInt(1, entity.getPositionNo());
        ps.setString(2, entity.getPosition());
        ps.setString(3, entity.getUserCreate());
        ps.setString(4, DatesControls.dateToString(entity.getDateCreate()));
        ps.setString(5, entity.getUserChange());
        ps.setString(6, DatesControls.dateToString(entity.getDateChange()));
    }

    @Override
    protected void setMappingParamsToUpdate(PreparedStatement ps, Position entity) throws SQLException {
        ps.setInt(6, entity.getPositionNo());
        ps.setString(1, entity.getPosition());
        ps.setString(2, entity.getUserCreate());
        ps.setString(3, DatesControls.dateToString(entity.getDateCreate()));
        ps.setString(4, entity.getUserChange());
        ps.setString(5, DatesControls.dateToString(entity.getDateChange()));
    }

}
