/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import java.io.Serializable;
import java.math.BigDecimal;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
public class ProduccionLote implements Serializable{
    
    ObjectId idLote;
    BigDecimal prodLote;
    
    String leyendaLote;
    String leyendaHectareas;

    public ProduccionLote() {
        prodLote = new BigDecimal(0);
        leyendaHectareas = "";
    }

    
    public ObjectId getIdLote() {
        return idLote;
    }

    public void setIdLote(ObjectId idLote) {
        this.idLote = idLote;
    }

    public BigDecimal getProdLote() {
        return prodLote;
    }

    public void setProdLote(BigDecimal prodLote) {
        this.prodLote = prodLote;
    }

    public String getLeyendaHectareas() {
        return leyendaHectareas;
    }

    public void setLeyendaHectareas(String leyendaHectareas) {
        this.leyendaHectareas = leyendaHectareas;
    }

    public String getLeyendaLote() {
        return leyendaLote;
    }

    public void setLeyendaLote(String leyendaLote) {
        this.leyendaLote = leyendaLote;
    }
    
    
    
}
