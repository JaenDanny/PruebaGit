/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jaend
 */
public abstract class Servicio {

    protected Connection conexion = null;
    private String host = "localhost";
    private String puerto = "3306";
    private String sid = "cd_Demo";
    private String usuario = "root";
    private String clave = "Root";

    public void conectar() throws ClassNotFoundException, SQLException {
        //Paso 1
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Paso 2
        conexion = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + puerto + "/" + sid + /*"?autoReconnect=true"*/ "?serverTimeZone=UTC",
                usuario, clave);
    }

    public void cerrarPreparedStatement(PreparedStatement ps) {
        try {
            if (ps != null && !ps.isClosed()) {
                ps.close();
                ps = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrarResultset(ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
                rs = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            if (getConexion() != null && !getConexion().isClosed()) {
                getConexion().close();
                this.conexion = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Connection getConexion() {
        return conexion;
    }

    protected void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

}
