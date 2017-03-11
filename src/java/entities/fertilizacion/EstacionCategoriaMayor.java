/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VICTOR OQUENDO
 */
public class EstacionCategoriaMayor implements Serializable {

    String codigoMayor;
    List<PeriodoMonitoreoAux> listaPeriodosMonitoreos;
    List<PeriodoMonitoreoAux> listaPeriodosMonitoreosPendiente;

    List<EstacionMonitoreo> listaEstacionMonitoreo;

    public EstacionCategoriaMayor() {
        this.codigoMayor = "";
        this.listaPeriodosMonitoreos = new ArrayList<>();
        this.listaPeriodosMonitoreosPendiente = new ArrayList<>();
        this.listaEstacionMonitoreo = new ArrayList<>();
    }

    public List<EstacionMonitoreo> getListaEstacionMonitoreo() {
        return listaEstacionMonitoreo;
    }

    public void setListaEstacionMonitoreo(List<EstacionMonitoreo> listaEstacionMonitoreo) {
        this.listaEstacionMonitoreo = listaEstacionMonitoreo;
    }

    public String getCodigoMayor() {
        return codigoMayor;
    }

    public void setCodigoMayor(String codigoMayor) {
        this.codigoMayor = codigoMayor;
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

    public void ListaPeriodosMonitoreosPendientes() {
        int sizeLista = listaPeriodosMonitoreos.size();
        for (int i = 0; i < sizeLista; i++) {
            if (listaPeriodosMonitoreos.get(i).getPendiente().equals("pendiente")) {
                listaPeriodosMonitoreosPendiente.add(listaPeriodosMonitoreos.get(i));
            }
        }
    }
}
