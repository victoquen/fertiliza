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
public class ResultadoLaboratorioAnalisisCompuesto implements Serializable{
    ObjectId subanalisis;
    BigDecimal resultado;
    String dadoBaja; //Si, No
    String unidad; //% s.m.s, mg/Kg s.m.s
    
    String leyendaSubanalisis;

    public ResultadoLaboratorioAnalisisCompuesto() {
        resultado = new BigDecimal(0);
        leyendaSubanalisis = "";
        dadoBaja ="No";
        unidad = "";
    }

    public BigDecimal getResultado() {
        return resultado;
    }

    public void setResultado(BigDecimal resultado) {
        this.resultado = resultado;
    }

    public String getDadoBaja() {
        return dadoBaja;
    }

    public void setDadoBaja(String dadoBaja) {
        this.dadoBaja = dadoBaja;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public ObjectId getSubanalisis() {
        return subanalisis;
    }

    public void setSubanalisis(ObjectId subanalisis) {
        this.subanalisis = subanalisis;
    }

    public String getLeyendaSubanalisis() {
        return leyendaSubanalisis;
    }

    public void setLeyendaSubanalisis(String leyendaSubanalisis) {
        this.leyendaSubanalisis = leyendaSubanalisis;
    }
 
    
}
