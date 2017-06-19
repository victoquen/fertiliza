/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Cliente;
import entities.fertilizacion.Hacienda;
import entities.fertilizacion.Lote;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.ClienteModel;
import models.HaciendaModel;
import models.LoteModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class LoteController implements Serializable {

    Lote actual;
    Lote selected;
    List<Lote> listado;
    List<Lote> filter;
    LoteModel model;

    Hacienda actualH;
    Hacienda selectedH;
    List<Hacienda> listadoH;
    List<Hacienda> filterH;
    HaciendaModel modelH;

    Cliente actualC;
    Cliente selectedC;
    List<Cliente> listadoC;
    List<Cliente> filterC;
    ClienteModel modelC;
    
    

    public LoteController() {
        this.actual = new Lote();
        this.listado = Lote.getAllLotes();
        this.model = new LoteModel(listado);

        

        actualC = new Cliente();
        listadoC = Cliente.getAllClientes();
        modelC = new ClienteModel(listadoC);
        
        
       
    }

    public LoteController(String op){
        this.actual = new Lote();
        this.listado = Lote.getAllLotes();
        this.model = new LoteModel(listado);
    }

    public Lote getActual() {
        return actual;
    }

    public void setActual(Lote actual) {
        this.actual = actual;
    }

    public Lote getSelected() {
        return selected;
    }

    public void setSelected(Lote selected) {
        this.selected = selected;
    }

    public List<Lote> getListado() {
        return listado;
    }

    public void setListado(List<Lote> listado) {
        this.listado = listado;
    }

    public List<Lote> getFilter() {
        return filter;
    }

    public void setFilter(List<Lote> filter) {
        this.filter = filter;
    }

    public LoteModel getModel() {
        return model;
    }

    public void setModel(LoteModel model) {
        this.model = model;
    }

    public Hacienda getActualH() {
        return actualH;
    }

    public void setActualH(Hacienda actualH) {
        this.actualH = actualH;
    }

    public Hacienda getSelectedH() {
        return selectedH;
    }

    public void setSelectedH(Hacienda selectedH) {
        this.selectedH = selectedH;
    }

    public List<Hacienda> getListadoH() {
        return listadoH;
    }

    public void setListadoH(List<Hacienda> listadoH) {
        this.listadoH = listadoH;
    }

    public List<Hacienda> getFilterH() {
        return filterH;
    }

    public void setFilterH(List<Hacienda> filterH) {
        this.filterH = filterH;
    }

    public HaciendaModel getModelH() {
        return modelH;
    }

    public void setModelH(HaciendaModel modelH) {
        this.modelH = modelH;
    }

    public Cliente getActualC() {
        return actualC;
    }

    public void setActualC(Cliente actualC) {
        this.actualC = actualC;
    }

    public Cliente getSelectedC() {
        return selectedC;
    }

    public void setSelectedC(Cliente selectedC) {
        this.selectedC = selectedC;
    }

    public List<Cliente> getListadoC() {
        return listadoC;
    }

    public void setListadoC(List<Cliente> listadoC) {
        this.listadoC = listadoC;
    }

    public List<Cliente> getFilterC() {
        return filterC;
    }

    public void setFilterC(List<Cliente> filterC) {
        this.filterC = filterC;
    }

    public ClienteModel getModelC() {
        return modelC;
    }

    public void setModelC(ClienteModel modelC) {
        this.modelC = modelC;
    }

    public void save() {

        if (controlDatos(actual)) {

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Lote Ingresado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Lote Modificado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Lote();
        listado = Lote.getAllLotes();
        model = new LoteModel(listado);
    }

    Boolean controlDatos(Lote u) {
        Boolean res = true;

        if ((u.getCodigo().equals("")) || (u.getHectareas().equals(""))) {
            res = false;
        }
        return res;
    }
    
    public void loadUpdate(){
        selectedC = Cliente.getClienteById(Hacienda.getHaciendaById(this.selected.getIdHacienda()).getIdCliente());
        listadoH = Hacienda.getAllHaciendaByClienteId(this.selectedC.getId());
    }
    
    public void onChangeCliente(){
        listadoH = Hacienda.getAllHaciendaByClienteId(this.actualC.getId());
        this.actual.setIdHacienda(null);
    }
    
    public void onChangeClienteSelected(){
        listadoH = Hacienda.getAllHaciendaByClienteId(this.selectedC.getId());
        this.selected.setIdHacienda(null);
    }
}
