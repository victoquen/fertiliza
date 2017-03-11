/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Cultivo;
import entities.fertilizacion.Edad;
import entities.fertilizacion.EstacionMonitoreo;
import entities.fertilizacion.Hacienda;
import entities.fertilizacion.Lote;
import entities.fertilizacion.SondaAux;
import entities.fertilizacion.SondaTipo;
import entities.fertilizacion.Variedad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.CultivoModel;
import models.EdadModel;
import models.HaciendaModel;
import models.LoteModel;
import models.VariedadModel;

/**
 *
 * @author VICTOR OQUENDO
 */
//@Named
//@ViewScoped
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

    Cultivo actualC;
    Cultivo selectedC;
    List<Cultivo> listadoC;
    List<Cultivo> filterC;
    CultivoModel modelC;

    Variedad actualV;
    Variedad selectedV;
    List<Variedad> listadoV;
    List<Variedad> filterV;
    VariedadModel modelV;

    Edad actualE;
    Edad selectedE;
    List<Edad> listadoE;
    List<Edad> filterE;
    EdadModel modelE;

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

    public LoteController() {
        this.actual = new Lote();
        this.listado = Lote.getAllLotes();
        this.model = new LoteModel(listado);

        actualH = new Hacienda();
        listadoH = Hacienda.getAllHacienda();
        modelH = new HaciendaModel(listadoH);

        actualC = new Cultivo();
        listadoC = Cultivo.getAllCultivos();
        modelC = new CultivoModel(listadoC);

        actualV = new Variedad();
        listadoV = Variedad.getAllVariedad();
        modelV = new VariedadModel(listadoV);

        actualE = new Edad();
        listadoE = Edad.getAllEdad();
        modelE = new EdadModel(listadoE);

        actualM = new EstacionMonitoreo();
        listadoM = new ArrayList<>();
        //sondas = new ArrayList<>();
        
        listadoSondaTipo = SondaTipo.getAllSondaTipo();
        actualSonda = "";
        
        actualSondaAux= new SondaAux();
        listadoSondasAux = new ArrayList<>();

    }

    public LoteController(String op){
        this.actual = new Lote();
        this.listado = Lote.getAllLotes();
        this.model = new LoteModel(listado);
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

    public Cultivo getActualC() {
        return actualC;
    }

    public void setActualC(Cultivo actualC) {
        this.actualC = actualC;
    }

    public Cultivo getSelectedC() {
        return selectedC;
    }

    public void setSelectedC(Cultivo selectedC) {
        this.selectedC = selectedC;
    }

    public List<Cultivo> getListadoC() {
        return listadoC;
    }

    public void setListadoC(List<Cultivo> listadoC) {
        this.listadoC = listadoC;
    }

    public List<Cultivo> getFilterC() {
        return filterC;
    }

    public void setFilterC(List<Cultivo> filterC) {
        this.filterC = filterC;
    }

    public CultivoModel getModelC() {
        return modelC;
    }

    public void setModelC(CultivoModel modelC) {
        this.modelC = modelC;
    }

    public Variedad getActualV() {
        return actualV;
    }

    public void setActualV(Variedad actualV) {
        this.actualV = actualV;
    }

    public Variedad getSelectedV() {
        return selectedV;
    }

    public void setSelectedV(Variedad selectedV) {
        this.selectedV = selectedV;
    }

    public List<Variedad> getListadoV() {
        return listadoV;
    }

    public void setListadoV(List<Variedad> listadoV) {
        this.listadoV = listadoV;
    }

    public List<Variedad> getFilterV() {
        return filterV;
    }

    public void setFilterV(List<Variedad> filterV) {
        this.filterV = filterV;
    }

    public VariedadModel getModelV() {
        return modelV;
    }

    public void setModelV(VariedadModel modelV) {
        this.modelV = modelV;
    }

    public Edad getActualE() {
        return actualE;
    }

    public void setActualE(Edad actualE) {
        this.actualE = actualE;
    }

    public Edad getSelectedE() {
        return selectedE;
    }

    public void setSelectedE(Edad selectedE) {
        this.selectedE = selectedE;
    }

    public List<Edad> getListadoE() {
        return listadoE;
    }

    public void setListadoE(List<Edad> listadoE) {
        this.listadoE = listadoE;
    }

    public List<Edad> getFilterE() {
        return filterE;
    }

    public void setFilterE(List<Edad> filterE) {
        this.filterE = filterE;
    }

    public EdadModel getModelE() {
        return modelE;
    }

    public void setModelE(EdadModel modelE) {
        this.modelE = modelE;
    }

    public void save() {

        if (controlDatos(actual)) {

            actual.setEstacion(listadoM);
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

    //CULTIVO*******************************************************************
    public void saveC() {

       
        if (controlDatosC(actualC)) {

            actual.setIdCultivo(actualC.save());
            loadC();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Cultivo Ingresado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void updateC() {
        if (controlDatosC(selectedC)) {
            selectedC.update();
            loadC();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Cultivo Modificado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void loadC() {
        actualC = new Cultivo();
        listadoC = Cultivo.getAllCultivos();
        modelC = new CultivoModel(listadoC);
    }

    Boolean controlDatosC(Cultivo u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }

    //**************************************************************************
    //VARIEDAD******************************************************************
    public void saveV() {

        if (controlDatosV(actualV)) {

            actual.setIdVariedad(actualV.save());
            loadV();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Variedad Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void updateV() {
        if (controlDatosV(selectedV)) {
            selectedV.update();
            loadV();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Variedad Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void loadV() {
        actualV = new Variedad();
        listadoV = Variedad.getAllVariedad();
        modelV = new VariedadModel(listadoV);
    }

    Boolean controlDatosV(Variedad u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }
    //**************************************************************************

    //EDAD**********************************************************************
    public void saveE() {

        if (controlDatosE(actualE)) {

            actual.setIdEdad(actualE.save());
            loadE();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Edad Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void updateE() {
        if (controlDatosE(selectedE)) {
            selectedE.update();
            loadE();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Edad Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void loadE() {
        actualE = new Edad();
        listadoE = Edad.getAllEdad();
        modelE = new EdadModel(listadoE);
    }

    Boolean controlDatosE(Edad u) {
        Boolean res = true;

        if ((u.getNombre().equals(""))) {
            res = false;
        }
        return res;
    }
    //**************************************************************************

    //METODOS PARA ESTACIONES DE MONITOREO Y SONDAS ****************************
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
        
        //sondas = new ArrayList<>();
        listadoSondasAux = new ArrayList<>();
    }

    
    
    /*public void addSonda() {
        if (!actualSonda.equals("")) {
            sondas.add(actualSonda);
        }
        actualSonda = "";
    }

    public void removeSonda() {
        int pos = findSonda();
        if (pos != -1) {

            sondas.remove(pos);

        }
        actualSonda = "";
    }

    public void updateSonda() {
        int pos = findSonda();
        if (pos != -1) {
            if (!actualSonda.equals("")) {
                sondas.set(pos, actualSonda);
            }
        }
        actualSonda = "";
    }

    public int findSonda() {
        int cont = 0;
        int res = -1;
        while (cont < sondas.size()) {
            if (sondas.get(cont).equals(selectedSonda)) {
                res = cont;
                cont = sondas.size();
            }
            cont++;
        }
        return res;
    }*/
    //**************************************************************************
    
    //METODOS SONDA AUX CON TIPO SONDA******************************************
    public void addSondaAux() {
        if (controlSondaAux(actualSondaAux)) {
            actualSondaAux.setLeyendaTipoSonda(SondaTipo.getSondaTipoById(actualSondaAux.getTipoSonda()).getNombre());
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
    
    public Boolean controlSondaAux(SondaAux sond){
        Boolean res = true;
        if(sond.getDescripcion().equals("")||sond.getTipoSonda().equals("")){
            res = false;
        }
        return res;
    }
    //**************************************************************************
}
