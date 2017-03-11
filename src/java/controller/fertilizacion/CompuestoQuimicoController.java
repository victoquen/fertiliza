/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.CompuestoQuimico;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.CompuestoQuimicoModel;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class CompuestoQuimicoController implements Serializable{
    
    CompuestoQuimico actual;
    CompuestoQuimico selected;
    List<CompuestoQuimico> listado;
    List<CompuestoQuimico> filter;
    CompuestoQuimicoModel model;

    public CompuestoQuimicoController() {
        
        actual = new CompuestoQuimico();
        listado = CompuestoQuimico.getAllCompuestoQuimicos();
        model = new CompuestoQuimicoModel(listado);
    }

    public CompuestoQuimico getActual() {
        return actual;
    }

    public void setActual(CompuestoQuimico actual) {
        this.actual = actual;
    }

    public CompuestoQuimico getSelected() {
        return selected;
    }

    public void setSelected(CompuestoQuimico selected) {
        this.selected = selected;
    }

    public List<CompuestoQuimico> getListado() {
        return listado;
    }

    public void setListado(List<CompuestoQuimico> listado) {
        this.listado = listado;
    }

    public List<CompuestoQuimico> getFilter() {
        return filter;
    }

    public void setFilter(List<CompuestoQuimico> filter) {
        this.filter = filter;
    }

    public CompuestoQuimicoModel getModel() {
        return model;
    }

    public void setModel(CompuestoQuimicoModel model) {
        this.model = model;
    }
    
    public void save(){
        
        if(controlDatos(actual)){
            
            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "CompuestoQuimico Ingresado"));
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }        
        
    }
    
    public void update(){
        if(controlDatos(selected)){            
            selected.update();
             load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "CompuestoQuimico Modificado"));
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }
    
    public void load(){
        actual = new CompuestoQuimico();
        listado = CompuestoQuimico.getAllCompuestoQuimicos();
        model = new CompuestoQuimicoModel(listado);
    }
    
    Boolean controlDatos(CompuestoQuimico u){
        Boolean res = true;
        
        if((u.getSimbolo().equals("")))
        {
            res = false;
        }
        return res;
    }
    
}
