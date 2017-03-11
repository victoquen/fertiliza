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
public class EstacionMonitoreo implements Serializable{
    String codigo;
    String latitudestacion;
    String longitudestacion;
    List<SondaAux> listaSondas;
    List<SondaAux> sondasLeyenda;

    public EstacionMonitoreo() {
        this.codigo = "";
        
       listaSondas = new ArrayList<>();
       sondasLeyenda = new ArrayList<>();
       
    }

    public String getLatitudestacion() {
        return latitudestacion;
    }

    public void setLatitudestacion(String latitudestacion) {
        this.latitudestacion = latitudestacion;
    }

    public String getLongitudestacion() {
        return longitudestacion;
    }

    public void setLongitudestacion(String longitudestacion) {
        this.longitudestacion = longitudestacion;
    }

    public List<SondaAux> getListaSondas() {
        return listaSondas;
    }

    public void setListaSondas(List<SondaAux> listaSondas) {
        this.listaSondas = listaSondas;
    }

    public List<SondaAux> getSondasLeyenda() {
        return sondasLeyenda;
    }

    public void setSondasLeyenda(List<SondaAux> sondasLeyenda) {
        this.sondasLeyenda = sondasLeyenda;
    }        

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
 
}
