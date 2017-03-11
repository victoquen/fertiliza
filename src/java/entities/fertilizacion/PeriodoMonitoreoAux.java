/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author VICTOR OQUENDO
 */
public class PeriodoMonitoreoAux implements Serializable{
    int numeroMonitoreo;
    int numeroDias;
    Date fechaMonitoreo;
    String pendiente; //pendiente ,realizado
    String fechaMonitoreoFormat;

    public PeriodoMonitoreoAux() {
        this.numeroMonitoreo = 0;
        this.numeroDias =0;
        this.pendiente ="pendiente";
        fechaMonitoreoFormat = "";
    }

    public String getFechaMonitoreoFormat() {
        return fechaMonitoreoFormat;
    }

    public void setFechaMonitoreoFormat(String fechaMonitoreoFormat) {
        this.fechaMonitoreoFormat = fechaMonitoreoFormat;
    }

    public int getNumeroMonitoreo() {
        return numeroMonitoreo;
    }

    public void setNumeroMonitoreo(int numeroMonitoreo) {
        this.numeroMonitoreo = numeroMonitoreo;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public String getPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }

    public Date getFechaMonitoreo() {
        return fechaMonitoreo;
    }

    public void setFechaMonitoreo(Date fechaMonitoreo) {
        this.fechaMonitoreo = fechaMonitoreo;
    }

    
    
}
