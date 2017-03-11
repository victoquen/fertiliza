/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

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
public class Canton implements Serializable{
    ObjectId id;
    ObjectId idProvincia;
    String nombre;
    
    String leyendaPais;
    String leyendaProvincia;

    public Canton() {
        nombre ="";
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

    public ObjectId getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(ObjectId idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getLeyendaPais() {
        return leyendaPais;
    }

    public void setLeyendaPais(String leyendaPais) {
        this.leyendaPais = leyendaPais;
    }

    public String getLeyendaProvincia() {
        return leyendaProvincia;
    }

    public void setLeyendaProvincia(String leyendaProvincia) {
        this.leyendaProvincia = leyendaProvincia;
    }
    
    
    
    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("canton");

        this.nombre = nombre.toUpperCase();
        
        Document obj = new Document("idprovincia", this.idProvincia).append("nombre", this.nombre);
        table.insertOne(obj);
        
        return (ObjectId) obj.get("_id");
    }

    public void update() {

        
        Canton before = getCantonById(this.id);

        this.nombre = nombre.toUpperCase();
        MongoManager mongo = MongoManager.getInstance(); 
        mongo.db.getCollection("canton").updateOne(new Document("_id", this.id), new Document("$set", new Document("idprovincia", this.idProvincia).append("nombre", this.nombre)));

    }
    
    
    
    public static Canton getCantonByName(String name) {
        Canton obj = new Canton();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("canton").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");         
                obj.idProvincia = (ObjectId) document.get("idprovincia");   
                obj.nombre = document.get("nombre").toString(); 
                
                obj.leyendaProvincia = Provincia.getProvinciaById(obj.idProvincia).nombre;
                obj.leyendaPais = Pais.getPaisById(Provincia.getProvinciaById(obj.idProvincia).idPais).nombre;
            }

        });

        return obj;
    }
    

    public static Canton getCantonById(ObjectId id) {
        Canton obj = new Canton();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("canton").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");  
                obj.idProvincia = (ObjectId) document.get("idprovincia");
                obj.nombre = document.get("nombre").toString();   
                
                obj.leyendaProvincia = Provincia.getProvinciaById(obj.idProvincia).nombre;
                obj.leyendaPais = Pais.getPaisById(Provincia.getProvinciaById(obj.idProvincia).idPais).nombre;
            }            

        });

        return obj;
    }

    public static List<Canton> getAllCanton() {
        List<Canton> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("canton").find();       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Canton obj = new Canton();
                obj.id = (ObjectId) document.get("_id"); 
                obj.idProvincia = (ObjectId) document.get("idprovincia");
                obj.nombre = document.get("nombre").toString();    
                
                obj.leyendaProvincia = Provincia.getProvinciaById(obj.idProvincia).nombre;
                obj.leyendaPais = Pais.getPaisById(Provincia.getProvinciaById(obj.idProvincia).idPais).nombre;

                res.add(obj);
            }

        });

        return res;
    }
    
    public static List<Canton> getAllCantonByProvincia(ObjectId id) {
        List<Canton> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("canton").find(new Document("idprovincia", id));       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Canton obj = new Canton();
                obj.id = (ObjectId) document.get("_id"); 
                obj.idProvincia = (ObjectId) document.get("idprovincia");
                obj.nombre = document.get("nombre").toString();    
                
                obj.leyendaProvincia = Provincia.getProvinciaById(obj.idProvincia).nombre;
                obj.leyendaPais = Pais.getPaisById(Provincia.getProvinciaById(obj.idProvincia).idPais).nombre;

                res.add(obj);
            }

        });

        return res;
    }
         
}
