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
public class Departamento implements Serializable{
    ObjectId id;
    String nombre;
    String abreviatura;

    public Departamento() {
        this.nombre = "";
        this.abreviatura = "";
    }

    public Departamento(String nombre) {
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

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    
    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("departamento");

        Document obj = new Document("nombre", this.nombre.toUpperCase()).append("abreviatura", this.abreviatura.toUpperCase());
        table.insertOne(obj);
        
        return (ObjectId) obj.get("_id");
    }

    public void update() {

        
        Departamento before = getById(this.id);

    
        MongoManager mongo = MongoManager.getInstance(); 
        mongo.db.getCollection("departamento").updateOne(new Document("_id", this.id), new Document("$set", new Document("nombre", this.nombre.toUpperCase())
                .append("abreviatura", this.abreviatura.toUpperCase())));

    }
    
    
    
    public static Departamento getByName(String name) {
        Departamento obj = new Departamento();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("departamento").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();  
                obj.abreviatura = document.getString("abreviatura");
            }

        });

        return obj;
    }
    

    public static Departamento getById(ObjectId id) {
        Departamento obj = new Departamento();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("departamento").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();  
                obj.abreviatura = document.getString("abreviatura");
            }            

        });

        return obj;
    }

    
    
    
    public static List<Departamento> getAll() {
        List<Departamento> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("departamento").find().sort(new Document("_id", -1));       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Departamento obj = new Departamento();
                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString(); 
                obj.abreviatura = document.getString("abreviatura");

                res.add(obj);
            }

        });

        return res;
    }
}
