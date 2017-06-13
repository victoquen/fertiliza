/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.CompuestoQuimico;
import entities.fertilizacion.Cultivo;
import entities.fertilizacion.EtapaCultivo;
import entities.fertilizacion.InterpretacionLaboratorio;
import entities.fertilizacion.Metodologia;
import entities.fertilizacion.Subanalisis;
import entities.fertilizacion.Variedad;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.InterpretacionLaboratorioModel;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class InterpretacionLaboratorioController implements Serializable {

    InterpretacionLaboratorio actual;
    InterpretacionLaboratorio selected;
    List<InterpretacionLaboratorio> listado;
    List<InterpretacionLaboratorio> filter;
    InterpretacionLaboratorioModel model;

    List<Cultivo> listaCultivo;
    List<Subanalisis> listaSubanalisis;
    List<String> listaMatriz;
    List<Variedad> listaVariedad;
    List<EtapaCultivo> listaEdad;
    List<Metodologia> listaMetodologia;

    Metodologia actualMetodologia;

    public InterpretacionLaboratorioController() {
        actual = new InterpretacionLaboratorio();
        listado = InterpretacionLaboratorio.getAllInterpretacionLaboratorio();
        model = new InterpretacionLaboratorioModel(listado);

        listaCultivo = Cultivo.getAllCultivos();
        listaSubanalisis = Subanalisis.getAllActivos();
        listaMatriz = Arrays.asList("HOJA", "SUELO", "AGUA DE RIEGO", "SOLUCION SUELO");

        listaMetodologia = Metodologia.getAllMetodologia();
    }

    public List<Metodologia> getListaMetodologia() {
        return listaMetodologia;
    }

    public void setListaMetodologia(List<Metodologia> listaMetodologia) {
        this.listaMetodologia = listaMetodologia;
    }

    public List<Variedad> getListaVariedad() {
        return listaVariedad;
    }

    public void setListaVariedad(List<Variedad> listaVariedad) {
        this.listaVariedad = listaVariedad;
    }

    public List<EtapaCultivo> getListaEdad() {
        return listaEdad;
    }

    public void setListaEdad(List<EtapaCultivo> listaEdad) {
        this.listaEdad = listaEdad;
    }

    public List<Cultivo> getListaCultivo() {
        return listaCultivo;
    }

    public void setListaCultivo(List<Cultivo> listaCultivo) {
        this.listaCultivo = listaCultivo;
    }

    public List<Subanalisis> getListaSubanalisis() {
        return listaSubanalisis;
    }

    public void setListaSubanalisis(List<Subanalisis> listaSubanalisis) {
        this.listaSubanalisis = listaSubanalisis;
    }

    public List<String> getListaMatriz() {
        return listaMatriz;
    }

    public void setListaMatriz(List<String> listaMatriz) {
        this.listaMatriz = listaMatriz;
    }

    public InterpretacionLaboratorio getActual() {
        return actual;
    }

    public void setActual(InterpretacionLaboratorio actual) {
        this.actual = actual;
    }

    public InterpretacionLaboratorio getSelected() {
        return selected;
    }

    public void setSelected(InterpretacionLaboratorio selected) {
        this.selected = selected;
    }

    public List<InterpretacionLaboratorio> getListado() {
        return listado;
    }

    public void setListado(List<InterpretacionLaboratorio> listado) {
        this.listado = listado;
    }

    public List<InterpretacionLaboratorio> getFilter() {
        return filter;
    }

    public void setFilter(List<InterpretacionLaboratorio> filter) {
        this.filter = filter;
    }

    public InterpretacionLaboratorioModel getModel() {
        return model;
    }

    public void setModel(InterpretacionLaboratorioModel model) {
        this.model = model;
    }

    public Metodologia getActualMetodologia() {
        return actualMetodologia;
    }

    public void setActualMetodologia(Metodologia actualMetodologia) {
        this.actualMetodologia = actualMetodologia;
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
        actual = new InterpretacionLaboratorio();
        listado = InterpretacionLaboratorio.getAllInterpretacionLaboratorio();
        model = new InterpretacionLaboratorioModel(listado);
    }

    Boolean controlDatos(InterpretacionLaboratorio u) {
        Boolean res = true;

        if (u.getCultivo().equals("") || u.getCultivo() == null
                || u.getMatriz() == null
                || u.getSubanalisis() == null
                || u.getVariedad() == null
                || u.getEdad() == null
                || u.getMetodologia() == null
                || u.getInicioRangoInterpretacion().equals("") || u.getFinRangoInterpretacion().equals("")) {
            res = false;
        }
        return res;
    }

    public void nuevaMetodologia() {
        actualMetodologia = new Metodologia();
    }

    public void saveMetodologia() {
        if (controlDatos(actualMetodologia)) {
            actual.setMetodologia(actualMetodologia.save());
            listaMetodologia = Metodologia.getAllMetodologia();
        }

    }

    public void saveMetodologiaSelected() {
        if (controlDatos(actualMetodologia)) {
            selected.setMetodologia(actualMetodologia.save());
            listaMetodologia = Metodologia.getAllMetodologia();
        }

    }

    Boolean controlDatos(Metodologia u) {
        Boolean res = true;

        if ((u.getNombre().equals("")) || (u.getLiteratura().equals(""))) {
            res = false;
        }
        return res;
    }

    public void onCultivoChange() {
        if (this.actual.getCultivo() != null) {
            listaVariedad = Variedad.getAllVariedadByCultivo(this.actual.getCultivo());
            listaEdad = EtapaCultivo.getAllByVariedad(this.actual.getVariedad());
        }
    }

    public void onCultivoChangeSelected() {
        if (this.selected.getCultivo() != null) {
            listaVariedad = Variedad.getAllVariedadByCultivo(this.selected.getCultivo());
            listaEdad = EtapaCultivo.getAllByVariedad(this.selected.getVariedad());
        }
    }

    public void onSelectInterpretacion() {
        listaVariedad = Variedad.getAllVariedadByCultivo(this.selected.getCultivo());
        listaEdad = EtapaCultivo.getAllByVariedad(this.selected.getVariedad());
    }
}
