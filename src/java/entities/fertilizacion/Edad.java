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
public class Edad implements Serializable{
    
    ObjectId id;
    ObjectId variedad;
    String nombre;
    
    String leyendaCultivo;
    String leyendaVariedad;

    public Edad() {
        this.nombre = "";
        this.leyendaCultivo = "";
    }
    
    public Edad(String nombre) {
        this.nombre = nombre;
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

    public ObjectId getVariedad() {
        return variedad;
    }

    public void setVariedad(ObjectId variedad) {
        this.variedad = variedad;
    }

    public String getLeyendaVariedad() {
        return leyendaVariedad;
    }

    public void setLeyendaVariedad(String leyendaVariedad) {
        this.leyendaVariedad = leyendaVariedad;
    }

    public String getLeyendaCultivo() {
        return leyendaCultivo;
    }

    public void setLeyendaCultivo(String leyendaCultivo) {
        this.leyendaCultivo = leyendaCultivo;
    }

    
    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("edad");

        
        
        Document obj = new Document("nombre", this.nombre.toUpperCase()).append("variedad", this.variedad);
        table.insertOne(obj);
        
        return (ObjectId) obj.get("_id");
    }

    public void update() {

        
        Edad before = getEdadById(this.id);

    
        MongoManager mongo = MongoManager.getInstance(); 
        mongo.db.getCollection("edad").updateOne(new Document("_id", this.id), new Document("$set", new Document("nombre", this.nombre.toUpperCase()).append("variedad", this.variedad)));

    }
    
    
    
    public static Edad getEdadByName(String name) {
        Edad obj = new Edad();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("edad").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();                   
                obj.variedad = document.getObjectId("variedad");
                obj.leyendaVariedad= Variedad.getVariedadById(obj.variedad).nombre;
                obj.leyendaCultivo = Variedad.getVariedadById(obj.variedad).leyendaCultivo;
            }

        });

        return obj;
    }
    

    public static Edad getEdadById(ObjectId id) {
        Edad obj = new Edad();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("edad").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();                    
                obj.variedad = document.getObjectId("variedad");
                obj.leyendaVariedad= Variedad.getVariedadById(obj.variedad).nombre;
                obj.leyendaCultivo = Variedad.getVariedadById(obj.variedad).leyendaCultivo;
            }            

        });

        return obj;
    }
    
    public static List<Edad> getAllEdadByVariedad(ObjectId id) {
        List<Edad> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("edad").find(new Document("variedad", id)).sort(new Document("_id", -1));       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Edad obj = new Edad();
                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();        
                obj.variedad = document.getObjectId("variedad");
                obj.leyendaVariedad= Variedad.getVariedadById(obj.variedad).nombre;
                obj.leyendaCultivo = Variedad.getVariedadById(obj.variedad).leyendaCultivo;
                
                res.add(obj);
            }

        });

        return res;
    }

    public static List<Edad> getAllEdad() {
        List<Edad> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("edad").find().sort(new Document("_id", -1));       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Edad obj = new Edad();
                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();        
                obj.variedad = document.getObjectId("variedad");
                obj.leyendaVariedad= Variedad.getVariedadById(obj.variedad).nombre;
                obj.leyendaCultivo = Variedad.getVariedadById(obj.variedad).leyendaCultivo;
                
                res.add(obj);
            }

        });

        return res;
    }
}
