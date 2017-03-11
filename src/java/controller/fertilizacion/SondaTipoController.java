/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.SondaTipo;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.SondaTipoModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class SondaTipoController implements Serializable{
    
    SondaTipo actual;
    SondaTipo selected;
    List<SondaTipo> listado;
    List<SondaTipo> filter;
    SondaTipoModel model;

    public SondaTipoController() {
        actual = new SondaTipo();
        listado = SondaTipo.getAllSondaTipo();
        model = new SondaTipoModel(listado);
    }

    public SondaTipo getActual() {
        return actual;
    }

    public void setActual(SondaTipo actual) {
        this.actual = actual;
    }

    public SondaTipo getSelected() {
        return selected;
    }

    public void setSelected(SondaTipo selected) {
        this.selected = selected;
    }

    public List<SondaTipo> getListado() {
        return listado;
    }

    public void setListado(List<SondaTipo> listado) {
        this.listado = listado;
    }

    public List<SondaTipo> getFilter() {
        return filter;
    }

    public void setFilter(List<SondaTipo> filter) {
        this.filter = filter;
    }

    public SondaTipoModel getModel() {
        return model;
    }

    public void setModel(SondaTipoModel model) {
        this.model = model;
    }
    
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "SondaTipo Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "SondaTipo Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new SondaTipo();
        listado = SondaTipo.getAllSondaTipo();
        model = new SondaTipoModel(listado);
    }

    Boolean controlDatos(SondaTipo u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }
    
    
}
