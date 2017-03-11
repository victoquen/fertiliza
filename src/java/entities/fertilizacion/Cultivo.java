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
public class Cultivo implements Serializable{
    
    ObjectId id;
    String nombre;

    public Cultivo() {
        this.nombre ="";
    }    

    public Cultivo(String nombre) {
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

        MongoCollection table = mongo.db.getCollection("cultivo");

        Document obj = new Document("nombre", this.nombre.toUpperCase());
        table.insertOne(obj);
        
        return (ObjectId) obj.get("_id");
    }

    public void update() {

        
        Cultivo before = getCultivoById(this.id);

    
        MongoManager mongo = MongoManager.getInstance(); 
        mongo.db.getCollection("cultivo").updateOne(new Document("_id", this.id), new Document("$set", new Document("nombre", this.nombre.toUpperCase())));

    }
    
    
    
    public static Cultivo getCultivoByName(String name) {
        Cultivo obj = new Cultivo();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("cultivo").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();                                
            }

        });

        return obj;
    }
    

    public static Cultivo getCultivoById(ObjectId id) {
        Cultivo obj = new Cultivo();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("cultivo").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();               
            }            

        });

        return obj;
    }

    
    
    
    public static List<Cultivo> getAllCultivos() {
        List<Cultivo> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("cultivo").find().sort(new Document("_id", -1));       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Cultivo obj = new Cultivo();
                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();             

                res.add(obj);
            }

        });

        return res;
    }
    
    
}
