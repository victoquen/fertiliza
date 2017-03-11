/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
public class Usuario implements Serializable {

    ObjectId idUsuario;

    String nombre;
    String password;
    Integer tipo;
    String email;

    //leyendas
    String leyendaTipo;  //0 superadministrador; 1 fertilizacion

    public Usuario() {
        this.nombre = "";
        this.password = "";
        this.tipo = -1;
        this.email = "";
        this.leyendaTipo = "";
    }

    public Usuario(String nombre, String password, Integer tipo, String email) {
        this.nombre = nombre;
        this.password = password;
        this.tipo = tipo;
        this.email = email;

    }

    public ObjectId getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(ObjectId idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLeyendaTipo() {
        return leyendaTipo;
    }

    public void setLeyendaTipo(String leyendaTipo) {
        this.leyendaTipo = leyendaTipo;
    }

    public void save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("usuario");

        Document obj = new Document("nombre", this.nombre).append("password", this.password).append("tipo", this.tipo).append("email", this.email);
        table.insertOne(obj);
    }

    public static Usuario getUsuarioByName(String name) {
        Usuario user = new Usuario();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("usuario").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                user.idUsuario = (ObjectId) document.get("_id");
                user.nombre = document.get("nombre").toString();
                user.password = document.get("password").toString();
                user.tipo = (Integer) document.get("tipo");
                user.email = document.get("email").toString();
                user.leyendaTipo = user.returnLeyendaTipo(user.tipo);
            }

        });

        return user;
    }

    public static Usuario getUsuarioById(ObjectId id) {
        Usuario user = new Usuario();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("usuario").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                user.idUsuario = (ObjectId) document.get("_id");
                user.nombre = document.get("nombre").toString();
                user.password = document.get("password").toString();
                user.tipo = (Integer) document.get("tipo");
                user.email = document.get("email").toString();
                user.leyendaTipo = user.returnLeyendaTipo(user.tipo);
            }

        });

        
        return user;
    }

    public static List<Usuario> getAllUsuarios() {
        List<Usuario> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("usuario").find();

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Usuario user = new Usuario();
                user.idUsuario = (ObjectId) document.get("_id");
                user.nombre = document.get("nombre").toString();
                user.password = document.get("password").toString();
                user.tipo = (Integer) document.get("tipo");
                user.email = document.get("email").toString();
                user.leyendaTipo = user.returnLeyendaTipo(user.tipo);

                res.add(user);
            }

        });

        return res;
    }

    public void update() {

        //obtener el usuario antes de modificar
        Usuario before = getUsuarioById(this.idUsuario);

        //actualizar usuario modificado
        MongoManager mongo = MongoManager.getInstance(); //instacion BD Mongo
        mongo.db.getCollection("usuario").updateOne(new Document("_id", this.idUsuario), new Document("$set", new Document("nombre", this.nombre).append("password", password).append("tipo", this.tipo).append("email", this.email)));

    }

    public String returnLeyendaTipo(Integer op) {
        String res = "";

        switch (op) {
            case 0:
                res = "SuperAdministrador";
                break;
            case 1:
                res = "AdminFertilizacion";
                break;

        }

        return res;
    }

}
