/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Cultivo;
import entities.fertilizacion.Variedad;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.VariedadModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class VariedadController implements Serializable {

    Variedad actual;
    Variedad selected;
    List<Variedad> listado;
    List<Variedad> filter;
    VariedadModel model;
    
    List<Cultivo> listadoCultivo;

    public VariedadController() {
        actual = new Variedad();
        listado = Variedad.getAllVariedad();
        model = new VariedadModel(listado);

        listadoCultivo = Cultivo.getAllCultivos();
    }

    public List<Cultivo> getListadoCultivo() {
        return listadoCultivo;
    }

    public void setListadoCultivo(List<Cultivo> listadoCultivo) {
        this.listadoCultivo = listadoCultivo;
    }
    
    public Variedad getActual() {
        return actual;
    }

    public void setActual(Variedad actual) {
        this.actual = actual;
    }

    public Variedad getSelected() {
        return selected;
    }

    public void setSelected(Variedad selected) {
        this.selected = selected;
    }

    public List<Variedad> getListado() {
        return listado;
    }

    public void setListado(List<Variedad> listado) {
        this.listado = listado;
    }

    public List<Variedad> getFilter() {
        return filter;
    }

    public void setFilter(List<Variedad> filter) {
        this.filter = filter;
    }

    public VariedadModel getModel() {
        return model;
    }

    public void setModel(VariedadModel model) {
        this.model = model;
    }

    
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Variedad Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Variedad Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Variedad();
        listado = Variedad.getAllVariedad();
        model = new VariedadModel(listado);
    }

    Boolean controlDatos(Variedad u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))||(u.getCultivo()==null)) {
            res = false;
        }
        return res;
    }
}
