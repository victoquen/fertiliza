/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.TipoAplicacionFuente;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.TipoAplicacionFuenteModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class TipoAplicacionFuenteController implements Serializable{
    
    TipoAplicacionFuente actual;
    TipoAplicacionFuente selected;
    List<TipoAplicacionFuente> listado;
    List<TipoAplicacionFuente> filter;
    TipoAplicacionFuenteModel model;

    public TipoAplicacionFuenteController() {
        actual = new TipoAplicacionFuente();
        listado = TipoAplicacionFuente.getAll();
        model = new TipoAplicacionFuenteModel(listado);
    }

    public TipoAplicacionFuente getActual() {
        return actual;
    }

    public void setActual(TipoAplicacionFuente actual) {
        this.actual = actual;
    }

    public TipoAplicacionFuente getSelected() {
        return selected;
    }

    public void setSelected(TipoAplicacionFuente selected) {
        this.selected = selected;
    }

    public List<TipoAplicacionFuente> getListado() {
        return listado;
    }

    public void setListado(List<TipoAplicacionFuente> listado) {
        this.listado = listado;
    }

    public List<TipoAplicacionFuente> getFilter() {
        return filter;
    }

    public void setFilter(List<TipoAplicacionFuente> filter) {
        this.filter = filter;
    }

    public TipoAplicacionFuenteModel getModel() {
        return model;
    }

    public void setModel(TipoAplicacionFuenteModel model) {
        this.model = model;
    }
    
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "TipoAplicacionFuente Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "TipoAplicacionFuente Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new TipoAplicacionFuente();
        listado = TipoAplicacionFuente.getAll();
        model = new TipoAplicacionFuenteModel(listado);
    }

    Boolean controlDatos(TipoAplicacionFuente u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }
}
