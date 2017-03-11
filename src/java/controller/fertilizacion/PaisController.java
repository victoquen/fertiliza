/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import helpers.Pais;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.PaisModel;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class PaisController implements Serializable{
    Pais actual;
    Pais selected;
    List<Pais> listado;
    List<Pais> filter;
    PaisModel model;
    
    

    public PaisController() {
        actual = new Pais();
        listado = Pais.getAllPais();
        model = new PaisModel(listado);
    }

    public Pais getActual() {
        return actual;
    }

    public void setActual(Pais actual) {
        this.actual = actual;
    }

    public Pais getSelected() {
        return selected;
    }

    public void setSelected(Pais selected) {
        this.selected = selected;
    }

    public List<Pais> getListado() {
        return listado;
    }

    public void setListado(List<Pais> listado) {
        this.listado = listado;
    }

    public List<Pais> getFilter() {
        return filter;
    }

    public void setFilter(List<Pais> filter) {
        this.filter = filter;
    }

    public PaisModel getModel() {
        return model;
    }

    public void setModel(PaisModel model) {
        this.model = model;
    }
    
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Pais Ingresado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Pais Modificado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Pais();
        listado = Pais.getAllPais();
        model = new PaisModel(listado);
    }

    Boolean controlDatos(Pais u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }
    
    
}
