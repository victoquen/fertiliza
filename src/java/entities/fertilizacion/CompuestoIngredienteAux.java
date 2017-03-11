/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo
 */
public class CompuestoIngredienteAux implements Serializable{

    ObjectId idCompuesto;
    String simboloCompuesto;
    BigDecimal ingredienteActivo;
    
    
    //campos calculados
    
    BigDecimal porcentajePesoTotalIA;
    BigDecimal precioKgIA;
    BigDecimal kgIAconcentracionPC;
    BigDecimal unoKgIA;
    
    
    

    public CompuestoIngredienteAux() {
        ingredienteActivo = new BigDecimal(0);
    }

    public CompuestoIngredienteAux(ObjectId idCompuesto, String nombreCompuesto, BigDecimal ingredienteActivo) {
        this.idCompuesto = idCompuesto;
        this.simboloCompuesto = nombreCompuesto;
        this.ingredienteActivo = ingredienteActivo;
    }

    public String getSimboloCompuesto() {
        return simboloCompuesto;
    }

    public void setSimboloCompuesto(String simboloCompuesto) {
        this.simboloCompuesto = simboloCompuesto;
    }
        
    public ObjectId getIdCompuesto() {
        return idCompuesto;
    }

    public void setIdCompuesto(ObjectId idCompuesto) {
        this.idCompuesto = idCompuesto;
    }

    public BigDecimal getIngredienteActivo() {
        return ingredienteActivo;
    }

    public void setIngredienteActivo(BigDecimal ingredienteActivo) {
        this.ingredienteActivo = ingredienteActivo;
    }

    public BigDecimal getPorcentajePesoTotalIA() {
        return porcentajePesoTotalIA;
    }

    public void setPorcentajePesoTotalIA(BigDecimal porcentajePesoTotalIA) {
        this.porcentajePesoTotalIA = porcentajePesoTotalIA;
    }

    public BigDecimal getPrecioKgIA() {
        return precioKgIA;
    }

    public void setPrecioKgIA(BigDecimal precioKgIA) {
        this.precioKgIA = precioKgIA;
    }

    public BigDecimal getKgIAconcentracionPC() {
        return kgIAconcentracionPC;
    }

    public void setKgIAconcentracionPC(BigDecimal kgIAconcentracionPC) {
        this.kgIAconcentracionPC = kgIAconcentracionPC;
    }

    public BigDecimal getUnoKgIA() {
        return unoKgIA;
    }

    public void setUnoKgIA(BigDecimal unoKgIA) {
        this.unoKgIA = unoKgIA;
    }
    
    
    public void calculoCamposComplementarios(BigDecimal total, BigDecimal precio, BigDecimal presentacion){
        porcentajePesoTotalIA = ingredienteActivo.divide(total,5, RoundingMode.HALF_EVEN);        
        precioKgIA = (precio.multiply(porcentajePesoTotalIA)).divide(presentacion, 5, RoundingMode.HALF_EVEN);        
        kgIAconcentracionPC = presentacion.multiply(ingredienteActivo, MathContext.UNLIMITED);
        unoKgIA = precio.divide(kgIAconcentracionPC,5, RoundingMode.HALF_EVEN);
    }   
    
    
    
    
    
    
    
}
