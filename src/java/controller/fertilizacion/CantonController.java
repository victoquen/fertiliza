/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import helpers.Canton;
import helpers.Pais;
import helpers.Provincia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.CantonModel;
import models.PaisModel;
import models.ProvinciaModel;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class CantonController implements Serializable{
    Canton actual;
    Canton selected;
    List<Canton> listado;
    List<Canton> filter;
    CantonModel model;
    
    Provincia actualPr;
    Provincia selectedPr;
    List<Provincia> listadoPr;
    List<Provincia> filterPr;
    ProvinciaModel modelPr;
    
    Pais actualP;
    Pais selectedP;
    List<Pais> listadoP;
    List<Pais> filterP;
    PaisModel modelP;

    public CantonController() {
        actual = new Canton();
        listado = Canton.getAllCanton();
        model = new CantonModel(listado);
        
        actualP = new Pais();
        listadoP = Pais.getAllPais();
        modelP = new PaisModel(listadoP);
        
        actualPr = new Provincia();
        //listadoPr = Provincia.getAllProvincia();
        //modelPr = new ProvinciaModel(listadoPr);
    }

    public Canton getActual() {
        return actual;
    }

    public void setActual(Canton actual) {
        this.actual = actual;
    }

    public Canton getSelected() {
        return selected;
    }

    public void setSelected(Canton selected) {
        this.selected = selected;
    }

    public List<Canton> getListado() {
        return listado;
    }

    public void setListado(List<Canton> listado) {
        this.listado = listado;
    }

    public List<Canton> getFilter() {
        return filter;
    }

    public void setFilter(List<Canton> filter) {
        this.filter = filter;
    }

    public CantonModel getModel() {
        return model;
    }

    public void setModel(CantonModel model) {
        this.model = model;
    }

    public Provincia getActualPr() {
        return actualPr;
    }

    public void setActualPr(Provincia actualPr) {
        this.actualPr = actualPr;
    }

    public Provincia getSelectedPr() {
        return selectedPr;
    }

    public void setSelectedPr(Provincia selectedPr) {
        this.selectedPr = selectedPr;
    }

    public List<Provincia> getListadoPr() {
        return listadoPr;
    }

    public void setListadoPr(List<Provincia> listadoPr) {
        this.listadoPr = listadoPr;
    }

    public List<Provincia> getFilterPr() {
        return filterPr;
    }

    public void setFilterPr(List<Provincia> filterPr) {
        this.filterPr = filterPr;
    }

    public ProvinciaModel getModelPr() {
        return modelPr;
    }

    public void setModelPr(ProvinciaModel modelPr) {
        this.modelPr = modelPr;
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
    
    
    
    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Cantón Ingresado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Cantón Modificado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Canton();
        listado = Canton.getAllCanton();
        model = new CantonModel(listado);
    }

    Boolean controlDatos(Canton u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }
    
    
    public void loadPaisProvincia(){
        
        selectedPr = Provincia.getProvinciaById(selected.getIdProvincia());
        selectedP = Pais.getPaisById(selectedPr.getIdPais());
        listadoPr = Provincia.getAllProvinciaByPais(selectedP.getId());
    }
    
   
    
    public void loadProvincia(){
        selectedPr = Provincia.getProvinciaById(selected.getIdProvincia());
    }
    
    
    
    public void onCountryChange() {
        if(actualP !=null && !actualP.equals(""))
            listadoPr = Provincia.getAllProvinciaByPais(actualP.getId());
       
    }
    
    public void onCountryChangeSelected() {
        if(actualP !=null && !actualP.equals(""))
            listadoPr = Provincia.getAllProvinciaByPais(selectedP.getId());
       
    }
}
