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
public class SondaAux implements Serializable{
    ObjectId tipoSonda;
    String descripcion;
    ObjectId profundidad;
    String leyendaProfundidad;
    String leyendaTipoSonda;

    public SondaAux() {
        this.descripcion = "";
    }

    public String getLeyendaProfundidad() {
        return leyendaProfundidad;
    }

    public void setLeyendaProfundidad(String leyendaProfundidad) {
        this.leyendaProfundidad = leyendaProfundidad;
    }
    
    public ObjectId getTipoSonda() {
        return tipoSonda;
    }

    public void setTipoSonda(ObjectId tipoSonda) {
        this.tipoSonda = tipoSonda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLeyendaTipoSonda() {
        return leyendaTipoSonda;
    }

    public void setLeyendaTipoSonda(String leyendaTipoSonda) {
        this.leyendaTipoSonda = leyendaTipoSonda;
    }

    public ObjectId getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(ObjectId profundidad) {
        this.profundidad = profundidad;
    }
    
    
   
    
}
