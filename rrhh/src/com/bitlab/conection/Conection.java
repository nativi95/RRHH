/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nativi
 */
public class Conection {

    //++++++++++++++++++++++++++++++Atributos de conexion+++++++++++++++++++++++++++++
    private static final String BD = "employees";
    private static final String USER = "root";
    private static final String PASS = "bathory80";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BD + "?useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    /**
     * metodo para generar conexion a base de datos JDBC
     *
     * @return Conexion
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    Connection conn = null;

    //++++++++++++++++++++++++++++++++metodos para abrir y cerrar conexion++++++++++++++++
    public static Connection openConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);//se ingresa driver de conexion
        Connection conn = DriverManager.getConnection(URL, USER, PASS);//retorna conexion a BD
        if (conn != null) {
            System.out.println("se conecto");
        } else {
            System.out.println("nell");
        }
        return conn;
    }

    /**
     * metodo para cerrar la conexion
     *
     * @param con objeto de conexion
     * @throws SQLException
     */
    public static void closeConnection(Connection con) throws SQLException {
        if (con != null && !con.isClosed())// validacion de objeto con no estar cerrado y diferente de null
        {
            con.close();//cierra conexion
        }
    }
    
    public static void main(String[] args) {
        try {
            Conection.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
