/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Profundidad;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.ProfundidadModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class ProfundidadController implements Serializable{
    Profundidad actual;
    Profundidad selected;
    List<Profundidad> listado;
    List<Profundidad> filter;
    ProfundidadModel model;
    
    
    
    

    public ProfundidadController() {
        this.actual = new Profundidad();
        this.listado = Profundidad.getAll();
        this.model = new ProfundidadModel(listado);
        
        
    }

    public Profundidad getActual() {
        return actual;
    }

    public void setActual(Profundidad actual) {
        this.actual = actual;
    }

    public Profundidad getSelected() {
        return selected;
    }

    public void setSelected(Profundidad selected) {
        this.selected = selected;
    }

    public List<Profundidad> getListado() {
        return listado;
    }

    public void setListado(List<Profundidad> listado) {
        this.listado = listado;
    }

    public List<Profundidad> getFilter() {
        return filter;
    }

    public void setFilter(List<Profundidad> filter) {
        this.filter = filter;
    }

    public ProfundidadModel getModel() {
        return model;
    }

    public void setModel(ProfundidadModel model) {
        this.model = model;
    }
           
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Profundidad();
        listado = Profundidad.getAll();
        model = new ProfundidadModel(listado);
    }

    Boolean controlDatos(Profundidad u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }
}
