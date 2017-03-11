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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo
 */
public class Fuente implements Serializable {

    ObjectId id;
    String simbolo;
    String nombre;

    String tipo; //solido, liquido
    //String presentacion;// solido(granulado, polvo)- liquido(liquido) 
    //String propiedad;//soluble, insoluble

    //tipoAplicacionFertilizante
    String ffoliar;
    String fertirriego;
    String fedafico;

    String proveedor;

    public Fuente() {
        this.simbolo = "";
        this.nombre = "";

        this.tipo = "";
        //this.presentacion="";
        //this.propiedad="";
        this.ffoliar = "--";
        this.fertirriego = "--";
        this.fedafico = "--";
        this.proveedor = "--";
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

   
    public String getFertirriego() {
        return fertirriego;
    }

    public void setFertirriego(String fertirriego) {
        this.fertirriego = fertirriego;
    }

    public String getFfoliar() {
        return ffoliar;
    }

    public void setFfoliar(String ffoliar) {
        this.ffoliar = ffoliar;
    }

    public String getFedafico() {
        return fedafico;
    }

    public void setFedafico(String fedafico) {
        this.fedafico = fedafico;
    }

   

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("fuente");

        this.proveedor = this.proveedor.toUpperCase();

        Document obj = new Document("simbolo", this.simbolo).append("nombre", this.nombre.toUpperCase()).append("tipo", this.tipo)
                .append("ffoliar", this.ffoliar.toUpperCase()).append("fertirriego", this.fertirriego.toUpperCase())
                .append("fedafico", this.fedafico.toUpperCase()).append("proveedor", this.proveedor);
        table.insertOne(obj);
    }

    public void update() {
        
        Fuente before = getFuenteById(this.id);

        MongoManager mongo = MongoManager.getInstance();
        mongo.db.getCollection("fuente").updateOne(new Document("_id", this.id), new Document("$set", new Document("simbolo", this.simbolo)
                .append("nombre", this.nombre.toUpperCase()).append("tipo", this.tipo)
                .append("ffoliar", this.ffoliar.toUpperCase()).append("fertirriego", this.fertirriego.toUpperCase())
                .append("fedafico", this.fedafico.toUpperCase()).append("proveedor", this.proveedor.toUpperCase())));

    }

    public static Fuente getFuenteByName(String name) {
        Fuente obj = new Fuente();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("fuente").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.simbolo = document.get("simbolo").toString();
                obj.nombre = document.get("nombre").toString();
                obj.tipo = document.get("tipo").toString();
                obj.ffoliar = document.get("ffoliar").toString();
                obj.fertirriego = document.get("fertirriego").toString();
                obj.fedafico = document.get("fedafico").toString();
                obj.proveedor = document.get("proveedor").toString();

            }

        });

        return obj;
    }

    public static Fuente getFuenteBySimbolo(String simb) {
        Fuente obj = new Fuente();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("fuente").find(new Document("simbolo", simb));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.simbolo = document.get("simbolo").toString();
                obj.nombre = document.get("nombre").toString();
                obj.tipo = document.get("tipo").toString();
                obj.ffoliar = document.get("ffoliar").toString();
                obj.fertirriego = document.get("fertirriego").toString();
                obj.fedafico = document.get("fedafico").toString();
                obj.proveedor = document.get("proveedor").toString();
            }

        });

        return obj;
    }

    public static Fuente getFuenteById(ObjectId id) {
        Fuente obj = new Fuente();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("fuente").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.simbolo = document.get("simbolo").toString();
                obj.nombre = document.get("nombre").toString();
                obj.tipo = document.get("tipo").toString();
                obj.ffoliar = document.get("ffoliar").toString();
                obj.fertirriego = document.get("fertirriego").toString();
                obj.fedafico = document.get("fedafico").toString();
                obj.proveedor = document.get("proveedor").toString();
            }

        });

        return obj;
    }

    public static List<Fuente> getAllFuentes() {
        List<Fuente> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("fuente").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Fuente obj = new Fuente();
                obj.id = (ObjectId) document.get("_id");
                obj.simbolo = document.get("simbolo").toString();
                obj.nombre = document.get("nombre").toString();
                obj.tipo = document.get("tipo").toString();
                obj.ffoliar = document.get("ffoliar").toString();
                obj.fertirriego = document.get("fertirriego").toString();
                obj.fedafico = document.get("fedafico").toString();
                obj.proveedor = document.get("proveedor").toString();

                res.add(obj);
            }

        });

        return res;
    }

    public static List<String> getAllProveedores() {
        List<String> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("fuente").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Fuente obj = new Fuente();

                res.add(obj.proveedor = document.get("proveedor").toString());
            }

        });

        return res;
    }
    
    public static List<String> getAllFoliar() {
        List<String> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("fuente").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Fuente obj = new Fuente();

                res.add(obj.proveedor = document.get("ffoliar").toString());
            }

        });

        return res;
    }
    
    public static List<String> getAllFertirriego() {
        List<String> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("fuente").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Fuente obj = new Fuente();

                res.add(obj.proveedor = document.get("fertirriego").toString());
            }

        });

        return res;
    }
    
    public static List<String> getAllFedafico() {
        List<String> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("fuente").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Fuente obj = new Fuente();

                res.add(obj.proveedor = document.get("fedafico").toString());
            }

        });

        return res;
    }

}
