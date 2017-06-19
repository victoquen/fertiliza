/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Cliente;
import entities.fertilizacion.Cultivo;
import entities.fertilizacion.Hacienda;
import entities.fertilizacion.Lote;
import entities.fertilizacion.HistoricoFertilizacion;
import entities.fertilizacion.HistoricoFertilizacionLote;
import entities.fertilizacion.HistoricoFertilizacion;
import entities.fertilizacion.Produccion;
import entities.fertilizacion.ProduccionLote;
import entities.fertilizacion.Variedad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.HistoricoFertilizacionModel;
import models.HistoricoFertilizacionModel;
import org.bson.types.ObjectId;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class HistoricoFertilizacionController implements Serializable {

    HistoricoFertilizacion actual;
    HistoricoFertilizacion selected;
    List<HistoricoFertilizacion> listado;
    List<HistoricoFertilizacion> filter;
    HistoricoFertilizacionModel model;

    List<HistoricoFertilizacionLote> listadoHistoricoFertilizacionLote;

    List<Cliente> listadoCliente;
    List<Hacienda> listadoHacienda;
    List<Produccion> listadoProduccion;

    String leyendaCultivo;

    public HistoricoFertilizacionController() {
        actual = new HistoricoFertilizacion();
        listado = HistoricoFertilizacion.getAll();
        model = new HistoricoFertilizacionModel(listado);
        listadoHistoricoFertilizacionLote = new ArrayList<>();

        listadoCliente = Cliente.getAllClientes();

    }

    public HistoricoFertilizacion getActual() {
        return actual;
    }

    public void setActual(HistoricoFertilizacion actual) {
        this.actual = actual;
    }

    public HistoricoFertilizacion getSelected() {
        return selected;
    }

    public void setSelected(HistoricoFertilizacion selected) {
        this.selected = selected;
    }

    public List<HistoricoFertilizacion> getListado() {
        return listado;
    }

    public void setListado(List<HistoricoFertilizacion> listado) {
        this.listado = listado;
    }

    public List<HistoricoFertilizacion> getFilter() {
        return filter;
    }

    public void setFilter(List<HistoricoFertilizacion> filter) {
        this.filter = filter;
    }

    public HistoricoFertilizacionModel getModel() {
        return model;
    }

    public void setModel(HistoricoFertilizacionModel model) {
        this.model = model;
    }

    public List<HistoricoFertilizacionLote> getListadoHistoricoFertilizacionLote() {
        return listadoHistoricoFertilizacionLote;
    }

    public void setListadoHistoricoFertilizacionLote(List<HistoricoFertilizacionLote> listadoHistoricoFertilizacionLote) {
        this.listadoHistoricoFertilizacionLote = listadoHistoricoFertilizacionLote;
    }

    public List<Cliente> getListadoCliente() {
        return listadoCliente;
    }

    public void setListadoCliente(List<Cliente> listadoCliente) {
        this.listadoCliente = listadoCliente;
    }

    public List<Hacienda> getListadoHacienda() {
        return listadoHacienda;
    }

    public void setListadoHacienda(List<Hacienda> listadoHacienda) {
        this.listadoHacienda = listadoHacienda;
    }

    public List<Produccion> getListadoProduccion() {
        return listadoProduccion;
    }

    public void setListadoProduccion(List<Produccion> listadoProduccion) {
        this.listadoProduccion = listadoProduccion;
    }

    public String getLeyendaCultivo() {
        return leyendaCultivo;
    }

    public void setLeyendaCultivo(String leyendaCultivo) {
        this.leyendaCultivo = leyendaCultivo;
    }

    public void save() {

        if (controlDatos(actual)) {

            actual.setListadoHistoricoFertilizacionLote(listadoHistoricoFertilizacionLote);
            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(actual)) {
            actual.setListadoHistoricoFertilizacionLote(listadoHistoricoFertilizacionLote);
            actual.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new HistoricoFertilizacion();
        listado = HistoricoFertilizacion.getAll();
        model = new HistoricoFertilizacionModel(listado);
        listadoHistoricoFertilizacionLote = new ArrayList<>();

        listadoCliente = Cliente.getAllClientes();
    }

    Boolean controlDatos(HistoricoFertilizacion u) {
        Boolean res = true;

        if ((u.getAnio().equals(""))
                || (u.getIdCliente().equals(null))
                || (u.getIdHacienda().equals(null))
                || (u.getIdProduccion().equals(null))
               ) {
            res = false;
        }
        return res;
    }

    //*********************************************************************************************************************
    //METODOS control datos de lotes cultivo aux***************************************************************************
    public void onClienteChange() {
        if (this.actual.getIdCliente() != null) {
            this.actual.setIdHacienda(null);
            this.actual.setIdProduccion(null);

            listadoHacienda = Hacienda.getAllHaciendaByClienteId(this.actual.getIdCliente());
            listadoProduccion = new ArrayList<>();
            listadoHistoricoFertilizacionLote = new ArrayList<>();

        } else {
            listadoHacienda = new ArrayList<>();
            listadoProduccion = new ArrayList<>();
            listadoHistoricoFertilizacionLote = new ArrayList<>();
        }
        onHaciendaChange();
    }

    public void onHaciendaChange() {
        if (this.actual.getIdHacienda() != null) {

            this.actual.setIdProduccion(null);

            listadoProduccion = Produccion.getAllByClienteHacienda(this.actual.getIdCliente(), this.actual.getIdHacienda());
            listadoHistoricoFertilizacionLote = new ArrayList<>();

        } else {

            listadoProduccion = new ArrayList<>();
            listadoHistoricoFertilizacionLote = new ArrayList<>();
        }

    }

    public void onProduccionChange() {
        if (this.actual.getIdProduccion() != null) {
            Produccion objPr = Produccion.getById(this.actual.getIdProduccion());
            listadoHistoricoFertilizacionLote = new ArrayList<>();
            List<ProduccionLote> auxList = objPr.getListadoProduccionLote();
            int n = auxList.size();
            for (int i = 0; i < n; i++) {
                HistoricoFertilizacionLote obj = new HistoricoFertilizacionLote();
                obj.setIdLote(auxList.get(i).getIdLote());
                obj.setProdLote(auxList.get(i).getProdLote());
                obj.setLeyendaHectareas(auxList.get(i).getLeyendaHectareas());
                obj.setLeyendaLote(auxList.get(i).getLeyendaLote());
                obj.setLeyendaCultivo(objPr.getLeyendaCultivo());
                listadoHistoricoFertilizacionLote.add(obj);
            }

        } else {
            listadoHistoricoFertilizacionLote = new ArrayList<>();
        }

    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            Integer oldV = Integer.parseInt(oldValue.toString());
            Integer newV = Integer.parseInt(newValue.toString());
            Integer res = newV - oldV;

            calculoTotales();
        }
    }

    boolean checkCultivo(ObjectId id, List<Cultivo> lt) {
        boolean res = true;

        int i = 0;
        int n = lt.size();
        while (i < n) {
            if (lt.get(i).getId().equals(id)) {
                res = false;
                i = n;
            }
            i++;
        }

        return res;
    }

    boolean checkVariedad(ObjectId id, List<Variedad> lt) {
        boolean res = true;

        int i = 0;
        int n = lt.size();
        while (i < n) {
            if (lt.get(i).getId().equals(id)) {
                res = false;
                i = n;
            }
            i++;
        }

        return res;
    }

    public void calculoTotales() {

    }

    //*********************************************************************************************************************
    //*********************************************************************************************************************
    public void loadSelected() {
        this.listadoHistoricoFertilizacionLote = this.actual.getListadoHistoricoFertilizacionLote();
        listadoHacienda = Hacienda.getAllHaciendaByClienteId(this.actual.getIdCliente());
        listadoProduccion = Produccion.getAllByClienteHacienda(this.actual.getIdCliente(), this.actual.getIdHacienda());
        
    }
}
