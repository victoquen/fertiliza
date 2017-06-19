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
public class HistoricoFertilizacionLote implements Serializable {

    ObjectId idLote;

    BigDecimal n;
    BigDecimal p;
    BigDecimal k;
    BigDecimal ca;
    BigDecimal mg;
    BigDecimal s;
    BigDecimal b;
    BigDecimal mn;
    BigDecimal zn;
    BigDecimal cu;

    BigDecimal prodLote;
    String leyendaLote;
    String leyendaHectareas;
    String leyendaCultivo;
    

    public HistoricoFertilizacionLote() {

        prodLote = new BigDecimal(0);
        n = new BigDecimal(0);
        p = new BigDecimal(0);
        k = new BigDecimal(0);
        ca = new BigDecimal(0);
        mg = new BigDecimal(0);
        s = new BigDecimal(0);
        b = new BigDecimal(0);
        mn = new BigDecimal(0);
        zn = new BigDecimal(0);
        cu = new BigDecimal(0);
    }

    public String getLeyendaCultivo() {
        return leyendaCultivo;
    }

    public void setLeyendaCultivo(String leyendaCultivo) {
        this.leyendaCultivo = leyendaCultivo;
    }

    public BigDecimal getProdLote() {
        return prodLote;
    }

    public void setProdLote(BigDecimal prodLote) {
        this.prodLote = prodLote;
    }

    public ObjectId getIdLote() {
        return idLote;
    }

    public void setIdLote(ObjectId idLote) {
        this.idLote = idLote;
    }

    public BigDecimal getN() {
        return n;
    }

    public void setN(BigDecimal n) {
        this.n = n;
    }

    public BigDecimal getP() {
        return p;
    }

    public void setP(BigDecimal p) {
        this.p = p;
    }

    public BigDecimal getK() {
        return k;
    }

    public void setK(BigDecimal k) {
        this.k = k;
    }

    public BigDecimal getCa() {
        return ca;
    }

    public void setCa(BigDecimal ca) {
        this.ca = ca;
    }

    public BigDecimal getMg() {
        return mg;
    }

    public void setMg(BigDecimal mg) {
        this.mg = mg;
    }

    public BigDecimal getS() {
        return s;
    }

    public void setS(BigDecimal s) {
        this.s = s;
    }

    public BigDecimal getB() {
        return b;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }

    public BigDecimal getMn() {
        return mn;
    }

    public void setMn(BigDecimal mn) {
        this.mn = mn;
    }

    public BigDecimal getZn() {
        return zn;
    }

    public void setZn(BigDecimal zn) {
        this.zn = zn;
    }

    public BigDecimal getCu() {
        return cu;
    }

    public void setCu(BigDecimal cu) {
        this.cu = cu;
    }

    public String getLeyendaLote() {
        return leyendaLote;
    }

    public void setLeyendaLote(String leyendaLote) {
        this.leyendaLote = leyendaLote;
    }

    public String getLeyendaHectareas() {
        return leyendaHectareas;
    }

    public void setLeyendaHectareas(String leyendaHectareas) {
        this.leyendaHectareas = leyendaHectareas;
    }

}
