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
public class Provincia implements Serializable {

    ObjectId id;
    ObjectId idPais;
    String nombre;
    
    String leyendaPais;

    public Provincia() {
        nombre = "";
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

    public ObjectId getIdPais() {
        return idPais;
    }

    public String getLeyendaPais() {
        return leyendaPais;
    }

    public void setLeyendaPais(String leyendaPais) {
        this.leyendaPais = leyendaPais;
    }    

    public void setIdPais(ObjectId idPais) {
        this.idPais = idPais;
    }

    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("provincia");

        this.nombre = nombre.toUpperCase();

        Document obj = new Document("idpais", idPais).append("nombre", nombre);
        table.insertOne(obj);

        return (ObjectId) obj.get("_id");
    }

    public void update() {

        Provincia before = getProvinciaById(this.id);

        this.nombre = nombre.toUpperCase();
        MongoManager mongo = MongoManager.getInstance();
        mongo.db.getCollection("provincia").updateOne(new Document("_id", this.id), new Document("$set", new Document("idpais", idPais).append("nombre", nombre)));

    }

    public static Provincia getProvinciaByName(String name) {
        Provincia obj = new Provincia();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("provincia").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.idPais = (ObjectId) document.get("idpais");
                obj.nombre = document.get("nombre").toString();
                                                             
                obj.leyendaPais = Pais.getPaisById(obj.idPais).nombre;
            }

        });

        return obj;
    }

    public static Provincia getProvinciaById(ObjectId id) {
        Provincia obj = new Provincia();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("provincia").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.idPais = (ObjectId) document.get("idpais");
                obj.nombre = document.get("nombre").toString();
                
                obj.leyendaPais = Pais.getPaisById(obj.idPais).nombre;
            }

        });

        return obj;
    }

    public static List<Provincia> getAllProvincia() {
        List<Provincia> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("provincia").find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Provincia obj = new Provincia();
                obj.id = (ObjectId) document.get("_id");
                obj.idPais = (ObjectId) document.get("idpais");
                obj.nombre = document.get("nombre").toString();
                
                obj.leyendaPais = Pais.getPaisById(obj.idPais).nombre;

                res.add(obj);
            }

        });

        return res;
    }
    
    public static List<Provincia> getAllProvinciaByPais(ObjectId id) {
        List<Provincia> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("provincia").find(new Document("idpais", id));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Provincia obj = new Provincia();
                obj.id = (ObjectId) document.get("_id");
                obj.idPais = (ObjectId) document.get("idpais");
                obj.nombre = document.get("nombre").toString();
                
                obj.leyendaPais = Pais.getPaisById(obj.idPais).nombre;

                res.add(obj);
            }

        });

        return res;
    }

}
