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
 * @author pablo
 */
public class Laboratorio implements Serializable{
    ObjectId id;
    String nombre;
    String pais;
    String proveedorAnalisis;

    public Laboratorio() {
        this.nombre = "";
        this.pais = "";
        this.proveedorAnalisis = "";
    }           

    public Laboratorio(String nombre, String pais, String proveedorAnalisis) {
        this.nombre = nombre;
        this.pais = pais;
        this.proveedorAnalisis = proveedorAnalisis;
    }
    

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProveedorAnalisis() {
        return proveedorAnalisis;
    }

    public void setProveedorAnalisis(String proveedorAnalisis) {
        this.proveedorAnalisis = proveedorAnalisis;
    }
    
    
    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("laboratorio");

        
        
        Document obj = new Document("nombre", this.nombre.toUpperCase()).append("pais", this.pais).append("proveedorAnalisis", this.proveedorAnalisis.toUpperCase());
        table.insertOne(obj);
        
        return (ObjectId) obj.get("_id");
    }

    public void update() {

        
        Laboratorio before = getLaboratorioById(this.id);

    
        MongoManager mongo = MongoManager.getInstance(); 
        mongo.db.getCollection("laboratorio").updateOne(new Document("_id", this.id), new Document("$set", new Document("nombre", this.nombre.toUpperCase())
                .append("pais", this.pais.toUpperCase()).append("proveedorAnalisis", this.proveedorAnalisis.toUpperCase())));

    }
    
    
    
    public static Laboratorio getLaboratorioByName(String name) {
        Laboratorio obj = new Laboratorio();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("laboratorio").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();
                obj.pais = document.get("pais").toString();
                obj.proveedorAnalisis = document.getString("proveedorAnalisis");
            }

        });

        return obj;
    }
    

    public static Laboratorio getLaboratorioById(ObjectId id) {
        Laboratorio obj = new Laboratorio();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("laboratorio").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();
                obj.pais = document.get("pais").toString();
                obj.proveedorAnalisis = document.getString("proveedorAnalisis");
            }            

        });

        return obj;
    }

    public static List<Laboratorio> getAllLaboratorio() {
        List<Laboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("laboratorio").find().sort(new Document("_id", -1));       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Laboratorio obj = new Laboratorio();
                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString(); 
                obj.pais = document.get("pais").toString();
                obj.proveedorAnalisis = document.getString("proveedorAnalisis");

                res.add(obj);
            }

        });

        return res;
    }
    
    
}
