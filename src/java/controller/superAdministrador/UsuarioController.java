/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.superAdministrador;

import entities.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.UsuarioModel;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class UsuarioController implements Serializable{
    
    Usuario actual;
    Usuario selected;
    List<Usuario> listado;
    List<Usuario> filter;
    UsuarioModel model;
    
    Integer auxTipo;

    public UsuarioController() {
        actual = new Usuario();
        listado = Usuario.getAllUsuarios();
        model = new UsuarioModel(listado);
        
        ExternalContext tmpEC;
        Map sMap;
        tmpEC = FacesContext.getCurrentInstance().getExternalContext();
        sMap = tmpEC.getSessionMap();
        Usuario loginBean = (Usuario) sMap.get("auth_user");

        selected = loginBean;
        
        auxTipo=-1;
        
    
    }

    public Usuario getActual() {
        return actual;
    }

    public void setActual(Usuario actual) {
        this.actual = actual;
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    public List<Usuario> getListado() {
        return listado;
    }

    public void setListado(List<Usuario> listado) {
        this.listado = listado;
    }

    public List<Usuario> getFilter() {
        return filter;
    }

    public void setFilter(List<Usuario> filter) {
        this.filter = filter;
    }

    public UsuarioModel getModel() {
        return model;
    }

    public void setModel(UsuarioModel model) {
        this.model = model;
    }

    public Integer getAuxTipo() {
        return auxTipo;
    }

    public void setAuxTipo(Integer auxTipo) {
        this.auxTipo = auxTipo;
    }
    
    
    
    public void save(){
        
        if(controlDatos(actual)){
            actual.setLeyendaTipo(actual.returnLeyendaTipo(actual.getTipo()));
            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Usuario Ingresado"));
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }        
        
    }
    
    public void update(){
        if(controlDatos(selected)){
            actual.setLeyendaTipo(actual.returnLeyendaTipo(actual.getTipo()));
            selected.update();
             load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Usuario Modificado"));
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }
    
    public void load(){
        actual = new Usuario();
        listado = Usuario.getAllUsuarios();
        model = new UsuarioModel(listado);
    }
    
    Boolean controlDatos(Usuario u){
        Boolean res = true;
        
        if((u.getNombre().equals(""))||(u.getPassword().equals(""))||(u.getTipo()==-1))
        {
            res = false;
        }
        return res;
    }
    
    
}
