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
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
public class Metodologia implements Serializable{
    ObjectId id;
    String codigo;
    String nombre;
    String literatura;

    public Metodologia() {
        this.codigo = "";
        this.nombre = "";
        this.literatura = "";
    }
    
    public Metodologia(String codigo, String nombre, String literatura) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.literatura = literatura;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLiteratura() {
        return literatura;
    }

    public void setLiteratura(String literatura) {
        this.literatura = literatura;
    }
    
    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("metodologia");

        Document obj = new Document("codigo", this.codigo).append("nombre", this.nombre.toUpperCase()).append("literatura", this.literatura.toUpperCase());
        table.insertOne(obj);
        
        return (ObjectId) obj.get("_id");
    }

    public void update() {

        
        Metodologia before = getMetodologiaById(this.id);

    
        MongoManager mongo = MongoManager.getInstance(); 
        mongo.db.getCollection("metodologia").updateOne(new Document("_id", this.id), new Document("$set", new Document("codigo", this.codigo)
                .append("nombre", this.nombre.toUpperCase()).append("literatura", this.literatura.toUpperCase())));

    }
    
    
    
    public static Metodologia getMetodologiaByName(String name) {
        Metodologia obj = new Metodologia();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("metodologia").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");   
                obj.codigo = document.get("codigo").toString();                    
                obj.nombre = document.get("nombre").toString();  
                obj.literatura = document.get("literatura").toString(); 
            }

        });

        return obj;
    }
    

    public static Metodologia getMetodologiaById(ObjectId id) {
        Metodologia obj = new Metodologia();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("metodologia").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.codigo = document.get("codigo").toString();                    
                obj.nombre = document.get("nombre").toString();  
                obj.literatura = document.get("literatura").toString();         
            }            

        });

        return obj;
    }

    public static List<Metodologia> getAllMetodologia() {
        List<Metodologia> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("metodologia").find().sort(new Document("_id", -1));       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Metodologia obj = new Metodologia();
                obj.id = (ObjectId) document.get("_id");               
                obj.codigo = document.get("codigo").toString();                    
                obj.nombre = document.get("nombre").toString();  
                obj.literatura = document.get("literatura").toString(); 

                res.add(obj);
            }

        });

        return res;
    }
}
