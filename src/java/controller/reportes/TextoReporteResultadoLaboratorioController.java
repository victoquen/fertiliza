/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.reportes;

import entities.reportes.TextoReporteResultadoLaboratorio;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.TextoReporteResultadoLaboratorioModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class TextoReporteResultadoLaboratorioController implements Serializable {

    TextoReporteResultadoLaboratorio actual;
    TextoReporteResultadoLaboratorio selected;
    List<TextoReporteResultadoLaboratorio> listado;
    List<TextoReporteResultadoLaboratorio> filter;
    TextoReporteResultadoLaboratorioModel model;

    public TextoReporteResultadoLaboratorioController() {
        actual = new TextoReporteResultadoLaboratorio();
        listado = TextoReporteResultadoLaboratorio.getAll();
        model = new TextoReporteResultadoLaboratorioModel(listado);
    }

    public TextoReporteResultadoLaboratorio getActual() {
        return actual;
    }

    public void setActual(TextoReporteResultadoLaboratorio actual) {
        this.actual = actual;
    }

    public TextoReporteResultadoLaboratorio getSelected() {
        return selected;
    }

    public void setSelected(TextoReporteResultadoLaboratorio selected) {
        this.selected = selected;
    }

    public List<TextoReporteResultadoLaboratorio> getListado() {
        return listado;
    }

    public void setListado(List<TextoReporteResultadoLaboratorio> listado) {
        this.listado = listado;
    }

    public List<TextoReporteResultadoLaboratorio> getFilter() {
        return filter;
    }

    public void setFilter(List<TextoReporteResultadoLaboratorio> filter) {
        this.filter = filter;
    }

    public TextoReporteResultadoLaboratorioModel getModel() {
        return model;
    }

    public void setModel(TextoReporteResultadoLaboratorioModel model) {
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
        actual = new TextoReporteResultadoLaboratorio();
        listado = TextoReporteResultadoLaboratorio.getAll();
        model = new TextoReporteResultadoLaboratorioModel(listado);
    }

    Boolean controlDatos(TextoReporteResultadoLaboratorio u) {
        Boolean res = true;

        if ((u.getReferencia().equals("")) ||(u.getDescripcion().equals(""))) {
            res = false;
        }
        return res;
    }
    
}
