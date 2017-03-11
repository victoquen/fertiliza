/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.reportes;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import db.MongoManager;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
public class TextoReporteResultadoLaboratorio {

    ObjectId id;
    String referencia;
    String descripcion;

    public TextoReporteResultadoLaboratorio() {
        this.referencia = "";
        this.descripcion = "";

    }

    public TextoReporteResultadoLaboratorio(String referencia, String descripcion) {
        this.referencia = referencia;
        this.descripcion = descripcion;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("textoReporteResultadoLaboratorio");

        Document obj = new Document("referencia", this.referencia.toUpperCase()).append("descripcion", this.descripcion);
        table.insertOne(obj);

        return (ObjectId) obj.get("_id");
    }

    public void update() {

        MongoManager mongo = MongoManager.getInstance();
        mongo.db.getCollection("textoReporteResultadoLaboratorio").updateOne(new Document("_id", this.id), new Document("$set", new Document("referencia", this.referencia.toUpperCase())
                .append("descripcion", this.descripcion)));

    }

    public static TextoReporteResultadoLaboratorio getById(ObjectId id) {
        TextoReporteResultadoLaboratorio obj = new TextoReporteResultadoLaboratorio();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("textoReporteResultadoLaboratorio").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.referencia = document.get("referencia").toString();
                obj.descripcion = document.get("descripcion").toString();

            }

        });

        return obj;
    }
    
    public static TextoReporteResultadoLaboratorio getByReferencia(String ref) {
        TextoReporteResultadoLaboratorio obj = new TextoReporteResultadoLaboratorio();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("textoReporteResultadoLaboratorio").find(new Document("referencia", ref));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.referencia = document.get("referencia").toString();
                obj.descripcion = document.get("descripcion").toString();

            }

        });

        return obj;
    }

    public static List<TextoReporteResultadoLaboratorio> getAll() {
        List<TextoReporteResultadoLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("textoReporteResultadoLaboratorio").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                TextoReporteResultadoLaboratorio obj = new TextoReporteResultadoLaboratorio();
                obj.id = (ObjectId) document.get("_id");
                obj.referencia = document.get("referencia").toString();
                obj.descripcion = document.get("descripcion").toString();

                res.add(obj);
            }
        });

        return res;
    }

}
