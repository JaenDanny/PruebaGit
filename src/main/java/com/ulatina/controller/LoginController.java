/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.controller;

import com.ulatina.data.Usuario;
import com.ulatina.service.ServicioUsuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.PrimeFaces;

/**
 *
 * @author jaend
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

    private String user;
    private String pass;
    private Usuario usuario = new Usuario();
    private Usuario selectedUsuario = null;
    private List<Usuario> listaUsuario = new ArrayList<>();
    private ServicioUsuario servicioUsuario = new ServicioUsuario();
    private boolean esInsertar = false;

    public LoginController() {
    }

    public void ingresar() {
        this.usuario = servicioUsuario.validarUsuario(user, pass);
        if (usuario != null) {
            //Usuario Valido

            this.listaUsuario = servicioUsuario.demeUsuario();
            this.redireccionar("/Bienvenida.xhtml");
        } else {

            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_WARN, "Campos invalidos", "El usuairo o contrasena es invalido"));
        }
    }

    public void openNew() {
        System.out.println("Estoy en el opneNew");
        this.selectedUsuario = new Usuario();
        this.esInsertar = true;
    }

    public void saveUser() {
        System.out.println("Estoy en saveUser..." + this.selectedUsuario);

        try {
            if (this.servicioUsuario.validarUsuario(this.selectedUsuario.getCorreo()) == null) {
                this.servicioUsuario.insertar(this.selectedUsuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario agregado correctamente."));
            } else {
                this.servicioUsuario.actualizar(this.selectedUsuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario actualizado correctamente."));
            }
            this.listaUsuario = servicioUsuario.demeUsuario();
            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El usuaario no se pudo agregar. Intente nuevamente"));
        }

        PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
    }

    public void deleteUsuario(int id) {
       
    }

    public void limpiar() {
        this.esInsertar = false;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public boolean esInsertar() {
        return esInsertar;
    }

    public void setEsInsertar(boolean esInsertar) {
        this.esInsertar = esInsertar;
    }

}
