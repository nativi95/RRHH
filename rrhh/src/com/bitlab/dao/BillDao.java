/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.abstracts.AbstractDao;
import com.bitlab.entities.Bill;
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
public class BillDao extends AbstractDao<Bill>{

    @Override
    public String getTableName() {
        return "emp_bil_bill";
    }

    @Override
    protected String[] getTableColumns() {
        String[] columns = {"bill_bill_no","bil_payroll_no", "bil_value", "bil_description", "A_user_create", "A_date_create", "A_user_change", "A_date_change"};
        return columns;
    }

    @Override
    protected String getTableKey() {
        return "bill_bill_no";
    }

    @Override
    protected Bill getMappingResults(ResultSet rs) throws SQLException {
        PayrollDao pDao = new PayrollDao();
        try {
            return new Bill(rs.getInt(1), pDao.find(rs.getInt(2)), rs.getDouble(3), rs.getString(4), rs.getString(5),
                    rs.getDate(6), rs.getString(7), rs.getDate(8));
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PayrollDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void setMappingParamsToCreate(PreparedStatement ps, Bill entity) throws SQLException {
        ps.setInt(1, entity.getBillNo());
        ps.setInt(2, entity.getPayrollNo().getPayrollNo());
        ps.setDouble(3, entity.getBilValue());
        ps.setString(4, entity.getBilDescription());
        ps.setString(5, entity.getUserCreate());
        ps.setString(6, DatesControls.dateToString(new Date()));
        ps.setString(7, entity.getUserChange());
        ps.setString(8, DatesControls.dateToString(new Date()));
    }

    @Override
    protected void setMappingParamsToUpdate(PreparedStatement ps, Bill entity) throws SQLException {
        ps.setInt(6, entity.getBillNo());
        ps.setInt(1, entity.getPayrollNo().getPayrollNo());
        ps.setDouble(2, entity.getBilValue());
        ps.setString(3, entity.getBilDescription());
        ps.setString(4, entity.getUserChange());
        ps.setString(5, DatesControls.dateToString(new Date()));
    }

    @Override
    protected String getColumnLike() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public double wagesValue(int id){
        double i=0;
        try {
            String sql= "SELECT SUM(bil_value) FROM emp_bil_bill where bil_payroll_no= "+id+" and bil_value > 0";
            Connection con = getConnection(); //Se conecta a la BD
            PreparedStatement ps = con.prepareStatement(getFindAllSQL()); //Crea el Statement
            ResultSet rs = ps.executeQuery(); //Ejecuta la query
            
            while (rs.next()) { //Si la BD encontro registros por cada uno itera
                i=rs.getInt(1); //Agrega los datos
            }
            closeJDBCObjects(con, ps, rs); //Cierra la conexión

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BillDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
    public List<Bill> billDescription(int id){
        
        List<Bill> lsBill=new ArrayList<>();
        try {
            String sql= getFindAllSQL()+SQL_WHERE +"bil_payroll_no=?";
            Connection con = getConnection(); //Se conecta a la BD
            PreparedStatement ps = con.prepareStatement(getFindAllSQL()); //Crea el Statement
            ResultSet rs = ps.executeQuery(); //Ejecuta la query
            
            while (rs.next()) { //Si la BD encontro registros por cada uno itera
               lsBill.add(getMappingResults(rs)); 
            }
            closeJDBCObjects(con, ps, rs); //Cierra la conexión

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BillDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsBill;
    }
    
}
