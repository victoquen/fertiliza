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
public class AnalisisLaboratorioAux implements Serializable{
    
    ObjectId idAnalisis;
    ObjectId idLaboratorio;
    
    String leyendaAnalisis;
    String leyendaLaboratorio;

    public AnalisisLaboratorioAux() {
        this.leyendaAnalisis = "";
        this.leyendaLaboratorio = "";
    }

    public AnalisisLaboratorioAux(ObjectId idAnalisis, ObjectId idLaboratorio) {
        this.idAnalisis = idAnalisis;
        this.idLaboratorio = idLaboratorio;
        this.leyendaAnalisis = AnalisisLaboratorio.getAnalisisLaboratorioById(idAnalisis).descripcion;
        this.leyendaLaboratorio = Laboratorio.getLaboratorioById(idLaboratorio).nombre + " ("+Laboratorio.getLaboratorioById(idLaboratorio).pais +")";
        
    }

    public AnalisisLaboratorioAux(String leyendaAnalisis, String leyendaLaboratorio) {
        this.leyendaAnalisis = leyendaAnalisis;
        this.leyendaLaboratorio = leyendaLaboratorio;
    }

    public ObjectId getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(ObjectId idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public ObjectId getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(ObjectId idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getLeyendaAnalisis() {
        return leyendaAnalisis;
    }

    public void setLeyendaAnalisis(String leyendaAnalisis) {
        this.leyendaAnalisis = leyendaAnalisis;
    }

    public String getLeyendaLaboratorio() {
        return leyendaLaboratorio;
    }

    public void setLeyendaLaboratorio(String leyendaLaboratorio) {
        this.leyendaLaboratorio = leyendaLaboratorio;
    }
    
    
}
