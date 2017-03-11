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
public class Pais implements Serializable {

    ObjectId id;
    String nombre;

    public Pais() {
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

    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("pais");

        this.nombre = nombre.toUpperCase();

        Document obj = new Document("nombre", this.nombre.toUpperCase());
        table.insertOne(obj);

        return (ObjectId) obj.get("_id");
    }

    public void update() {

        Pais before = getPaisById(this.id);

        this.nombre = nombre.toUpperCase();
        MongoManager mongo = MongoManager.getInstance();
        mongo.db.getCollection("pais").updateOne(new Document("_id", this.id), new Document("$set", new Document("nombre", this.nombre.toUpperCase())));

    }

    public static Pais getPaisByName(String name) {
        Pais obj = new Pais();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("pais").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.nombre = document.get("nombre").toString();
            }

        });

        return obj;
    }

    public static Pais getPaisById(ObjectId id) {
        Pais obj = new Pais();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("pais").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.nombre = document.get("nombre").toString();
            }

        });

        return obj;
    }

    public static List<Pais> getAllPais() {
        List<Pais> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("pais").find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Pais obj = new Pais();
                obj.id = (ObjectId) document.get("_id");
                obj.nombre = document.get("nombre").toString();

                res.add(obj);
            }

        });

        return res;
    }

}
