/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import models.AnalisisLaboratorioModel;
import entities.fertilizacion.AnalisisLaboratorio;
import entities.fertilizacion.Laboratorio;
import entities.fertilizacion.Matriz;
import entities.fertilizacion.Subanalisis;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author VICTOR OQUENDO
 */
//PAQUETE ANALISIS
@Named
@ViewScoped
public class AnalisisLaboratorioController implements Serializable {

    AnalisisLaboratorio actual;
    AnalisisLaboratorio selected;
    List<AnalisisLaboratorio> filter;
    List<AnalisisLaboratorio> listado;
    AnalisisLaboratorioModel model;

    List<Matriz> listadoMatriz;
    List<Laboratorio> listadoLaboratorio;

    private DualListModel<Subanalisis> listadoElementos;
    Subanalisis actualElemento;

    Integer tat;
    BigDecimal costo;
    String descripcion;

    @PostConstruct
    public void init() {
        List<Subanalisis> elementosSource = new ArrayList<Subanalisis>();
        List<Subanalisis> elementosTarget = new ArrayList<Subanalisis>();

        listadoElementos = new DualListModel<Subanalisis>(elementosSource, elementosTarget);
    }

    public AnalisisLaboratorioController() {

        actual = new AnalisisLaboratorio();
        listado = AnalisisLaboratorio.getAllAnalisisActivo();
        model = new AnalisisLaboratorioModel(listado);

        this.listadoMatriz = Matriz.getAll();
        this.listadoLaboratorio = Laboratorio.getAllLaboratorio();

        actual.setCodigo(numeroSecuencialCodigo());
        this.tat = 0;
        this.costo = new BigDecimal(0);
        this.descripcion = "";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Integer getTat() {
        return tat;
    }

    public void setTat(Integer tat) {
        this.tat = tat;
    }

    public List<Matriz> getListadoMatriz() {
        return listadoMatriz;
    }

    public void setListadoMatriz(List<Matriz> listadoMatriz) {
        this.listadoMatriz = listadoMatriz;
    }

    public List<Laboratorio> getListadoLaboratorio() {
        return listadoLaboratorio;
    }

    public void setListadoLaboratorio(List<Laboratorio> listadoLaboratorio) {
        this.listadoLaboratorio = listadoLaboratorio;
    }

    public Subanalisis getActualElemento() {
        return actualElemento;
    }

    public void setActualElemento(Subanalisis actualElemento) {
        this.actualElemento = actualElemento;
    }

    public DualListModel<Subanalisis> getListadoElementos() {
        return listadoElementos;
    }

    public void setListadoElementos(DualListModel<Subanalisis> listadoElementos) {
        this.listadoElementos = listadoElementos;
    }

    public AnalisisLaboratorio getActual() {
        return actual;
    }

    public void setActual(AnalisisLaboratorio actual) {
        this.actual = actual;
    }

    public AnalisisLaboratorio getSelected() {
        return selected;
    }

    public void setSelected(AnalisisLaboratorio selected) {
        this.selected = selected;
    }

    public List<AnalisisLaboratorio> getFilter() {
        return filter;
    }

    public void setFilter(List<AnalisisLaboratorio> filter) {
        this.filter = filter;
    }

    public List<AnalisisLaboratorio> getListado() {
        return listado;
    }

    public void setListado(List<AnalisisLaboratorio> listado) {
        this.listado = listado;
    }

    public AnalisisLaboratorioModel getModel() {
        return model;
    }

    public void setModel(AnalisisLaboratorioModel model) {
        this.model = model;
    }

    public void save() {

        if (controlDatos(actual)) {

            actual.setListadoElementos(listadoElementos.getTarget());
            actual.setCodigo(numeroSecuencialCodigo());
            actual.setCosto(costo);
            actual.setDescripcion(descripcion);
            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.setListadoElementos(listadoElementos.getTarget());
            selected.setCosto(costo);
            selected.setDescripcion(descripcion);
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new AnalisisLaboratorio();
        listado = AnalisisLaboratorio.getAllAnalisisActivo();
        model = new AnalisisLaboratorioModel(listado);

        List<Subanalisis> elementosSource = new ArrayList<Subanalisis>();
        List<Subanalisis> elementosTarget = new ArrayList<Subanalisis>();
        listadoElementos = new DualListModel<Subanalisis>(elementosSource, elementosTarget);

        listadoMatriz = Matriz.getAll();
        listadoLaboratorio = Laboratorio.getAllLaboratorio();

        this.tat = 0;
        this.costo = new BigDecimal(0);
        this.descripcion = "";
        actual.setCodigo(numeroSecuencialCodigo());
    }

    Boolean controlDatos(AnalisisLaboratorio u) {
        Boolean res = true;

        if (this.descripcion.equals("") || listadoElementos.getTarget().isEmpty() || u.getMatriz().equals("") || u.getMatriz() == null || u.getLaboratorio().equals("") || u.getLaboratorio() == null) {
            res = false;
        }
        return res;
    }

    public void cargaListaElementosSelected() {
        int auxMayor = 0;
        List<Subanalisis> elementosSource = Subanalisis.getAllActivosByLaboratorioAndMatriz(selected.getLaboratorio(), selected.getMatriz());
        List<Subanalisis> elementosTarget = new ArrayList<Subanalisis>();
        for (int i = 0; i < selected.getListadoElementos().size(); i++) {

            elementosTarget.add(selected.getListadoElementos().get(i));

            if (selected.getListadoElementos().get(i).getTat() > auxMayor) {
                auxMayor = selected.getListadoElementos().get(i).getTat();
            }
            int k = findElemento(elementosSource, selected.getListadoElementos().get(i));
            if (k > -1) {
                elementosSource.remove(k);
            }

            //elementosSource.remove(selected.getListadoElementos().get(i));
        }
        tat = auxMayor;
        costo = selected.getCosto();
        descripcion = selected.getDescripcion();
        listadoElementos = new DualListModel<Subanalisis>(elementosSource, elementosTarget);
    }

    int findElemento(List<Subanalisis> lista, Subanalisis cm) {
        int pos = -1;
        int cont = 0;
        while (cont < lista.size()) {
            if (lista.get(cont).getId().equals(cm.getId())) {
                pos = cont;
                cont = lista.size();
            }
            cont++;
        }
        return pos;

    }

    //metodos elemento compuesto quimico****************************************
    public void nuevoElemento() {
        actualElemento = new Subanalisis();
    }

    public void saveElemento() {
        if (controlDatosElemento(actualElemento)) {
            actualElemento.save();

            List<Subanalisis> elementosSource = Subanalisis.getAllActivos();
            List<Subanalisis> elementosTarget = listadoElementos.getTarget();
            elementosTarget.add(actualElemento);

            listadoElementos = new DualListModel<Subanalisis>(elementosSource, elementosTarget);
        }

    }

    public void saveElementoSelected() {
        if (controlDatosElemento(actualElemento)) {
            actualElemento.save();
            listadoElementos.getTarget().add(actualElemento);

            List<Subanalisis> elementosSource = Subanalisis.getAllActivos();
            List<Subanalisis> elementosTarget = listadoElementos.getTarget();
            elementosTarget.add(actualElemento);

            listadoElementos = new DualListModel<Subanalisis>(elementosSource, elementosTarget);
        }
    }

    Boolean controlDatosElemento(Subanalisis u) {
        Boolean res = true;

        if ((u.getSimbolo().equals(""))) {
            res = false;
        }
        return res;
    }

    //**********************************************************************************************
    public void onLaboratorioChange(){
        List<Subanalisis> elementosSource = Subanalisis.getAllActivosByLaboratorioAndMatriz(this.actual.getLaboratorio(),this.actual.getMatriz());
        List<Subanalisis> elementosTarget = new ArrayList<Subanalisis>();

        listadoElementos = new DualListModel<Subanalisis>(elementosSource, elementosTarget);
    }
    
    public void onLaboratorioChangeSelected(){
        List<Subanalisis> elementosSource = Subanalisis.getAllActivosByLaboratorioAndMatriz(this.selected.getLaboratorio(), this.selected.getMatriz());
        List<Subanalisis> elementosTarget = new ArrayList<Subanalisis>();

        listadoElementos = new DualListModel<Subanalisis>(elementosSource, elementosTarget);
    }
    
    
    
    int numeroSecuencialCodigo() {

        int res = AnalisisLaboratorio.getMaxNumeroSecuencialCodigo() + 1;
        return res;
    }

    public void onTransfer(TransferEvent event) {
        int ing = 0;
        BigDecimal tot = new BigDecimal(0);
        String desc = "";
        for (Object item : event.getItems()) {
            int cont = listadoElementos.getTarget().size();
            for (int i = 0; i < cont; i++) {
                tot = tot.add(listadoElementos.getTarget().get(i).getCosto());
                String simb = listadoElementos.getTarget().get(i).getSimbolo();
                String nomb = listadoElementos.getTarget().get(i).getNombre();
                if (i == 0) {
                    if (!simb.equals("")) {
                        desc = simb;
                    } else {
                        desc = nomb;
                    }

                } else if (!simb.equals("")) {
                    desc = desc + ", " + simb;
                } else {
                    desc = desc +", " + nomb;
                }
                if ((listadoElementos.getTarget().get(i).getTat() > ing)) {
                    ing = listadoElementos.getTarget().get(i).getTat();
                }
            }
            this.tat = ing;
            this.costo = tot;
            this.descripcion = desc;
        }
    }

}
