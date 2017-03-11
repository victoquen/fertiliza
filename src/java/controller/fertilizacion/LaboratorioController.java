/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Laboratorio;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.LaboratorioModel;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class LaboratorioController implements Serializable{
    Laboratorio actual;
    Laboratorio selected;
    List<Laboratorio> filter;
    List<Laboratorio> listado;
    LaboratorioModel model;

    public LaboratorioController() {
        actual = new Laboratorio();
        listado = Laboratorio.getAllLaboratorio();
        model = new LaboratorioModel(listado);
    }

    public Laboratorio getActual() {
        return actual;
    }

    public void setActual(Laboratorio actual) {
        this.actual = actual;
    }

    public Laboratorio getSelected() {
        return selected;
    }

    public void setSelected(Laboratorio selected) {
        this.selected = selected;
    }

    public List<Laboratorio> getFilter() {
        return filter;
    }

    public void setFilter(List<Laboratorio> filter) {
        this.filter = filter;
    }

    public List<Laboratorio> getListado() {
        return listado;
    }

    public void setListado(List<Laboratorio> listado) {
        this.listado = listado;
    }

    public LaboratorioModel getModel() {
        return model;
    }

    public void setModel(LaboratorioModel model) {
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
        actual = new Laboratorio();
        listado = Laboratorio.getAllLaboratorio();
        model = new LaboratorioModel(listado);
    }

    Boolean controlDatos(Laboratorio u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }
    
    
    
    
    
}
