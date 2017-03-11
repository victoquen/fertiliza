/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Cliente;
import entities.fertilizacion.Contacto;
import entities.fertilizacion.Cultivo;
import entities.fertilizacion.Edad;
import entities.fertilizacion.EstacionMonitoreo;
import entities.fertilizacion.Hacienda;
import entities.fertilizacion.HaciendaLoteCultivoAux;
import entities.fertilizacion.PeriodoMonitoreoAux;
import entities.fertilizacion.Profundidad;
import entities.fertilizacion.SondaAux;
import entities.fertilizacion.SondaTipo;

import entities.fertilizacion.Variedad;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.ClienteModel;
import models.HaciendaModel;
import org.bson.types.ObjectId;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.ReverseGeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class HaciendaController implements Serializable {

    Hacienda actual;
    Hacienda selected;
    List<Hacienda> listado;
    List<Hacienda> filter;
    HaciendaModel model;

    Contacto actualC;
    Contacto selectedC;
    List<Contacto> listadoC;

    String actualMailG;
    String selectedMailG;
    List<String> mailsG;
    String actualFonoG;
    String selectedFonoG;
    List<String> fonosG;

    String actualMailF;
    String selectedMailF;
    List<String> mailsF;
    String actualFonoF;
    String selectedFonoF;
    List<String> fonosF;

    String actualMailH;
    String selectedMailH;
    List<String> mailsH;
    String actualFonoH;
    String selectedFonoH;
    List<String> fonosH;

    String actualMailA;
    String selectedMailA;
    List<String> mailsA;
    String actualFonoA;
    String selectedFonoA;
    List<String> fonosA;

    String actualMailC;
    String selectedMailC;
    List<String> mailsC;
    String actualFonoC;
    String selectedFonoC;
    List<String> fonosC;

    Cliente actualCl;
    Cliente selectedCl;
    List<Cliente> listadoCl;
    List<Cliente> filterCl;
    ClienteModel modelCl;

    private MapModel geoModel;
    private MapModel revGeoModel;
    private String centerGeoMap = "-2.207019, -79.913864";
    private String centerRevGeoMap = "-2.207019, -79.913864";

    String latitud;
    String longitud;

    List<HaciendaLoteCultivoAux> listadoLotes;
    HaciendaLoteCultivoAux auxLote;
    HaciendaLoteCultivoAux auxLoteSelected;
    List<Cultivo> listadoCultivo;
    List<Variedad> listadoVariedad;
    List<Edad> listadoEdad;

    HaciendaLoteCultivoAux auxLoteSelectedAuxiliar;

    Edad edad;
    Variedad variedad;
    Cultivo cultivo;

    EstacionMonitoreo actualM;
    EstacionMonitoreo selectedM;
    List<EstacionMonitoreo> listadoM;

    String actualSonda;
    String selectedSonda;
    //List<String> sondas;

    List<SondaTipo> listadoSondaTipo;
    String actualSondaTipo;
    String selectedSondaTipo;

    List<SondaAux> listadoSondasAux;
    SondaAux actualSondaAux;
    SondaAux selectedSondaAux;

    int numeroMonitoreos;
    List<PeriodoMonitoreoAux> listadoMonitoreos;

    List<Profundidad> listadoProfundidad;

    int hectareasHacienda;
    int hectareasLote;
    int hectareasAcumulador;

    @PostConstruct
    public void init() {
        geoModel = new DefaultMapModel();
        revGeoModel = new DefaultMapModel();
    }

    public HaciendaController() {
        actual = new Hacienda();
        //listado = Hacienda.getAllHacienda();
        //model = new HaciendaModel(listado);

        actualCl = new Cliente();
        listadoCl = Cliente.getAllClientes();
        modelCl = new ClienteModel(listadoCl);

        actualC = new Contacto();
        listadoC = new ArrayList<>();

        Contacto g = new Contacto("GERENCIA");
        listadoC.add(g);
        Contacto f = new Contacto("FACTURACION");
        listadoC.add(f);
        Contacto o = new Contacto("OFICINA EN HACIENDA");
        listadoC.add(o);
        Contacto a = new Contacto("ADMINISTRADOR DE CAMPO");
        listadoC.add(a);
        Contacto c = new Contacto("CAPATAZ o MAYORDOMO");
        listadoC.add(c);

        actualMailG = "";
        mailsG = new ArrayList<>();
        actualFonoG = "";
        fonosG = new ArrayList<>();

        actualMailF = "";
        mailsF = new ArrayList<>();
        actualFonoF = "";
        fonosF = new ArrayList<>();

        actualMailH = "";
        mailsH = new ArrayList<>();
        actualFonoH = "";
        fonosH = new ArrayList<>();

        actualMailA = "";
        mailsA = new ArrayList<>();
        actualFonoA = "";
        fonosA = new ArrayList<>();

        actualMailC = "";
        mailsC = new ArrayList<>();
        actualFonoC = "";
        fonosC = new ArrayList<>();

        listadoLotes = new ArrayList<>();
        listadoCultivo = Cultivo.getAllCultivos();
        listadoVariedad = new ArrayList<>();
        listadoEdad = new ArrayList<>();

        this.auxLote = new HaciendaLoteCultivoAux();
        this.auxLoteSelected = new HaciendaLoteCultivoAux();

        actualM = new EstacionMonitoreo();
        listadoM = new ArrayList<>();
        //sondas = new ArrayList<>();

        listadoSondaTipo = SondaTipo.getAllSondaTipo();
        actualSonda = "";

        actualSondaAux = new SondaAux();
        listadoSondasAux = new ArrayList<>();

        numeroMonitoreos = 0;
        listadoMonitoreos = new ArrayList<>();

        listadoProfundidad = Profundidad.getAll();

        hectareasHacienda = 0;
        hectareasLote = 0;
        hectareasAcumulador = 0;
    }

    public int getHectareasHacienda() {
        return hectareasHacienda;
    }

    public void setHectareasHacienda(int hectareasHacienda) {
        this.hectareasHacienda = hectareasHacienda;
    }

    public int getHectareasLote() {
        return hectareasLote;
    }

    public void setHectareasLote(int hectareasLote) {
        this.hectareasLote = hectareasLote;
    }

    public List<Profundidad> getListadoProfundidad() {
        return listadoProfundidad;
    }

    public void setListadoProfundidad(List<Profundidad> listadoProfundidad) {
        this.listadoProfundidad = listadoProfundidad;
    }

    public List<PeriodoMonitoreoAux> getListadoMonitoreos() {
        return listadoMonitoreos;
    }

    public void setListadoMonitoreos(List<PeriodoMonitoreoAux> listadoMonitoreos) {
        this.listadoMonitoreos = listadoMonitoreos;
    }

    public int getNumeroMonitoreos() {
        return numeroMonitoreos;
    }

    public void setNumeroMonitoreos(int numeroMonitoreos) {
        this.numeroMonitoreos = numeroMonitoreos;
    }

    public EstacionMonitoreo getActualM() {
        return actualM;
    }

    public void setActualM(EstacionMonitoreo actualM) {
        this.actualM = actualM;
    }

    public EstacionMonitoreo getSelectedM() {
        return selectedM;
    }

    public void setSelectedM(EstacionMonitoreo selectedM) {
        this.selectedM = selectedM;
    }

    public List<EstacionMonitoreo> getListadoM() {
        return listadoM;
    }

    public void setListadoM(List<EstacionMonitoreo> listadoM) {
        this.listadoM = listadoM;
    }

    public String getActualSonda() {
        return actualSonda;
    }

    public void setActualSonda(String actualSonda) {
        this.actualSonda = actualSonda;
    }

    public String getSelectedSonda() {
        return selectedSonda;
    }

    public void setSelectedSonda(String selectedSonda) {
        this.selectedSonda = selectedSonda;
    }

    public List<SondaTipo> getListadoSondaTipo() {
        return listadoSondaTipo;
    }

    public void setListadoSondaTipo(List<SondaTipo> listadoSondaTipo) {
        this.listadoSondaTipo = listadoSondaTipo;
    }

    public String getActualSondaTipo() {
        return actualSondaTipo;
    }

    public void setActualSondaTipo(String actualSondaTipo) {
        this.actualSondaTipo = actualSondaTipo;
    }

    public String getSelectedSondaTipo() {
        return selectedSondaTipo;
    }

    public void setSelectedSondaTipo(String selectedSondaTipo) {
        this.selectedSondaTipo = selectedSondaTipo;
    }

    public List<SondaAux> getListadoSondasAux() {
        return listadoSondasAux;
    }

    public void setListadoSondasAux(List<SondaAux> listadoSondasAux) {
        this.listadoSondasAux = listadoSondasAux;
    }

    public SondaAux getActualSondaAux() {
        return actualSondaAux;
    }

    public void setActualSondaAux(SondaAux actualSondaAux) {
        this.actualSondaAux = actualSondaAux;
    }

    public SondaAux getSelectedSondaAux() {
        return selectedSondaAux;
    }

    public void setSelectedSondaAux(SondaAux selectedSondaAux) {
        this.selectedSondaAux = selectedSondaAux;
    }

    public Edad getEdad() {
        return edad;
    }

    public void setEdad(Edad edad) {
        this.edad = edad;
    }

    public Variedad getVariedad() {
        return variedad;
    }

    public void setVariedad(Variedad variedad) {
        this.variedad = variedad;
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public void setCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
    }

    public HaciendaLoteCultivoAux getAuxLoteSelected() {
        return auxLoteSelected;
    }

    public void setAuxLoteSelected(HaciendaLoteCultivoAux auxLoteSelected) {
        this.auxLoteSelected = auxLoteSelected;
    }

    public HaciendaLoteCultivoAux getAuxLote() {
        return auxLote;
    }

    public void setAuxLote(HaciendaLoteCultivoAux auxLote) {
        this.auxLote = auxLote;
    }

    public List<Cultivo> getListadoCultivo() {
        return listadoCultivo;
    }

    public void setListadoCultivo(List<Cultivo> listadoCultivo) {
        this.listadoCultivo = listadoCultivo;
    }

    public List<Variedad> getListadoVariedad() {
        return listadoVariedad;
    }

    public void setListadoVariedad(List<Variedad> listadoVariedad) {
        this.listadoVariedad = listadoVariedad;
    }

    public List<Edad> getListadoEdad() {
        return listadoEdad;
    }

    public void setListadoEdad(List<Edad> listadoEdad) {
        this.listadoEdad = listadoEdad;
    }

    public List<HaciendaLoteCultivoAux> getListadoLotes() {
        return listadoLotes;
    }

    public void setListadoLotes(List<HaciendaLoteCultivoAux> listadoLotes) {
        this.listadoLotes = listadoLotes;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getActualMailG() {
        return actualMailG;
    }

    public void setActualMailG(String actualMailG) {
        this.actualMailG = actualMailG;
    }

    public String getSelectedMailG() {
        return selectedMailG;
    }

    public void setSelectedMailG(String selectedMailG) {
        this.selectedMailG = selectedMailG;
    }

    public List<String> getMailsG() {
        return mailsG;
    }

    public void setMailsG(List<String> mailsG) {
        this.mailsG = mailsG;
    }

    public String getActualFonoG() {
        return actualFonoG;
    }

    public void setActualFonoG(String actualFonoG) {
        this.actualFonoG = actualFonoG;
    }

    public String getSelectedFonoG() {
        return selectedFonoG;
    }

    public void setSelectedFonoG(String selectedFonoG) {
        this.selectedFonoG = selectedFonoG;
    }

    public List<String> getFonosG() {
        return fonosG;
    }

    public void setFonosG(List<String> fonosG) {
        this.fonosG = fonosG;
    }

    public String getActualMailF() {
        return actualMailF;
    }

    public void setActualMailF(String actualMailF) {
        this.actualMailF = actualMailF;
    }

    public String getSelectedMailF() {
        return selectedMailF;
    }

    public void setSelectedMailF(String selectedMailF) {
        this.selectedMailF = selectedMailF;
    }

    public List<String> getMailsF() {
        return mailsF;
    }

    public void setMailsF(List<String> mailsF) {
        this.mailsF = mailsF;
    }

    public String getActualFonoF() {
        return actualFonoF;
    }

    public void setActualFonoF(String actualFonoF) {
        this.actualFonoF = actualFonoF;
    }

    public String getSelectedFonoF() {
        return selectedFonoF;
    }

    public void setSelectedFonoF(String selectedFonoF) {
        this.selectedFonoF = selectedFonoF;
    }

    public List<String> getFonosF() {
        return fonosF;
    }

    public void setFonosF(List<String> fonosF) {
        this.fonosF = fonosF;
    }

    public String getActualMailH() {
        return actualMailH;
    }

    public void setActualMailH(String actualMailH) {
        this.actualMailH = actualMailH;
    }

    public String getSelectedMailH() {
        return selectedMailH;
    }

    public void setSelectedMailH(String selectedMailH) {
        this.selectedMailH = selectedMailH;
    }

    public List<String> getMailsH() {
        return mailsH;
    }

    public void setMailsH(List<String> mailsH) {
        this.mailsH = mailsH;
    }

    public String getActualFonoH() {
        return actualFonoH;
    }

    public void setActualFonoH(String actualFonoH) {
        this.actualFonoH = actualFonoH;
    }

    public String getSelectedFonoH() {
        return selectedFonoH;
    }

    public void setSelectedFonoH(String selectedFonoH) {
        this.selectedFonoH = selectedFonoH;
    }

    public List<String> getFonosH() {
        return fonosH;
    }

    public void setFonosH(List<String> fonosH) {
        this.fonosH = fonosH;
    }

    public String getActualMailA() {
        return actualMailA;
    }

    public void setActualMailA(String actualMailA) {
        this.actualMailA = actualMailA;
    }

    public String getSelectedMailA() {
        return selectedMailA;
    }

    public void setSelectedMailA(String selectedMailA) {
        this.selectedMailA = selectedMailA;
    }

    public List<String> getMailsA() {
        return mailsA;
    }

    public void setMailsA(List<String> mailsA) {
        this.mailsA = mailsA;
    }

    public String getActualFonoA() {
        return actualFonoA;
    }

    public void setActualFonoA(String actualFonoA) {
        this.actualFonoA = actualFonoA;
    }

    public String getSelectedFonoA() {
        return selectedFonoA;
    }

    public void setSelectedFonoA(String selectedFonoA) {
        this.selectedFonoA = selectedFonoA;
    }

    public List<String> getFonosA() {
        return fonosA;
    }

    public void setFonosA(List<String> fonosA) {
        this.fonosA = fonosA;
    }

    public String getActualMailC() {
        return actualMailC;
    }

    public void setActualMailC(String actualMailC) {
        this.actualMailC = actualMailC;
    }

    public String getSelectedMailC() {
        return selectedMailC;
    }

    public void setSelectedMailC(String selectedMailC) {
        this.selectedMailC = selectedMailC;
    }

    public List<String> getMailsC() {
        return mailsC;
    }

    public void setMailsC(List<String> mailsC) {
        this.mailsC = mailsC;
    }

    public String getActualFonoC() {
        return actualFonoC;
    }

    public void setActualFonoC(String actualFonoC) {
        this.actualFonoC = actualFonoC;
    }

    public String getSelectedFonoC() {
        return selectedFonoC;
    }

    public void setSelectedFonoC(String selectedFonoC) {
        this.selectedFonoC = selectedFonoC;
    }

    public List<String> getFonosC() {
        return fonosC;
    }

    public void setFonosC(List<String> fonosC) {
        this.fonosC = fonosC;
    }

    public Contacto getActualC() {
        return actualC;
    }

    public void setActualC(Contacto actualC) {
        this.actualC = actualC;
    }

    public Contacto getSelectedC() {
        return selectedC;
    }

    public void setSelectedC(Contacto selectedC) {
        this.selectedC = selectedC;
    }

    public List<Contacto> getListadoC() {
        return listadoC;
    }

    public void setListadoC(List<Contacto> listadoC) {
        this.listadoC = listadoC;
    }

    public Cliente getActualCl() {
        return actualCl;
    }

    public void setActualCl(Cliente actualCl) {
        this.actualCl = actualCl;
    }

    public Cliente getSelectedCl() {
        return selectedCl;
    }

    public void setSelectedCl(Cliente selectedCl) {
        this.selectedCl = selectedCl;
    }

    public List<Cliente> getListadoCl() {
        return listadoCl;
    }

    public void setListadoCl(List<Cliente> listadoCl) {
        this.listadoCl = listadoCl;
    }

    public List<Cliente> getFilterCl() {
        return filterCl;
    }

    public void setFilterCl(List<Cliente> filterCl) {
        this.filterCl = filterCl;
    }

    public ClienteModel getModelCl() {
        return modelCl;
    }

    public void setModelCl(ClienteModel modelCl) {
        this.modelCl = modelCl;
    }

    public Hacienda getActual() {
        return actual;
    }

    public void setActual(Hacienda actual) {
        this.actual = actual;
    }

    public Hacienda getSelected() {
        return selected;
    }

    public void setSelected(Hacienda selected) {
        this.selected = selected;
    }

    public List<Hacienda> getListado() {
        return listado;
    }

    public void setListado(List<Hacienda> listado) {
        this.listado = listado;
    }

    public List<Hacienda> getFilter() {
        return filter;
    }

    public void setFilter(List<Hacienda> filter) {
        this.filter = filter;
    }

    public HaciendaModel getModel() {
        return model;
    }

    public void setModel(HaciendaModel model) {
        this.model = model;
    }

    public ObjectId save() {
        ObjectId res = null;
        if (controlDatos(actual)) {
            listadoC.get(0).setTelefono(fonosG);
            listadoC.get(0).setEmail(mailsG);
            listadoC.get(1).setTelefono(fonosF);
            listadoC.get(1).setEmail(mailsF);
            listadoC.get(2).setTelefono(fonosH);
            listadoC.get(2).setEmail(mailsH);
            listadoC.get(3).setTelefono(fonosA);
            listadoC.get(3).setEmail(mailsA);
            listadoC.get(4).setTelefono(fonosC);
            listadoC.get(4).setEmail(mailsC);

            actual.setListadoLotes(listadoLotes);

            actual.setContactos(listadoC);
            res = actual.save();
            //load();

        }
        return res;
    }

    public void update() {
        if (controlDatos(selected)) {
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Hacienda Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Hacienda();
        listado = Hacienda.getAllHacienda();
        model = new HaciendaModel(listado);

        /*listadoLotes = new ArrayList<>();
        haciendaLoteCultivo = new HaciendaLoteCultivoAux();*/
    }

    Boolean controlDatos(Hacienda u) {
        Boolean res = true;

        if ((u.getNombre().equals("")) || listadoLotes.isEmpty()) {
            res = false;
        }
        return res;
    }

    //MAPA**********************************************************************
    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();

        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();

            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }
        }
    }

    public void onReverseGeocode(ReverseGeocodeEvent event) {
        List<String> addresses = event.getAddresses();
        LatLng coord = event.getLatlng();

        if (addresses != null && !addresses.isEmpty()) {
            centerRevGeoMap = coord.getLat() + "," + coord.getLng();
            revGeoModel.addOverlay(new Marker(coord, addresses.get(0)));
        }
    }

    public MapModel getGeoModel() {
        return geoModel;
    }

    public MapModel getRevGeoModel() {
        return revGeoModel;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public String getCenterRevGeoMap() {
        return centerRevGeoMap;
    }

    //**************************************************************************
    //mails y telefonos de contacto*********************************************
    public void addMailG() {
        if (!actualMailG.equals("")) {
            mailsG.add(actualMailG);
        }
        actualMailG = "";
    }

    public void removeMailG() {
        int pos = findMailG();
        if (pos != -1) {
            if (!actualMailG.equals("")) {
                mailsG.remove(pos);
            }
        }
        actualMailG = "";
    }

    public void updateMailG() {
        int pos = findMailG();
        if (pos != -1) {
            if (!actualMailG.equals("")) {
                mailsG.set(pos, actualMailG);
            }
        }
        actualMailG = "";
    }

    public int findMailG() {
        int cont = 0;
        int res = -1;
        while (cont < mailsG.size()) {
            if (mailsG.get(cont).equals(selectedMailG)) {
                res = cont;
                cont = mailsG.size();
            }
            cont++;
        }
        return res;
    }

    public void addFonoG() {
        if (!actualFonoG.equals("")) {
            fonosG.add(actualFonoG);
        }
        actualFonoG = "";
    }

    public void removeFonoG() {
        int pos = findFonoG();
        if (pos != -1) {
            if (!actualFonoG.equals("")) {
                fonosG.remove(pos);
            }
        }
        actualFonoG = "";
    }

    public void updateFonoG() {
        int pos = findFonoG();
        if (pos != -1) {
            if (!actualFonoG.equals("")) {
                fonosG.set(pos, actualFonoG);
            }
        }
        actualFonoG = "";
    }

    public int findFonoG() {
        int cont = 0;
        int res = -1;
        while (cont < fonosG.size()) {
            if (fonosG.get(cont).equals(selectedFonoG)) {
                res = cont;
                cont = fonosG.size();
            }
            cont++;
        }
        return res;
    }

    public void addMailF() {
        if (!actualMailF.equals("")) {
            mailsF.add(actualMailF);
        }
        actualMailF = "";
    }

    public void removeMailF() {
        int pos = findMailF();
        if (pos != -1) {
            if (!actualMailF.equals("")) {
                mailsF.remove(pos);
            }
        }
        actualMailF = "";
    }

    public void updateMailF() {
        int pos = findMailF();
        if (pos != -1) {
            if (!actualMailF.equals("")) {
                mailsF.set(pos, actualMailF);
            }
        }
        actualMailF = "";
    }

    public int findMailF() {
        int cont = 0;
        int res = -1;
        while (cont < mailsF.size()) {
            if (mailsF.get(cont).equals(selectedMailF)) {
                res = cont;
                cont = mailsF.size();
            }
            cont++;
        }
        return res;
    }

    public void addFonoF() {
        if (!actualFonoF.equals("")) {
            fonosF.add(actualFonoF);
        }
        actualFonoF = "";
    }

    public void removeFonoF() {
        int pos = findFonoF();
        if (pos != -1) {
            if (!actualFonoF.equals("")) {
                fonosF.remove(pos);
            }
        }
        actualFonoF = "";
    }

    public void updateFonoF() {
        int pos = findFonoF();
        if (pos != -1) {
            if (!actualFonoF.equals("")) {
                fonosF.set(pos, actualFonoF);
            }
        }
        actualFonoF = "";
    }

    public int findFonoF() {
        int cont = 0;
        int res = -1;
        while (cont < fonosF.size()) {
            if (fonosF.get(cont).equals(selectedFonoF)) {
                res = cont;
                cont = fonosF.size();
            }
            cont++;
        }
        return res;
    }

    public void addMailH() {
        if (!actualMailH.equals("")) {
            mailsH.add(actualMailH);
        }
        actualMailH = "";
    }

    public void removeMailH() {
        int pos = findMailH();
        if (pos != -1) {
            if (!actualMailH.equals("")) {
                mailsH.remove(pos);
            }
        }
        actualMailH = "";
    }

    public void updateMailH() {
        int pos = findMailH();
        if (pos != -1) {
            if (!actualMailH.equals("")) {
                mailsH.set(pos, actualMailH);
            }
        }
        actualMailH = "";
    }

    public int findMailH() {
        int cont = 0;
        int res = -1;
        while (cont < mailsH.size()) {
            if (mailsH.get(cont).equals(selectedMailH)) {
                res = cont;
                cont = mailsH.size();
            }
            cont++;
        }
        return res;
    }

    public void addFonoH() {
        if (!actualFonoH.equals("")) {
            fonosH.add(actualFonoH);
        }
        actualFonoH = "";
    }

    public void removeFonoH() {
        int pos = findFonoH();
        if (pos != -1) {
            if (!actualFonoH.equals("")) {
                fonosH.remove(pos);
            }
        }
        actualFonoH = "";
    }

    public void updateFonoH() {
        int pos = findFonoH();
        if (pos != -1) {
            if (!actualFonoH.equals("")) {
                fonosH.set(pos, actualFonoH);
            }
        }
        actualFonoH = "";
    }

    public int findFonoH() {
        int cont = 0;
        int res = -1;
        while (cont < fonosH.size()) {
            if (fonosH.get(cont).equals(selectedFonoH)) {
                res = cont;
                cont = fonosH.size();
            }
            cont++;
        }
        return res;
    }

    public void addMailA() {
        if (!actualMailA.equals("")) {
            mailsA.add(actualMailA);
        }
        actualMailA = "";
    }

    public void removeMailA() {
        int pos = findMailA();
        if (pos != -1) {
            if (!actualMailA.equals("")) {
                mailsA.remove(pos);
            }
        }
        actualMailA = "";
    }

    public void updateMailA() {
        int pos = findMailA();
        if (pos != -1) {
            if (!actualMailA.equals("")) {
                mailsA.set(pos, actualMailA);
            }
        }
        actualMailA = "";
    }

    public int findMailA() {
        int cont = 0;
        int res = -1;
        while (cont < mailsA.size()) {
            if (mailsA.get(cont).equals(selectedMailA)) {
                res = cont;
                cont = mailsA.size();
            }
            cont++;
        }
        return res;
    }

    public void addFonoA() {
        if (!actualFonoA.equals("")) {
            fonosA.add(actualFonoA);
        }
        actualFonoA = "";
    }

    public void removeFonoA() {
        int pos = findFonoA();
        if (pos != -1) {
            if (!actualFonoA.equals("")) {
                fonosA.remove(pos);
            }
        }
        actualFonoA = "";
    }

    public void updateFonoA() {
        int pos = findFonoA();
        if (pos != -1) {
            if (!actualFonoA.equals("")) {
                fonosA.set(pos, actualFonoA);
            }
        }
        actualFonoA = "";
    }

    public int findFonoA() {
        int cont = 0;
        int res = -1;
        while (cont < fonosA.size()) {
            if (fonosA.get(cont).equals(selectedFonoA)) {
                res = cont;
                cont = fonosA.size();
            }
            cont++;
        }
        return res;
    }

    public void addMailC() {
        if (!actualMailC.equals("")) {
            mailsC.add(actualMailC);
        }
        actualMailC = "";
    }

    public void removeMailC() {
        int pos = findMailC();
        if (pos != -1) {
            if (!actualMailC.equals("")) {
                mailsC.remove(pos);
            }
        }
        actualMailC = "";
    }

    public void updateMailC() {
        int pos = findMailC();
        if (pos != -1) {
            if (!actualMailC.equals("")) {
                mailsC.set(pos, actualMailC);
            }
        }
        actualMailC = "";
    }

    public int findMailC() {
        int cont = 0;
        int res = -1;
        while (cont < mailsC.size()) {
            if (mailsC.get(cont).equals(selectedMailC)) {
                res = cont;
                cont = mailsC.size();
            }
            cont++;
        }
        return res;
    }

    public void addFonoC() {
        if (!actualFonoC.equals("")) {
            fonosC.add(actualFonoC);
        }
        actualFonoC = "";
    }

    public void removeFonoC() {
        int pos = findFonoC();
        if (pos != -1) {
            if (!actualFonoC.equals("")) {
                fonosC.remove(pos);
            }
        }
        actualFonoC = "";
    }

    public void updateFonoC() {
        int pos = findFonoC();
        if (pos != -1) {
            if (!actualFonoC.equals("")) {
                fonosC.set(pos, actualFonoC);
            }
        }
        actualFonoC = "";
    }

    public int findFonoC() {
        int cont = 0;
        int res = -1;
        while (cont < fonosC.size()) {
            if (fonosC.get(cont).equals(selectedFonoC)) {
                res = cont;
                cont = fonosC.size();
            }
            cont++;
        }
        return res;
    }

    //**************************************************************************
    //HACIENDA control datos de lotes cultivo aux*********************************************************
    public void onCultivoChange() {
        if (this.auxLote.getCultivo() != null) {
            listadoVariedad = Variedad.getAllVariedadByCultivo(this.auxLote.getCultivo());
            listadoEdad = new ArrayList<>();
        } else {
            listadoVariedad = new ArrayList<>();
        }
        onVariedadChange();
    }

    public void onVariedadChange() {
        if (this.auxLote.getVariedad() != null) {
            listadoEdad = Edad.getAllEdadByVariedad(this.auxLote.getVariedad());
        } else {
            listadoEdad = new ArrayList<>();
        }

    }

    public void onVariedadChangeSelected() {
        if (this.auxLoteSelected.getVariedad() != null) {
            listadoEdad = Edad.getAllEdadByVariedad(this.auxLoteSelected.getVariedad());
            this.auxLoteSelected.setLeyendaVariedad(Variedad.getVariedadById(auxLoteSelected.getVariedad()).getNombre());

        } else {
            listadoEdad = new ArrayList<>();
        }
    }

    public void onCultivoChangeSelected() {
        if (this.auxLoteSelected.getCultivo() != null) {
            listadoVariedad = Variedad.getAllVariedadByCultivo(this.auxLoteSelected.getCultivo());
            //listadoEdad = Edad.getAllEdadByVariedad(this.auxLoteSelected.getVariedad());
            //listadoEdad = Edad.getAllEdadByCultivo(this.auxLoteSelected.getCultivo());
            this.auxLoteSelected.setLeyendaCultivo(Cultivo.getCultivoById(auxLoteSelected.getCultivo()).getNombre());

            listadoEdad = new ArrayList<>();

        } else {
            listadoVariedad = new ArrayList<>();
        }
        this.auxLoteSelected.setEdad(null);
        this.auxLoteSelected.setVariedad(null);
    }

    public void onVariedadChangeSelectedIni() {
        if (this.auxLoteSelected.getVariedad() != null) {
            listadoEdad = Edad.getAllEdadByVariedad(this.auxLoteSelected.getVariedad());
            this.auxLoteSelected.setLeyendaVariedad(Variedad.getVariedadById(auxLoteSelected.getVariedad()).getNombre());

        } else {
            listadoEdad = new ArrayList<>();
        }
    }

    public void onCultivoChangeSelectedIni() {
        this.auxLoteSelectedAuxiliar = new HaciendaLoteCultivoAux();

        if (this.auxLoteSelected.getCultivo() != null) {
            listadoVariedad = Variedad.getAllVariedadByCultivo(this.auxLoteSelected.getCultivo());
            //listadoEdad = Edad.getAllEdadByVariedad(this.auxLoteSelected.getVariedad());
            //listadoEdad = Edad.getAllEdadByCultivo(this.auxLoteSelected.getCultivo());
            this.auxLoteSelected.setLeyendaCultivo(Cultivo.getCultivoById(auxLoteSelected.getCultivo()).getNombre());

            listadoEdad = new ArrayList<>();
        } else {
            listadoVariedad = new ArrayList<>();
        }
        onVariedadChangeSelectedIni();
    }

    Boolean controlDatosLotesCultivoAux(HaciendaLoteCultivoAux u) {
        Boolean res = true;
        if ((u.getIdLotes().equals(""))
                || (u.getCultivo() == null) || (u.getVariedad() == null)
                || (u.getEdad() == null) || (u.getCodigoMayorEstacion().equals(""))) {
            res = false;
        }
        return res;
    }

    public void addLotesCultivoAux() {

        if (controlDatosLotesCultivoAux(this.auxLote)) {

            this.auxLote.setLeyendaCultivo(Cultivo.getCultivoById(auxLote.getCultivo()).getNombre());
            this.auxLote.setLeyendaVariedad(Variedad.getVariedadById(auxLote.getVariedad()).getNombre());
            this.auxLote.setLeyendaEdad(Edad.getEdadById(auxLote.getEdad()).getNombre());

            this.auxLote.setListaEstacionMonitoreo(listadoM);
            this.auxLote.setListaPeriodosMonitoreos(listadoMonitoreos);

            this.listadoLotes.add(this.auxLote);
            loadLotesCultivoAux();
        }

    }

    public void loadLotesCultivoAux() {
        this.auxLote = new HaciendaLoteCultivoAux();
        this.listadoCultivo = Cultivo.getAllCultivos();
        this.listadoEdad = new ArrayList<>();
        this.listadoVariedad = new ArrayList<>();

        actualM = new EstacionMonitoreo();
        listadoM = new ArrayList<>();

        this.listadoMonitoreos = new ArrayList<>();

        this.numeroMonitoreos = 0;
        this.auxLoteSelectedAuxiliar = new HaciendaLoteCultivoAux();
        
        hectareasLote = 0;
    }

    public void deleteLotesCultivoAux() {

        Iterator<HaciendaLoteCultivoAux> iter = listadoLotes.iterator();
        while (iter.hasNext()) {
            HaciendaLoteCultivoAux o = iter.next();
            if (o.getIdLotes().equals(auxLoteSelected.getIdLotes())) {
                iter.remove();

            }
        }
        this.auxLoteSelectedAuxiliar = new HaciendaLoteCultivoAux();
    }

    public void updateLotesCultivoAux() {
        if (controlDatosLotesCultivoAux(this.auxLoteSelected)) {

            this.auxLoteSelected.setLeyendaCultivo(Cultivo.getCultivoById(auxLoteSelected.getCultivo()).getNombre());
            this.auxLoteSelected.setLeyendaVariedad(Variedad.getVariedadById(auxLoteSelected.getVariedad()).getNombre());
            this.auxLoteSelected.setLeyendaEdad(Edad.getEdadById(auxLoteSelected.getEdad()).getNombre());

            this.auxLoteSelected.setListaEstacionMonitoreo(listadoM);
            this.auxLoteSelected.setListaPeriodosMonitoreos(listadoMonitoreos);
            loadLotesCultivoAux();
        } else {
            if (this.auxLoteSelected.getVariedad() == null) {
                this.auxLoteSelected.setLeyendaVariedad("");
            }
            if (this.auxLoteSelected.getEdad() == null) {
                this.auxLoteSelected.setLeyendaEdad("");
            }
        }
    }

    public void cerrarLotesCultivoAux() {
        if (this.auxLoteSelected.getVariedad() == null) {
            this.auxLoteSelected.setLeyendaVariedad("");
        }
        if (this.auxLoteSelected.getEdad() == null) {
            this.auxLoteSelected.setLeyendaEdad("");
        }
    }

    //*********************************************************************************************************************
    //metodos CULTIVO EDAD VARIEDAD****************************************************************************************
    public void newEdad() {
        this.edad = new Edad();
        this.edad.setVariedad(auxLote.getVariedad());
        this.edad.setLeyendaVariedad(Variedad.getVariedadById(auxLote.getVariedad()).getNombre());
        this.edad.setLeyendaCultivo(Variedad.getVariedadById(auxLote.getVariedad()).getLeyendaCultivo());

    }

    public void newVariedad() {
        this.variedad = new Variedad();
        this.variedad.setCultivo(auxLote.getCultivo());
        this.variedad.setLeyendaCultivo(Cultivo.getCultivoById(auxLote.getCultivo()).getNombre());
    }

    public void newCultivo() {
        this.cultivo = new Cultivo();
    }

    boolean controlEdad(Edad e) {
        boolean res = true;

        if (e.getNombre().equals("") || e.getVariedad() == null) {
            res = false;
        }

        return res;
    }

    boolean controlVariedad(Variedad e) {
        boolean res = true;

        if (e.getNombre().equals("") || e.getCultivo() == null) {
            res = false;
        }

        return res;
    }

    boolean controlCultivo(Cultivo e) {
        boolean res = true;

        if (e.getNombre().equals("")) {
            res = false;
        }

        return res;
    }

    public void saveNewEdad() {
        if (controlEdad(this.edad)) {
            this.auxLote.setEdad(this.edad.save());
            this.listadoEdad = Edad.getAllEdadByVariedad(this.auxLote.getVariedad());
        }
    }

    public void saveNewVariedad() {
        if (controlVariedad(this.variedad)) {
            this.auxLote.setVariedad(this.variedad.save());
            this.listadoVariedad = Variedad.getAllVariedadByCultivo(this.auxLote.getCultivo());
            onVariedadChange();
        }
    }

    public void saveNewCultivo() {
        if (controlCultivo(this.cultivo)) {
            this.auxLote.setCultivo(this.cultivo.save());
            this.listadoCultivo = Cultivo.getAllCultivos();
            onCultivoChange();
        }
    }
    //********************************************************************************************************************

    //METODOS PARA ESTACIONES DE MONITOREO Y SONDAS ***********************************************************************
    public void addEstacionMonitoreo() {
        if (controlDatosEstacionMonitoreo(actualM)) {
            //actualM.setSondas(sondas);
            actualM.setListaSondas(listadoSondasAux);

            listadoM.add(actualM);
            loadMonitoreo();
        }
    }

    public void updateEstacionMonitoreo() {
        int pos = findEstacionMonitoreo();
        if (pos != -1) {
            if (!actualM.equals("")) {
                listadoM.set(pos, actualM);
            }
        }
        loadMonitoreo();
    }

    public void removeEstacionMonitoreo() {
        int pos = findEstacionMonitoreo();
        if (pos != -1) {

            listadoM.remove(pos);

        }
        actualSondaAux = new SondaAux();
    }

    public int findEstacionMonitoreo() {
        int cont = 0;
        int res = -1;
        while (cont < listadoM.size()) {
            if (listadoM.get(cont).getCodigo().equals(selectedM.getCodigo())) {
                res = cont;
                cont = listadoM.size();
            }
            cont++;
        }
        return res;
    }

    public Boolean controlDatosEstacionMonitoreo(EstacionMonitoreo obj) {
        Boolean res = true;
        if (obj.getCodigo().equals("")) {
            res = false;
        }
        return res;
    }

    public void loadMonitoreo() {
        actualM = new EstacionMonitoreo();

        listadoSondasAux = new ArrayList<>();

    }

    //**************************************************************************
    //METODOS SONDA AUX CON TIPO SONDA******************************************
    public void addSondaAux() {
        if (controlSondaAux(actualSondaAux)) {
            actualSondaAux.setLeyendaTipoSonda(SondaTipo.getSondaTipoById(actualSondaAux.getTipoSonda()).getNombre());
            actualSondaAux.setLeyendaProfundidad(Profundidad.getById(actualSondaAux.getProfundidad()).getNombre());
            listadoSondasAux.add(actualSondaAux);
        }
        actualSondaAux = new SondaAux();
    }

    public void removeSondaAux() {
        int pos = findSondaAux();
        if (pos != -1) {
            listadoSondasAux.remove(pos);
        }
        actualSondaAux = new SondaAux();
    }

    public void updateSondaAux() {
        int pos = findSondaAux();
        if (pos != -1) {
            if (controlSondaAux(actualSondaAux)) {
                actualSondaAux.setLeyendaTipoSonda(SondaTipo.getSondaTipoById(actualSondaAux.getTipoSonda()).getNombre());
                actualSondaAux.setLeyendaProfundidad(Profundidad.getById(actualSondaAux.getProfundidad()).getNombre());
                listadoSondasAux.set(pos, actualSondaAux);
            }
        }
        actualSondaAux = new SondaAux();
    }

    public int findSondaAux() {
        int cont = 0;
        int res = -1;
        while (cont < listadoSondasAux.size()) {
            if (listadoSondasAux.get(cont).getDescripcion().equals(selectedSondaAux.getDescripcion()) && listadoSondasAux.get(cont).getTipoSonda().equals(selectedSondaAux.getTipoSonda())) {
                res = cont;
                cont = listadoSondasAux.size();
            }
            cont++;
        }
        return res;
    }

    public Boolean controlSondaAux(SondaAux sond) {
        Boolean res = true;
        if (sond.getDescripcion().equals("") || sond.getTipoSonda().equals("") || sond.getProfundidad() == null) {
            res = false;
        }
        return res;
    }
    //**************************************************************************

    //METODOS PARA LA CREACION DE PERIODO DE MONITOREOS*************************
    public void crearMonitoreos() {
        this.listadoMonitoreos = new ArrayList<>();
        for (int i = 0; i < this.numeroMonitoreos; i++) {
            PeriodoMonitoreoAux obj = new PeriodoMonitoreoAux();
            if (i == 0) {
                obj.setFechaMonitoreo(new Date());
            }
            obj.setNumeroMonitoreo(i + 1);

            this.listadoMonitoreos.add(obj);
        }
    }

    public void onCellEditMonitoreo(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        /*if (newValue != null && !newValue.equals(oldValue)) {
            int contMonit = this.listadoMonitoreos.size();
            for (int i = 0; i < contMonit; i++) {
                if (i != 0) {
                    Date fechaCalculo = sumarRestarDiasFecha(listadoMonitoreos.get(i - 1).getFechaMonitoreo(), listadoMonitoreos.get(i).getNumeroDias()) ;
                    listadoMonitoreos.get(i).setFechaMonitoreo(fechaCalculo);
                }

            }

        }*/
    }

    public Date sumarRestarDiasFecha(Date fecha, int dias) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos	

    }

    public void loadProgramaMonitoreo() {
        this.listadoMonitoreos = this.auxLote.getListaPeriodosMonitoreos();
        this.numeroMonitoreos = this.auxLote.getListaPeriodosMonitoreos().size();
    }
    //**************************************************************************

    public void onChangeHectareas() {
        if (hectareasHacienda > 0) {
            hectareasAcumulador = sumaHectareasLotes();
            if (hectareasLote + hectareasAcumulador <= hectareasHacienda) {
               
                auxLote.setHectareas(new BigDecimal(hectareasLote));
            } else {
                hectareasLote = 0;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Hectareas Lote supera al de Hacienda"));
            }

        } else {
            hectareasLote = 0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar hectareas de Hacienda"));
        }
    }

    int sumaHectareasLotes() {
        int res = 0;
        for (int i = 0; i < listadoLotes.size(); i++) {
            res += listadoLotes.get(i).getHectareas().intValue();
        }

        return res;
    }

}
