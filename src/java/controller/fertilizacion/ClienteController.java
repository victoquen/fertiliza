/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Cliente;
import entities.fertilizacion.Cultivo;
import entities.fertilizacion.Departamento;
import entities.fertilizacion.EtapaCultivo;
import entities.fertilizacion.Hacienda;
import entities.fertilizacion.HaciendaLoteCultivoAux;
import entities.fertilizacion.Lote;
import entities.fertilizacion.Variedad;
import helpers.Canton;
import helpers.Pais;
import helpers.Provincia;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.CantonModel;
import models.ClienteModel;
import models.PaisModel;
import models.ProvinciaModel;
import org.bson.types.ObjectId;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.ReverseGeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class ClienteController implements Serializable {

    Cliente actual;
    Cliente selected;
    List<Cliente> listado;
    List<Cliente> filter;
    ClienteModel model;

    

    String actualMail;
    String selectedMail;
    List<String> mails;

    String actualFono;
    String selectedFono;
    List<String> fonos;

    

    Canton actualCan;
    Canton selectedCan;
    List<Canton> listadoCan;
    List<Canton> filterCan;
    CantonModel modelCan;

    Provincia actualPr;
    Provincia selectedPr;
    List<Provincia> listadoPr;
    List<Provincia> filterPr;
    ProvinciaModel modelPr;

    Pais actualPs;
    Pais selectedPs;
    List<Pais> listadoPs;
    List<Pais> filterPs;
    PaisModel modelPs;

    List<Departamento> listadoDepartamento;
    
    
    
   

    public ClienteController() {
        this.actual = new Cliente();
        this.listado = Cliente.getAllClientes();
        this.model = new ClienteModel(listado);
      
        actualMail = "";
        mails = new ArrayList<>();

        actualFono = "";
        fonos = new ArrayList<>();
      
        actualPs = new Pais();
        listadoPs = Pais.getAllPais();
        actualPr = new Provincia();
        actualCan = new Canton();

        listadoDepartamento = Departamento.getAll();

        

    }

    public Cliente getActual() {
        return actual;
    }

    public void setActual(Cliente actual) {
        this.actual = actual;
    }

    public Cliente getSelected() {
        return selected;
    }

    public void setSelected(Cliente selected) {
        this.selected = selected;
    }

    public List<Cliente> getListado() {
        return listado;
    }

    public void setListado(List<Cliente> listado) {
        this.listado = listado;
    }

    public List<Cliente> getFilter() {
        return filter;
    }

    public void setFilter(List<Cliente> filter) {
        this.filter = filter;
    }

    public ClienteModel getModel() {
        return model;
    }

    public void setModel(ClienteModel model) {
        this.model = model;
    }

    public String getActualMail() {
        return actualMail;
    }

    public void setActualMail(String actualMail) {
        this.actualMail = actualMail;
    }

    public String getSelectedMail() {
        return selectedMail;
    }

    public void setSelectedMail(String selectedMail) {
        this.selectedMail = selectedMail;
    }

    public List<String> getMails() {
        return mails;
    }

    public void setMails(List<String> mails) {
        this.mails = mails;
    }

    public String getActualFono() {
        return actualFono;
    }

    public void setActualFono(String actualFono) {
        this.actualFono = actualFono;
    }

    public String getSelectedFono() {
        return selectedFono;
    }

    public void setSelectedFono(String selectedFono) {
        this.selectedFono = selectedFono;
    }

    public List<String> getFonos() {
        return fonos;
    }

    public void setFonos(List<String> fonos) {
        this.fonos = fonos;
    }

    public Canton getActualCan() {
        return actualCan;
    }

    public void setActualCan(Canton actualCan) {
        this.actualCan = actualCan;
    }

    public Canton getSelectedCan() {
        return selectedCan;
    }

    public void setSelectedCan(Canton selectedCan) {
        this.selectedCan = selectedCan;
    }

    public List<Canton> getListadoCan() {
        return listadoCan;
    }

    public void setListadoCan(List<Canton> listadoCan) {
        this.listadoCan = listadoCan;
    }

    public List<Canton> getFilterCan() {
        return filterCan;
    }

    public void setFilterCan(List<Canton> filterCan) {
        this.filterCan = filterCan;
    }

    public CantonModel getModelCan() {
        return modelCan;
    }

    public void setModelCan(CantonModel modelCan) {
        this.modelCan = modelCan;
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

    public Pais getActualPs() {
        return actualPs;
    }

    public void setActualPs(Pais actualPs) {
        this.actualPs = actualPs;
    }

    public Pais getSelectedPs() {
        return selectedPs;
    }

    public void setSelectedPs(Pais selectedPs) {
        this.selectedPs = selectedPs;
    }

    public List<Pais> getListadoPs() {
        return listadoPs;
    }

    public void setListadoPs(List<Pais> listadoPs) {
        this.listadoPs = listadoPs;
    }

    public List<Pais> getFilterPs() {
        return filterPs;
    }

    public void setFilterPs(List<Pais> filterPs) {
        this.filterPs = filterPs;
    }

    public PaisModel getModelPs() {
        return modelPs;
    }

    public void setModelPs(PaisModel modelPs) {
        this.modelPs = modelPs;
    }

    public List<Departamento> getListadoDepartamento() {
        return listadoDepartamento;
    }

    public void setListadoDepartamento(List<Departamento> listadoDepartamento) {
        this.listadoDepartamento = listadoDepartamento;
    }

    

    public void save() {

        if (controlDatos(actual)) {

            actual.setTelefono(fonos);
            actual.setEmail(mails);
            ObjectId auxCli = actual.save();

            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Cliente Ingresado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        
        selected = actual;
        if (controlDatos(selected)) {
            selected.setTelefono(fonos);
            selected.setEmail(mails);
            selected.update();
            
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Cliente Modificado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar  y seleccionar datos obligatorios Cliente y (Hacienda solo clientes Plantech)"));
        }
    }

    public void load() {
        actual = new Cliente();
        listado = Cliente.getAllClientes();
        model = new ClienteModel(listado);

        actualMail = "";
        mails = new ArrayList<>();

        actualFono = "";
        fonos = new ArrayList<>();

    }

    Boolean controlDatos(Cliente u) {
        Boolean res = true;

        if ((u.getNombre().equals("")) || (u.getRucCi().equals("")) || (u.getPais()==null) ||(u.getProvincia()==null) || (u.getCanton()==null)
                || (u.getDepartamento()==null) || ("".equals(u.getTipoCliente()))) {
            res = false;
 
        }

        return res;
    }

   

    //**************************************************************************
    //MAIL & FONO **************************************************************
    public void addMail() {

        if (!actualMail.equals("")) {
            mails.add(actualMail);
        }

        actualMail = "";
    }

    public void removeMail() {

        int pos = findMail();
        if (pos != -1) {
            mails.remove(pos);
        }
        actualMail = "";
    }

    public void updateMail() {
        int pos = findMail();
        if (pos != -1) {

            if (!actualMail.equals("")) {
                mails.set(pos, actualMail);
            }
        }

        actualMail = "";
    }

    public int findMail() {
        int cont = 0;
        int res = -1;
        while (cont < mails.size()) {
            if (mails.get(cont).equals(selectedMail)) {
                res = cont;
                cont = mails.size();
            }
            cont++;
        }

        return res;

    }

    public void addFono() {
        if (!actualFono.equals("")) {
            fonos.add(actualFono);
        }

        actualFono = "";
    }

    public void removeFono() {

        int pos = findFono();
        if (pos != -1) {
            fonos.remove(pos);
        }

        actualFono = "";
    }

    public void updateFono() {
        int pos = findFono();
        if (pos != -1) {
            if (!actualFono.equals("")) {
                fonos.set(pos, actualFono);
            }
        }

        actualFono = "";
    }

    public int findFono() {
        int cont = 0;
        int res = -1;
        while (cont < fonos.size()) {
            if (fonos.get(cont).equals(selectedFono)) {
                res = cont;
                cont = fonos.size();
            }
            cont++;
        }

        return res;

    }

    //**************************************************************************
   
    //**************************************************************************
    //METODOS PAIS PROVINCIA CANTON*********************************************
    public void onCountryChange() {
        if (actual.getPais() != null && !actual.getPais().equals("")) {
            listadoPr = Provincia.getAllProvinciaByPais(actual.getPais());
        }

    }

    public void onCountryChangeSelected() {
        if (selected.getPais() != null && !selected.getPais().equals("")) {
            listadoPr = Provincia.getAllProvinciaByPais(selected.getPais());
        }

    }

    public void onProvinciaChange() {
        if (actual.getProvincia() != null && !actual.getProvincia().equals("")) {
            listadoCan = Canton.getAllCantonByProvincia(actual.getProvincia());
        }

    }

    public void onProvinciaChangeSelected() {
        if (selected.getProvincia() != null && !selected.getProvincia().equals("")) {
            listadoCan = Canton.getAllCantonByProvincia(selected.getProvincia());
        }

    }

    public void loadSelectedClienteProvinciaCanton() {
        listadoPr = Provincia.getAllProvinciaByPais(actual.getPais());
        listadoCan = Canton.getAllCantonByProvincia(actual.getProvincia());

       

        this.mails = new ArrayList<String>(this.actual.getEmail());
        this.fonos = new ArrayList<String>(this.actual.getTelefono());

        /*listadoPr = Provincia.getAllProvinciaByPais(selected.getPais());
        listadoCan = Canton.getAllCantonByProvincia(selected.getProvincia());

        this.actual = this.selected;

        this.mails = new ArrayList<String>(this.selected.getEmail());
        this.fonos = new ArrayList<String>(this.selected.getTelefono());
        */
        
    }
    //**************************************************************************

}
