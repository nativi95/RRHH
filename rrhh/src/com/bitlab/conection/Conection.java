/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author nativi
 */
public class Conection {
    
    //++++++++++++++++++++++++++++++Atributos de conexion+++++++++++++++++++++++++++++
    private static final String BD="db_employee";
    private static final String USER="root";
    private static final String PASS="bathory80";
    private static final String URL="jdbc:mysql://localhost:3306/"+BD+"?useSSL=false";
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    
    /**
     * metodo para generar conexion a base de datos JDBC
     * @return Conexion
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    
    //++++++++++++++++++++++++++++++++metodos para abrir y cerrar conexion++++++++++++++++
    public static Connection openConnection() throws SQLException, ClassNotFoundException{
    Class.forName(DRIVER);//se ingresa driver de conexion
    return DriverManager.getConnection(URL, USER, PASS);//retorna conexion a BD
    }
    /**
     * metodo para cerrar la conexion
     * @param con objeto de conexion
     * @throws SQLException 
     */
     public static void closeConnection(Connection con) throws SQLException{
        if(con!=null && !con.isClosed())// validacion de objeto con no estar cerrado y diferente de null
            con.close();//cierra conexion
    }
}
