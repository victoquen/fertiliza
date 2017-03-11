/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.AnalisisLaboratorio;
import entities.fertilizacion.Cliente;
import entities.fertilizacion.Courier;
import entities.fertilizacion.Cultivo;
import entities.fertilizacion.Departamento;
import entities.fertilizacion.EstacionMonitoreo;
import entities.fertilizacion.Hacienda;
import entities.fertilizacion.HaciendaLoteCultivoAux;
import entities.fertilizacion.Laboratorio;
import entities.fertilizacion.Matriz;
import entities.fertilizacion.MuestraLaboratorio;
import entities.fertilizacion.PeriodoMonitoreoAux;
import entities.fertilizacion.Subanalisis;
import helpers.Canton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.event.CellEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.MuestraLaboratorioModel;
import org.bson.types.ObjectId;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class MuestraLaboratorioController implements Serializable {

    MuestraLaboratorio actual;
    MuestraLaboratorio selected;
    List<MuestraLaboratorio> listado;
    List<MuestraLaboratorio> filter;
    MuestraLaboratorioModel model;

    List<Cliente> listaCliente;
    List<Hacienda> listaHacienda;
    List<HaciendaLoteCultivoAux> listaLote;
    List<Cultivo> listaCultivo;

    List<Matriz> listaMatriz;
    List<Courier> listaCourier;

    Date fechaIngresoLab;
    String formatFechaIngresoLab;
    Date fechaRecepcionInf;
    String formatFechaRecepcionInf;

    String barcode;

    List<Departamento> listaDepartamento;
    Boolean renderDatosCliente;
    List<EstacionMonitoreo> listaEstacionMonitoreo;
    List<PeriodoMonitoreoAux> listaPeriodoMonitoreo;
    
    /*List<AnalisisLaboratorio> listaSuelo;
    List<AnalisisLaboratorio> listaAgua;
    List<AnalisisLaboratorio> listaSolucionSuelo;
    List<AnalisisLaboratorio> listaPaqueteAgua;
    List<AnalisisLaboratorio> listaPaqueteSuelo;
    List<AnalisisLaboratorio> listaPaqueteSolucionSuelo;
    List<AnalisisLaboratorio> listaPaqueteFoliar;
    List<AnalisisLaboratorio> listaMetalesPesadosAgua;
    List<AnalisisLaboratorio> listaMetalesPesadosSuelo;
    List<AnalisisLaboratorio> listaMetalesPesadosSolucionSuelo;
    List<AnalisisLaboratorio> listaMetalesPesadosFoliar;

    List<ObjectId> analisisSuelo;
    List<ObjectId> analisisAgua;
    List<ObjectId> analisisSolucionSuelo;
    List<ObjectId> analisisPaqueteSuelo;
    List<ObjectId> analisisPaqueteAgua;
    List<ObjectId> analisisPaqueteSolucionSuelo;
    List<ObjectId> analisisPaqueteFoliar;
    List<ObjectId> analisisMetalesSuelo;
    List<ObjectId> analisisMetalesAgua;
    List<ObjectId> analisisMetalesSolucionSuelo;
    List<ObjectId> analisisMetalesFoliar;*/
    Integer tat;

    ObjectId matrizSelected;

    private DualListModel<Subanalisis> listadoElementos;
    private DualListModel<AnalisisLaboratorio> listadoPaquetes;

    List<Laboratorio> listaLaboratorio;
    List<Subanalisis> listadoAnalisisLaboratorioAuxE;
    List<AnalisisLaboratorio> listadoAnalisisLaboratorioAuxP;

    @PostConstruct
    public void init() {

        //List<Subanalisis> elementosSourceE = Subanalisis.getAllActivos();
        List<Subanalisis> elementosSourceE = new ArrayList<Subanalisis>();
        List<Subanalisis> elementosTargetE = new ArrayList<Subanalisis>();
        listadoElementos = new DualListModel<Subanalisis>(elementosSourceE, elementosTargetE);

        //List<AnalisisLaboratorio> elementosSourceP = AnalisisLaboratorio.getAllAnalisisActivo();
        List<AnalisisLaboratorio> elementosSourceP = new ArrayList<AnalisisLaboratorio>();
        List<AnalisisLaboratorio> elementosTargetP = new ArrayList<AnalisisLaboratorio>();
        listadoPaquetes = new DualListModel<AnalisisLaboratorio>(elementosSourceP, elementosTargetP);
    }

    public MuestraLaboratorioController() {
        actual = new MuestraLaboratorio();
        listado = MuestraLaboratorio.getAllMuestraLaboratorio();
        model = new MuestraLaboratorioModel(listado);

        listaCliente = Cliente.getAllClientes();

        listaLaboratorio = Laboratorio.getAllLaboratorio();
        listadoAnalisisLaboratorioAuxE = new ArrayList<>();
        listadoAnalisisLaboratorioAuxP = new ArrayList<>();
        
        listaDepartamento = Departamento.getAll();

        listaMatriz = Matriz.getAll();
        listaCourier = Courier.getAll();
        formatFechaIngresoLab = "---";
        formatFechaRecepcionInf = "---";

        this.tat = 0;
        this.renderDatosCliente = false;
        this.listaEstacionMonitoreo = new ArrayList<>();
        this.listaPeriodoMonitoreo = new ArrayList<>();
        
        actual.setNumeroCodigo(MuestraLaboratorio.getMaxNumeroCodigo());
        
    }

    public List<PeriodoMonitoreoAux> getListaPeriodoMonitoreo() {
        return listaPeriodoMonitoreo;
    }

    public void setListaPeriodoMonitoreo(List<PeriodoMonitoreoAux> listaPeriodoMonitoreo) {
        this.listaPeriodoMonitoreo = listaPeriodoMonitoreo;
    }

    public List<EstacionMonitoreo> getListaEstacionMonitoreo() {
        return listaEstacionMonitoreo;
    }

    public void setListaEstacionMonitoreo(List<EstacionMonitoreo> listaEstacionMonitoreo) {
        this.listaEstacionMonitoreo = listaEstacionMonitoreo;
    }

    public Boolean getRenderDatosCliente() {
        return renderDatosCliente;
    }

    public void setRenderDatosCliente(Boolean renderDatosCliente) {
        this.renderDatosCliente = renderDatosCliente;
    }

    
    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }
    
    public List<AnalisisLaboratorio> getListadoAnalisisLaboratorioAuxP() {
        return listadoAnalisisLaboratorioAuxP;
    }

    public void setListadoAnalisisLaboratorioAuxP(List<AnalisisLaboratorio> listadoAnalisisLaboratorioAuxP) {
        this.listadoAnalisisLaboratorioAuxP = listadoAnalisisLaboratorioAuxP;
    }

    public DualListModel<AnalisisLaboratorio> getListadoPaquetes() {
        return listadoPaquetes;
    }

    public void setListadoPaquetes(DualListModel<AnalisisLaboratorio> listadoPaquetes) {
        this.listadoPaquetes = listadoPaquetes;
    }

    public Integer getTat() {
        return tat;
    }

    public void setTat(Integer tat) {
        this.tat = tat;
    }

    public DualListModel<Subanalisis> getListadoElementos() {
        return listadoElementos;
    }

    public void setListadoElementos(DualListModel<Subanalisis> listadoElementos) {
        this.listadoElementos = listadoElementos;
    }

    public Date getFechaIngresoLab() {
        return fechaIngresoLab;
    }

    public void setFechaIngresoLab(Date fechaIngresoLab) {
        this.fechaIngresoLab = fechaIngresoLab;
    }

    public String getFormatFechaIngresoLab() {
        return formatFechaIngresoLab;
    }

    public void setFormatFechaIngresoLab(String formatFechaIngresoLab) {
        this.formatFechaIngresoLab = formatFechaIngresoLab;
    }

    public Date getFechaRecepcionInf() {
        return fechaRecepcionInf;
    }

    public void setFechaRecepcionInf(Date fechaRecepcionInf) {
        this.fechaRecepcionInf = fechaRecepcionInf;
    }

    public String getFormatFechaRecepcionInf() {
        return formatFechaRecepcionInf;
    }

    public void setFormatFechaRecepcionInf(String formatFechaRecepcionInf) {
        this.formatFechaRecepcionInf = formatFechaRecepcionInf;
    }

    public List<Matriz> getListaMatriz() {
        return listaMatriz;
    }

    public void setListaMatriz(List<Matriz> listaMatriz) {
        this.listaMatriz = listaMatriz;
    }

    public List<Courier> getListaCourier() {
        return listaCourier;
    }

    public void setListaCourier(List<Courier> listaCourier) {
        this.listaCourier = listaCourier;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public List<Hacienda> getListaHacienda() {
        return listaHacienda;
    }

    public void setListaHacienda(List<Hacienda> listaHacienda) {
        this.listaHacienda = listaHacienda;
    }

    public List<HaciendaLoteCultivoAux> getListaLote() {
        return listaLote;
    }

    public void setListaLote(List<HaciendaLoteCultivoAux> listaLote) {
        this.listaLote = listaLote;
    }

    public List<Cultivo> getListaCultivo() {
        return listaCultivo;
    }

    public void setListaCultivo(List<Cultivo> listaCultivo) {
        this.listaCultivo = listaCultivo;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public MuestraLaboratorio getActual() {
        return actual;
    }

    public void setActual(MuestraLaboratorio actual) {
        this.actual = actual;
    }

    public MuestraLaboratorio getSelected() {
        return selected;
    }

    public void setSelected(MuestraLaboratorio selected) {
        this.selected = selected;
    }

    public List<MuestraLaboratorio> getListado() {
        return listado;
    }

    public void setListado(List<MuestraLaboratorio> listado) {
        this.listado = listado;
    }

    public List<MuestraLaboratorio> getFilter() {
        return filter;
    }

    public void setFilter(List<MuestraLaboratorio> filter) {
        this.filter = filter;
    }

    public MuestraLaboratorioModel getModel() {
        return model;
    }

    public void setModel(MuestraLaboratorioModel model) {
        this.model = model;
    }

    public List<Laboratorio> getListaLaboratorio() {
        return listaLaboratorio;
    }

    public void setListaLaboratorio(List<Laboratorio> listaLaboratorio) {
        this.listaLaboratorio = listaLaboratorio;
    }

    public List<Subanalisis> getListadoAnalisisLaboratorioAuxE() {
        return listadoAnalisisLaboratorioAuxE;
    }

    public void setListadoAnalisisLaboratorioAuxE(List<Subanalisis> listadoAnalisisLaboratorioAuxE) {
        this.listadoAnalisisLaboratorioAuxE = listadoAnalisisLaboratorioAuxE;
    }

    public List<AnalisisLaboratorio> fillLista(List<ObjectId> listaP) {
        List<AnalisisLaboratorio> res = new ArrayList<>();
        if (!listaP.isEmpty()) {
            for (int k = 0; k < listaP.size(); k++) {
                AnalisisLaboratorio aux = new AnalisisLaboratorio();

                //bjectId id = new ObjectId();
                aux = AnalisisLaboratorio.getAnalisisLaboratorioById(listaP.get(k));
                res.add(aux);
            }
        }
        return res;
    }

    public List<ObjectId> fillListaObjectId(List<AnalisisLaboratorio> listaP) {
        List<ObjectId> res = new ArrayList<>();

        for (int k = 0; k < listaP.size(); k++) {
            ObjectId aux;
            aux = listaP.get(k).getId();
            res.add(aux);
        }
        return res;
    }

    public void save() {
            //if (controlDatosLaboratorio()) {
            if (controlDatos(actual)) {

                actual.setListadoPaquetesoAux(listadoAnalisisLaboratorioAuxP);
                actual.setListadoSubanalisisAux(listadoAnalisisLaboratorioAuxE);
                actual.save();
                load();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Ingresada"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
            }
        /* else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccionar laboratorio para todo Análisis"));
        }*/

    }

    public void update() {
        //if (controlDatosLaboratorio()) {
            if (controlDatos(selected)) {

                
                selected.setListadoPaquetesoAux(listadoAnalisisLaboratorioAuxP);
                selected.setListadoSubanalisisAux(listadoAnalisisLaboratorioAuxE);
                selected.update();
                load();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Modificada"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
            }
        /*} else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccionar laboratorio para todo Análisis"));
        }*/
    }

    public void load() {
        actual = new MuestraLaboratorio();
        listado = MuestraLaboratorio.getAllMuestraLaboratorio();
        model = new MuestraLaboratorioModel(listado);

        listadoAnalisisLaboratorioAuxE = new ArrayList<>();
        listadoAnalisisLaboratorioAuxP = new ArrayList<>();

        //List<Subanalisis> elementosSourceE = Subanalisis.getAllActivos();
        List<Subanalisis> elementosSourceE = new ArrayList<Subanalisis>();
        List<Subanalisis> elementosTargetE = new ArrayList<Subanalisis>();
        listadoElementos = new DualListModel<Subanalisis>(elementosSourceE, elementosTargetE);

        //List<AnalisisLaboratorio> elementosSourceP = AnalisisLaboratorio.getAllAnalisisActivo();
        List<AnalisisLaboratorio> elementosSourceP = new ArrayList<AnalisisLaboratorio>();
        List<AnalisisLaboratorio> elementosTargetP = new ArrayList<AnalisisLaboratorio>();
        listadoPaquetes = new DualListModel<AnalisisLaboratorio>(elementosSourceP, elementosTargetP);
        
        listaDepartamento = Departamento.getAll();

    }

    Boolean controlDatos(MuestraLaboratorio u) {
        Boolean res = true;

        if ((u.getCodigo().equals(""))  
                || u.getMatriz()==null 
                || u.getCourier()==null 
                || u.getLote()=="" 
                || u.getCliente()==null
                || u.getHacienda()==null 
                || u.getCultivo()==null
                || u.getCanton()==null 
                || u.getDepartamento()==null) {
            res = false;
        }
        return res;
    }

    Boolean controlDatosLaboratorio() {
        Boolean found = true;
        for (Subanalisis myObj : listadoAnalisisLaboratorioAuxE) {
            if (myObj.getLaboratorio() == null) {
                found = false;

            }
        }

        for (AnalisisLaboratorio myObj : listadoAnalisisLaboratorioAuxP) {
            if (myObj.getLaboratorio() == null) {
                found = false;

            }
        }
        return found;
    }

    public void onClienteChange() {
        if (actual.getCliente() != null) {
            listaHacienda = Hacienda.getAllHaciendaByClienteId(actual.getCliente());

            actual.setCanton(Cliente.getClienteById(actual.getCliente()).getCanton());
            actual.setLeyendaCanton(Canton.getCantonById(Cliente.getClienteById(actual.getCliente()).getCanton()).getNombre() + " (" + Canton.getCantonById(Cliente.getClienteById(actual.getCliente()).getCanton()).getLeyendaPais() + ")");
            actual.setTipoMuestra(Cliente.getClienteById(actual.getCliente()).getTipoCliente());
            
            if(actual.getTipoMuestra().equals("PLANTECH")){
                this.renderDatosCliente = true;
            }else{
                this.renderDatosCliente =false;
            }
        }
    }

    public void onClienteChangeSelected() {
        if (selected.getCliente() != null) {
            listaHacienda = Hacienda.getAllHaciendaByClienteId(selected.getCliente());

            selected.setCanton(Cliente.getClienteById(selected.getCliente()).getCanton());
            selected.setLeyendaCanton(Canton.getCantonById(Cliente.getClienteById(selected.getCliente()).getCanton()).getNombre() + " (" + Canton.getCantonById(Cliente.getClienteById(selected.getCliente()).getCanton()).getLeyendaPais() + ")");
            selected.setTipoMuestra(Cliente.getClienteById(selected.getCliente()).getTipoCliente());
            
            if(selected.getTipoMuestra().equals("PLANTECH")){
                this.renderDatosCliente = true;
            }else{
                this.renderDatosCliente =false;
            }
            
        }
    }

    public void onHaciendaChange() {
        if (actual.getHacienda() != null) {
            listaLote = Hacienda.getHaciendaById(actual.getHacienda()).getListadoLotes();

            if (Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(0).getNombre() != null) {
                actual.setPersonaContacto(Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(0).getNombre() + " (" + Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(0).getCargo() + ")");
            } else if (Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(1).getNombre() != null) {
                actual.setPersonaContacto(Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(1).getNombre() + " (" + Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(1).getCargo() + ")");
            } else if (Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(2).getNombre() != null) {
                actual.setPersonaContacto(Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(2).getNombre() + " (" + Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(2).getCargo() + ")");
            } else if (Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(3).getNombre() != null) {
                actual.setPersonaContacto(Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(3).getNombre() + " (" + Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(3).getCargo() + ")");
            }

            String mails = "";
            if (!Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(0).getEmail().isEmpty()) {
                StringBuilder auxmail = new StringBuilder(75);
                int contHac = Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(0).getEmail().size();
                for (int j = 0; j < contHac; j++) {
                    
                    auxmail.append(Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(0).getEmail().get(j)).append(";");
                }
                mails = mails + auxmail.toString();
            }
            if (!Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(1).getEmail().isEmpty()) {
                StringBuilder auxmail = new StringBuilder(75);
                int contHac = Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(1).getEmail().size();
                for (int j = 0; j <contHac ; j++) {
                    auxmail.append(Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(1).getEmail().get(j)).append(";");
                }
                mails = mails + auxmail.toString();
            }
            if (!Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(2).getEmail().isEmpty()) {
                StringBuilder auxmail = new StringBuilder(75);
                int contHac =Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(2).getEmail().size();
                for (int j = 0; j < contHac; j++) {
                    auxmail.append(Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(2).getEmail().get(j)).append(";");
                }
                mails = mails + auxmail.toString();
            }
            if (!Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(3).getEmail().isEmpty()) {
                StringBuilder auxmail = new StringBuilder(75);
                int contHac =Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(3).getEmail().size();
                for (int j = 0; j < contHac; j++) {
                    auxmail.append(Hacienda.getHaciendaById(actual.getHacienda()).getContactos().get(3).getEmail().get(j)).append(";");
                }
                mails = mails + auxmail.toString();
            }
            actual.setEmailInforme(mails);
        }
    }

    public void onHaciendaChangeSelected() {
        if (selected.getHacienda() != null) {           
            listaLote = Hacienda.getHaciendaById(selected.getHacienda()).getListadoLotes();
            if (Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(0).getNombre() != null) {
                selected.setPersonaContacto(Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(0).getNombre() + " (" + Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(0).getCargo() + ")");
            } else if (Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(1).getNombre() != null) {
                selected.setPersonaContacto(Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(1).getNombre() + " (" + Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(1).getCargo() + ")");
            } else if (Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(2).getNombre() != null) {
                selected.setPersonaContacto(Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(2).getNombre() + " (" + Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(2).getCargo() + ")");
            } else if (Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(3).getNombre() != null) {
                selected.setPersonaContacto(Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(3).getNombre() + " (" + Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(3).getCargo() + ")");
            }

            String mails = "";
            if (!Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(0).getEmail().isEmpty()) {
                StringBuilder auxmail = new StringBuilder(75);
                for (int j = 0; j < Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(0).getEmail().size(); j++) {
                    auxmail.append(Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(0).getEmail().get(j)).append(";");
                }
                mails = mails + auxmail.toString();
            }
            if (!Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(1).getEmail().isEmpty()) {
                StringBuilder auxmail = new StringBuilder(75);
                for (int j = 0; j < Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(1).getEmail().size(); j++) {
                    auxmail.append(Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(1).getEmail().get(j)).append(";");
                }
                mails = mails + auxmail.toString();
            }
            if (!Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(2).getEmail().isEmpty()) {
                StringBuilder auxmail = new StringBuilder(75);
                for (int j = 0; j < Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(2).getEmail().size(); j++) {
                    auxmail.append(Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(2).getEmail().get(j)).append(";");
                }
                mails = mails + auxmail.toString();
            }
            if (!Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(3).getEmail().isEmpty()) {
                StringBuilder auxmail = new StringBuilder(75);
                for (int j = 0; j < Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(3).getEmail().size(); j++) {
                    auxmail.append(Hacienda.getHaciendaById(selected.getHacienda()).getContactos().get(3).getEmail().get(j)).append(";");
                }
                mails = mails + auxmail.toString();
            }
            selected.setEmailInforme(mails);

        }
    }

    HaciendaLoteCultivoAux getLoteCompleto(MuestraLaboratorio obj){
        HaciendaLoteCultivoAux res = null;
        
        res = Hacienda.getHaciendaLoteCultivoByIdLotes(Hacienda.getHaciendaById(obj.getHacienda()).getListadoLotes(), obj.getLote());
        return res;
    }
    
    /*EstacionMonitoreo getEstacionMonitoreoCompleto(HaciendaLoteCultivoAux obj, String codEstacion){
        EstacionMonitoreo res = null;
        
        res = Hacienda.getPeriodoMonitoreoAuxByIdEstacionMonitoreo(obj.getListadoMonitoreo(), codEstacion);
       
        return res;
    }*/
    
    HaciendaLoteCultivoAux getEstacionMonitoreoCompleto(Hacienda obj, String codigoMayorEstacion){
        HaciendaLoteCultivoAux res = null;
        
        res = Hacienda.getPeriodoMonitoreoAuxByIdEstacionCategoriaMayor(obj.getListadoLotes(), codigoMayorEstacion);
       
        return res;
    }
    
    public void onLoteChange() {
        if (actual.getLote() != null) {
            actual.setCultivo(getLoteCompleto(actual).getCultivo());
            actual.setLeyendaCultivo(getLoteCompleto(actual).getLeyendaCultivo());
            //this.listaEstacionMonitoreo = getLoteCompleto(actual).getListadoMonitoreo();
            actual.setEstacionMonitoreo(getLoteCompleto(actual).getCodigoMayorEstacion());
            
            this.listaEstacionMonitoreo = getLoteCompleto(actual).getListaEstacionMonitoreo();           
            this.listaPeriodoMonitoreo = getLoteCompleto(actual).getListaPeriodosMonitoreos();
        }
    }

    public void onLoteChangeSelected() {
        if (selected.getLote() != null) {
            selected.setCultivo(getLoteCompleto(selected).getCultivo());
            selected.setLeyendaCultivo(getLoteCompleto(selected).getLeyendaCultivo());
            selected.setEstacionMonitoreo(getLoteCompleto(selected).getCodigoMayorEstacion());
            //this.listaEstacionMonitoreo = getLoteCompleto(selected).getListadoMonitoreo();
            
            this.listaEstacionMonitoreo = getLoteCompleto(actual).getListaEstacionMonitoreo();
            this.listaPeriodoMonitoreo = getLoteCompleto(actual).getListaPeriodosMonitoreos();
        }
    }
    
    /*
    public void onEstacionMonitoreoChange(){
        if(!actual.getEstacionMonitoreo().equals("")){            
            this.listaPeriodoMonitoreo = getEstacionMonitoreoCompleto(getLoteCompleto(actual), actual.getEstacionMonitoreo()).getListaPeriodosMonitoreos();
        }
    }
    
    public void onEstacionMonitoreoChangeSelected(){
        if(!selected.getEstacionMonitoreo().equals("")){            
            this.listaPeriodoMonitoreo = getEstacionMonitoreoCompleto(getLoteCompleto(selected), selected.getEstacionMonitoreo()).getListaPeriodosMonitoreos();
        }
    }
    */
    public void onDepartamentoChange(){
        Calendar fecha = Calendar.getInstance();
        String pre = Departamento.getById(this.actual.getDepartamento()).getAbreviatura();
        
        this.actual.setCodigo(pre +"-" +fecha.get(Calendar.YEAR));
    }
    
     public void onDepartamentoChangeSelected(){
        Calendar fecha = Calendar.getInstance();
        String pre = Departamento.getById(this.selected.getDepartamento()).getAbreviatura();
        
        this.selected.setCodigo(pre +"-" +fecha.get(Calendar.YEAR));
    }
    

    public void onCodeChange() {
        if (actual.getCodigo() != null && !"".equals(actual.getCodigo())) {
            this.barcode = actual.getCodigo();
        }
    }

    public void onCodeChangeSelected() {
        if (selected.getCodigo() != null && !"".equals(selected.getCodigo())) {
            this.barcode = selected.getCodigo();
        }
    }

    public void loadListasSelected() {

        this.listaCliente = Cliente.getAllClientes();
        
        if(selected.getTipoMuestra().equals("PLANTECH")){
            this.renderDatosCliente= true;
            this.listaHacienda = Hacienda.getAllHaciendaByClienteId(selected.getCliente());        
            this.listaLote = Hacienda.getHaciendaById(selected.getHacienda()).getListadoLotes();
            
            //this.listaEstacionMonitoreo = getLoteCompleto(selected).getListadoMonitoreo();
            this.listaEstacionMonitoreo = getLoteCompleto(selected).getListaEstacionMonitoreo();
            //this.listaPeriodoMonitoreo = getEstacionMonitoreoCompleto(getLoteCompleto(selected). , selected.getEstacionMonitoreo()).getListaPeriodosMonitoreos();
            this.listaPeriodoMonitoreo = getLoteCompleto(selected).getListaPeriodosMonitoreos();
        }
        
        
        
        
        this.listadoAnalisisLaboratorioAuxE = selected.getListadoSubanalisisAux();
        this.listadoAnalisisLaboratorioAuxP = selected.getListadoPaquetesoAux();

        int tatE = 0;
        List<Subanalisis> elementosSourceE = Subanalisis.getAllActivosByMatriz(selected.getMatriz());
        List<Subanalisis> elementosTargetE = new ArrayList<Subanalisis>();
        for (int i = 0; i < this.listadoAnalisisLaboratorioAuxE.size(); i++) {
            elementosTargetE.add(this.listadoAnalisisLaboratorioAuxE.get(i));
            elementosSourceE.remove(findElemento(this.listadoAnalisisLaboratorioAuxE.get(i).getId(), elementosSourceE));

            tatE = this.listadoAnalisisLaboratorioAuxE.get(i).getTat();
        }
        listadoElementos = new DualListModel<Subanalisis>(elementosSourceE, elementosTargetE);

        int tatP = 0;
        List<AnalisisLaboratorio> elementosSourceP = AnalisisLaboratorio.getAllAnalisisActivoByMatriz(selected.getMatriz());
        List<AnalisisLaboratorio> elementosTargetP = new ArrayList<AnalisisLaboratorio>();
        for (int i = 0; i < this.listadoAnalisisLaboratorioAuxP.size(); i++) {
            elementosTargetP.add(this.listadoAnalisisLaboratorioAuxP.get(i));
            elementosSourceP.remove(findPaquete(this.listadoAnalisisLaboratorioAuxP.get(i).getId(), elementosSourceP));

            tatP = this.listadoAnalisisLaboratorioAuxP.get(i).getTat();
        }
        listadoPaquetes = new DualListModel<AnalisisLaboratorio>(elementosSourceP, elementosTargetP);

        if (tatE > tatP) {
            this.tat = tatE;
        } else {
            this.tat = tatP;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(selected.getFechaEnvio());
        cal.add(Calendar.DATE, 3);
        this.fechaIngresoLab = cal.getTime();

        cal.setTime(fechaIngresoLab);
        cal.add(Calendar.DATE, this.tat);
        this.fechaRecepcionInf = cal.getTime();

        this.matrizSelected = selected.getMatriz();

    }

    public void loadListaLaboratorio(int op) {

        List<ObjectId> listaAuxId = new ArrayList<>();
       
        
        listadoAnalisisLaboratorioAuxP = new ArrayList<>();
        for (int i = 0; i < listadoPaquetes.getTarget().size(); i++) {
            //Boolean found = false;
            //for (AnalisisLaboratorio myObj : listadoAnalisisLaboratorioAuxP) {
                //if (myObj.getId() != null && myObj.getId().equals(listadoPaquetes.getTarget().get(i).getId())) {
                    //found = true;

                //}
            //}
            //if (!found) {
                AnalisisLaboratorio obj = new AnalisisLaboratorio();
                obj.setId(listadoPaquetes.getTarget().get(i).getId());
                obj.setDescripcion(AnalisisLaboratorio.getAnalisisLaboratorioById(listadoPaquetes.getTarget().get(i).getId()).getDescripcion());

                obj.setLaboratorio(AnalisisLaboratorio.getAnalisisLaboratorioById(listadoPaquetes.getTarget().get(i).getId()).getLaboratorio());
                if (AnalisisLaboratorio.getAnalisisLaboratorioById(listadoPaquetes.getTarget().get(i).getId()).getLeyendaLaboratorio().equals("")) {
                    obj.setLeyendaLaboratorio("Seleccionar Laboratorio");
                } else {
                    obj.setLeyendaLaboratorio(AnalisisLaboratorio.getAnalisisLaboratorioById(listadoPaquetes.getTarget().get(i).getId()).getLeyendaLaboratorio());
                }

                listadoAnalisisLaboratorioAuxP.add(obj);
            //}
        }

        listadoAnalisisLaboratorioAuxE = new ArrayList<>();
        for (int i = 0; i < listadoElementos.getTarget().size(); i++) {
            //Boolean found = false;
           // for (Subanalisis myObj : listadoAnalisisLaboratorioAuxE) {
                //if (myObj.getId() != null && myObj.getId().equals(listadoElementos.getTarget().get(i).getId())) {
                    //found = true;

                //}
            //}
            //if (!found) {
                Subanalisis obj = new Subanalisis();
                obj.setId(listadoElementos.getTarget().get(i).getId());
                obj.setNombre(Subanalisis.getById(listadoElementos.getTarget().get(i).getId()).getNombre() + " (" + Subanalisis.getById(listadoElementos.getTarget().get(i).getId()).getSimbolo() + ")");

                obj.setLaboratorio(Subanalisis.getById(listadoElementos.getTarget().get(i).getId()).getLaboratorio());
                if (Subanalisis.getById(listadoElementos.getTarget().get(i).getId()).getLeyendaLaboratorio().equals("")) {
                    obj.setLeyendaLaboratorio("Seleccionar Laboratorio");
                } else {
                    obj.setLeyendaLaboratorio(Subanalisis.getById(listadoElementos.getTarget().get(i).getId()).getLeyendaLaboratorio());
                }

                listadoAnalisisLaboratorioAuxE.add(obj);
            //}
        }
        
        if(op==1){
            save();
        }else{
            update();
        }

    }

    public void onCellEditLaboratorio(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            ObjectId idaux = (ObjectId) newValue;
            for (int i = 0; i < listadoAnalisisLaboratorioAuxE.size(); i++) {
                if (listadoAnalisisLaboratorioAuxE.get(i).getLaboratorio() != null && listadoAnalisisLaboratorioAuxE.get(i).getLaboratorio().equals(idaux)) {
                    listadoAnalisisLaboratorioAuxE.get(i).setLeyendaLaboratorio(Laboratorio.getLaboratorioById(idaux).getNombre() + " (" + Laboratorio.getLaboratorioById(idaux).getPais() + ")");
                }
            }

        }
    }

    public void onCellEditLaboratorioP(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            ObjectId idaux = (ObjectId) newValue;
            for (int i = 0; i < listadoAnalisisLaboratorioAuxP.size(); i++) {
                if (listadoAnalisisLaboratorioAuxP.get(i).getLaboratorio() != null && listadoAnalisisLaboratorioAuxP.get(i).getLaboratorio().equals(idaux)) {
                    listadoAnalisisLaboratorioAuxP.get(i).setLeyendaLaboratorio(Laboratorio.getLaboratorioById(idaux).getNombre() + " (" + Laboratorio.getLaboratorioById(idaux).getPais() + ")");
                }
            }

        }
    }

    public void onChangeFechaEnvio() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(actual.getFechaEnvio());
        cal.add(Calendar.DATE, 3);
        this.fechaIngresoLab = cal.getTime();

        cal.setTime(fechaIngresoLab);
        cal.add(Calendar.DATE, this.tat);
        this.fechaRecepcionInf = cal.getTime();
    }

    public void onChangeFechaEnvioSelected() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(selected.getFechaEnvio());
        cal.add(Calendar.DATE, 3);
        this.fechaIngresoLab = cal.getTime();

        cal.setTime(fechaIngresoLab);
        cal.add(Calendar.DATE, this.tat);
        this.fechaRecepcionInf = cal.getTime();
    }

    public void onTransferE(TransferEvent event) {
        int ingE = 0;
        int ingP = 0;

        for (Object item : event.getItems()) {
            Boolean check = true;
            for (int i = 0; i < listadoElementos.getTarget().size(); i++) {
                if ((listadoElementos.getTarget().get(i).getTat() > ingE)) {
                    ingE = listadoElementos.getTarget().get(i).getTat();

                }
            }

            for (int i = 0; i < listadoPaquetes.getTarget().size(); i++) {
                for (int j = 0; j < listadoPaquetes.getTarget().get(i).getListadoElementos().size(); j++) {
                    if ((listadoPaquetes.getTarget().get(i).getListadoElementos().get(j).getTat() > ingP)) {
                        ingP = listadoPaquetes.getTarget().get(i).getListadoElementos().get(j).getTat();
                    }

                    if (((Subanalisis) item).getId().equals(listadoPaquetes.getTarget().get(i).getListadoElementos().get(j).getId()) && check) {

                        Subanalisis aux = ((Subanalisis) item);
                        List<Subanalisis> elementosSourceE = listadoElementos.getSource();
                        List<Subanalisis> elementosTargetE = listadoElementos.getTarget();
                        elementosSourceE.add(aux);
                        elementosTargetE.remove(findElemento(aux.getId(), elementosTargetE));
                        listadoElementos = new DualListModel<Subanalisis>(elementosSourceE, elementosTargetE);

                        check = false;
                    }
                }
            }

            

            if (!check) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El subanálisis ya se encuentra en un paquete de análisis"));
            }else{
                if (ingE > ingP) {
                    this.tat = ingE;
                } else {
                    this.tat = ingP;
                }
            }

        }

        if (actual.getFechaEnvio() != null) {
            onChangeFechaEnvio();
        }
    }

    public void onTransferP(TransferEvent event) {
        int ingE = 0;
        int ingP = 0;

        for (Object item : event.getItems()) {
            Boolean check = true;
            for (int i = 0; i < listadoPaquetes.getTarget().size(); i++) {
                for (int j = 0; j < listadoPaquetes.getTarget().get(i).getListadoElementos().size(); j++) {
                    if ((listadoPaquetes.getTarget().get(i).getListadoElementos().get(j).getTat() > ingP)) {
                        ingP = listadoPaquetes.getTarget().get(i).getListadoElementos().get(j).getTat();
                    }
                }
            }

            for (int i = 0; i < listadoElementos.getTarget().size(); i++) {
                if ((listadoElementos.getTarget().get(i).getTat() > ingE)) {
                    ingE = listadoElementos.getTarget().get(i).getTat();
                }
                AnalisisLaboratorio aux = ((AnalisisLaboratorio) item);
                for (int j = 0; j < aux.getListadoElementos().size(); j++) {
                    if (aux.getListadoElementos().get(j).getId().equals(listadoElementos.getTarget().get(i).getId()) && check) {
                        List<AnalisisLaboratorio> elementosSourceP = listadoPaquetes.getSource();
                        List<AnalisisLaboratorio> elementosTargetP = listadoPaquetes.getTarget();
                        elementosSourceP.add(aux);
                        elementosTargetP.remove(findPaquete(aux.getId(), elementosTargetP));
                        listadoPaquetes = new DualListModel<AnalisisLaboratorio>(elementosSourceP, elementosTargetP);
                        check = false;
                    }
                }
            }

            

            if (!check) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El paquete de análisis contiene un subanálisis "));
            }else{
                if (ingE > ingP) {
                    this.tat = ingE;
                } else {
                    this.tat = ingP;
                }
            }
        }
        if (actual.getFechaEnvio() != null) {
            onChangeFechaEnvio();
        }
    }
    
    public void onTransferESelected(TransferEvent event) {
        int ingE = 0;
        int ingP = 0;

        for (Object item : event.getItems()) {
            Boolean check = true;
            for (int i = 0; i < listadoElementos.getTarget().size(); i++) {
                if ((listadoElementos.getTarget().get(i).getTat() > ingE)) {
                    ingE = listadoElementos.getTarget().get(i).getTat();

                }
            }

            for (int i = 0; i < listadoPaquetes.getTarget().size(); i++) {
                for (int j = 0; j < listadoPaquetes.getTarget().get(i).getListadoElementos().size(); j++) {
                    if ((listadoPaquetes.getTarget().get(i).getListadoElementos().get(j).getTat() > ingP)) {
                        ingP = listadoPaquetes.getTarget().get(i).getListadoElementos().get(j).getTat();
                    }

                    if (((Subanalisis) item).getId().equals(listadoPaquetes.getTarget().get(i).getListadoElementos().get(j).getId()) && check) {

                        Subanalisis aux = ((Subanalisis) item);
                        List<Subanalisis> elementosSourceE = listadoElementos.getSource();
                        List<Subanalisis> elementosTargetE = listadoElementos.getTarget();
                        elementosSourceE.add(aux);
                        elementosTargetE.remove(findElemento(aux.getId(), elementosTargetE));
                        listadoElementos = new DualListModel<Subanalisis>(elementosSourceE, elementosTargetE);

                        check = false;
                    }
                }
            }

            

            if (!check) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El subanálisis ya se encuentra en un paquete de análisis"));
            }else{
                if (ingE > ingP) {
                    this.tat = ingE;
                } else {
                    this.tat = ingP;
                }
            }

        }

        if (selected.getFechaEnvio() != null) {
            onChangeFechaEnvioSelected();
        }
    }

    public void onTransferPSelected(TransferEvent event) {
        int ingE = 0;
        int ingP = 0;

        for (Object item : event.getItems()) {
            Boolean check = true;
            for (int i = 0; i < listadoPaquetes.getTarget().size(); i++) {
                for (int j = 0; j < listadoPaquetes.getTarget().get(i).getListadoElementos().size(); j++) {
                    if ((listadoPaquetes.getTarget().get(i).getListadoElementos().get(j).getTat() > ingP)) {
                        ingP = listadoPaquetes.getTarget().get(i).getListadoElementos().get(j).getTat();
                    }
                }
            }

            for (int i = 0; i < listadoElementos.getTarget().size(); i++) {
                if ((listadoElementos.getTarget().get(i).getTat() > ingE)) {
                    ingE = listadoElementos.getTarget().get(i).getTat();
                }
                AnalisisLaboratorio aux = ((AnalisisLaboratorio) item);
                for (int j = 0; j < aux.getListadoElementos().size(); j++) {
                    if (aux.getListadoElementos().get(j).getId().equals(listadoElementos.getTarget().get(i).getId()) && check) {
                        List<AnalisisLaboratorio> elementosSourceP = listadoPaquetes.getSource();
                        List<AnalisisLaboratorio> elementosTargetP = listadoPaquetes.getTarget();
                        elementosSourceP.add(aux);
                        elementosTargetP.remove(findPaquete(aux.getId(), elementosTargetP));
                        listadoPaquetes = new DualListModel<AnalisisLaboratorio>(elementosSourceP, elementosTargetP);
                        check = false;
                    }
                }
            }

            

            if (!check) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El paquete de análisis contiene un subanálisis "));
            }else{
                if (ingE > ingP) {
                    this.tat = ingE;
                } else {
                    this.tat = ingP;
                }
            }
        }
        if (selected.getFechaEnvio() != null) {
            onChangeFechaEnvioSelected();
        }
    }

    int findPaquete(ObjectId id, List<AnalisisLaboratorio> listado) {
        int pos = -1;
        int cont = 0;

        while (cont < listado.size()) {
            if (listado.get(cont).getId().equals(id)) {
                pos = cont;
                cont = listado.size();
            }
            cont++;
        }
        return pos;
    }

    int findElemento(ObjectId id, List<Subanalisis> listado) {
        int pos = -1;
        int cont = 0;

        while (cont < listado.size()) {
            if (listado.get(cont).getId().equals(id)) {
                pos = cont;
                cont = listado.size();
            }
            cont++;
        }
        return pos;
    }

    public void onMatrizChange() {
        List<Subanalisis> elementosSourceE = Subanalisis.getAllActivosByMatriz(actual.getMatriz());
        List<Subanalisis> elementosTargetE = new ArrayList<Subanalisis>();
        listadoElementos = new DualListModel<Subanalisis>(elementosSourceE, elementosTargetE);

        List<AnalisisLaboratorio> elementosSourceP = AnalisisLaboratorio.getAllAnalisisActivoByMatriz(actual.getMatriz());
        List<AnalisisLaboratorio> elementosTargetP = new ArrayList<AnalisisLaboratorio>();
        listadoPaquetes = new DualListModel<AnalisisLaboratorio>(elementosSourceP, elementosTargetP);
    }

    public void onMatrizChangeSelected() {

        if (this.matrizSelected.equals(selected.getMatriz())) {
            int tatE = 0;
            List<Subanalisis> elementosSourceE = Subanalisis.getAllActivosByMatriz(selected.getMatriz());
            List<Subanalisis> elementosTargetE = new ArrayList<Subanalisis>();
            for (int i = 0; i < this.listadoAnalisisLaboratorioAuxE.size(); i++) {
                elementosTargetE.add(this.listadoAnalisisLaboratorioAuxE.get(i));
                elementosSourceE.remove(findElemento(this.listadoAnalisisLaboratorioAuxE.get(i).getId(), elementosSourceE));

                tatE = this.listadoAnalisisLaboratorioAuxE.get(i).getTat();
            }
            listadoElementos = new DualListModel<Subanalisis>(elementosSourceE, elementosTargetE);

            int tatP = 0;
            List<AnalisisLaboratorio> elementosSourceP = AnalisisLaboratorio.getAllAnalisisActivoByMatriz(selected.getMatriz());
            List<AnalisisLaboratorio> elementosTargetP = new ArrayList<AnalisisLaboratorio>();
            for (int i = 0; i < this.listadoAnalisisLaboratorioAuxP.size(); i++) {
                elementosTargetP.add(this.listadoAnalisisLaboratorioAuxP.get(i));
                elementosSourceP.remove(findPaquete(this.listadoAnalisisLaboratorioAuxP.get(i).getId(), elementosSourceP));

                tatP = this.listadoAnalisisLaboratorioAuxP.get(i).getTat();
            }
            listadoPaquetes = new DualListModel<AnalisisLaboratorio>(elementosSourceP, elementosTargetP);
        } else {
            List<Subanalisis> elementosSourceE = Subanalisis.getAllActivosByMatriz(selected.getMatriz());
            List<Subanalisis> elementosTargetE = new ArrayList<Subanalisis>();
            listadoElementos = new DualListModel<Subanalisis>(elementosSourceE, elementosTargetE);

            List<AnalisisLaboratorio> elementosSourceP = AnalisisLaboratorio.getAllAnalisisActivoByMatriz(selected.getMatriz());
            List<AnalisisLaboratorio> elementosTargetP = new ArrayList<AnalisisLaboratorio>();
            listadoPaquetes = new DualListModel<AnalisisLaboratorio>(elementosSourceP, elementosTargetP);
        }

    }

}
