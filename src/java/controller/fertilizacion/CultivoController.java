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
import models.CultivoModel;
import models.EdadModel;
import models.VariedadModel;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class CultivoController implements Serializable {

    Cultivo actual;
    Cultivo selected;
    List<Cultivo> listado;
    List<Cultivo> filter;
    CultivoModel model;
    
    
    
    

    public CultivoController() {
        this.actual = new Cultivo();
        this.listado = Cultivo.getAllCultivos();
        this.model = new CultivoModel(listado);
        
        
    }

    public Cultivo getActual() {
        return actual;
    }

    public void setActual(Cultivo actual) {
        this.actual = actual;
    }

    public Cultivo getSelected() {
        return selected;
    }

    public void setSelected(Cultivo selected) {
        this.selected = selected;
    }

    public List<Cultivo> getListado() {
        return listado;
    }

    public void setListado(List<Cultivo> listado) {
        this.listado = listado;
    }

    public List<Cultivo> getFilter() {
        return filter;
    }

    public void setFilter(List<Cultivo> filter) {
        this.filter = filter;
    }

    public CultivoModel getModel() {
        return model;
    }

    public void setModel(CultivoModel model) {
        this.model = model;
    }
          
    
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Cultivo Ingresado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Cultivo Modificado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Cultivo();
        listado = Cultivo.getAllCultivos();
        model = new CultivoModel(listado);
    }

    Boolean controlDatos(Cultivo u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }

}
