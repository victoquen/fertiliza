/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.AnalisisLaboratorio;
import entities.fertilizacion.Subanalisis;
import entities.fertilizacion.Cultivo;
import entities.fertilizacion.Laboratorio;
import entities.fertilizacion.Matriz;
import entities.fertilizacion.MuestraLaboratorio;
import entities.fertilizacion.ResultadoLaboratorio;
import entities.fertilizacion.ResultadoLaboratorioAnalisisCompuesto;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ResultadoLaboratorioModel;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class ResultadoLaboratorioController implements Serializable {

    ResultadoLaboratorio actual;
    ResultadoLaboratorio selected;
    List<ResultadoLaboratorio> listado;
    List<ResultadoLaboratorio> filter;
    ResultadoLaboratorioModel model;
    
    List<ResultadoLaboratorio> listadoCompleto;
    List<ResultadoLaboratorio> filterCompleto;
    ResultadoLaboratorioModel modelCompleto;
    
    List<ResultadoLaboratorio> listadoPendiente;
    List<ResultadoLaboratorio> filterPendiente;
    ResultadoLaboratorioModel modelPendiente;

    List<Cultivo> listaCultivo;
    List<Subanalisis> listaSubanalisis;
    List<String> listaMatriz;
    List<Laboratorio> listaLaboratorio;
    List<MuestraLaboratorio> listaMuestra;
    List<MuestraLaboratorio> listaMuestraSelected;

    ResultadoLaboratorioAnalisisCompuesto actualAnalisisCompuesto;
    ResultadoLaboratorioAnalisisCompuesto selectedAnalisisCompuesto;
    List<ResultadoLaboratorioAnalisisCompuesto> listaAnalisisCompuesto;

    ResultadoLaboratorioAnalisisCompuesto actualAnalisisPaquete;
    ResultadoLaboratorioAnalisisCompuesto selectedAnalisisPaquete;
    List<ResultadoLaboratorioAnalisisCompuesto> listaAnalisisPaquete;

    Date fechaActual;
    String matriz;

    public ResultadoLaboratorioController() {
        actual = new ResultadoLaboratorio();
        listado = ResultadoLaboratorio.getAllResultadoLaboratorio();
        model = new ResultadoLaboratorioModel(listado);
        
        listadoCompleto = ResultadoLaboratorio.getAllResultadoLaboratorioCompleto();
        modelCompleto = new ResultadoLaboratorioModel(listadoCompleto);
        
        listadoPendiente = ResultadoLaboratorio.getAllResultadoLaboratorioPendiente();
        modelPendiente = new ResultadoLaboratorioModel(listadoPendiente);

        listaCultivo = Cultivo.getAllCultivos();

        listaMatriz = Arrays.asList("HOJA", "SUELO", "AGUA DE RIEGO", "SOLUCION SUELO");
        listaLaboratorio = Laboratorio.getAllLaboratorio();
        cargaMuestrasLaboratorio();

        actualAnalisisCompuesto = new ResultadoLaboratorioAnalisisCompuesto();
        listaAnalisisCompuesto = new ArrayList<>();

        actualAnalisisPaquete = new ResultadoLaboratorioAnalisisCompuesto();
        listaAnalisisPaquete = new ArrayList<>();

        fechaActual = new Date();
        
    }

    

    void cargaMuestrasLaboratorio() {
        List<MuestraLaboratorio> auxListaMuestras = MuestraLaboratorio.getAllMuestraLaboratorio();

        for (ResultadoLaboratorio res : this.listado) {
            Iterator<MuestraLaboratorio> mues = auxListaMuestras.iterator();
            while (mues.hasNext()) {
                MuestraLaboratorio m = mues.next();
                if (res.getMuestra().equals(m.getId())) {
                    mues.remove();
                }
            }
        }
        this.listaMuestra = auxListaMuestras;
    }

    public List<ResultadoLaboratorio> getListadoCompleto() {
        return listadoCompleto;
    }

    public void setListadoCompleto(List<ResultadoLaboratorio> listadoCompleto) {
        this.listadoCompleto = listadoCompleto;
    }

    public List<ResultadoLaboratorio> getFilterCompleto() {
        return filterCompleto;
    }

    public void setFilterCompleto(List<ResultadoLaboratorio> filterCompleto) {
        this.filterCompleto = filterCompleto;
    }

    public ResultadoLaboratorioModel getModelCompleto() {
        return modelCompleto;
    }

    public void setModelCompleto(ResultadoLaboratorioModel modelCompleto) {
        this.modelCompleto = modelCompleto;
    }

    public List<ResultadoLaboratorio> getListadoPendiente() {
        return listadoPendiente;
    }

    public void setListadoPendiente(List<ResultadoLaboratorio> listadoPendiente) {
        this.listadoPendiente = listadoPendiente;
    }

    public List<ResultadoLaboratorio> getFilterPendiente() {
        return filterPendiente;
    }

    public void setFilterPendiente(List<ResultadoLaboratorio> filterPendiente) {
        this.filterPendiente = filterPendiente;
    }

    public ResultadoLaboratorioModel getModelPendiente() {
        return modelPendiente;
    }

    public void setModelPendiente(ResultadoLaboratorioModel modelPendiente) {
        this.modelPendiente = modelPendiente;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getMatriz() {
        return matriz;
    }

    public void setMatriz(String matriz) {
        this.matriz = matriz;
    }

    
    public List<MuestraLaboratorio> getListaMuestraSelected() {
        return listaMuestraSelected;
    }

    public void setListaMuestraSelected(List<MuestraLaboratorio> listaMuestraSelected) {
        this.listaMuestraSelected = listaMuestraSelected;
    }

    public ResultadoLaboratorioAnalisisCompuesto getActualAnalisisPaquete() {
        return actualAnalisisPaquete;
    }

    public void setActualAnalisisPaquete(ResultadoLaboratorioAnalisisCompuesto actualAnalisisPaquete) {
        this.actualAnalisisPaquete = actualAnalisisPaquete;
    }

    public ResultadoLaboratorioAnalisisCompuesto getSelectedAnalisisPaquete() {
        return selectedAnalisisPaquete;
    }

    public void setSelectedAnalisisPaquete(ResultadoLaboratorioAnalisisCompuesto selectedAnalisisPaquete) {
        this.selectedAnalisisPaquete = selectedAnalisisPaquete;
    }

    public List<ResultadoLaboratorioAnalisisCompuesto> getListaAnalisisPaquete() {
        return listaAnalisisPaquete;
    }

    public void setListaAnalisisPaquete(List<ResultadoLaboratorioAnalisisCompuesto> listaAnalisisPaquete) {
        this.listaAnalisisPaquete = listaAnalisisPaquete;
    }

    public List<MuestraLaboratorio> getListaMuestra() {
        return listaMuestra;
    }

    public void setListaMuestra(List<MuestraLaboratorio> listaMuestra) {
        this.listaMuestra = listaMuestra;
    }

    public List<Laboratorio> getListaLaboratorio() {
        return listaLaboratorio;
    }

    public void setListaLaboratorio(List<Laboratorio> listaLaboratorio) {
        this.listaLaboratorio = listaLaboratorio;
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

    public ResultadoLaboratorio getActual() {
        return actual;
    }

    public void setActual(ResultadoLaboratorio actual) {
        this.actual = actual;
    }

    public ResultadoLaboratorio getSelected() {
        return selected;
    }

    public void setSelected(ResultadoLaboratorio selected) {
        this.selected = selected;
    }

    public List<ResultadoLaboratorio> getListado() {
        return listado;
    }

    public void setListado(List<ResultadoLaboratorio> listado) {
        this.listado = listado;
    }

    public List<ResultadoLaboratorio> getFilter() {
        return filter;
    }

    public void setFilter(List<ResultadoLaboratorio> filter) {
        this.filter = filter;
    }

    public ResultadoLaboratorioModel getModel() {
        return model;
    }

    public void setModel(ResultadoLaboratorioModel model) {
        this.model = model;
    }

    public ResultadoLaboratorioAnalisisCompuesto getActualAnalisisCompuesto() {
        return actualAnalisisCompuesto;
    }

    public void setActualAnalisisCompuesto(ResultadoLaboratorioAnalisisCompuesto actualAnalisisCompuesto) {
        this.actualAnalisisCompuesto = actualAnalisisCompuesto;
    }

    public ResultadoLaboratorioAnalisisCompuesto getSelectedAnalisisCompuesto() {
        return selectedAnalisisCompuesto;
    }

    public void setSelectedAnalisisCompuesto(ResultadoLaboratorioAnalisisCompuesto selectedAnalisisCompuesto) {
        this.selectedAnalisisCompuesto = selectedAnalisisCompuesto;
    }

    public List<ResultadoLaboratorioAnalisisCompuesto> getListaAnalisisCompuesto() {
        return listaAnalisisCompuesto;
    }

    public void setListaAnalisisCompuesto(List<ResultadoLaboratorioAnalisisCompuesto> listaAnalisisCompuesto) {
        this.listaAnalisisCompuesto = listaAnalisisCompuesto;
    }

    public void save() {

        if (controlDatos(actual)) {

            actual.setListaResultado(listaAnalisisCompuesto);
            actual.setListaResultadoPaquetes(listaAnalisisPaquete);
            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
            selected.setListaResultado(listaAnalisisCompuesto);
            selected.setListaResultadoPaquetes(listaAnalisisPaquete);
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new ResultadoLaboratorio();
        listado = ResultadoLaboratorio.getAllResultadoLaboratorio();
        model = new ResultadoLaboratorioModel(listado);
        
        listadoCompleto = ResultadoLaboratorio.getAllResultadoLaboratorioCompleto();
        modelCompleto = new ResultadoLaboratorioModel(listadoCompleto);
        
        listadoPendiente = ResultadoLaboratorio.getAllResultadoLaboratorioPendiente();
        modelPendiente = new ResultadoLaboratorioModel(listadoPendiente);

        listaAnalisisCompuesto = new ArrayList<>();
        listaAnalisisPaquete = new ArrayList<>();
        cargaMuestrasLaboratorio();
    }

    Boolean controlDatos(ResultadoLaboratorio u) {
        Boolean res = true;

        if ((u.getCultivo() == null)) {
            res = false;
        }
        return res;
    }

    public void fillSelected() {
        selectedAnalisisCompuesto = new ResultadoLaboratorioAnalisisCompuesto();
        actualAnalisisCompuesto = new ResultadoLaboratorioAnalisisCompuesto();
        listaAnalisisCompuesto = selected.getListaResultado();

        listaAnalisisPaquete = selected.getListaResultadoPaquetes();
        this.matriz = selected.getLeyendaMatriz();
        
        
        cargaMuestrasLaboratorioSelected();
    }

    void cargaMuestrasLaboratorioSelected() {
        List<MuestraLaboratorio> auxListaMuestras = MuestraLaboratorio.getAllMuestraLaboratorio();

        for (ResultadoLaboratorio res : this.listado) {
            Iterator<MuestraLaboratorio> mues = auxListaMuestras.iterator();
            while (mues.hasNext()) {
                MuestraLaboratorio m = mues.next();
                if (res.getMuestra().equals(m.getId())) {
                    mues.remove();
                }
            }
        }
        this.listaMuestra = auxListaMuestras;
        this.listaMuestra.add(MuestraLaboratorio.getMuestraLaboratorioById(selected.getMuestra()));
    }

    public void onMuestraChange() {
        actual.setFechaRecepcion(MuestraLaboratorio.getMuestraLaboratorioById(actual.getMuestra()).getFechaMuestreo());
        actual.setFechaEnvio(MuestraLaboratorio.getMuestraLaboratorioById(actual.getMuestra()).getFechaEnvio());
        actual.setCultivo(MuestraLaboratorio.getMuestraLaboratorioById(actual.getMuestra()).getCultivo());
        actual.setLeyendaCultivo(Cultivo.getCultivoById(actual.getCultivo()).getNombre());
        
        this.matriz = Matriz.getById(MuestraLaboratorio.getMuestraLaboratorioById(actual.getMuestra()).getMatriz()).getNombre();

        listaAnalisisPaquete = new ArrayList<>();
        List<AnalisisLaboratorio> listaAnalisisLaboratorioAuxP = MuestraLaboratorio.getMuestraLaboratorioById(actual.getMuestra()).getListadoPaquetesoAux();

        for (AnalisisLaboratorio obj : listaAnalisisLaboratorioAuxP) {
            for (Subanalisis comp : AnalisisLaboratorio.getAnalisisLaboratorioById(obj.getId()).getListadoElementos()) {
                ResultadoLaboratorioAnalisisCompuesto resAn = new ResultadoLaboratorioAnalisisCompuesto();
                resAn.setSubanalisis(comp.getId());
                resAn.setLeyendaSubanalisis(comp.getNombre() + " --(" + comp.getSimbolo() + ")");
                listaAnalisisPaquete.add(resAn);
            }
        }

        listaAnalisisCompuesto = new ArrayList<>();
        List<Subanalisis> listaAnalisisLaboratorioAuxE = MuestraLaboratorio.getMuestraLaboratorioById(actual.getMuestra()).getListadoSubanalisisAux();

        for (Subanalisis obj : listaAnalisisLaboratorioAuxE) {
            Subanalisis comp = Subanalisis.getById(obj.getId());
            ResultadoLaboratorioAnalisisCompuesto resAn = new ResultadoLaboratorioAnalisisCompuesto();
            resAn.setSubanalisis(comp.getId());
            resAn.setLeyendaSubanalisis(comp.getNombre() + " --(" + comp.getSimbolo() + ")");
            listaAnalisisCompuesto.add(resAn);
        }
    }

    public void onMuestraChangeSelected() {
        selected.setFechaRecepcion(MuestraLaboratorio.getMuestraLaboratorioById(selected.getMuestra()).getFechaMuestreo());
        selected.setFechaEnvio(MuestraLaboratorio.getMuestraLaboratorioById(selected.getMuestra()).getFechaEnvio());
        selected.setCultivo(MuestraLaboratorio.getMuestraLaboratorioById(selected.getMuestra()).getCultivo());
        selected.setLeyendaCultivo(Cultivo.getCultivoById(selected.getCultivo()).getNombre());
        
        this.matriz = Matriz.getById(MuestraLaboratorio.getMuestraLaboratorioById(selected.getMuestra()).getMatriz()).getNombre();

        listaAnalisisPaquete = new ArrayList<>();
        List<AnalisisLaboratorio> listaAnalisisLaboratorioAuxP = MuestraLaboratorio.getMuestraLaboratorioById(selected.getMuestra()).getListadoPaquetesoAux();

        for (AnalisisLaboratorio obj : listaAnalisisLaboratorioAuxP) {
            for (Subanalisis comp : AnalisisLaboratorio.getAnalisisLaboratorioById(obj.getId()).getListadoElementos()) {
                ResultadoLaboratorioAnalisisCompuesto resAn = new ResultadoLaboratorioAnalisisCompuesto();
                resAn.setSubanalisis(comp.getId());
                resAn.setLeyendaSubanalisis(comp.getNombre() + " --(" + comp.getSimbolo() + ")");
                listaAnalisisPaquete.add(resAn);
            }
        }

        listaAnalisisCompuesto = new ArrayList<>();
        List<Subanalisis> listaAnalisisLaboratorioAuxE = MuestraLaboratorio.getMuestraLaboratorioById(selected.getMuestra()).getListadoSubanalisisAux();

        for (Subanalisis obj : listaAnalisisLaboratorioAuxE) {
            Subanalisis comp = Subanalisis.getById(obj.getId());
            ResultadoLaboratorioAnalisisCompuesto resAn = new ResultadoLaboratorioAnalisisCompuesto();
            resAn.setSubanalisis(comp.getId());
            resAn.setLeyendaSubanalisis(comp.getNombre() + " --(" + comp.getSimbolo() + ")");
            listaAnalisisCompuesto.add(resAn);
        }
    }

    public void pdfReporteLab() {

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            HttpServletRequest request
                    = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpServletResponse response
                    = (HttpServletResponse) context.getExternalContext().getResponse();
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("../reporteResultadoLaboratorio.pdf");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
        } finally {
            context.responseComplete();
        }
    }

}
