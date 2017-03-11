/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import helpers.Pais;
import helpers.Provincia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.PaisModel;
import models.ProvinciaModel;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class ProvinciaController implements Serializable{
    Provincia actual;
    Provincia selected;
    List<Provincia> listado;
    List<Provincia> filter;
    ProvinciaModel model;
    
    Pais actualP;
    Pais selectedP;
    List<Pais> listadoP;
    List<Pais> filterP;
    PaisModel modelP;
    
    
    

    public ProvinciaController() {        
        actual = new Provincia();
        listado = Provincia.getAllProvincia();
        model = new ProvinciaModel(listado);
        
        actualP = new Pais();
        listadoP = Pais.getAllPais();
        modelP = new PaisModel(listadoP);
    }

   
    
    public Pais getActualP() {
        return actualP;
    }

    public void setActualP(Pais actualP) {
        this.actualP = actualP;
    }

    public Pais getSelectedP() {
        return selectedP;
    }

    public void setSelectedP(Pais selectedP) {
        this.selectedP = selectedP;
    }

    public List<Pais> getListadoP() {
        return listadoP;
    }

    public void setListadoP(List<Pais> listadoP) {
        this.listadoP = listadoP;
    }

    public List<Pais> getFilterP() {
        return filterP;
    }

    public void setFilterP(List<Pais> filterP) {
        this.filterP = filterP;
    }

    public PaisModel getModelP() {
        return modelP;
    }

    public void setModelP(PaisModel modelP) {
        this.modelP = modelP;
    }    
    
    public Provincia getActual() {
        return actual;
    }

    public void setActual(Provincia actual) {
        this.actual = actual;
    }

    public Provincia getSelected() {
        return selected;
    }

    public void setSelected(Provincia selected) {
        this.selected = selected;
    }

    public List<Provincia> getListado() {
        return listado;
    }

    public void setListado(List<Provincia> listado) {
        this.listado = listado;
    }

    public List<Provincia> getFilter() {
        return filter;
    }

    public void setFilter(List<Provincia> filter) {
        this.filter = filter;
    }

    public ProvinciaModel getModel() {
        return model;
    }

    public void setModel(ProvinciaModel model) {
        this.model = model;
    }
    
    public void save() {

        if (controlDatos(actual)) {
            actual.setIdPais(actualP.getId());
            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Provincia Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            
            selected.setIdPais(selectedP.getId());
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Provincia Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Provincia();
        listado = Provincia.getAllProvincia();
        model = new ProvinciaModel(listado);
    }

    Boolean controlDatos(Provincia u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }
    
    
    
    public void loadPais(){
        selectedP = Pais.getPaisById(selected.getIdPais());
    }
    
    public List<Pais> completeObj(String query) {
        List<Pais> all = Pais.getAllPais();
        List<Pais> filtered = new ArrayList<Pais>();
         
        for (int i = 0; i < all.size(); i++) {
            Pais obj = all.get(i);
            if(obj.getNombre().toLowerCase().startsWith(query)) {
                filtered.add(obj);
            }
        }
         
        return filtered;
    }
    
}
