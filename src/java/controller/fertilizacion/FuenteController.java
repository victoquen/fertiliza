/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Fuente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.FuenteModel;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class FuenteController implements Serializable {

    Fuente actual;
    Fuente selected;
    List<Fuente> listado;
    List<Fuente> filter;
    FuenteModel model;

    List<String> listaPresentacion;

    List<String> listaProveedores;
    List<String> listaFfoliar;
    List<String> listaFertirriego;
    List<String> listaFedafico;

    public FuenteController() {
        actual = new Fuente();
        listado = Fuente.getAllFuentes();
        model = new FuenteModel(listado);

        listaProveedores = Fuente.getAllProveedores();
        listaFfoliar = Fuente.getAllFoliar();
        listaFertirriego = Fuente.getAllFertirriego();
        listaFedafico = Fuente.getAllFedafico();
    }

    public Fuente getActual() {
        return actual;
    }

    public void setActual(Fuente actual) {
        this.actual = actual;
    }

    public Fuente getSelected() {
        return selected;
    }

    public void setSelected(Fuente selected) {
        this.selected = selected;
    }

    public List<Fuente> getListado() {
        return listado;
    }

    public void setListado(List<Fuente> listado) {
        this.listado = listado;
    }

    public List<Fuente> getFilter() {
        return filter;
    }

    public void setFilter(List<Fuente> filter) {
        this.filter = filter;
    }

    public FuenteModel getModel() {
        return model;
    }

    public void setModel(FuenteModel model) {
        this.model = model;
    }

    public List<String> getListaPresentacion() {
        return listaPresentacion;
    }

    public void setListaPresentacion(List<String> listaPresentacion) {
        this.listaPresentacion = listaPresentacion;
    }

    public List<String> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(List<String> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public List<String> getListaFfoliar() {
        return listaFfoliar;
    }

    public void setListaFfoliar(List<String> listaFfoliar) {
        this.listaFfoliar = listaFfoliar;
    }

    public List<String> getListaFertirriego() {
        return listaFertirriego;
    }

    public void setListaFertirriego(List<String> listaFertirriego) {
        this.listaFertirriego = listaFertirriego;
    }

    public List<String> getListaFedafico() {
        return listaFedafico;
    }

    public void setListaFedafico(List<String> listaFedafico) {
        this.listaFedafico = listaFedafico;
    }

    
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Fuente Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Fuente Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Fuente();
        listado = Fuente.getAllFuentes();
        model = new FuenteModel(listado);

        listaPresentacion = new ArrayList<>();
        listaProveedores = Fuente.getAllProveedores();
        listaFfoliar = Fuente.getAllFoliar();
        listaFertirriego = Fuente.getAllFertirriego();
        listaFedafico = Fuente.getAllFedafico();
    }

    Boolean controlDatos(Fuente u) {
        Boolean res = true;

        if ((u.getSimbolo().equals(""))) {
            res = false;
        }
        return res;
    }

    public void onTipoChange() {
        listaPresentacion = new ArrayList<>();
        if (actual.getTipo().equals("SOLIDO")) {
            listaPresentacion.add("GRANULADO");
            listaPresentacion.add("POLVO");
        } else {
            listaPresentacion.add("LIQUIDO");
        }

    }

    public void onTipoChangeSelected() {
        listaPresentacion = new ArrayList<>();
        if (selected.getTipo().equals("SOLIDO")) {
            listaPresentacion.add("GRANULADO");
            listaPresentacion.add("POLVO");
        } else {
            listaPresentacion.add("LIQUIDO");
        }

    }

    public void loadSelectedTipoPresentacion() {

        listaPresentacion = new ArrayList<>();
        if (selected.getTipo().equals("SOLIDO")) {
            listaPresentacion.add("GRANULADO");
            listaPresentacion.add("POLVO");
        } else {
            listaPresentacion.add("LIQUIDO");
        }
    }

}
