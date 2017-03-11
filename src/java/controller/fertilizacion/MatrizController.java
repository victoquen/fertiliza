/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Matriz;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.MatrizModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class MatrizController implements Serializable{
    Matriz actual;
    Matriz selected;
    List<Matriz> listado;
    List<Matriz> filter;
    MatrizModel model;

    public MatrizController() {
        actual = new Matriz();
        listado = Matriz.getAll();
        model = new MatrizModel(listado);
    }

    public Matriz getActual() {
        return actual;
    }

    public void setActual(Matriz actual) {
        this.actual = actual;
    }

    public Matriz getSelected() {
        return selected;
    }

    public void setSelected(Matriz selected) {
        this.selected = selected;
    }

    public List<Matriz> getListado() {
        return listado;
    }

    public void setListado(List<Matriz> listado) {
        this.listado = listado;
    }

    public List<Matriz> getFilter() {
        return filter;
    }

    public void setFilter(List<Matriz> filter) {
        this.filter = filter;
    }

    public MatrizModel getModel() {
        return model;
    }

    public void setModel(MatrizModel model) {
        this.model = model;
    }
    
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Matriz Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Matriz Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Matriz();
        listado = Matriz.getAll();
        model = new MatrizModel(listado);
    }

    Boolean controlDatos(Matriz u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }
    
}
