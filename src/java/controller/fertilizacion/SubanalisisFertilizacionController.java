/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.SubanalisisFertilizacion;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.SubanalisisFertilizacionModel;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class SubanalisisFertilizacionController implements Serializable{
    SubanalisisFertilizacion actual;
    SubanalisisFertilizacion selected;
    
    List<SubanalisisFertilizacion> listado;
    List<SubanalisisFertilizacion> filter;
    
    SubanalisisFertilizacionModel model;

    public SubanalisisFertilizacionController() {
        actual = new SubanalisisFertilizacion();
        listado = SubanalisisFertilizacion.getAllSubanalisis();
        model = new SubanalisisFertilizacionModel(listado);
    }

    public SubanalisisFertilizacion getActual() {
        return actual;
    }

    public void setActual(SubanalisisFertilizacion actual) {
        this.actual = actual;
    }

    public SubanalisisFertilizacion getSelected() {
        return selected;
    }

    public void setSelected(SubanalisisFertilizacion selected) {
        this.selected = selected;
    }

    public List<SubanalisisFertilizacion> getListado() {
        return listado;
    }

    public void setListado(List<SubanalisisFertilizacion> listado) {
        this.listado = listado;
    }

    public List<SubanalisisFertilizacion> getFilter() {
        return filter;
    }

    public void setFilter(List<SubanalisisFertilizacion> filter) {
        this.filter = filter;
    }

    public SubanalisisFertilizacionModel getModel() {
        return model;
    }

    public void setModel(SubanalisisFertilizacionModel model) {
        this.model = model;
    }
    
    
    
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Subanalisis de Fertilizacion Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }
    
    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Subanalisis de Fertilizacion Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }
    
    public void load() {
        actual = new SubanalisisFertilizacion();
        listado = SubanalisisFertilizacion.getAllSubanalisis();
        model = new SubanalisisFertilizacionModel(listado);
    }
    
    Boolean controlDatos(SubanalisisFertilizacion u) {
        Boolean res = true;

        if ((u.getNombre().equals("")) || u.getSimbolo().equals("")) {
            res = false;
        }
        return res;
    }
}
