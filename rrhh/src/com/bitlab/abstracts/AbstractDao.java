/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.abstracts;

import com.bitlab.conection.Conection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author nativi
 */
public abstract class AbstractDao<T> {

    //++++++++++++++++++++++++++++++++++++++++++atributos++++++++++++++++++++++++++++++++++++
    private final int MAXROWS = 1000;

    //+++++++base para las consultas++++++++++
    private final String SQL_FINDALL = "SELECT [COLUMNS] FROM [TABLE]";//consulta basica de consulta select
    private final String SQL_CREATE = "INSERT INTO [TABLE] VALUES ([COLUMNS_INDEX])";//estructura para nuevos registros
    private final String SQL_REMOVE = "DELETE FROM [TABLE] WHERE [CONDITIONS]";// estructura sql para eliminar registros
    private final String SQL_UPDATE = "UPDATE [TABLE] SET [COLUMNS] WHERE [CONDITIONS]";//estructura sql para actualizar

    //+++++++sustitutos de consultas+++++++++++
    private final String COLUMNS_INDICATOR_INDEX = "[COLUMNS_INDEX]";//comodin para sustituir values de insert
    private final String TABLE_INDICATOR = "[TABLE]";// comodin del nombre de la tabla
    private final String COLUMNS_INDICATOR = "[COLUMNS]";//comodin nombre de columna
    public final String SQL_WHERE = " WHERE ";// complemente para sentencias con condicion
    private final String CONDITIONS_INDICATOR = "[CONDITIONS]";// comodin de condicion

    //++++++++++++++++++++++++++++++++++abstraccion de nomenclatura de BD++++++++++++++++++++++++++++++++
    /**
     * metodo abstracto retorna nombre de la tabla
     *
     * @return String
     */
    protected abstract String getTableName();

    /**
     * metodo abstracto retorna los nombres de las columnas para sentencias
     * update o insert
     *
     * @return String[]
     */
    protected abstract String[] getTableColumns();
    
    
    /**
     * metodo abstracto retorna los nombres de la columna a buscar
     *
     * @return String[]
     */
    protected abstract String getColumnLike();

    /**
     * metodo abstracto retorna condiccion de las sentencias para sentencias
     * select, update o delete
     *
     * @return String
     */
    protected abstract String getTableKey();

    /**
     * metodo abstracto retorna mapeo del ResultSet para sentencias select
     *
     * @param rs ResultSet
     * @return T entity
     */
    protected abstract T getMappingResults(ResultSet rs) throws SQLException;

    /**
     * metodo abstracto retorna parametros de la setencias Insert
     *
     * @param ps PreparedStatement
     * @param T entity
     */
    protected abstract void setMappingParamsToCreate(PreparedStatement ps, T entity) throws SQLException;

    /**
     * metodo abstracto retorna parametros de la setencias updates
     *
     * @param ps PreparedStatement
     * @param T entity
     */
    protected abstract void setMappingParamsToUpdate(PreparedStatement ps, T entity) throws SQLException;

    public List getColumnsName() {
        List names = new ArrayList<>();

        for (byte i = 1; i < getTableColumns().length; i++) {
            if (!getTableColumns()[i].equals("A_user_create") || !getTableColumns()[i].equals("A_user_change") || !getTableColumns()[i].equals("A_date_create") || !getTableColumns()[i].equals("A_date_change")) {
                names.add(getTableColumns()[i].substring(4));
            }

        }
        return names;

    }

    //+++++++++++++++++++++++++++++++++++gestion de conecion++++++++++++++++++++++++++++++++++++++++++
    /**
     * metodo para crear una conexion JDBC a BD
     *
     * @return con de la clase com.bitlab.conection.Conection
     */
    protected Connection getConnection() throws SQLException, ClassNotFoundException {
        return Conection.openConnection();
    }

    /**
     * metodo para cerrar la conexion de los objetos
     *
     * @param con de la clase Connection
     */
    protected void closeJDBCObjects(Connection con) throws SQLException {
        Conection.closeConnection(con);
    }

    /**
     * metodo para cerrar la conexion de los objetos
     *
     * @param con de la interface Connection
     * @param ps de la interface PreparedStatement
     */
    protected void closeJDBCObjects(Connection con, PreparedStatement ps) throws SQLException {
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        Conection.closeConnection(con);
    }

    /**
     * metodo para cerrar la conexion de los objetos
     *
     * @param con de la interface Connection
     * @param ps de la interface PreparedStatement
     * @param rs de la interface ResultSet
     */
    protected void closeJDBCObjects(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        Conection.closeConnection(con);
    }

    //++++++++++++++++++++++++++++++++++++sentencias contruidas+++++++++++++++++++++++++++++++++++++++
    /**
     * metodo que permite construir la sentencia estandar SELECT a partir de los
     * siguientes metodos: getTableName() ingresar nombre de tabla
     * getTableColumns() nombre de las columnas en orden
     *
     * @return String sql
     */
    protected String getFindAllSQL() {
        String sql = SQL_FINDALL;
        sql = sql.replace(TABLE_INDICATOR, getTableName()).replace(COLUMNS_INDICATOR, Arrays.toString(getTableColumns()));
        sql = sql.replace("[", " ").replace("]", " ");
        return sql;
    }

    /**
     * metodo que permite construir la sentencia estandar INSERT a partir de los
     * siguientes metodos: getTableName() ingresar nombre de tabla
     * getTableColumns() nombre de las columnas en orden
     *
     * @return String sql
     */
    protected String getCreateSQL() {
        // Se toma el SQL INSERT INTO [TABLE] ([COLUMNS]) VALUES ([COLUMNS_INDEX])
        String sql = SQL_CREATE;

        //Se crea un pequeno proceso de concatenación para basado en la cantidad de columnas genere 
        //los indicadores ? dando como resultado: ?,?,?.... para la sección final del sql insert JDBC
        String sqlColumnsIndicator = "";
        for (int i = 0; i < getTableColumns().length; i++) {
            sqlColumnsIndicator += "?,";
        }

        //Al finalizar el proceso de concatenación tendremos una "," de más se elimina con un substring
        sqlColumnsIndicator = sqlColumnsIndicator.substring(0, sqlColumnsIndicator.length() - 1);
        //Se reemplaza el [COLUMNS_INDICATOR_INDEX] con el resultado del proceso de concatenación
        sql = sql.replace(COLUMNS_INDICATOR_INDEX, sqlColumnsIndicator);

        //Se reemplazan los valores de los comodines [TABLE_INDICATOR] y [COLUMNS_INDICATOR] 
        //con el nombre de la tabla y columnas que indicará la clase hija 
        sql = sql.replace(TABLE_INDICATOR, getTableName())
                .replace("[", "").replace("]", ""); //Debido al metodo Arrays devuelve los resultas [campo,campo2,campo3] se aplica el replace de los corchetes []

        return sql;
    }

    /**
     * metodo que permite construir la sentencia estandar UPDATE a partir de los
     * siguientes metodos: getTableName() ingresar nombre de tabla getTableKey()
     * condicion WHERE getTableColumns() nombre de las columnas en orden
     *
     * @return String sql
     */
    protected String getUpdateSQL() {
        //Se toma la constante con los comodines UPDATE [TABLE] SET [COLUMNS] WHERE [CONDITIONS]
        //En primera linea se reemplaza el comodin de [CONDITIONS] por la columna llave que se indicará en la clase hija
        //Para llevar la siguiente estructura UPDATE [TABLE] SET [COLUMNS] WHERE *columnaLlave = ?*
        String sql = SQL_UPDATE.replace(CONDITIONS_INDICATOR, getTableKey() + " = ?");
        //Se reemplaza el comodin [TABLE] por la tabla
        sql = sql.replace(TABLE_INDICATOR, getTableName());

        //Se crea un pequeno proceso de concatenación de CAMPO=?,
        StringBuilder strIndicators = new StringBuilder();
        for (byte i = 1; i < getTableColumns().length; i++) {
            if (!getTableColumns()[i].equals("A_user_create") && !getTableColumns()[i].equals("A_date_create") && !getTableColumns()[i].equals(getTableKey())) {
                strIndicators.append(getTableColumns()[i]).append("=?,");
            }
        }
byte size=(byte)strIndicators.toString().length();
        //Se quita la ultima "," de la cadena para formatear correctamente el SQL y se reemplaza el comodin 
        sql = sql.replace(COLUMNS_INDICATOR, strIndicators.toString().substring(0, size- 1));
        return sql;
    }

    /**
     * metodo que permite construir la sentencia estandar REMOVE a partir de los
     * siguientes metodos: getTableName() ingresar nombre de tabla getTableKey()
     * condicion WHERE getTableColumns() nombre de las columnas en orden
     * getTableKey() establece la condicion
     *
     * @return String sql
     */
    protected String getRemoveSQL() {
        //Se reemplazan los comodines [TABLE] y [CONDITIONS] por las condiciones de *llaveTabla = ?*
        String sql = SQL_REMOVE.replace(TABLE_INDICATOR, getTableName()).replace(CONDITIONS_INDICATOR, getTableKey() + " = ?");

        return sql;
    }

    //++++++++++++++++++++++++++++++++++++++++++crud basico+++++++++++++++++++++++++++++++++++++++++++++
    /**
     * metodo para ejecutar SELECT y listar resultados
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     * @return List<T>
     */
    public List<T> findAll() throws SQLException, ClassNotFoundException {
        Connection con = getConnection(); //Se conecta a la BD
        PreparedStatement ps = con.prepareStatement(getFindAllSQL()); //Crea el Statement
        ps.setMaxRows(MAXROWS); //aplica el máximo de registros a devolver
        ResultSet rs = ps.executeQuery(); //Ejecuta la query
        List<T> objects = new ArrayList<>();
        while (rs.next()) { //Si la BD encontro registros por cada uno itera
            objects.add(getMappingResults(rs)); //Agrega los datos
        }
        closeJDBCObjects(con, ps, rs); //Cierra la conexión
        return objects;
    }

    /**
     * metodo para ejecutar INSERT y agregar un campo
     *
     * @param entity de la base de datos
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void create(T entity) throws SQLException, ClassNotFoundException {
        Connection con = getConnection(); //Se conecta a la BD
        PreparedStatement ps = con.prepareStatement(getCreateSQL()); //Crea el Statement
        setMappingParamsToCreate(ps, entity); //Se mapean los datos de la entidad al statement para enviarlos a la BD
        ps.executeUpdate(); //Se ejecuta en la BD
        closeJDBCObjects(con, ps); //Se cierran los objetos
    }

    /**
     * metodo para ejecutar DELETE y eliminar un campo
     *
     * @param id llave identificadora del campo
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void remove(Object id) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();//Se obtiene la conexión
        PreparedStatement ps = con.prepareStatement(getRemoveSQL()); //Se aplica el Statement
        ps.setObject(1, id); //Se aplica el parametro de condición
        ps.execute(); //Se ejecuta el query
        closeJDBCObjects(con, ps); //Se cierran los datos de conexión
    }

    /**
     * metodo para ejecutar UPDATE y actualiza un campo
     *
     * @param entity llave identificadora del campo y las actualizaciones del
     * registro
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void update(T entity) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();//Se obtiene la conexión
        PreparedStatement ps = con.prepareStatement(getUpdateSQL());//Se aplica el SQL
        setMappingParamsToUpdate(ps, entity);//Se hace el mapeo de la entidad al PreparedStatement
        ps.executeUpdate(); //Se ejecuta el query
        closeJDBCObjects(con, ps); //Se cierran los objetos
    }

    /**
     * metodo para ejecutar SELECT condicionado por ID
     *
     * @param id llave identificadora del campo
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public T find(Object id) throws SQLException, ClassNotFoundException {
        String sql = getFindAllSQL() + SQL_WHERE + getTableKey() + " = ?"; //Se juega con el SQL dinámico
        Connection con = getConnection(); //Se obtiene la conexión
        PreparedStatement ps = con.prepareStatement(sql); //Se prepara el Statement
        ps.setObject(1, id); //Se aplican los parametros
        ResultSet rs = ps.executeQuery(); //Se ejecuta y se utiliza un ResultSet para obtener los valores
        T e = null;
        if (rs.next()) { //Si la BD devolvio coincidencias (Registros)
            e = getMappingResults(rs); //Se mapean y se asignan a la variable
        }
        closeJDBCObjects(con, ps, rs); //Se cierra la conexión
        return e;
    }
    
    public List<T> findLike(Object name) throws SQLException, ClassNotFoundException {
        String sql = getFindAllSQL() + SQL_WHERE + getColumnLike() + " LIKE %?%"; //Se juega con el SQL dinámico
        Connection con = getConnection(); //Se obtiene la conexión
        PreparedStatement ps = con.prepareStatement(sql); //Se prepara el Statement
        ps.setObject(1, name); //Se aplican los parametros
        ResultSet rs = ps.executeQuery(); //Se ejecuta y se utiliza un ResultSet para obtener los valores
        List<T> objects = new ArrayList<>();
        while (rs.next()) { //Si la BD encontro registros por cada uno itera
            objects.add(getMappingResults(rs)); //Agrega los datos
        }
        closeJDBCObjects(con, ps, rs); //Cierra la conexión
        return objects;
    }
}
