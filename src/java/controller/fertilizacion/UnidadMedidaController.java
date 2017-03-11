/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.UnidadMedida;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.UnidadMedidaModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class UnidadMedidaController implements Serializable{
    
    UnidadMedida actual;
    UnidadMedida selected;
    List<UnidadMedida> listado;
    List<UnidadMedida> filter;
    UnidadMedidaModel model;

    public UnidadMedidaController() {
        actual = new UnidadMedida();
        listado = UnidadMedida.getAll();
        model = new UnidadMedidaModel(listado);
    }

    public UnidadMedida getActual() {
        return actual;
    }

    public void setActual(UnidadMedida actual) {
        this.actual = actual;
    }

    public UnidadMedida getSelected() {
        return selected;
    }

    public void setSelected(UnidadMedida selected) {
        this.selected = selected;
    }

    public List<UnidadMedida> getListado() {
        return listado;
    }

    public void setListado(List<UnidadMedida> listado) {
        this.listado = listado;
    }

    public List<UnidadMedida> getFilter() {
        return filter;
    }

    public void setFilter(List<UnidadMedida> filter) {
        this.filter = filter;
    }

    public UnidadMedidaModel getModel() {
        return model;
    }

    public void setModel(UnidadMedidaModel model) {
        this.model = model;
    }
    
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "UnidadMedida Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "UnidadMedida Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new UnidadMedida();
        listado = UnidadMedida.getAll();
        model = new UnidadMedidaModel(listado);
    }

    Boolean controlDatos(UnidadMedida u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }
    
}
