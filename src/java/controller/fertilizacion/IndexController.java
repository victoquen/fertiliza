/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.MuestraLaboratorio;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.MuestraLaboratorioModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

    Date fechaInicio;
    Date fechaFin;

    MuestraLaboratorio actualAgricola;
    MuestraLaboratorio selectedAgricola;
    List<MuestraLaboratorio> listadoAgricola;
    List<MuestraLaboratorio> filterAgricola;
    MuestraLaboratorioModel modelAgricola;
    
    MuestraLaboratorio actualLaboratorio;
    MuestraLaboratorio selectedLaboratorio;
    List<MuestraLaboratorio> listadoLaboratorio;
    List<MuestraLaboratorio> filterLaboratorio;
    MuestraLaboratorioModel modelLaboratorio;

    public IndexController() {
        fechaInicio = new Date();
        fechaFin = new Date();
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public MuestraLaboratorio getActualAgricola() {
        return actualAgricola;
    }

    public void setActualAgricola(MuestraLaboratorio actualAgricola) {
        this.actualAgricola = actualAgricola;
    }

    public MuestraLaboratorio getSelectedAgricola() {
        return selectedAgricola;
    }

    public void setSelectedAgricola(MuestraLaboratorio selectedAgricola) {
        this.selectedAgricola = selectedAgricola;
    }

    public List<MuestraLaboratorio> getListadoAgricola() {
        return listadoAgricola;
    }

    public void setListadoAgricola(List<MuestraLaboratorio> listadoAgricola) {
        this.listadoAgricola = listadoAgricola;
    }

    public List<MuestraLaboratorio> getFilterAgricola() {
        return filterAgricola;
    }

    public void setFilterAgricola(List<MuestraLaboratorio> filterAgricola) {
        this.filterAgricola = filterAgricola;
    }

    public MuestraLaboratorioModel getModelAgricola() {
        return modelAgricola;
    }

    public void setModelAgricola(MuestraLaboratorioModel modelAgricola) {
        this.modelAgricola = modelAgricola;
    }

    public MuestraLaboratorio getActualLaboratorio() {
        return actualLaboratorio;
    }

    public void setActualLaboratorio(MuestraLaboratorio actualLaboratorio) {
        this.actualLaboratorio = actualLaboratorio;
    }

    public MuestraLaboratorio getSelectedLaboratorio() {
        return selectedLaboratorio;
    }

    public void setSelectedLaboratorio(MuestraLaboratorio selectedLaboratorio) {
        this.selectedLaboratorio = selectedLaboratorio;
    }

    public List<MuestraLaboratorio> getListadoLaboratorio() {
        return listadoLaboratorio;
    }

    public void setListadoLaboratorio(List<MuestraLaboratorio> listadoLaboratorio) {
        this.listadoLaboratorio = listadoLaboratorio;
    }

    public List<MuestraLaboratorio> getFilterLaboratorio() {
        return filterLaboratorio;
    }

    public void setFilterLaboratorio(List<MuestraLaboratorio> filterLaboratorio) {
        this.filterLaboratorio = filterLaboratorio;
    }

    public MuestraLaboratorioModel getModelLaboratorio() {
        return modelLaboratorio;
    }

    public void setModelLaboratorio(MuestraLaboratorioModel modelLaboratorio) {
        this.modelLaboratorio = modelLaboratorio;
    }

    

    public void buscarByFechas(){
        listadoLaboratorio = MuestraLaboratorio.getAllMuestraLaboratorioBetweenDateFechaCreacion(fechaInicio, fechaFin);
        modelLaboratorio = new MuestraLaboratorioModel(listadoLaboratorio);
        
        listadoAgricola = MuestraLaboratorio.getAllMuestraLaboratorioPlantechSortByEstacionMonitoreoByFechas(fechaInicio, fechaFin);
        modelAgricola = new MuestraLaboratorioModel(listadoAgricola);
    }
    
}
