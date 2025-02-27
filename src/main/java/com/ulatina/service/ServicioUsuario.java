/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import com.ulatina.data.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaend
 */
public class ServicioUsuario extends Servicio {

    public Usuario validarUsuario(String user, String pass) {

        Usuario usuario = null;

        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            super.conectar();
            String sql = "SELECT id, Nombre, correo, clave FROM usuario WHERE correo = ?AND clave = ?";
            pstm = super.getConexion().prepareStatement(sql);
            pstm.setString(1, user);
            pstm.setString(2, pass);
            rs = pstm.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setClave(rs.getString("clave"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //paso 4
            cerrarPreparedStatement(pstm);
            cerrarConexion();
        }

        return usuario;
    }

    public Usuario validarUsuario(String correo) {

        Usuario usuario = null;

        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            super.conectar();
            String sql = "SELECT id, Nombre, correo, clave FROM usuario WHERE correo = ?";
            pstm = super.getConexion().prepareStatement(sql);
            pstm.setString(1, correo);
            rs = pstm.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setClave(rs.getString("clave"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //paso 4
            cerrarPreparedStatement(pstm);
            cerrarConexion();
        }

        return usuario;
    }

    public List<Usuario> demeUsuario() {

        List<Usuario> listaRetorno = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            super.conectar();
            String sql = "SELECT id, Nombre, correo, clave FROM usuario";
            pstm = super.getConexion().prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setClave(rs.getString("clave"));
                listaRetorno.add(usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //paso 4
            cerrarPreparedStatement(pstm);
            cerrarConexion();
        }

        return listaRetorno;
    }

    public void insertar(Usuario usuario) {
        PreparedStatement pstm = null;

        try {
            super.conectar();
            String sql = "INSERT INTO usuario (Nombre, correo, clave) VALUES (?,?,?)";
            pstm = super.getConexion().prepareStatement(sql);
            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getCorreo());
            pstm.setString(3, usuario.getClave());
            int cantidad = pstm.executeUpdate();
            if (cantidad == 0) {
                throw new SQLException("No se logro realizar el insert del usuario");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(pstm);
            cerrarConexion();
        }
    }

    public void actualizar(Usuario usuario) {
        PreparedStatement pstm = null;

        try {
            super.conectar();
            String sql = "UPDATE usuario SET Nombre = ?, clave = ? WHERE correo = ? ";
            pstm = super.getConexion().prepareStatement(sql);
            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getClave());
            pstm.setString(3, usuario.getCorreo());
            int cantidad = pstm.executeUpdate();
            if (cantidad == 0) {
                throw new SQLException("No se logro realizar el update del usuario");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(pstm);
            cerrarConexion();
        }
    }
    public void deleteUsuario(int id){
         PreparedStatement pstm = null;

        try {
            super.conectar();
            String sql = "DELETE FROM usuario where id = '"+ id +"' ";
            int cantidad = pstm.executeUpdate();
            if (cantidad == 0) {
                throw new SQLException("No se logro realizar el update del usuario");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(pstm);
            cerrarConexion();
        }
    }
    
}
