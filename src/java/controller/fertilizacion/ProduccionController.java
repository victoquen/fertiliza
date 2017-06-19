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
import entities.fertilizacion.LoteSiembraAux;
import entities.fertilizacion.Produccion;
import entities.fertilizacion.ProduccionLote;
import entities.fertilizacion.SiembraCultivo;
import entities.fertilizacion.Variedad;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.ProduccionModel;
import org.apache.jasper.tagplugins.jstl.ForEach;
import org.bson.types.ObjectId;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class ProduccionController implements Serializable {

    Produccion actual;
    Produccion selected;
    List<Produccion> listado;
    List<Produccion> filter;
    ProduccionModel model;

    List<ProduccionLote> listadoProduccionLote;

    List<Cliente> listadoCliente;
    List<Hacienda> listadoHacienda;
    List<Cultivo> listadoCultivo;
    List<Variedad> listadoVariedad;
    List<Lote> listadoLote;

    Cliente actualCliente;
    Hacienda actualHacienda;
    Cultivo actualCultivo;
    Variedad actualVariedad;
    Lote actualLote;

    String leyendaCultivo;

    public ProduccionController() {
        actual = new Produccion();
        listado = Produccion.getAll();
        model = new ProduccionModel(listado);

        listadoProduccionLote = new ArrayList<>();

        actualCliente = new Cliente();
        actualHacienda = new Hacienda();
        actualCultivo = new Cultivo();
        actualVariedad = new Variedad();
        actualLote = new Lote();
        listadoCliente = Cliente.getAllClientes();

        leyendaCultivo = "";

    }

    public String getLeyendaCultivo() {
        return leyendaCultivo;
    }

    public void setLeyendaCultivo(String leyendaCultivo) {
        this.leyendaCultivo = leyendaCultivo;
    }

    public List<ProduccionLote> getListadoProduccionLote() {
        return listadoProduccionLote;
    }

    public void setListadoProduccionLote(List<ProduccionLote> listadoProduccionLote) {
        this.listadoProduccionLote = listadoProduccionLote;
    }

    public Produccion getActual() {
        return actual;
    }

    public void setActual(Produccion actual) {
        this.actual = actual;
    }

    public Produccion getSelected() {
        return selected;
    }

    public void setSelected(Produccion selected) {
        this.selected = selected;
    }

    public List<Produccion> getListado() {
        return listado;
    }

    public void setListado(List<Produccion> listado) {
        this.listado = listado;
    }

    public List<Produccion> getFilter() {
        return filter;
    }

    public void setFilter(List<Produccion> filter) {
        this.filter = filter;
    }

    public ProduccionModel getModel() {
        return model;
    }

    public void setModel(ProduccionModel model) {
        this.model = model;
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

    public List<Lote> getListadoLote() {
        return listadoLote;
    }

    public void setListadoLote(List<Lote> listadoLote) {
        this.listadoLote = listadoLote;
    }

    public Cliente getActualCliente() {
        return actualCliente;
    }

    public void setActualCliente(Cliente actualCliente) {
        this.actualCliente = actualCliente;
    }

    public Hacienda getActualHacienda() {
        return actualHacienda;
    }

    public void setActualHacienda(Hacienda actualHacienda) {
        this.actualHacienda = actualHacienda;
    }

    public Cultivo getActualCultivo() {
        return actualCultivo;
    }

    public void setActualCultivo(Cultivo actualCultivo) {
        this.actualCultivo = actualCultivo;
    }

    public Variedad getActualVariedad() {
        return actualVariedad;
    }

    public void setActualVariedad(Variedad actualVariedad) {
        this.actualVariedad = actualVariedad;
    }

    public Lote getActualLote() {
        return actualLote;
    }

    public void setActualLote(Lote actualLote) {
        this.actualLote = actualLote;
    }

    public void save() {

        if (controlDatos(actual)) {

            actual.setListadoProduccionLote(listadoProduccionLote);
            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(actual)) {
            actual.setListadoProduccionLote(listadoProduccionLote);
            actual.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Produccion();
        listado = Produccion.getAll();
        model = new ProduccionModel(listado);
        listadoProduccionLote = new ArrayList<>();

        actualCliente = new Cliente();
        actualHacienda = new Hacienda();
        actualCultivo = new Cultivo();
        actualVariedad = new Variedad();
        actualLote = new Lote();
        listadoCliente = Cliente.getAllClientes();
    }

    Boolean controlDatos(Produccion u) {
        Boolean res = true;

        if ((u.getAnio().equals(""))
                || (u.getCiclo().equals(""))
                || (u.getUnidadProduccion().equals(""))
                || (u.getFactorConversion().equals(new BigDecimal(0)))
                || (u.getIdCliente().equals(null))
                || (u.getIdHacienda().equals(null))
                || (u.getIdCultivo().equals(null))
                || (u.getIdVariedad().equals(null))) {
            res = false;
        }
        return res;
    }

    //*********************************************************************************************************************
    //METODOS control datos de lotes cultivo aux***************************************************************************
    public void onClienteChange() {
        if (this.actual.getIdCliente() != null) {
            this.actual.setIdHacienda(null);
            this.actual.setIdCultivo(null);
            this.actual.setIdVariedad(null);

            listadoHacienda = Hacienda.getAllHaciendaByClienteId(this.actual.getIdCliente());

            listadoProduccionLote = new ArrayList<>();
            listadoCultivo = new ArrayList<>();
            listadoVariedad = new ArrayList<>();

            this.actual.setTotalProduccion(new BigDecimal(0));
            this.actual.setTotalKgAnual(new BigDecimal(0));
            this.actual.setMermaNatural(new BigDecimal(0));
            this.actual.setMermaFitosanitaria(new BigDecimal(0));
            this.actual.setProyeccion(new BigDecimal(0));

        } else {
            listadoHacienda = new ArrayList<>();
            listadoCultivo = new ArrayList<>();
            listadoVariedad = new ArrayList<>();
            listadoProduccionLote = new ArrayList<>();
        }
        onHaciendaChange();
    }

    public void onHaciendaChange() {
        if (this.actual.getIdHacienda() != null) {

            this.actual.setIdCultivo(null);
            this.actual.setIdVariedad(null);

            List<SiembraCultivo> listadoSiembraCultivo = SiembraCultivo.getAllByHacienda(this.actual.getIdHacienda());

            listadoCultivo = new ArrayList<>();
            int n = listadoSiembraCultivo.size();
            for (int i = 0; i < n; i++) {
                SiembraCultivo objs = listadoSiembraCultivo.get(i);
                if (checkCultivo(objs.getIdCultivo(), listadoCultivo)) {
                    listadoCultivo.add(Cultivo.getCultivoById(objs.getIdCultivo()));
                }
            }

            listadoVariedad = new ArrayList<>();
            listadoProduccionLote = new ArrayList<>();

            this.actual.setTotalProduccion(new BigDecimal(0));
            this.actual.setTotalKgAnual(new BigDecimal(0));
            this.actual.setMermaNatural(new BigDecimal(0));
            this.actual.setMermaFitosanitaria(new BigDecimal(0));
            this.actual.setProyeccion(new BigDecimal(0));

        } else {

            listadoCultivo = new ArrayList<>();
            listadoVariedad = new ArrayList<>();
            listadoProduccionLote = new ArrayList<>();
        }
        onCultivoChange();

    }

    public void onCultivoChange() {
        if (this.actual.getIdCultivo() != null) {

            this.actual.setIdVariedad(null);

            List<SiembraCultivo> listadoSiembraCultivo = SiembraCultivo.getAllByHaciendaCultivo(this.actual.getIdHacienda(), this.actual.getIdCultivo());

            listadoVariedad = new ArrayList<>();
            int n = listadoSiembraCultivo.size();
            for (int i = 0; i < n; i++) {
                SiembraCultivo objs = listadoSiembraCultivo.get(i);
                if (checkVariedad(objs.getIdVariedad(), listadoVariedad)) {
                    listadoVariedad.add(Variedad.getVariedadById(objs.getIdVariedad()));
                }
            }

            listadoProduccionLote = new ArrayList<>();

            this.actual.setTotalProduccion(new BigDecimal(0));
            this.actual.setTotalKgAnual(new BigDecimal(0));
            this.actual.setMermaNatural(new BigDecimal(0));
            this.actual.setMermaFitosanitaria(new BigDecimal(0));
            this.actual.setProyeccion(new BigDecimal(0));

        } else {

            listadoProduccionLote = new ArrayList<>();
        }

        onVariedadChange();
    }

    public void onVariedadChange() {
        if (this.actual.getIdCultivo() != null) {

            List<SiembraCultivo> listadoSiembraCultivo = SiembraCultivo.getAllByHaciendaCultivoVariedad(this.actual.getIdHacienda(), this.actual.getIdCultivo(), this.actual.getIdVariedad());

            listadoProduccionLote = new ArrayList<>();
            int n = listadoSiembraCultivo.size();
            for (int i = 0; i < n; i++) {
                SiembraCultivo objs = listadoSiembraCultivo.get(i);
                List<LoteSiembraAux> auxLt = objs.getListaLotesSiembra();
                for (LoteSiembraAux item : auxLt) {
                    if (item.getEstado().equals("activo")) {
                        Lote lt = Lote.getLoteById(item.getIdLote());
                        ProduccionLote auxpl = new ProduccionLote();
                        auxpl.setIdLote(lt.getId());
                        auxpl.setLeyendaLote(lt.getCodigo());
                        auxpl.setLeyendaHectareas(lt.getHectareas());
                        this.actualCultivo = Cultivo.getCultivoById(this.actual.getIdCultivo());
                        listadoProduccionLote.add(auxpl);
                    }
                }
            }

            this.actual.setTotalProduccion(new BigDecimal(0));
            this.actual.setTotalKgAnual(new BigDecimal(0));
            this.actual.setMermaNatural(new BigDecimal(0));
            this.actual.setMermaFitosanitaria(new BigDecimal(0));
            this.actual.setProyeccion(new BigDecimal(0));
        } else {
            listadoProduccionLote = new ArrayList<>();
        }
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            Integer oldV = Integer.parseInt(oldValue.toString());
            Integer newV = Integer.parseInt(newValue.toString());
            Integer res = newV - oldV;
            this.actual.setTotalProduccion(this.actual.getTotalProduccion().add(new BigDecimal(res)));

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

        if (this.actual.getTotalProduccion().compareTo(BigDecimal.ZERO) != 0) {
            this.actual.setTotalKgAnual(this.actual.getTotalProduccion().multiply(this.actual.getFactorConversion()));
            this.actual.setTotalKgAnual(this.actual.getTotalKgAnual().setScale(2, BigDecimal.ROUND_HALF_UP));

            this.actual.setMermaNatural(this.actual.getTotalKgAnual().multiply(new BigDecimal(0.12)));
            this.actual.setMermaNatural(this.actual.getMermaNatural().setScale(2, BigDecimal.ROUND_HALF_UP));

            this.actual.setMermaFitosanitaria(this.actual.getTotalKgAnual().multiply(new BigDecimal(0.1)));
            this.actual.setMermaFitosanitaria(this.actual.getMermaFitosanitaria().setScale(2, BigDecimal.ROUND_HALF_UP));

            this.actual.setProyeccion(this.actual.getTotalKgAnual().multiply(new BigDecimal(0.02)));
            this.actual.setProyeccion(this.actual.getProyeccion().setScale(2, BigDecimal.ROUND_HALF_UP));
        }

    }

    //*********************************************************************************************************************
    //*********************************************************************************************************************
    public void loadSelectedCliHacCultVar() {

        this.listadoProduccionLote = this.actual.getListadoProduccionLote();
        listadoHacienda = Hacienda.getAllHaciendaByClienteId(this.actual.getIdCliente());

        List<SiembraCultivo> listadoSiembraCultivo = SiembraCultivo.getAllByHacienda(this.actual.getIdHacienda());

        listadoCultivo = new ArrayList<>();
        int n = listadoSiembraCultivo.size();
        for (int i = 0; i < n; i++) {
            SiembraCultivo objs = listadoSiembraCultivo.get(i);
            if (checkCultivo(objs.getIdCultivo(), listadoCultivo)) {
                listadoCultivo.add(Cultivo.getCultivoById(objs.getIdCultivo()));
            }
        }

        List<SiembraCultivo> listadoSiembraCultivo2 = SiembraCultivo.getAllByHaciendaCultivo(this.actual.getIdHacienda(), this.actual.getIdCultivo());

        listadoVariedad = new ArrayList<>();
        n = listadoSiembraCultivo2.size();
        for (int i = 0; i < n; i++) {
            SiembraCultivo objs = listadoSiembraCultivo2.get(i);
            if (checkVariedad(objs.getIdVariedad(), listadoVariedad)) {
                listadoVariedad.add(Variedad.getVariedadById(objs.getIdVariedad()));
            }
        }
        
       this.actualCultivo = Cultivo.getCultivoById(this.actual.getIdCultivo());

    }
}
