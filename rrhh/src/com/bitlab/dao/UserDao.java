/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.abstracts.AbstractDao;
import com.bitlab.conection.Conection;
import com.bitlab.entities.Rol;
import com.bitlab.entities.User;
import com.bitlab.util.DatesControls;
import com.bitlab.util.Sha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author CarlosAlex
 */
public class UserDao extends AbstractDao<User> {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(UserDao.class);

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
        RolDao rDao=new RolDao();
        try {
            logger.debug("Mapeo de resulset de User");
            return new User(rs.getInt(1),rs.getString(2),rs.getString(3), (rDao.find(rs.getInt(4))),rs.getString(5), rs.getDate(6),rs.getString(7), rs.getDate(8));
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en el mapeo", ex);
        }
        return null;
    }
    
   

    @Override
    protected void setMappingParamsToCreate(PreparedStatement ps, User entity) throws SQLException {
        logger.debug("Mapeo de paramentros para crear Usuario");
        ps.setInt(1, entity.getUserNo());
        ps.setString(2, entity.getUser());
        ps.setString(3, Sha.encrypt(entity.getPassword()));
        ps.setInt(4, entity.getRolNo().getRolRolNo());
        ps.setString(5, entity.getUserCreate());
        ps.setString(6, DatesControls.dateToString(entity.getDateCreate()));
        ps.setString(7, entity.getUserChange());
        ps.setString(8, DatesControls.dateToString(entity.getDateChange()));
    }

    @Override
    protected void setMappingParamsToUpdate(PreparedStatement ps, User entity) throws SQLException {
        logger.debug("Mapeo de paramentros para actualizar Usuario");
        ps.setInt(6, entity.getUserNo());
        ps.setString(1, entity.getUser());
        ps.setString(2, Sha.encrypt(entity.getPassword()));
        ps.setInt(3, entity.getRolNo().getRolRolNo());
        ps.setString(4, entity.getUserChange());
        ps.setString(5, DatesControls.dateToString(entity.getDateChange()));
    }

    @Override
    protected String getColumnLike() {
        return "usr_user";
    }
    
    public User login (String user, String pass){
        try {
            Connection con=Conection.openConnection();
            String sql=getFindAllSQL()+" "+SQL_WHERE+"usr_user=? AND user_password=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery(); //Se ejecuta y se utiliza un ResultSet para obtener los valores
        List<User> objects = new ArrayList<>();
        while (rs.next()) { //Si la BD encontro registros por cada uno itera
            objects.add(getMappingResults(rs)); //Agrega los datos
        }
        closeJDBCObjects(con, ps, rs); //Cierra la conexión
        
        if(!objects.isEmpty()){
        return objects.get(0);
        }
        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
