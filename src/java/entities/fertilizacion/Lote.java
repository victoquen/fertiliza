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
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
public class Lote implements Serializable {

    ObjectId id;
    ObjectId idHacienda;
    
    String codigo;
    String hectareas;
    String tipoSuelo;
    String leyendaHacienda;
    String leyendaCliente;


    public Lote() {
        this.codigo = "";
        this.hectareas = "";
        this.tipoSuelo="";
        this.leyendaHacienda = "";
        this.leyendaCliente = "";
       
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getIdHacienda() {
        return idHacienda;
    }

    public void setIdHacienda(ObjectId idHacienda) {
        this.idHacienda = idHacienda;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getHectareas() {
        return hectareas;
    }

    public void setHectareas(String hectareas) {
        this.hectareas = hectareas;
    }

    public String getLeyendaHacienda() {
        return leyendaHacienda;
    }

    public void setLeyendaHacienda(String leyendaHacienda) {
        this.leyendaHacienda = leyendaHacienda;
    }

    public String getLeyendaCliente() {
        return leyendaCliente;
    }

    public void setLeyendaCliente(String leyendaCliente) {
        this.leyendaCliente = leyendaCliente;
    }

    public String getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(String tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }


    public void save() {              
       
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("lote");

        Document obj = new Document("idhacienda", this.idHacienda)                
                .append("codigo", this.codigo.toUpperCase())
                .append("hectareas", this.hectareas)
                .append("tiposuelo", this.tipoSuelo);
        
        table.insertOne(obj);
    }

    public void update() {

        MongoManager mongo = MongoManager.getInstance();
        Document obj = new Document("idhacienda", this.idHacienda)                
                .append("codigo", this.codigo.toUpperCase())
                .append("hectareas", this.hectareas)
                .append("tiposuelo", this.tipoSuelo);
        
        mongo.db.getCollection("lote").updateOne(new Document("_id", this.id), new Document("$set",obj));

    }

    public static Lote getLoteByCodigo(String cod) {
        Lote obj = new Lote();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("lote").find(new Document("codigo", cod));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.idHacienda = (ObjectId) document.get("idhacienda");               
                obj.codigo = document.get("codigo").toString();
                obj.hectareas = document.get("hectareas").toString();
                obj.tipoSuelo = document.get("tiposuelo").toString();
                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.idHacienda).nombre;
                obj.leyendaCliente = Cliente.getClienteById(Hacienda.getHaciendaById(obj.idHacienda).idCliente).nombre;
            }

        });

        return obj;
    }

    public static Lote getLoteById(ObjectId id) {
        Lote obj = new Lote();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("lote").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

               obj.id = (ObjectId) document.get("_id");
                obj.idHacienda = (ObjectId) document.get("idhacienda");               
                obj.codigo = document.get("codigo").toString();
                obj.hectareas = document.get("hectareas").toString();
                obj.tipoSuelo = document.get("tiposuelo").toString();
                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.idHacienda).nombre;
                obj.leyendaCliente = Cliente.getClienteById(Hacienda.getHaciendaById(obj.idHacienda).idCliente).nombre;
            }

        });

        return obj;
    }
    
    
    public static List<Lote> getAllLotesByHaciendaId(ObjectId id) {
        List<Lote> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("lote").find(new Document("idhacienda", id)).sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Lote obj = new Lote();

                obj.id = (ObjectId) document.get("_id");
                obj.idHacienda = (ObjectId) document.get("idhacienda");               
                obj.codigo = document.get("codigo").toString();
                obj.hectareas = document.get("hectareas").toString();
                obj.tipoSuelo = document.get("tiposuelo").toString();
                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.idHacienda).nombre;
                obj.leyendaCliente = Cliente.getClienteById(Hacienda.getHaciendaById(obj.idHacienda).idCliente).nombre;

                res.add(obj);
            }

        });

        return res;
    }

    public static List<Lote> getAllLotes() {
        List<Lote> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("lote").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Lote obj = new Lote();

                obj.id = (ObjectId) document.get("_id");
                obj.idHacienda = (ObjectId) document.get("idhacienda");               
                obj.codigo = document.get("codigo").toString();
                obj.hectareas = document.get("hectareas").toString();
                obj.tipoSuelo = document.get("tiposuelo").toString();
                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.idHacienda).nombre;
                obj.leyendaCliente = Cliente.getClienteById(Hacienda.getHaciendaById(obj.idHacienda).idCliente).nombre;
                

                res.add(obj);
            }

        });

        return res;
    }

}
