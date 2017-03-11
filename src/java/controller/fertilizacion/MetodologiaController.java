/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Metodologia;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.MetodologiaModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class MetodologiaController implements Serializable {

    Metodologia actual;
    Metodologia selected;
    List<Metodologia> listado;
    List<Metodologia> filter;
    MetodologiaModel model;

    public MetodologiaController() {
        actual = new Metodologia();
        listado = Metodologia.getAllMetodologia();
        model = new MetodologiaModel(listado);
    }

    public Metodologia getActual() {
        return actual;
    }

    public void setActual(Metodologia actual) {
        this.actual = actual;
    }

    public Metodologia getSelected() {
        return selected;
    }

    public void setSelected(Metodologia selected) {
        this.selected = selected;
    }

    public List<Metodologia> getListado() {
        return listado;
    }

    public void setListado(List<Metodologia> listado) {
        this.listado = listado;
    }

    public List<Metodologia> getFilter() {
        return filter;
    }

    public void setFilter(List<Metodologia> filter) {
        this.filter = filter;
    }

    public MetodologiaModel getModel() {
        return model;
    }

    public void setModel(MetodologiaModel model) {
        this.model = model;
    }
    
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Metodología Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Metodología Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Metodologia();
        listado = Metodologia.getAllMetodologia();
        model = new MetodologiaModel(listado);
    }

    Boolean controlDatos(Metodologia u) {
        Boolean res = true;

        if ((u.getNombre().equals("")) ||(u.getLiteratura().equals(""))) {
            res = false;
        }
        return res;
    }

}
