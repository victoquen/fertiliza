/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import db.MongoManager;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
public class InterpretacionLaboratorio implements Serializable {

    ObjectId id;
    ObjectId cultivo;
    ObjectId subanalisis;
    ObjectId variedad;
    ObjectId edad;
    ObjectId metodologia;

    String matriz; //hoja, suelo, agua de riego, solucion suelo
    BigDecimal inicioRangoInterpretacion;
    BigDecimal finRangoInterpretacion;

    String leyendaCultivo;
    String leyendaSubanalisis;
    String leyendaVariedad;
    String leyendaEdad;
    String leyendaMetodologiaNombre;
    String leyendaMetodologiaLiteratura;

    public InterpretacionLaboratorio() {
        matriz = "";
        inicioRangoInterpretacion = new BigDecimal(0);
        finRangoInterpretacion = new BigDecimal(0);
        leyendaCultivo = "";
        leyendaSubanalisis = "";
        leyendaEdad = "";
        leyendaVariedad = "";
        leyendaMetodologiaNombre = "";
        leyendaMetodologiaLiteratura = "";
    }

    public ObjectId getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(ObjectId metodologia) {
        this.metodologia = metodologia;
    }

    public String getLeyendaMetodologiaNombre() {
        return leyendaMetodologiaNombre;
    }

    public void setLeyendaMetodologiaNombre(String leyendaMetodologiaNombre) {
        this.leyendaMetodologiaNombre = leyendaMetodologiaNombre;
    }

    public String getLeyendaMetodologiaLiteratura() {
        return leyendaMetodologiaLiteratura;
    }

    public void setLeyendaMetodologiaLiteratura(String leyendaMetodologiaLiteratura) {
        this.leyendaMetodologiaLiteratura = leyendaMetodologiaLiteratura;
    }

    public ObjectId getVariedad() {
        return variedad;
    }

    public String getLeyendaVariedad() {
        return leyendaVariedad;
    }

    public void setLeyendaVariedad(String leyendaVariedad) {
        this.leyendaVariedad = leyendaVariedad;
    }

    public String getLeyendaEdad() {
        return leyendaEdad;
    }

    public void setLeyendaEdad(String leyendaEdad) {
        this.leyendaEdad = leyendaEdad;
    }

    public void setVariedad(ObjectId variedad) {
        this.variedad = variedad;
    }

    public ObjectId getEdad() {
        return edad;
    }

    public void setEdad(ObjectId edad) {
        this.edad = edad;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getCultivo() {
        return cultivo;
    }

    public void setCultivo(ObjectId cultivo) {
        this.cultivo = cultivo;
    }

    public ObjectId getSubanalisis() {
        return subanalisis;
    }

    public void setSubanalisis(ObjectId subanalisis) {
        this.subanalisis = subanalisis;
    }
    
    public String getMatriz() {
        return matriz;
    }

    public void setMatriz(String matriz) {
        this.matriz = matriz;
    }

    public BigDecimal getInicioRangoInterpretacion() {
        return inicioRangoInterpretacion;
    }

    public void setInicioRangoInterpretacion(BigDecimal inicioRangoInterpretacion) {
        this.inicioRangoInterpretacion = inicioRangoInterpretacion;
    }

    public BigDecimal getFinRangoInterpretacion() {
        return finRangoInterpretacion;
    }

    public void setFinRangoInterpretacion(BigDecimal finRangoInterpretacion) {
        this.finRangoInterpretacion = finRangoInterpretacion;
    }

    public String getLeyendaCultivo() {
        return leyendaCultivo;
    }

    public void setLeyendaCultivo(String leyendaCultivo) {
        this.leyendaCultivo = leyendaCultivo;
    }

    public String getLeyendaSubanalisis() {
        return leyendaSubanalisis;
    }

    public void setLeyendaSubanalisis(String leyendaSubanalisis) {
        this.leyendaSubanalisis = leyendaSubanalisis;
    }

    
    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("interpretacionlaboratorio");

        Document obj = new Document("cultivo", this.cultivo).append("variedad", this.variedad)
                .append("edad", this.edad).append("matriz", this.matriz.toUpperCase())
                .append("subanalisis", this.subanalisis).append("iniciorangointerpretacion", BDecimalToStr(this.inicioRangoInterpretacion))
                .append("finrangointerpretacion", BDecimalToStr(this.finRangoInterpretacion)).append("metodologia", this.metodologia);
        table.insertOne(obj);

        return (ObjectId) obj.get("_id");
    }

    public void update() {

        MongoManager mongo = MongoManager.getInstance();
        mongo.db.getCollection("interpretacionlaboratorio").updateOne(new Document("_id", this.id), new Document("$set", new Document("cultivo", this.cultivo)
                .append("variedad", this.variedad).append("edad", this.edad).append("matriz", this.matriz.toUpperCase()).append("subanalisis", this.subanalisis)
                .append("iniciorangointerpretacion", BDecimalToStr(this.inicioRangoInterpretacion)).append("finrangointerpretacion", BDecimalToStr(this.finRangoInterpretacion))
                .append("metodologia", this.metodologia)));

    }

    public static InterpretacionLaboratorio getInterpretacionLaboratorioById(ObjectId id) {
        InterpretacionLaboratorio obj = new InterpretacionLaboratorio();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("interpretacionlaboratorio").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.cultivo = (ObjectId) document.get("cultivo");
                obj.matriz = document.get("matriz").toString();
                obj.subanalisis = (ObjectId) document.get("subanalisis");
                obj.inicioRangoInterpretacion = obj.StrToBDecimal(document.get("iniciorangointerpretacion").toString());
                obj.finRangoInterpretacion = obj.StrToBDecimal(document.get("finrangointerpretacion").toString());
                obj.variedad = (ObjectId) document.get("variedad");
                obj.edad = (ObjectId) document.get("edad");
                obj.metodologia = (ObjectId) document.get("metodologia");
                
                obj.leyendaMetodologiaNombre = Metodologia.getMetodologiaById(obj.metodologia).nombre;
                obj.leyendaMetodologiaLiteratura = Metodologia.getMetodologiaById(obj.metodologia).literatura;
                obj.leyendaVariedad = Variedad.getVariedadById(obj.variedad).nombre;
                obj.leyendaEdad = EtapaCultivo.getById(obj.edad).nombre;
                obj.leyendaCultivo = Cultivo.getCultivoById(obj.cultivo).nombre;
                obj.leyendaSubanalisis = Subanalisis.getById(obj.subanalisis).simbolo + "(" + Subanalisis.getById(obj.subanalisis).nombre + ")";
            }

        });

        return obj;
    }

    public static List<InterpretacionLaboratorio> getAllInterpretacionLaboratorio() {
        List<InterpretacionLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("interpretacionlaboratorio").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                InterpretacionLaboratorio obj = new InterpretacionLaboratorio();
                obj.id = (ObjectId) document.get("_id");
                obj.cultivo = (ObjectId) document.get("cultivo");
                obj.matriz = document.get("matriz").toString();
                obj.subanalisis = (ObjectId) document.get("subanalisis");
                obj.inicioRangoInterpretacion = obj.StrToBDecimal(document.get("iniciorangointerpretacion").toString());
                obj.finRangoInterpretacion = obj.StrToBDecimal(document.get("finrangointerpretacion").toString());
                obj.variedad = (ObjectId) document.get("variedad");
                obj.edad = (ObjectId) document.get("edad");
                obj.metodologia = (ObjectId) document.get("metodologia");
                
                obj.leyendaMetodologiaNombre = Metodologia.getMetodologiaById(obj.metodologia).nombre;
                obj.leyendaMetodologiaLiteratura = Metodologia.getMetodologiaById(obj.metodologia).literatura;
                obj.leyendaVariedad = Variedad.getVariedadById(obj.variedad).nombre;
                obj.leyendaEdad = EtapaCultivo.getById(obj.edad).nombre;
                obj.leyendaCultivo = Cultivo.getCultivoById(obj.cultivo).nombre;
                obj.leyendaSubanalisis = Subanalisis.getById(obj.subanalisis).simbolo + "(" + Subanalisis.getById(obj.subanalisis).nombre + ")";
                res.add(obj);
            }

        });

        return res;
    }

    String BDecimalToStr(BigDecimal arg) {
        String res;
        res = arg.toString();
        return res;
    }

    BigDecimal StrToBDecimal(String arg) {
        BigDecimal res;
        res = new BigDecimal(arg);
        return res;
    }

}
