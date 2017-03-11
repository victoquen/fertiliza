/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
public class HaciendaLoteCultivoAux implements Serializable {

    String idLotes;
    ObjectId cultivo;
    ObjectId variedad;
    ObjectId edad;
    BigDecimal hectareas;

    String darBaja; //0 activos, 1 desactivados

    String leyendaCultivo;
    String leyendaVariedad;
    String leyendaEdad;

    String codigoMayorEstacion;
    List<PeriodoMonitoreoAux> listaPeriodosMonitoreos;
    List<PeriodoMonitoreoAux> listaPeriodosMonitoreosPendiente;

    List<EstacionMonitoreo> listaEstacionMonitoreo;

    public HaciendaLoteCultivoAux() {
        this.idLotes = "";
        this.darBaja = "0";

        this.hectareas = new BigDecimal(0);

        this.codigoMayorEstacion = "";
        this.listaPeriodosMonitoreos = new ArrayList<>();
        this.listaPeriodosMonitoreosPendiente = new ArrayList<>();
        this.listaEstacionMonitoreo = new ArrayList<>();

    }

    public HaciendaLoteCultivoAux(String idLotes, String darBaja, String leyendaCultivo, String leyendaVariedad, String leyendaEdad) {
        this.idLotes = idLotes;
        this.darBaja = darBaja;
        this.leyendaCultivo = leyendaCultivo;
        this.leyendaVariedad = leyendaVariedad;
        this.leyendaEdad = leyendaEdad;
    }

    public String getCodigoMayorEstacion() {
        return codigoMayorEstacion;
    }

    public void setCodigoMayorEstacion(String codigoMayorEstacion) {
        this.codigoMayorEstacion = codigoMayorEstacion;
    }

    public List<PeriodoMonitoreoAux> getListaPeriodosMonitoreos() {
        return listaPeriodosMonitoreos;
    }

    public void setListaPeriodosMonitoreos(List<PeriodoMonitoreoAux> listaPeriodosMonitoreos) {
        this.listaPeriodosMonitoreos = listaPeriodosMonitoreos;
    }

    public List<PeriodoMonitoreoAux> getListaPeriodosMonitoreosPendiente() {
        return listaPeriodosMonitoreosPendiente;
    }

    public void setListaPeriodosMonitoreosPendiente(List<PeriodoMonitoreoAux> listaPeriodosMonitoreosPendiente) {
        this.listaPeriodosMonitoreosPendiente = listaPeriodosMonitoreosPendiente;
    }

    public List<EstacionMonitoreo> getListaEstacionMonitoreo() {
        return listaEstacionMonitoreo;
    }

    public void setListaEstacionMonitoreo(List<EstacionMonitoreo> listaEstacionMonitoreo) {
        this.listaEstacionMonitoreo = listaEstacionMonitoreo;
    }

    public BigDecimal getHectareas() {
        return hectareas;
    }

    public void setHectareas(BigDecimal hectareas) {
        this.hectareas = hectareas;
    }

    public String getDarBaja() {
        return darBaja;
    }

    public void setDarBaja(String darBaja) {
        this.darBaja = darBaja;
    }

    public String getIdLotes() {
        return idLotes;
    }

    public void setIdLotes(String idLotes) {
        this.idLotes = idLotes;
    }

    public ObjectId getCultivo() {
        return cultivo;
    }

    public void setCultivo(ObjectId cultivo) {
        this.cultivo = cultivo;
    }

    public ObjectId getVariedad() {
        return variedad;
    }

    public void setVariedad(ObjectId variedad) {
        this.variedad = variedad;
    }

    public ObjectId getEdad() {
        return edad;
    }

    public void setEdad(ObjectId edad) {
        this.edad = edad;
    }

    public String getLeyendaCultivo() {
        return leyendaCultivo;
    }

    public void setLeyendaCultivo(String leyendaCultivo) {
        this.leyendaCultivo = leyendaCultivo;
    }

    public String getLeyendaVariedad() {
        return leyendaVariedad;
    }

    public void setLeyendaVariedad(String leyendaVariedad) {
        this.leyendaVariedad = leyendaVariedad;
    }

    public String getLeyendaEdad() {
        return leyendaEdad;
    }

    public void setLeyendaEdad(String leyendaEdad) {
        this.leyendaEdad = leyendaEdad;
    }

    public void ListaPeriodosMonitoreosPendientes() {
        int sizeLista = listaPeriodosMonitoreos.size();
        for (int i = 0; i < sizeLista; i++) {
            if (listaPeriodosMonitoreos.get(i).getPendiente().equals("pendiente")) {
                listaPeriodosMonitoreosPendiente.add(listaPeriodosMonitoreos.get(i));
            }
        }
    }
}
