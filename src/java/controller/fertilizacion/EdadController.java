/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Cultivo;
import entities.fertilizacion.Edad;
import entities.fertilizacion.Variedad;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.EdadModel;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class EdadController implements Serializable {

    Edad actual;
    Edad selected;
    List<Edad> listado;
    List<Edad> filter;
    EdadModel model;

    ObjectId idCultivo;

    List<Cultivo> listadoCultivo;
    List<Variedad> listadoVariedad;

    public EdadController() {
        actual = new Edad();
        listado = Edad.getAllEdad();
        model = new EdadModel(listado);

        listadoCultivo = Cultivo.getAllCultivos();

    }

    public ObjectId getIdCultivo() {
        return idCultivo;
    }

    public void setIdCultivo(ObjectId idCultivo) {
        this.idCultivo = idCultivo;
    }

    public List<Cultivo> getListadoCultivo() {
        return listadoCultivo;
    }

    public void setListadoCultivo(List<Cultivo> listadoCultivo) {
        this.listadoCultivo = listadoCultivo;
    }

    public Edad getActual() {
        return actual;
    }

    public void setActual(Edad actual) {
        this.actual = actual;
    }

    public Edad getSelected() {
        return selected;
    }

    public void setSelected(Edad selected) {
        this.selected = selected;
    }

    public List<Edad> getListado() {
        return listado;
    }

    public void setListado(List<Edad> listado) {
        this.listado = listado;
    }

    public List<Edad> getFilter() {
        return filter;
    }

    public void setFilter(List<Edad> filter) {
        this.filter = filter;
    }

    public EdadModel getModel() {
        return model;
    }

    public void setModel(EdadModel model) {
        this.model = model;
    }

    public List<Variedad> getListadoVariedad() {
        return listadoVariedad;
    }

    public void setListadoVariedad(List<Variedad> listadoVariedad) {
        this.listadoVariedad = listadoVariedad;
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
        actual = new Edad();
        listado = Edad.getAllEdad();
        model = new EdadModel(listado);
        
        listadoCultivo = Cultivo.getAllCultivos();
    }

    public void loadListSelected() {
        
        this.idCultivo = Variedad.getVariedadById(selected.getVariedad()).getCultivo();
        listadoVariedad = Variedad.getAllVariedadByCultivo(this.idCultivo);
    }

    Boolean controlDatos(Edad u) {
        Boolean res = true;

        if ((u.getNombre().equals("")) || (u.getVariedad() == null)) {
            res = false;
        }
        return res;
    }

    public void onChangeCultivo() {
        listadoVariedad = Variedad.getAllVariedadByCultivo(this.idCultivo);
    }

}
