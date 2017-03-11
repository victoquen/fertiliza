/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Courier;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.CourierModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class CourierController implements Serializable{
    Courier actual;
    Courier selected;
    List<Courier> listado;
    List<Courier> filter;
    CourierModel model;

    public CourierController() {
        actual = new Courier();
        listado = Courier.getAll();
        model = new CourierModel(listado);
    }

    public Courier getActual() {
        return actual;
    }

    public void setActual(Courier actual) {
        this.actual = actual;
    }

    public Courier getSelected() {
        return selected;
    }

    public void setSelected(Courier selected) {
        this.selected = selected;
    }

    public List<Courier> getListado() {
        return listado;
    }

    public void setListado(List<Courier> listado) {
        this.listado = listado;
    }

    public List<Courier> getFilter() {
        return filter;
    }

    public void setFilter(List<Courier> filter) {
        this.filter = filter;
    }

    public CourierModel getModel() {
        return model;
    }

    public void setModel(CourierModel model) {
        this.model = model;
    }
    
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Courier Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Courier Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Courier();
        listado = Courier.getAll();
        model = new CourierModel(listado);
    }

    Boolean controlDatos(Courier u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }
}
