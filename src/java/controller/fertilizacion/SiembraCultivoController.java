/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.Cliente;
import entities.fertilizacion.Cultivo;
import entities.fertilizacion.EstacionMonitoreo;
import entities.fertilizacion.EtapaCultivo;
import entities.fertilizacion.Hacienda;
import entities.fertilizacion.HaciendaLoteCultivoAux;
import entities.fertilizacion.Lote;
import entities.fertilizacion.LoteSiembraAux;
import entities.fertilizacion.PeriodoMonitoreoAux;
import entities.fertilizacion.Profundidad;
import entities.fertilizacion.SiembraCultivo;
import entities.fertilizacion.SondaAux;
import entities.fertilizacion.SondaTipo;
import entities.fertilizacion.Variedad;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.SiembraCultivoModel;
import org.bson.types.ObjectId;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class SiembraCultivoController implements Serializable {

    Date fechaSiembreAux;
    SiembraCultivo actual;
    SiembraCultivo selected;
    List<SiembraCultivo> listado;
    List<SiembraCultivo> filter;
    SiembraCultivoModel model;

    List<Cultivo> listadoCultivo;
    List<Variedad> listadoVariedad;
    List<EtapaCultivo> listadoEtapaCultivo;

    EtapaCultivo etapaCultivo;
    Variedad variedad;
    Cultivo cultivo;

    EstacionMonitoreo actualM;
    EstacionMonitoreo selectedM;
    List<EstacionMonitoreo> listadoM;

    String actualSonda;
    String selectedSonda;

    List<SondaTipo> listadoSondaTipo;
    String actualSondaTipo;
    String selectedSondaTipo;

    List<SondaAux> listadoSondasAux;
    SondaAux actualSondaAux;
    SondaAux selectedSondaAux;

    int numeroMonitoreos;
    List<PeriodoMonitoreoAux> listadoMonitoreos;
    List<Profundidad> listadoProfundidad;

    ObjectId idCliente;
    List<Cliente> listadoCliente;
    List<Hacienda> listadoHacienda;

    private DualListModel<Lote> listadoLotes;
    Lote actualLote;

    @PostConstruct
    public void init() {
        List<Lote> elementosSource = new ArrayList<Lote>();
        List<Lote> elementosTarget = new ArrayList<Lote>();

        listadoLotes = new DualListModel<Lote>(elementosSource, elementosTarget);
    }

    public SiembraCultivoController() {

        this.actual = new SiembraCultivo();
        this.listado = SiembraCultivo.getAll();
        this.model = new SiembraCultivoModel(listado);

        listadoCultivo = Cultivo.getAllCultivos();
        listadoVariedad = new ArrayList<>();
        listadoEtapaCultivo = new ArrayList<>();

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

        listadoCliente = Cliente.getAllClientes();
        listadoHacienda = new ArrayList<>();

    }

    public Date getFechaSiembreAux() {
        return fechaSiembreAux;
    }

    public void setFechaSiembreAux(Date fechaSiembreAux) {
        this.fechaSiembreAux = fechaSiembreAux;
    }

    public ObjectId getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(ObjectId idCliente) {
        this.idCliente = idCliente;
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

    public DualListModel<Lote> getListadoLotes() {
        return listadoLotes;
    }

    public void setListadoLotes(DualListModel<Lote> listadoLotes) {
        this.listadoLotes = listadoLotes;
    }

    public Lote getActualLote() {
        return actualLote;
    }

    public void setActualLote(Lote actualLote) {
        this.actualLote = actualLote;
    }

    public SiembraCultivo getActual() {
        return actual;
    }

    public void setActual(SiembraCultivo actual) {
        this.actual = actual;
    }

    public SiembraCultivo getSelected() {
        return selected;
    }

    public void setSelected(SiembraCultivo selected) {
        this.selected = selected;
    }

    public List<SiembraCultivo> getListado() {
        return listado;
    }

    public void setListado(List<SiembraCultivo> listado) {
        this.listado = listado;
    }

    public List<SiembraCultivo> getFilter() {
        return filter;
    }

    public void setFilter(List<SiembraCultivo> filter) {
        this.filter = filter;
    }

    public SiembraCultivoModel getModel() {
        return model;
    }

    public void setModel(SiembraCultivoModel model) {
        this.model = model;
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

    public List<EtapaCultivo> getListadoEtapaCultivo() {
        return listadoEtapaCultivo;
    }

    public void setListadoEtapaCultivo(List<EtapaCultivo> listadoEtapaCultivo) {
        this.listadoEtapaCultivo = listadoEtapaCultivo;
    }

    public EtapaCultivo getEtapaCultivo() {
        return etapaCultivo;
    }

    public void setEtapaCultivo(EtapaCultivo etapaCultivo) {
        this.etapaCultivo = etapaCultivo;
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

    public int getNumeroMonitoreos() {
        return numeroMonitoreos;
    }

    public void setNumeroMonitoreos(int numeroMonitoreos) {
        this.numeroMonitoreos = numeroMonitoreos;
    }

    public List<PeriodoMonitoreoAux> getListadoMonitoreos() {
        return listadoMonitoreos;
    }

    public void setListadoMonitoreos(List<PeriodoMonitoreoAux> listadoMonitoreos) {
        this.listadoMonitoreos = listadoMonitoreos;
    }

    public List<Profundidad> getListadoProfundidad() {
        return listadoProfundidad;
    }

    public void setListadoProfundidad(List<Profundidad> listadoProfundidad) {
        this.listadoProfundidad = listadoProfundidad;
    }

    public void save() {

        if (controlDatos(actual)) {

            List<LoteSiembraAux> listAux = new ArrayList<>();
            int n = this.listadoLotes.getTarget().size();
            for (int i = 0; i < n; i++) {
                LoteSiembraAux aux = new LoteSiembraAux();
                aux.setIdLote(this.listadoLotes.getTarget().get(i).getId());
                aux.setEstado("activo");
                listAux.add(aux);
            }

            actual.setListaLotesSiembra(listAux);
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
        actual = new SiembraCultivo();
        listado = SiembraCultivo.getAll();
        model = new SiembraCultivoModel(listado);
    }

    Boolean controlDatos(SiembraCultivo u) {
        Boolean res = true;

        if ((u.getCodigo().equals(""))) {
            res = false;
        }
        return res;
    }

    public void loadSelected() {
        listadoCultivo = Cultivo.getAllCultivos();
        listadoCliente = Cliente.getAllClientes();
        this.idCliente = Hacienda.getHaciendaById(this.actual.getIdHacienda()).getIdCliente();
        listadoHacienda = Hacienda.getAllHaciendaByClienteId(this.idCliente);
        listadoVariedad = Variedad.getAllVariedadByCultivo(this.actual.getIdCultivo());
        onVariedadChange();

        List<Lote> elementosSource = new ArrayList<Lote>();
        List<Lote> elementosTarget = new ArrayList<Lote>();
        List<Lote> auxLot = Lote.getAllLotesByHaciendaId(this.actual.getIdHacienda());
        int n = actual.getListaLotesSiembra().size();
        int i;
        boolean ban;
        for (Lote item : auxLot) {
            i=0;
            ban = true;
            while (i < n) {
                if (item.getId().equals(actual.getListaLotesSiembra().get(i).getIdLote())) {                   
                    ban = false;
                    i=n;
                }
                i++;
            }
            if(ban){
                elementosSource.add(item);
            }else{
                elementosTarget.add(item);
            }
        }
        listadoLotes = new DualListModel<Lote>(elementosSource, elementosTarget);

    }

    //*********************************************************************************************************************
    //METODOS control datos de lotes cultivo aux***************************************************************************
    public void onClienteChange() {
        if (this.idCliente != null) {
            listadoHacienda = Hacienda.getAllHaciendaByClienteId(this.idCliente);

            List<Lote> elementosSource = new ArrayList<Lote>();
            List<Lote> elementosTarget = new ArrayList<Lote>();
            listadoLotes = new DualListModel<Lote>(elementosSource, elementosTarget);
        } else {
            listadoHacienda = new ArrayList<>();
        }
        onHaciendaChange();
    }

    public void onHaciendaChange() {
        if (this.actual.getIdHacienda() != null) {
            List<Lote> elementosSource = Lote.getAllLotesByHaciendaId(this.actual.getIdHacienda());
            List<Lote> elementosTarget = new ArrayList<Lote>();
            listadoLotes = new DualListModel<Lote>(elementosSource, elementosTarget);
        } else {
            List<Lote> elementosSource = new ArrayList<Lote>();
            List<Lote> elementosTarget = new ArrayList<Lote>();
            listadoLotes = new DualListModel<Lote>(elementosSource, elementosTarget);
        }

    }

    //*********************************************************************************************************************
    //*********************************************************************************************************************
    //*********************************************************************************************************************
    //METODOS control datos de lotes cultivo aux***************************************************************************
    public void onCultivoChange() {
        if (this.actual.getIdCultivo() != null) {
            this.actual.setIdVariedad(null);
            listadoVariedad = Variedad.getAllVariedadByCultivo(this.actual.getIdCultivo());
            listadoEtapaCultivo = new ArrayList<>();
        } else {
            listadoVariedad = new ArrayList<>();
        }
        onVariedadChange();
    }

    public void onVariedadChange() {
        if ((this.actual.getIdVariedad() != null)) {

            final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
            java.util.Date hoy = new Date(); //Fecha de hoy            

            long diferencia = (hoy.getTime() - this.actual.getFechaSiembra().getTime()) / MILLSECS_PER_DAY;
            Integer dias = (int) (long) diferencia;
            //String leyend = EtapaCultivo.getByDiasVariedad(dias, this.actual.getIdVariedad()).getNombre();
            List<EtapaCultivo> lisAux = EtapaCultivo.getAllByVariedad(this.actual.getIdVariedad());
            String leyend = "";
            int i = 0;
            int tot = lisAux.size();
            while (i < tot) {
                EtapaCultivo ec = lisAux.get(i);
                if ((dias >= ec.getDiasInicio()) && (dias < ec.getDiasFin())) {
                    leyend = ec.getNombre();
                    i = tot;
                }
                i++;
            }
            this.actual.setLeyendaEtapaCultivo(leyend);

            //listadoEtapaCultivo = EtapaCultivo.getAllByVariedad(this.variedad.getId());
        } else {
            listadoEtapaCultivo = new ArrayList<>();
        }

    }

    public void onFechaSiembreChange(SelectEvent event) {
        Date dateaux = (Date) event.getObject();
        this.actual.setFechaSiembra(dateaux);
        if (this.actual.getFechaSiembra() != null) {

            onVariedadChange();
            // this.actual.setLeyendaEtapaCultivo(this.actual.getFechaSiembra().toString());
        } else {
            this.actual.setLeyendaEtapaCultivo("buuuu");
        }

    }

    //*********************************************************************************************************************
    //*********************************************************************************************************************
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
    }

    public Date sumarRestarDiasFecha(Date fecha, int dias) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos	

    }

    //**************************************************************************
    public void onTransfer(TransferEvent event) {

    }
}
