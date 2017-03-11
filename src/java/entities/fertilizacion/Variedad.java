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
public class Variedad implements Serializable{
    ObjectId id;
    ObjectId cultivo;
    String nombre;
    
    String leyendaCultivo;

    public Variedad() {
        this.nombre = "";
        this.leyendaCultivo = "";
    }

    public ObjectId getCultivo() {
        return cultivo;
    }

    public void setCultivo(ObjectId cultivo) {
        this.cultivo = cultivo;
    }

    public String getLeyendaCultivo() {
        return leyendaCultivo;
    }

    public void setLeyendaCultivo(String leyendaCultivo) {
        this.leyendaCultivo = leyendaCultivo;
    }

    public Variedad(String nombre) {
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
    
    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("variedad");

        Document obj = new Document("nombre", this.nombre.toUpperCase()).append("cultivo", this.cultivo);
        table.insertOne(obj);
        
        return (ObjectId) obj.get("_id");
    }

    public void update() {

        
        Variedad before = getVariedadById(this.id);

    
        MongoManager mongo = MongoManager.getInstance(); 
        mongo.db.getCollection("variedad").updateOne(new Document("_id", this.id), new Document("$set", new Document("nombre", this.nombre.toUpperCase()).append("cultivo", this.cultivo)));

    }
    
    
    
    public static Variedad getVariedadByName(String name) {
        Variedad obj = new Variedad();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("variedad").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();        
                obj.cultivo = document.getObjectId("cultivo");
                obj.leyendaCultivo = Cultivo.getCultivoById(obj.cultivo).getNombre();
            }

        });

        return obj;
    }
    

    public static Variedad getVariedadById(ObjectId id) {
        Variedad obj = new Variedad();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("variedad").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();    
                obj.cultivo = document.getObjectId("cultivo");
                obj.leyendaCultivo = Cultivo.getCultivoById(obj.cultivo).getNombre();
            }            

        });

        return obj;
    }        
    
    public static List<Variedad> getAllVariedadByCultivo(ObjectId id) {
        List<Variedad> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("variedad").find(new Document("cultivo", id)).sort(new Document("_id", -1));       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Variedad obj = new Variedad();
                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();   
                obj.cultivo = document.getObjectId("cultivo");
                obj.leyendaCultivo = Cultivo.getCultivoById(obj.cultivo).getNombre();

                res.add(obj);
            }

        });

        return res;
    }

    public static List<Variedad> getAllVariedad() {
        List<Variedad> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("variedad").find().sort(new Document("_id", -1));       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Variedad obj = new Variedad();
                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();   
                obj.cultivo = document.getObjectId("cultivo");
                obj.leyendaCultivo = Cultivo.getCultivoById(obj.cultivo).getNombre();

                res.add(obj);
            }

        });

        return res;
    }
     
}
