/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Departamento;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.DepartamentoModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class DepartamentoController implements Serializable {

    Departamento actual;
    Departamento selected;
    List<Departamento> listado;
    List<Departamento> filter;
    DepartamentoModel model;

    public DepartamentoController() {
        actual = new Departamento();
        listado = Departamento.getAll();
        model = new DepartamentoModel(listado);
    }

    public Departamento getActual() {
        return actual;
    }

    public void setActual(Departamento actual) {
        this.actual = actual;
    }

    public Departamento getSelected() {
        return selected;
    }

    public void setSelected(Departamento selected) {
        this.selected = selected;
    }

    public List<Departamento> getListado() {
        return listado;
    }

    public void setListado(List<Departamento> listado) {
        this.listado = listado;
    }

    public List<Departamento> getFilter() {
        return filter;
    }

    public void setFilter(List<Departamento> filter) {
        this.filter = filter;
    }

    public DepartamentoModel getModel() {
        return model;
    }

    public void setModel(DepartamentoModel model) {
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
        actual = new Departamento();
        listado = Departamento.getAll();
        model = new DepartamentoModel(listado);
    }

    Boolean controlDatos(Departamento u) {
        Boolean res = true;

        if ((u.getNombre().equals("")) || (u.getAbreviatura().equals(""))) {
            res = false;
        }
        return res;
    }
}
