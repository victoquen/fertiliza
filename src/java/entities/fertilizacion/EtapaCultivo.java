/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.lte;
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
public class EtapaCultivo implements Serializable{
    
    ObjectId id;
    ObjectId variedad;
    String nombre;
    Integer diasInicio;
    Integer diasFin;
    
    String leyendaCultivo;
    String leyendaVariedad;
    

    public EtapaCultivo() {
        this.nombre = "";
        this.leyendaCultivo = "";
        this.leyendaVariedad = "";
       
    }
    
    public EtapaCultivo(String nombre) {
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

    public Integer getDiasInicio() {
        return diasInicio;
    }

    public void setDiasInicio(Integer diasInicio) {
        this.diasInicio = diasInicio;
    }

    public Integer getDiasFin() {
        return diasFin;
    }

    public void setDiasFin(Integer diasFin) {
        this.diasFin = diasFin;
    }

    
    
    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("edad");

        
        
        Document obj = new Document("nombre", this.nombre.toUpperCase())
                .append("variedad", this.variedad)
                .append("diasInicio", this.diasInicio)
                .append("diasFin", this.diasFin);
        table.insertOne(obj);
        
        return (ObjectId) obj.get("_id");
    }

    public void update() {

        
        EtapaCultivo before = getById(this.id);

    
        MongoManager mongo = MongoManager.getInstance(); 
        mongo.db.getCollection("edad").updateOne(new Document("_id", this.id), new Document("$set", new Document("nombre", this.nombre.toUpperCase())
                .append("variedad", this.variedad)
                .append("diasInicio", this.diasInicio)
                .append("diasFin", this.diasFin)));

    }
    
    
    
    public static EtapaCultivo getByName(String name) {
        EtapaCultivo obj = new EtapaCultivo();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("edad").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString(); 
                obj.diasInicio = document.getInteger("diasInicio");
                obj.diasFin = document.getInteger("diasFin");
                obj.variedad = document.getObjectId("diasFin");
                obj.leyendaVariedad= Variedad.getVariedadById(obj.variedad).nombre;
                obj.leyendaCultivo = Variedad.getVariedadById(obj.variedad).leyendaCultivo;
            }

        });

        return obj;
    }
    

    public static EtapaCultivo getById(ObjectId id) {
        EtapaCultivo obj = new EtapaCultivo();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("edad").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();  
                obj.diasInicio = document.getInteger("diasInicio");
                obj.diasFin = document.getInteger("diasFin");
                obj.variedad = document.getObjectId("variedad");
                obj.leyendaVariedad= Variedad.getVariedadById(obj.variedad).nombre;
                obj.leyendaCultivo = Variedad.getVariedadById(obj.variedad).leyendaCultivo;
            }            

        });

        return obj;
    }
    
    public static EtapaCultivo getByDiasVariedad(Integer dias,ObjectId idvar) {
        EtapaCultivo obj = new EtapaCultivo();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("edad").find(and(gte("diasInicio",dias),lt("diasFin",dias),eq("variedad",idvar)));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();  
                obj.diasInicio = document.getInteger("diasInicio");
                obj.diasFin = document.getInteger("diasFin");
                obj.variedad = document.getObjectId("variedad");
                obj.leyendaVariedad= Variedad.getVariedadById(obj.variedad).nombre;
                obj.leyendaCultivo = Variedad.getVariedadById(obj.variedad).leyendaCultivo;
            }            

        });

        return obj;
    }
    
    public static List<EtapaCultivo> getAllByVariedad(ObjectId id) {
        List<EtapaCultivo> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("edad").find(new Document("variedad", id)).sort(new Document("_id", -1));       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                EtapaCultivo obj = new EtapaCultivo();
                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString(); 
                obj.diasInicio = document.getInteger("diasInicio");
                obj.diasFin = document.getInteger("diasFin");
                obj.variedad = document.getObjectId("variedad");
                obj.leyendaVariedad= Variedad.getVariedadById(obj.variedad).nombre;
                obj.leyendaCultivo = Variedad.getVariedadById(obj.variedad).leyendaCultivo;
                
                res.add(obj);
            }

        });

        return res;
    }

    public static List<EtapaCultivo> getAll() {
        List<EtapaCultivo> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("edad").find().sort(new Document("variedad", 1).append("diasInicio", 1));       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                EtapaCultivo obj = new EtapaCultivo();
                obj.id = (ObjectId) document.get("_id");               
                obj.nombre = document.get("nombre").toString();
                obj.diasInicio = document.getInteger("diasInicio");
                obj.diasFin = document.getInteger("diasFin");
                obj.variedad = document.getObjectId("variedad");
                obj.leyendaVariedad= Variedad.getVariedadById(obj.variedad).nombre;
                obj.leyendaCultivo = Variedad.getVariedadById(obj.variedad).leyendaCultivo;
                
                res.add(obj);
            }

        });

        return res;
    }
}
