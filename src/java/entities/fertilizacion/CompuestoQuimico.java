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
public class CompuestoQuimico implements Serializable{
    
    ObjectId id;
    String simbolo;
    String nombre;
    BigDecimal valor;
    BigDecimal peso;

    public CompuestoQuimico() {
        this.simbolo = "";
        this.nombre = "";
        this.valor = new BigDecimal(0);
        this.peso = new BigDecimal(0);
    }
    
    public CompuestoQuimico(String simbolo, String nombre, BigDecimal valor, BigDecimal peso) {
        this.simbolo = simbolo;
        this.nombre = nombre;
        this.valor = valor;
        this.peso = peso;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }
    
    
    public void save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("compuesto");

        Document obj = new Document("simbolo", this.simbolo).append("nombre", this.nombre).append("valor", BDecimalToStr(this.valor)).append("peso", BDecimalToStr(this.peso));
        table.insertOne(obj);
    }

    public void update() {

        
        CompuestoQuimico before = getCompuestoQuimicoById(this.id);

    
        MongoManager mongo = MongoManager.getInstance(); 
        mongo.db.getCollection("compuesto").updateOne(new Document("_id", this.id), new Document("$set", new Document("simbolo", this.simbolo).append("nombre", this.nombre).append("valor", BDecimalToStr(this.valor)).append("peso", BDecimalToStr(this.peso))));

    }
    
    
    
    public static CompuestoQuimico getCompuestoQuimicoByName(String name) {
        CompuestoQuimico obj = new CompuestoQuimico();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("compuesto").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.simbolo = document.get("simbolo").toString();
                obj.nombre = document.get("nombre").toString();
                obj.valor =  obj.StrToBDecimal(document.get("valor").toString());
                obj.peso = obj.StrToBDecimal(document.get("peso").toString());
                
            }

        });

        return obj;
    }
    
    
    public static CompuestoQuimico getCompuestoQuimicoBySimbolo(String simb) {
        CompuestoQuimico obj = new CompuestoQuimico();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("compuesto").find(new Document("simbolo", simb));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.simbolo = document.get("simbolo").toString();
                obj.nombre = document.get("nombre").toString();
                obj.valor =  obj.StrToBDecimal(document.get("valor").toString());
                obj.peso = obj.StrToBDecimal(document.get("peso").toString());
                
            }

        });

        return obj;
    }

    public static CompuestoQuimico getCompuestoQuimicoById(ObjectId id) {
        CompuestoQuimico obj = new CompuestoQuimico();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("compuesto").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.simbolo = document.get("simbolo").toString();
                obj.nombre = document.get("nombre").toString();
                obj.valor =  obj.StrToBDecimal(document.get("valor").toString());
                obj.peso = obj.StrToBDecimal(document.get("peso").toString());
            }            

        });

        return obj;
    }

    public static List<CompuestoQuimico> getAllCompuestoQuimicos() {
        List<CompuestoQuimico> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("compuesto").find().sort(new Document("nombre", 1));       
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                CompuestoQuimico obj = new CompuestoQuimico();
                obj.id = (ObjectId) document.get("_id");
                obj.simbolo = document.get("simbolo").toString();
                obj.nombre = document.get("nombre").toString();
                obj.valor =  obj.StrToBDecimal(document.get("valor").toString());
                obj.peso = obj.StrToBDecimal(document.get("peso").toString());

                res.add(obj);
            }

        });

        return res;
    } 
    
    
    String  BDecimalToStr(BigDecimal arg){
        String res;
        res = arg.toString();        
        return res;
    }
    
    BigDecimal StrToBDecimal(String arg){
        BigDecimal res;
        res = new BigDecimal(arg);
        return res;
    }
    
}
