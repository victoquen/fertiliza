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
public class Codigo implements  Serializable{
    
    ObjectId id;
    String nombre;
    String simbolo;
    String modificarSecuencial; //Si - No

    public Codigo() {
        this.nombre = "";
        this.simbolo = "";
        this.modificarSecuencial = "";
    }

    
    public Codigo(String nombre, String simbolo, String modificarSecuencial) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.modificarSecuencial = modificarSecuencial;
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

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getModificarSecuencial() {
        return modificarSecuencial;
    }

    public void setModificarSecuencial(String modificarSecuencial) {
        this.modificarSecuencial = modificarSecuencial;
    }
    
   public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("codigo");

        Document obj = new Document("nombre", this.nombre.toUpperCase()).append("simbolo", this.simbolo).append("modificarSecuencial", this.modificarSecuencial);
        
        table.insertOne(obj);
        
        return (ObjectId) obj.get("_id");
    }

    public void update() {

        
        Codigo before = getById(this.id);

    
        MongoManager mongo = MongoManager.getInstance(); 
        mongo.db.getCollection("codigo").updateOne(new Document("_id", this.id), new Document("$set", new Document("nombre", this.nombre.toUpperCase()).append("simbolo", this.simbolo).append("modificarSecuencial", this.modificarSecuencial)));

    }
    
    
    
    public static Codigo getByName(String name) {
        Codigo obj = new Codigo();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("codigo").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();
                obj.simbolo = document.get("simbolo").toString();
                obj.modificarSecuencial = document.get("modificarSecuencial").toString();
            }

        });

        return obj;
    }
    

    public static Codigo getById(ObjectId id) {
        Codigo obj = new Codigo();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("codigo").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();
                obj.simbolo = document.get("simbolo").toString();
                obj.modificarSecuencial = document.get("modificarSecuencial").toString();
            }            

        });

        return obj;
    }

    public static List<Codigo> getAll() {
        List<Codigo> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("codigo").find().sort(new Document("_id", -1));       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Codigo obj = new Codigo();
                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString(); 
                obj.simbolo = document.get("simbolo").toString();
                obj.modificarSecuencial = document.get("modificarSecuencial").toString();

                res.add(obj);
            }

        });

        return res;
    } 
}
