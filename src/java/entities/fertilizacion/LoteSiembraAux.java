/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import java.io.Serializable;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
public class LoteSiembraAux implements Serializable{
    ObjectId idLote;
    String estado; //activo - inactivo
    
    String leyendaLote;

    public LoteSiembraAux() {
        String estado = "activo";
        String leyendaLote = "";
    }

    public ObjectId getIdLote() {
        return idLote;
    }

    public void setIdLote(ObjectId idLote) {
        this.idLote = idLote;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLeyendaLote() {
        return leyendaLote;
    }

    public void setLeyendaLote(String leyendaLote) {
        this.leyendaLote = leyendaLote;
    }
    
    
}
