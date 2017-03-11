/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Codigo;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.CodigoModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class CodigoController implements Serializable{
    Codigo actual;
    Codigo selected;
    List<Codigo> listado;
    List<Codigo> filter;
    CodigoModel model;

    public CodigoController() {
        actual = new Codigo();
        listado = Codigo.getAll();
        model = new CodigoModel(listado);
    }

    public Codigo getActual() {
        return actual;
    }

    public void setActual(Codigo actual) {
        this.actual = actual;
    }

    public Codigo getSelected() {
        return selected;
    }

    public void setSelected(Codigo selected) {
        this.selected = selected;
    }

    public List<Codigo> getListado() {
        return listado;
    }

    public void setListado(List<Codigo> listado) {
        this.listado = listado;
    }

    public List<Codigo> getFilter() {
        return filter;
    }

    public void setFilter(List<Codigo> filter) {
        this.filter = filter;
    }

    public CodigoModel getModel() {
        return model;
    }

    public void setModel(CodigoModel model) {
        this.model = model;
    }
    
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Codigo Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Codigo Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Codigo();
        listado = Codigo.getAll();
        model = new CodigoModel(listado);
    }

    Boolean controlDatos(Codigo u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }
}
